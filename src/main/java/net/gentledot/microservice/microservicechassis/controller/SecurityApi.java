package net.gentledot.microservice.microservicechassis.controller;

import net.gentledot.microservice.microservicechassis.model.response.PlainMessage;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/chassis/security")
public class SecurityApi {

    @GetMapping(value = "/basic_auth/one", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlainMessage> demoSecurityBasicAuthOne() {
        var response = new PlainMessage("You've passed basic authentication for first sample");

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/basic_auth/two", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlainMessage> demoSecurityBasicAuthTwo(
            @RequestBody PlainMessage requestBody) {
        var response = new PlainMessage(
                "You've passed basic authentication for second sample. Your message is : " + requestBody.getMessage());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/cache/time", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlainMessage> demoSecurityCustom() {
        var response = new PlainMessage("Time is " + LocalTime.now());

        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)).body(response);
    }

    @GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlainMessage> demoSecurityEmailOne() {
        var response = new PlainMessage(
                "If you can see this message, means you put valid email on header 'X-Developer-Email'.");

        return ResponseEntity.ok().body(response);
    }

}
