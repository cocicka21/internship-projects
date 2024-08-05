package by.artur.internship.service;

import by.artur.internship.dto.UserActionDto;
import by.artur.internship.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

        public void sendMessage(String userId, String action) {
        UserActionDto dto = new UserActionDto();
        dto.setUserId(userId);
        dto.setAction(action);
        dto.setActionsDate(LocalDateTime.now());
        kafkaTemplate.send(StringUtil.TOPIC_NAME, dto);
    }
}
