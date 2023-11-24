package com.crud.grpc.server;

import com.crud.grpc.services.UserServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class UserServer {

    @Bean
    public Server grpcServer(UserServiceImpl userServiceImpl) throws IOException {

        Server server = ServerBuilder.forPort(9090)
                .addService(userServiceImpl)
                .build();
        server.start();
        System.out.println("Server started at " + server.getPort());
        return server;
    }

//    public static final int MOVIE_CONTROLLER_SERVICE_PORT = 9090;
//    public static void main(String[] args)
//            throws IOException, InterruptedException {
//        Server server =
//                ServerBuilder.forPort(MOVIE_CONTROLLER_SERVICE_PORT)
//                        .addService(new UserServiceImpl())
//                        .build();
//        server.start();
//        System.out.println("Server started");
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            server.shutdown();
//            System.out.println("Successfully stopped the server");
//        }));
//        server.awaitTermination();
//    }
}
