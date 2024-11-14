package com.wora.waiting_room.entities.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class VisitEmbeddedId {
    @Column(name = "visitor_id")
    @Positive
    Long visitorId;

    @Column(name = "waiting_room_id")
    @Positive
    Long waitingRoomId;
}
