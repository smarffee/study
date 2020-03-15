package com.lin.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class NonBlockingNIO2 {

    @Test
    public void send() throws IOException {
        String ip = "127.0.0.1";
        int port = 8080;

        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put((new Date().toString()).getBytes());
        byteBuffer.flip();
        datagramChannel.send(byteBuffer, new InetSocketAddress(ip, port));
        byteBuffer.clear();

        datagramChannel.close();
    }

    @Test
    public void receive() throws IOException {
        int port = 8080;

        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        datagramChannel.bind(new InetSocketAddress(port));

        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            for (Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext();) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    datagramChannel.receive(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                    byteBuffer.clear();
                }

                iterator.remove();
            }

        }
    }
}
