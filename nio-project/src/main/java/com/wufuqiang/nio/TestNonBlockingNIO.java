package com.wufuqiang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class TestNonBlockingNIO {

	public void client() throws IOException {

		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
		sChannel.configureBlocking(false);

		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put(new Date().toString().getBytes());
		buf.flip();
		sChannel.write(buf);
		buf.clear();

		sChannel.close();
	}

	public void server() throws IOException {
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.configureBlocking(false);
		ssChannel.bind(new InetSocketAddress(9898));

		Selector selector = Selector.open();
		//将通道注册到选择器上，并且指定"监听接收事件"
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (selector.select()>0){
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()){
				SelectionKey sk = it.next();
				if(sk.isAcceptable()){
					SocketChannel socketChannel = ssChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector,SelectionKey.OP_READ);
				}else if(sk.isReadable()){
					SocketChannel sChannel = (SocketChannel) sk.channel();
					ByteBuffer buf = ByteBuffer.allocate(1024);
					int len = 0;
					while((len=sChannel.read(buf))>0){
						buf.flip();
						System.out.println(new String(buf.array(),0,len));
						buf.clear();
					}
				}
				it.remove();
			}
		}
	}
}
