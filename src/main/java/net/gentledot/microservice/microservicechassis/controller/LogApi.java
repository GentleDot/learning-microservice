package net.gentledot.microservice.microservicechassis.controller;

import net.gentledot.microservice.microservicechassis.model.response.PlainMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("api/chassis")
public class LogApi {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlainMessage> demoLog() {
        log.debug("DemoLog() starts");

        long startTime = System.currentTimeMillis();

        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(10, 1000));
        } catch (InterruptedException e) {
            log.error("randomDelay 구현 error", e);
        }

        long processTime = System.currentTimeMillis() - startTime;

        log.debug("DemoLog() ends");

        return ResponseEntity.ok().body(new PlainMessage(processTime + " 밀리초가 걸려 메시지가 생성되었습니다."));

    }
}
