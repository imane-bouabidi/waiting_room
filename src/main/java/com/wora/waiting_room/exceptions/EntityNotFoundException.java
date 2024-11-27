package com.wora.waiting_room.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException( String message ) {
        super( message );
    };
}
