package com.google.sps.servlets;

/** An item on a todo list. */
// public final class Task {

//     private final long id;
//     // private final String title;
//     private final long timestamp;
//     private final String textValue;

//     public Task(long id, /*String title, */long timestamp, String textValue) {
//         this.id = id;
//         // this.title = title;
//         this.timestamp = timestamp;
//         this.textValue = textValue;
//     }
    
// }

public final class Task {

    private final long id;
    private final String title;
    private final long timestamp;

    public Task(long id, String title, long timestamp) {
        this.id = id;
        this.title = title;
        this.timestamp = timestamp;
    }
}