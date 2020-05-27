package net.gentledot.microservice.microservicechassis.controller;

import net.gentledot.microservice.microservicechassis.exception.BadInputRequestException;
import net.gentledot.microservice.microservicechassis.model.response.PlainMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RestController
@RequestMapping("/api/chassis/exception")
public class ExceptionApi {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/global", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlainMessage> demoExceptionGlobal(
            @RequestParam(name = "param_one") String paramOne,
            @RequestParam(name = "param_two") String paramTwo,
            @RequestParam(name = "use_file", defaultValue = "false") boolean useFile)
            throws FileNotFoundException {
        // will throw NumberFormatException if string contains non-numeric
        int number = Integer.parseInt(paramOne);

        // max length is 5 chars
        if (paramTwo.length() > 5) {
            log.warn("Wrong input on param_two");
            throw new BadInputRequestException("param_two is too long");
        }

        // checked exception
        if (useFile) {
            var file = new FileReader("a-not-exists-file.txt");
            log.info("File is {}", file);
        }

        // build response (no exception thrown)
        var message = String.join(", ", "param_one (converted to number) : " + number, "param_two : " + paramTwo);
        var response = new PlainMessage(message);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/response", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlainMessage> demoExceptionResponse(@RequestParam(name = "param_one") String paramOne) {
        // 'microservice' 라는 접두사로 시작하지 않으면 exception이 발생
        if (!paramOne.startsWith("microservice")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param_one parameter는 'microservice'로 시작되어야 합니다.");
        }

        PlainMessage message = new PlainMessage("param_one : " + paramOne);

        return ResponseEntity.ok().body(message);
    }
}