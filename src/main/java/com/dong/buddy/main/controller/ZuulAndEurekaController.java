package com.dong.buddy.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

//@RestController
public class ZuulAndEurekaController
{

    // @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/api/zuul/test")
    public String say() throws Exception
    {
        return restTemplate.getForObject("http://EUREKA2/eureka/api/b", String.class);
    }
}
