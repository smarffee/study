package com.lin.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

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
public class NonBlockingNIO {

    //客户端
    @Test
    public void client() throws IOException {
        String ip = "127.0.0.1";
        int port = 8080;

        //1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));

        //2. 切换成非阻塞模式
        socketChannel.configureBlocking(false);

        //3. 分配指定大小缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //4. 发送数据给客户端
        byteBuffer.put(new Date().toString().getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        //5. 关闭通道
        socketChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        int port = 8080;
        //1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2. 切换非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //3. 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));

        //4. 获取选择器
        Selector selector = Selector.open();

        //5. 将通道注册到选择器上, 并且指定“监听事件”
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {
            //7. 获取当前选择器中所有注册的“选择键（就绪的监听时间）”
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext();) {
                //8. 获取“准备就绪”的事件
                SelectionKey selectionKey = iterator.next();
                //9. 判断具体是什么事件准备就绪
                if (selectionKey.isAcceptable()) {
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11. 切换非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12. 将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //14. 读数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = socketChannel.read(byteBuffer)) != -1) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, length));
                        byteBuffer.clear();
                    }
                }

                //15. 取消选择键
                iterator.remove();
            }
        }
    }

}
