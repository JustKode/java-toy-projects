package justkode.springprometheusexample.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping("/endpoint1")
    public ResponseEntity<Map<String, String>> endPoint1() {
        return ResponseEntity.ok(Map.of("message", "엔드포인트 1번 입니다."));
    }

    @GetMapping("/endpoint2")
    public ResponseEntity<Map<String, String>> endPoint2() {
        return ResponseEntity.ok(Map.of("message", "엔드포인트 2번 입니다."));
    }
}
