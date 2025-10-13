const fs = require('fs');
const path = require('path');

const LOG_LEVELS = {
  ERROR: 'ERROR',
  WARN: 'WARN',
  INFO: 'INFO',
  DEBUG: 'DEBUG'
};

const logToFile = (level, message, meta = {}) => {
  const timestamp = new Date().toISOString();
  const logEntry = {
    timestamp,
    level,
    message,
    ...meta
  };

  const logDir = path.join(__dirname, '../../logs');
  if (!fs.existsSync(logDir)) {
    fs.mkdirSync(logDir, { recursive: true });
  }

  const logFile = path.join(logDir, `app-${new Date().toISOString().split('T')[0]}.log`);
  fs.appendFileSync(logFile, JSON.stringify(logEntry) + '\n');
};

const logger = {
  error: (message, meta) => {
    console.error(`[ERROR] ${message}`, meta || '');
    logToFile(LOG_LEVELS.ERROR, message, meta);
  },
  
  warn: (message, meta) => {
    console.warn(`[WARN] ${message}`, meta || '');
    logToFile(LOG_LEVELS.WARN, message, meta);
  },
  
  info: (message, meta) => {
    console.info(`[INFO] ${message}`, meta || '');
    logToFile(LOG_LEVELS.INFO, message, meta);
  },
  
  debug: (message, meta) => {
    if (process.env.NODE_ENV === 'development') {
      console.log(`[DEBUG] ${message}`, meta || '');
      logToFile(LOG_LEVELS.DEBUG, message, meta);
    }
  }
};

module.exports = logger;
