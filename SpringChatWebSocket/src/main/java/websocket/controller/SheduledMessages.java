package websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import websocket.model.ChatMessage;

@Component
public class SheduledMessages {

    private static final Logger logger = LoggerFactory.getLogger(SheduledMessages.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        logger.info("Fixed delay task - {}", System.currentTimeMillis() / 1000);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        chatMessage.setSender("timer");
        chatMessage.setContent(String.valueOf(System.currentTimeMillis() / 1000));
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
