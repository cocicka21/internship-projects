package by.artur.internship.service;

import by.artur.internship.dto.UserActionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActionService {

    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;
    private final EurekaFeignClient feignClient;
    private final ObjectMapper objectMapper;

    public UserActionDto getUserAction(String userId) {
        InstanceInfo service = eurekaClient
                .getApplication("MONGODB")
                .getInstances()
                .getFirst();

        return restTemplate.getForObject("http://" + service.getAppName() + ":" +
                service.getPort() + "/api/action/" + userId, UserActionDto.class);
    }

    public List<UserActionDto> getActions() throws JsonProcessingException {
        return objectMapper.readValue(feignClient.getAllActions(), new TypeReference<>() {
        });
    }
}
