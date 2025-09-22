package com.carddemo.service;

import com.carddemo.config.SleepModeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class SleepModeService {
    
    private static final Logger logger = LoggerFactory.getLogger(SleepModeService.class);
    
    @Autowired
    private SleepModeConfig sleepModeConfig;
    
    @Autowired
    private AccountService accountService;
    
    private boolean isInSleepMode = false;
    private LocalDateTime lastActivity = LocalDateTime.now();
    
    @PostConstruct
    public void init() {
        if (sleepModeConfig.isEnabled()) {
            logger.info("Sleep Mode Service initialized - keeping services active during sleep");
        }
    }
    
    @Scheduled(fixedDelayString = "#{sleepModeConfig.heartbeatIntervalMs}")
    public void heartbeat() {
        if (sleepModeConfig.isEnabled()) {
            logger.debug("Sleep Mode Heartbeat - Services active at {}", LocalDateTime.now());
            lastActivity = LocalDateTime.now();
            
            if (sleepModeConfig.isKeepDatabaseActive()) {
                keepDatabaseAlive();
            }
        }
    }
    
    @Async
    public void keepDatabaseAlive() {
        try {
            accountService.getAllAccounts();
            logger.debug("Database connection kept alive");
        } catch (Exception e) {
            logger.warn("Failed to keep database alive: {}", e.getMessage());
        }
    }
    
    public boolean isInSleepMode() {
        return isInSleepMode;
    }
    
    public LocalDateTime getLastActivity() {
        return lastActivity;
    }
    
    public void updateActivity() {
        lastActivity = LocalDateTime.now();
    }
}
