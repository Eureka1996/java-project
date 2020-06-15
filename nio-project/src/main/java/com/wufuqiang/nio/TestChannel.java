package com.wufuqiang.nio;

import com.sun.javafx.image.ByteToBytePixelConverter;
import org.junit.Test;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class TestChannel {

	//编码、解码
	@Test
	public void test5() throws CharacterCodingException {
		Charset gdk = Charset.forName("GBK");
		CharsetEncoder charsetEncoder = gdk.newEncoder();
		CharsetDecoder charsetDecoder = gdk.newDecoder();

		CharBuffer cBuf = CharBuffer.allocate(100);
		cBuf.put("吴福强");
		cBuf.flip();

		ByteBuffer encode = charsetEncoder.encode(cBuf);
		for(int i = 0 ; i<encode.limit();i++){
			System.out.println(encode.get());
		}
		encode.flip();
		CharBuffer decode = charsetDecoder.decode(encode);
		System.out.println(decode.toString());


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
