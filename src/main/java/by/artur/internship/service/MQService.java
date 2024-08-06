package by.artur.internship.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static by.artur.internship.utils.StringUtil.MQ_EXCHANGE;
import static by.artur.internship.utils.StringUtil.MQ_KEY;

@Service
@RequiredArgsConstructor
public class MQService {

    private final RabbitTemplate rabbitTemplate;

    public void sendRabbitToMQ(String message) {
//    public void sendRabbitToMQ(String userId, String action) {
//        UserActionDto dto = new UserActionDto();
//        dto.setUserId(userId);
//        dto.setAction(action);
//        dto.setActionsDate(LocalDateTime.now());
        rabbitTemplate.convertAndSend(MQ_EXCHANGE, MQ_KEY, message);
    }

}
