package com.example;

import lombok.Data;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-17
 */
public interface Event {
     String getEventType();
}

@Data
class CustomerCreatedEvent implements Event{
     public static final String EVENT_TYPE = "customerCreated";
     String eventType;
    /* @Override
     public String getEventType() {
          return EVENT_TYPE;
     }*/
}