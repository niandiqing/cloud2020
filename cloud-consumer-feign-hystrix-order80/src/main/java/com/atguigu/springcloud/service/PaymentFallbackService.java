package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author NianDQ
 * @version 1.0
 * @date 2022/6/12
 **/
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "-------paymentFallbackService fall back,-paymentInfo_ok(ㄒoㄒ)~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-------paymentFallbackService fall back,-paymentInfo_TimeOut(ㄒoㄒ)~~";
    }
}
