package by.artur.internship.service;

import by.artur.internship.dto.UserActionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static by.artur.internship.utils.StringUtil.MQ_QUEUE_NAME;

@Service
@RequiredArgsConstructor
public class MQService {

    private final AmqpTemplate rabbitTemplate;

    public void sendRabbitToMQ(String userId, String action) {
        UserActionDto dto = new UserActionDto();
        dto.setUserId(userId);
        dto.setAction(action);
        dto.setActionsDate(LocalDateTime.now());
        rabbitTemplate.convertAndSend(MQ_QUEUE_NAME, dto);
    }

}
