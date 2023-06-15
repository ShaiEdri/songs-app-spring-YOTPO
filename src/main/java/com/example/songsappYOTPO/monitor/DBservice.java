package com.example.songsappYOTPO.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DBservice implements HealthIndicator {
    private final String DATABASE_SERVICE = "Database service";
    @Override
    public Health health() {
        if(isDbGood()){
            return Health.up().withDetail(DATABASE_SERVICE, "Running").build();
        }
        return Health.down().withDetail(DATABASE_SERVICE, "Not running").build();
    }
    private boolean isDbGood(){
        //logic
        return false;
    }
}
