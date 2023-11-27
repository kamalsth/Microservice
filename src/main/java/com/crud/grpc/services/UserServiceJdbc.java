package com.crud.grpc.services;

import com.crud.grpc.UserOuterClass;
import com.crud.grpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Service
public class UserServiceJdbc extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private DataSource dataSource;

    @Override
    public void createUser(UserOuterClass.User request, StreamObserver<UserOuterClass.User> responseObserver) {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database");
            try (Statement statement = connection.createStatement()) {
                String sql = "INSERT INTO users (name,phone,address) VALUES ('" + request.getName() + "','" + request.getPhone() + "','" + request.getAddress() + "')";
                int rows = statement.executeUpdate(sql);
                System.out.println(rows + " row(s) affected");
            } catch (SQLException e) {
                System.out.println("Error executing the SQL query" + e.getMessage());
            }
            UserOuterClass.User response = buildUserResponse(request);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database" + e.getMessage());
        }
    }

    @Override
    public void getUser(UserOuterClass.GetUserRequest request, StreamObserver<UserOuterClass.User> responseObserver) {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database");
            try (Statement statement = connection.createStatement()) {
                String sql = "SELECT * FROM users WHERE user_id=" + request.getUserId();
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    UserOuterClass.User response = UserOuterClass.User.newBuilder()
                            .setUserId(resultSet.getLong("user_id"))
                            .setName(resultSet.getString("name"))
                            .setPhone(resultSet.getString("phone"))
                            .setAddress(resultSet.getString("address"))
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            } catch (SQLException e) {
                System.out.println("Error executing the SQL query" + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to the database" + e.getMessage());
        }
    }

    @Override
    public void updateUser(UserOuterClass.User request, StreamObserver<UserOuterClass.User> responseObserver) {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database");
            try (Statement statement = connection.createStatement()) {
                String sql = "UPDATE users SET name='" + request.getName() + "',phone='" + request.getPhone() + "',address='" + request.getAddress() + "' WHERE user_id=" + request.getUserId();
                int rows = statement.executeUpdate(sql);
                System.out.println(rows + " row(s) affected");
            } catch (SQLException e) {
                System.out.println("Error executing the SQL query" + e.getMessage());
            }
            UserOuterClass.User response = buildUserResponse(request);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database" + e.getMessage());

        }
    }

    @Override
    public void deleteUser(UserOuterClass.DeleteUserRequest request, StreamObserver<UserOuterClass.User> responseObserver) {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database");
            try (Statement statement = connection.createStatement()) {
                String sql = "DELETE FROM users WHERE user_id=" + request.getUserId();
                int rows = statement.executeUpdate(sql);
                System.out.println("User deleted !!" + rows + " row(s) affected");
            } catch (SQLException e) {
                System.out.println("Error executing the SQL query" + e.getMessage());
            }
            UserOuterClass.User response = UserOuterClass.User.newBuilder()
                    .setUserId(request.getUserId()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database" + e.getMessage());

        }
    }


    private UserOuterClass.User buildUserResponse(UserOuterClass.User request) {
        return UserOuterClass.User.newBuilder()
                .setUserId(request.getUserId())
                .setName(request.getName())
                .setPhone(request.getPhone())
                .setAddress(request.getAddress())
                .build();
    }
}
