package by.artur.internship.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ActionController {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final EurekaClient eurekaClient;

    @GetMapping("/test/eureka/{userId}")
    public String getAction(@PathVariable("userId") String userId) {
        InstanceInfo service = eurekaClient
                .getApplication("MONGODB")
                .getInstances()
                .get(0);

        String serviceAResponse = restTemplate.getForObject("http://" + service.getAppName() + ":" + service.getPort() + "/api/action/" + userId, String.class);
        return serviceAResponse;
    }

    @GetMapping("/test/eureka/all")
    public String getActionAll() {
        log.info(discoveryClient.getInstances("MONGODB").getFirst().toString());
        InstanceInfo service = eurekaClient
                .getApplication("MONGODB")
                .getInstances()
                .get(0);

        String serviceAResponse = restTemplate.getForObject("http://" + service.getAppName() + ":" + service.getPort() + "/api/action", String.class);
        return serviceAResponse;
    }
}
