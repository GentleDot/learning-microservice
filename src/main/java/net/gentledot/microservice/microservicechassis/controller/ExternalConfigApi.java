package net.gentledot.microservice.microservicechassis.controller;

import net.gentledot.microservice.microservicechassis.model.response.PlainMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chassis")
public class ExternalConfigApi {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${microservice.title:Default Title}")
    private String title;

    @GetMapping(value = "/title", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlainMessage> demoTitle() {
        log.info("title :{}", title);
        PlainMessage message = new PlainMessage(title);
        return ResponseEntity.ok(message);
    }
}
