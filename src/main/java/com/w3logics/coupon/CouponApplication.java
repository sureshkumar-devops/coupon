package com.w3logics.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CouponApplication {

	   @GetMapping(value = "/index")
    public String index() {
        return "index";
     }   
	public static void main(String[] args) {
		SpringApplication.run(CouponApplication.class, args);
	}

}
