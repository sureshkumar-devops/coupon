package com.w3logics.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RestController
public class CouponApplication {

      
	public static void main(String[] args) {
		SpringApplication.run(CouponApplication.class, args);
	}
    
    @RequestMapping("/")
    public String index()
    {
        return "index.html";
    }
}
