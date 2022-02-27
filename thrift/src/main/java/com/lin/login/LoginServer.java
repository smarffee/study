package com.lin.login;

import com.lin.login.service.LoginServiceImpl;
import com.lin.login.thrift.LoginService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import java.net.ServerSocket;

public class LoginServer {

    public static void main(String[] args) throws Exception {
        // Transport
        ServerSocket socket = new ServerSocket(8888);
        TServerSocket serverTransport = new TServerSocket(socket);

        // Processor
        LoginServiceImpl loginService = new LoginServiceImpl();
        LoginService.Processor processor = new LoginService.Processor(loginService);

        TServer.Args tServerArgs = new TServer.Args(serverTransport);
        tServerArgs.processor(processor);

        // Server
        TServer server = new TSimpleServer(tServerArgs);
        System.out.println("Starting the simple server...");
        server.serve();
    }

}
