package com.google.sps.servlets;

public final class Message {

    private final long id;
    private final String textValue;
    private final long timestamp;

    public Message(long id, String textValue, long timestamp) {
        this.id = id;
        this.textValue = textValue;
        this.timestamp = timestamp;
    }
}
