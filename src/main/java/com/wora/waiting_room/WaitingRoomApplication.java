package com.wora.waiting_room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wora.waiting_room")
public class WaitingRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaitingRoomApplication.class, args);
	}

}
