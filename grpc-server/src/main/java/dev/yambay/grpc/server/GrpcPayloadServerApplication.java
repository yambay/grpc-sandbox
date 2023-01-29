package dev.yambay.grpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcPayloadServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcPayloadServerApplication.class, args);
    }
}
