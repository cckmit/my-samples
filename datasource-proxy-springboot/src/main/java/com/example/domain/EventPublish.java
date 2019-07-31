package com.example.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-24
 */
@Data
@Entity
public class EventPublish {
    @Id
    String id;
    EventPublishStatus status;
    String payload;
    Date createTime;
    String eventType;

    public enum EventPublishStatus{
        NEW,PUBLISHED
    }
    public static EventPublish NEW(){

        return new EventPublish();
    }

    public String getId() {
        return id==null? UUID.randomUUID().toString():id;
    }

    public EventPublishStatus getStatus() {
        return status==null?EventPublishStatus.NEW:status;
    }

    public Date getCreateTime() {
        return createTime==null?new Date():createTime;
    }

    public EventPublish setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }
}


