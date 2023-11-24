package com.crud.grpc.server;

import com.crud.grpc.services.UserServiceJdbc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class UserServer {


    @Bean
    public Server grpcServer(UserServiceJdbc userServiceJdbc) throws IOException {

        Server server = ServerBuilder.forPort(9090)
                .addService(userServiceJdbc)
                .build();
        server.start();
        System.out.println("Server started at " + server.getPort());
        return server;
    }
//    @Bean
//    public Server grpcServer(UserServiceImpl userServiceImpl) throws IOException {
//
//        Server server = ServerBuilder.forPort(9090)
//                .addService(userServiceImpl)
//                .build();
//        server.start();
//        System.out.println("Server started at " + server.getPort());
//        return server;
//    }


}
