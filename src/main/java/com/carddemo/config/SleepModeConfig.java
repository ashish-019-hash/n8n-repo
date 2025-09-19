package com.carddemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "carddemo.sleep-mode")
public class SleepModeConfig {
    
    private boolean enabled = true;
    private boolean keepApiActive = true;
    private boolean keepDatabaseActive = true;
    private long heartbeatIntervalMs = 30000;
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isKeepApiActive() {
        return keepApiActive;
    }
    
    public void setKeepApiActive(boolean keepApiActive) {
        this.keepApiActive = keepApiActive;
    }
    
    public boolean isKeepDatabaseActive() {
        return keepDatabaseActive;
    }
    
    public void setKeepDatabaseActive(boolean keepDatabaseActive) {
        this.keepDatabaseActive = keepDatabaseActive;
    }
    
    public long getHeartbeatIntervalMs() {
        return heartbeatIntervalMs;
    }
    
    public void setHeartbeatIntervalMs(long heartbeatIntervalMs) {
        this.heartbeatIntervalMs = heartbeatIntervalMs;
    }
}
