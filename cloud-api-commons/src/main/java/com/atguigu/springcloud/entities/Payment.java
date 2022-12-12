package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author NianDQ改动点
 * @version 1.0
 * @date 2022/5/29
 **/
@Data

@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
