package com.wora.waiting_room.config;

import com.wora.waiting_room.entities.enums.AlgorithmType;
import com.wora.waiting_room.entities.enums.WorkMode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "waiting-room")
@Getter
@Setter
public class WaitingRoomProperties {
    private AlgorithmType defaultAlgorithmType = AlgorithmType.FIFO;
    private WorkMode defaultWorkMode = WorkMode.MORNING;
    private Integer defaultCapacity = 15;
}
