package by.artur.internship.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "MONGODB")
public interface EurekaFeignClient {

    @GetMapping("/api/action")
    String getAllActions();
}
