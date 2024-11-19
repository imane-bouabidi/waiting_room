package com.wora.waiting_room.config;

import com.wora.waiting_room.entities.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "visit")
@Getter
@Setter
public class VisitProperties {
    private Status defaultVisitorStatus = Status.WAITING;
    private Byte defaultPriority = 100;
    private Duration defaultEstimatedTime = Duration.ofMinutes(10);
}
