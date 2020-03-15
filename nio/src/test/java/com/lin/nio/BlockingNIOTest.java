package com.lin.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一 使用NIO完成网络通信的三个核心：
 * 1. 通道(Channel): 负责连接
 *     java.nio.channels.Channel 接口：
 *         |--SelectableChannel
 *             |--SocketChannel
 *             |--ServerSocketChannel
 *             |--DatagramChannel
 *
 *             |--Pipe.SinkChannel
 *             |--Pipe.SourceChannel
 *
 * 2. 缓冲区(Buffer): 负责数据的存储
 *
 * 3. 选择器(Selector): 是SelectableChannel 多路复用器。用于监听SelectableChannel 的 IO 状况
 */
public class BlockingNIOTest {

    //客户端
    @Test
    public void client() throws IOException {
        String ip = "127.0.0.1";
        int port = 8080;

        //1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));
        FileChannel fileChannel = FileChannel.open(Paths.get( "1.jpg"), StandardOpenOption.READ);
        //2. 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(2014);

        //3. 读取本地文件，并发送到服务器
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //4. 关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        int port = 8080;

        //1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(port));

        //3. 获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        //4. 分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //5. 接受到客户端数据并保存到本地
        FileChannel fileChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        //6. 关闭通道
        fileChannel.close();
        socketChannel.close();
        serverSocketChannel.close();
    }

}
