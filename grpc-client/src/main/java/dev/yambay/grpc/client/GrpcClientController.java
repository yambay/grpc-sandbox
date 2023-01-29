package dev.yambay.grpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcClientController {

    private final Logger LOG = LoggerFactory.getLogger(GrpcClientController.class);
    @Autowired
    private GrpcClientService grpcClientService;

    @RequestMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String printMessage(@RequestParam(defaultValue = "1") final int id) {
        LOG.info("Calling the gRPC service");
        String grpcResponse = this.grpcClientService.sendMessage(id);
        LOG.info("The response is {}", grpcResponse);
        return "Call the gRPC server with the ID " + id +
                "\nResponse: " + grpcResponse;
    }
}
