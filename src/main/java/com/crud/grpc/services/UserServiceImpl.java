//package com.crud.grpc.services;
//
//
//import com.crud.grpc.UserOuterClass;
//import com.crud.grpc.UserServiceGrpc;
//import com.crud.grpc.entities.User;
//import com.crud.grpc.repositories.UserRepository;
//import io.grpc.stub.StreamObserver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
////@GRpcService
//
//@Service
//public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
//    @Autowired
//    private UserRepository userRepository;
//
//
//
//
//    //write a code to create a user and store it in the database
//    @Override
//    public void createUser(UserOuterClass.User request, StreamObserver<UserOuterClass.User> responseObserver) {
//        User user = new User(request.getUserId(), request.getName(), request.getPhone(), request.getAddress());
//        userRepository.save(user);
//        UserOuterClass.User response = UserOuterClass.User.newBuilder()
//                .setUserId(user.getUserId())
//                .setName(user.getName())
//                .setPhone(user.getPhone())
//                .setAddress(user.getAddress())
//                .build();
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//
//    }
//
//    //write a code to read a user from the database
//    @Override
//    public void getUser(UserOuterClass.GetUserRequest request, StreamObserver<UserOuterClass.User> responseObserver) {
//        User user = userRepository.findById(request.getUserId()).get();
//        UserOuterClass.User response = UserOuterClass.User.newBuilder()
//                .setUserId(user.getUserId())
//                .setName(user.getName())
//                .setPhone(user.getPhone())
//                .setAddress(user.getAddress())
//                .build();
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
//
//    //write a code to update a user in the database
//    @Override
//    public void updateUser(UserOuterClass.User request, StreamObserver<UserOuterClass.User> responseObserver) {
//        User user = userRepository.findById(request.getUserId()).get();
//        user.setName(request.getName());
//        user.setPhone(request.getPhone());
//        user.setAddress(request.getAddress());
//        userRepository.save(user);
//        UserOuterClass.User response = UserOuterClass.User.newBuilder()
//                .setUserId(user.getUserId())
//                .setName(user.getName())
//                .setPhone(user.getPhone())
//                .setAddress(user.getAddress())
//                .build();
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
//
//    //write a code to delete a user from the database
//    @Override
//    public void deleteUser(UserOuterClass.DeleteUserRequest request, StreamObserver<UserOuterClass.User> responseObserver) {
//        userRepository.deleteById(request.getUserId());
//        UserOuterClass.User response = UserOuterClass.User.newBuilder()
//                .setUserId(request.getUserId())
////                .setResponse("User with id " + request.getUserId() + " deleted successfully")
//                .build();
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
//
//
//    //get all users
////    @Override
////    public void getAllUsers(UserOuterClass.User request, StreamObserver<UserOuterClass.User> responseObserver) {
////        Iterable<User> users = userRepository.findAll();
////        users.forEach(user -> {
////            UserOuterClass.User response = UserOuterClass.User.newBuilder()
////                    .setUserId(user.getUserId())
////                    .setName(user.getName())
////                    .setPhone(user.getPhone())
////                    .setAddress(user.getAddress())
////                    .build();
////            responseObserver.onNext(response);
////        });
////        responseObserver.onCompleted();
////    }
////
//
//}
