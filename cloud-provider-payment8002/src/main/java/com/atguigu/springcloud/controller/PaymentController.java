package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.annotation.Retention;
import java.util.List;

/**
 * @author NianDQ
 * @version 1.0
 * @date 2022/5/29
 **/
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentServiceImpl paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        log.info("qingqiu："+payment);
        int result = paymentService.create(payment);
        log.info("数据插入结果："+result);

        if(result>0){
            return new CommonResult(200,"success,服务端口号："+serverPort,result);
        }else{
            return new CommonResult(444,"failure,服务端口号："+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("数据查询结果："+payment);
        if(payment != null){
            return new CommonResult(200,"success,服务端口号："+serverPort,payment);
        }else{
            return new CommonResult(444,"failure,服务端口号："+serverPort,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        //ctrl+alt+v 自动引入局部变量
        List<String> services = discoveryClient.getServices();
        //iter生成搞笑for循环,itar生成普通for循环，itco生成遍历集合for循环
        for (String element : services) {
            log.info("************element:"+element);
            List<ServiceInstance> instances = discoveryClient.getInstances(element);
            for (ServiceInstance instance : instances) {
                log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
            }
        }
        return discoveryClient;
    }

}
