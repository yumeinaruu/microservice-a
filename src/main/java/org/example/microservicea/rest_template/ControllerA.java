package org.example.microservicea.rest_template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ControllerA {
    public RestTemplate restTemplate;

    @Autowired
    public ControllerA(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/start/{id}")
    public User findUser(@PathVariable Integer id) {
        //путь
        //String url = "http://localhost:8081/user/" + id;
        String url = "http://localhost:8081/user";

        //параметры в боди
        MultiValueMap<String, Integer> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("user_id", id);

        //отправляет запрос
        return restTemplate.postForObject(url, paramMap, User.class); //синхронные запросы
    }
}
