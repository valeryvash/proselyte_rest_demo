package myapp.rest;

import myapp.model.healthcheck.HealthChecker;
import myapp.model.healthcheck.HealthStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Represents current health status (instead of actuator)
 */
@RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeathCheckController {
    @GetMapping(value = "/health")
    public ResponseEntity<HealthChecker> getStatus() {
        final var healthChecker = new HealthChecker();
        healthChecker.setStatus(HealthStatus.UP);
        return ResponseEntity.ok().body(healthChecker);
    }
}
