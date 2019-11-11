package com.example.producercancelacompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
public class CancelaCompraController {

    private Source source;

    @Autowired
    public CancelaCompraController(Source source){
        this.source = source;
    }

    @PostMapping("/cancela-compra")
    @ResponseStatus(HttpStatus.CREATED)
    public void processa(@RequestBody CancelaCompraInput input){
        System.out.println(input);

        Message<CancelaCompraInput> message = MessageBuilder
                .withPayload(input)
                .setHeader(KafkaHeaders.MESSAGE_KEY, input.getUserId() + "#" + input.getProductId())
                .build();

        source.output().send(message);
    }
}
