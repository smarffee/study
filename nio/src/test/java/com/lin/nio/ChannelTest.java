package com.lin.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

/**
 * 一 通道(channel): 用于源节点与目标节点的连接。在Java NIO中负责缓冲区中的数据传输。Channel本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二 通道的主要实现类
 *    java.nio.channels.Channel接口
 *         |--FileChannel
 *         |--SocketChannel
 *         |--ServerSocketChannel
 *         |--DatagramChannel
 *  三 获取通道
 *  1.Java针对支持通道的类提供了getChannel()方法
 *    本地IO：
 *    FileInputStream/FileOutputStream
 *    RandomAccessFile
 *
 *    网络IO：
 *    Socket
 *    ServerSocket
 *    DatagramSocket
 *
 * 2. 在jdk1.7 中的NIO.2 针对各个通道提供了静态方法open()
 * 3. 在jdk1.7 中的NIO.2 的Files 工具类的newByteChannel()
 *
 * 四 通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五 分散(scatter) 与 聚集(gather)
 * 分散读取(Scattering Reads): 将通道中的数据分散到多个缓冲区中
 * 聚集写入(Gathering Writes): 将多个缓冲区中的数据聚集到通道中
 *
 * 六 字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组 -> 字符串
 *
 */
public class ChannelTest {

    @Test
    public void test6() throws CharacterCodingException {
        Charset gbk = Charset.forName("GBK");

        //获取编码器与解码器
        CharsetEncoder gbkEncoder = gbk.newEncoder();
        CharsetDecoder gbkDecoder = gbk.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("林保同");
        charBuffer.flip();

        //编码
        ByteBuffer byteBuffer = gbkEncoder.encode(charBuffer);
        for (int i = 0; i < 6; i++) {
            System.out.println(byteBuffer.get());
        }

        byteBuffer.flip();
        //gbk解码
        CharBuffer charBuffer1 = gbkDecoder.decode(byteBuffer);
        System.out.println(charBuffer1.toString());

        System.out.println("-----------------");

        Charset utf8 = Charset.forName("UTF-8");
        //utf-8解码
        byteBuffer.flip();
        CharBuffer charBuffer2 = utf8.decode(byteBuffer);
        System.out.println(charBuffer2.toString());
    }

    @Test
    public void test5() {
        Map<String, Charset> charsetMap = Charset.availableCharsets();
        for (Map.Entry<String, Charset> charsetEntry : charsetMap.entrySet()) {
            System.out.println(charsetEntry.getKey() + "=" + charsetEntry.getValue());
        }
    }

    @Test
    public void test4() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");

        //1. 获取通道
        FileChannel channel = raf1.getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);

        //3. 分散读取
        ByteBuffer[] bufArr = {buffer1, buffer2};
        channel.read(bufArr);

        for (ByteBuffer buffer : bufArr) {
            buffer.flip();
        }

        System.out.println(new String(bufArr[0].array(), 0, bufArr[0].limit()));
        System.out.println("------------------");
        System.out.println(new String(bufArr[1].array(), 0, bufArr[1].limit()));


        //4. 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufArr);
    }

    //通道之间的数据传输（直接缓冲区）
    @Test
    public void test3() throws IOException {
        URL resource = this.getClass().getResource("/");
        FileChannel inChannel = FileChannel.open(Paths.get(resource.getPath() + "1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(resource.getPath() + "3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);
//        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    //使用直接缓冲区完成文件的复制(内存映射文件)
    @Test
    public void test2() throws IOException {
        URL resource = this.getClass().getResource("/");
        FileChannel inChannel = FileChannel.open(Paths.get(resource.getPath() + "1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(resource.getPath() + "3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] buffer = new byte[inMappedBuf.limit()];

        inMappedBuf.get(buffer);
        outMappedBuf.put(buffer);

        inChannel.close();
        outChannel.close();
    }

    //1.利用通道完成文件的复制
    @Test
    public void test1() throws IOException {
        URL resource = this.getClass().getResource("/");
        FileInputStream fis = new FileInputStream(resource.getPath() + "1.jpg");
        FileOutputStream fos = new FileOutputStream(resource.getPath() + "2.jpg");
        //1.获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        //2.分配缓冲区大小
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //3.将通道中的数据存入缓冲区
        while (inChannel.read(buf) != -1) {
            //4.缓冲区切换到读模式
            buf.flip();
            //5.将缓冲区中的数据写入到通道中
            outChannel.write(buf);
            //6.清空缓冲区
            buf.clear();
        }

        inChannel.close();
        outChannel.close();
        fos.close();
        fis.close();
    }

    @Test
    public void test() {
        URL resource = this.getClass().getResource("");
        System.out.println(resource);

        URL resource1 = this.getClass().getResource("/");
        System.out.println(resource1);

        URL resource2 = this.getClass().getClassLoader().getResource("");
        System.out.println(resource2);

        URL resource3 = ClassLoader.getSystemResource("");
        System.out.println(resource3);

        URL resource4 = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println(resource4);
    }

}
