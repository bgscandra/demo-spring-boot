package com.example.demo.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Builder
@Slf4j
public class AuditMessage {
    String message;
    int index;
    Instant eventTimestamp;

    public String toJSON(){
        Map<String, Object> foo = new HashMap<String, Object>();
        foo.put("message","Audit Message generated");
        foo.put("eventIndex",index);
        foo.put("eventTimestamp",Instant.now().toEpochMilli());
        try {
            return new ObjectMapper().writeValueAsString(foo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("impossible error ",e);
        }
    }
}
