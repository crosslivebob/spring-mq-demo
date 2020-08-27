package org.demo.mq.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Long number;
    private String name;
    private String major;
}
