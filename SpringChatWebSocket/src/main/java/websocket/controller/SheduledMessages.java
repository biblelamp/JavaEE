package websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SheduledMessage {

    private static final Logger logger = LoggerFactory.getLogger(SheduledMessage.class);

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        logger.info("Fixed delay task - {}", System.currentTimeMillis() / 1000);
    }
}
