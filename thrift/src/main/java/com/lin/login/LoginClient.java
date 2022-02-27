package com.lin.login;

import com.lin.login.thrift.LoginService;
import com.lin.login.thrift.Request;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class LoginClient {

    public static void main(String[] args) throws Exception {
        TTransport transport = null;
        try {
            // 创建TTransport
            transport = new TSocket("127.0.0.1", 8888);

            // 创建TProtocol 协议要与服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);

            // 创建client
            LoginService.Client client = new LoginService.Client(protocol);

            transport.open();  // 建立连接

            Request request = new Request().setUsername("linbaotong").setPassword("123456");

            // client调用server端方法
            System.out.println(client.doLogin(request));

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            transport.close();  // 请求结束，断开连接

        }

    }

}
