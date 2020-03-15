package com.lin.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest {

    @Test
    public void test1() throws IOException {
        //1. 获取管道
        Pipe pipe = Pipe.open();
        //2. 将缓冲区的数据写入管道
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("通过单向管道发送数据".getBytes());
        byteBuffer.flip();

        Pipe.SinkChannel sinkChannel = pipe.sink();
        sinkChannel.write(byteBuffer);

        //3. 读取缓冲区中的数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        byteBuffer.flip();
        int length = sourceChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), 0, length));

        sourceChannel.close();
        sinkChannel.close();


    }
}
