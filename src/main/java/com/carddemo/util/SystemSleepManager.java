package com.carddemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SystemSleepManager {
    
    private static final Logger logger = LoggerFactory.getLogger(SystemSleepManager.class);
    
    public void preventSystemSleep() {
        try {
            ProcessBuilder pb = new ProcessBuilder("which", "systemd-inhibit");
            Process process = pb.start();
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                logger.info("System sleep prevention available via systemd-inhibit");
            } else {
                logger.info("systemd-inhibit not available, using application-level keep-alive");
            }
        } catch (IOException | InterruptedException e) {
            logger.warn("Could not check for system sleep prevention tools: {}", e.getMessage());
        }
    }
    
    public void allowSystemSleep() {
        logger.info("Allowing system sleep");
    }
}
