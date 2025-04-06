package com.w3logics.coupon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;



@Controller
public class promcodeController
{
    @GetMapping(value = "/index")
    public String index() {
        return "index";
     }    
     @GetMapping(value = "/coupon")
     public String coupon() {
         return "coupon";
      }    
}

