package com.wufuqiang.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class TestChannel {

	//buffer的操作，编码、解码
	@Test
	public void test5() throws CharacterCodingException {
		//设置字符集
		Charset gdk = Charset.forName("GBK");
		CharsetEncoder charsetEncoder = gdk.newEncoder();
		CharsetDecoder charsetDecoder = gdk.newDecoder();

		//往charBuffer中放入数据
		CharBuffer cBuf = CharBuffer.allocate(100);
		cBuf.put("吴福强");
		cBuf.flip();

		//将charBuffer编码成byteBuffer
		ByteBuffer bBuf = charsetEncoder.encode(cBuf);
		for(int i = 0 ; i<bBuf.limit();i++){
			System.out.println(bBuf.get());
		}
		bBuf.flip();
		CharBuffer decodeCBuf = charsetDecoder.decode(bBuf);
		System.out.println(decodeCBuf.toString());


	}

	// 分散和聚集
	@Test
	public void test4() throws IOException {
		RandomAccessFile raf1 = new RandomAccessFile("1.txt","r");
		FileChannel channel1 = raf1.getChannel();

		ByteBuffer buf1 = ByteBuffer.allocate(100);
		ByteBuffer buf2 = ByteBuffer.allocate(100);
		ByteBuffer[] bufArr = {buf1,buf2};

		channel1.read(bufArr);
		for(ByteBuffer buf: bufArr){
			//flip()：设置limit为position的值，然后position置为0。对Buffer进行读取操作前调用
			//rewind()：仅仅将positionl置0。一般是在重新读取buffer数据前调用，比如要读取同一个buffer的数据写入多个通道时会用到
			//clear()：回到初始状态，即limit等于capacity，position置0。重新对Buffer进行写入操作前调用
			//compact()：将未读取完的数据移动到缓冲区开关，交将position设置为这段数据末尾的下一个位置。其实就等价于重新向缓冲区写入这一段数据
			buf.flip();
		}
		System.out.println(new String(buf1.array(),0,buf1.limit()));
		System.out.println(new String(buf2.array(),0,buf2.limit()));

		RandomAccessFile raf2 = new RandomAccessFile("2.txt","rw");
		FileChannel channelOut = raf2.getChannel();
		channelOut.write(bufArr);

		raf1.close();
		raf2.close();
		channel1.close();
		channelOut.close();
	}
}
