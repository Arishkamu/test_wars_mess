package org.itmo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Message {
    private LocalDateTime time;
    private String message;
    private String password;

    public Message(LocalDateTime time, String message, String password) {
        this.time = time;
        this.message = message;
        this.password = password;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getPassword() {
        return password;
    }
}
