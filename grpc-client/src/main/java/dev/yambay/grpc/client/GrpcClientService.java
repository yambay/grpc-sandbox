package dev.yambay.grpc.client;

import dev.yambay.grpc.api.PayloadRequest;
import dev.yambay.grpc.api.PayloadResponse;
import dev.yambay.grpc.api.PayloadServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GrpcClientService {

    private final Logger LOG = LoggerFactory.getLogger(GrpcClientService.class);
    @GrpcClient("local-grpc-server")
    private PayloadServiceGrpc.PayloadServiceBlockingStub blockingStub;

    public String sendMessage(final int id) {
        try {
            UUID uuid = UUID.randomUUID();
            LOG.info("Processing request with id={}. Random UUID is {}", id, uuid);
            PayloadRequest request = PayloadRequest.newBuilder().setUuid(uuid.toString()).build();
            PayloadResponse response = blockingStub.getPayload(request);
            return "Timestamp for UUID " + response.getUuid() + " is " + response.getTimestamp();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}
