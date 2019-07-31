package com.example.domain;


import lombok.Data;

import java.util.Date;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-24
 */
@Data
public class EventProcess {
    String id;
    EventStatus status;
    String payload;
    Date createTime;
    String eventType;
}
