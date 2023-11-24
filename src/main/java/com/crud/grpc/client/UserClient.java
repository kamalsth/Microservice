package com.crud.grpc.client;


import com.crud.grpc.UserOuterClass;
import com.crud.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;



public class UserClient {

    public static void main(String[] args) {
        // Create a gRPC channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()// For simplicity, use plaintext (non-encrypted) communication
                .build();

        // Create a blocking stub
        UserServiceGrpc.UserServiceBlockingStub blockingStub = UserServiceGrpc.newBlockingStub(channel);

        // Example: Make a blocking call to createUser
        UserOuterClass.User createUserRequest = UserOuterClass.User.newBuilder()
                .setName("Kamal")
                .setPhone("123456789")
                .setAddress("ktm")
                .build();

        UserOuterClass.User createdUser = blockingStub.createUser(createUserRequest);
        System.out.println("User created: " + createdUser);

        // Clean up and close the channel
        channel.shutdown();
    }




}
