package com.carddemo.controller;

import com.carddemo.service.SleepModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sleep-mode")
public class SleepModeController {
    
    @Autowired
    private SleepModeService sleepModeService;
    
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getSleepModeStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("isInSleepMode", sleepModeService.isInSleepMode());
        status.put("lastActivity", sleepModeService.getLastActivity());
        status.put("currentTime", LocalDateTime.now());
        status.put("servicesActive", true);
        
        sleepModeService.updateActivity();
        
        return ResponseEntity.ok(status);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        sleepModeService.updateActivity();
        return ResponseEntity.ok("Sleep Mode Service is active");
    }
}
