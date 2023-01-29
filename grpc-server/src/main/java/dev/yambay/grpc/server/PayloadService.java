package dev.yambay.grpc.server;

import com.google.protobuf.Timestamp;
import dev.yambay.grpc.api.PayloadRequest;
import dev.yambay.grpc.api.PayloadResponse;
import dev.yambay.grpc.api.PayloadServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@GrpcService
public class PayloadService extends PayloadServiceGrpc.PayloadServiceImplBase {
    private final Logger LOG = LoggerFactory.getLogger(PayloadService.class);

    @Override
    public void getPayload(PayloadRequest request, StreamObserver<PayloadResponse> responseObserver) {
        LOG.info("Processing request for the UUID {}", request.getUuid());
        Instant nowInstant = Instant.now();
        PayloadResponse response = PayloadResponse.newBuilder()
                .setUuid(request.getUuid())
                .setTimestamp(Timestamp.newBuilder()
                        .setSeconds(nowInstant.getEpochSecond())
                        .setNanos(nowInstant.getNano())
                        .build())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        LOG.info("Response sent: {}", response);
    }
}
