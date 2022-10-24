package models;

import java.time.LocalDateTime;

public class Message {
    private String author;
    private String text;
    private LocalDateTime created;
    private boolean systemMessage;

    public static final int USER_LOGGED_IN = 1;
    public static final int USER_LOGGED_OUT = 2;

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
        this.created = LocalDateTime.now();
        systemMessage = false;
    }

    public Message(int type, String user) {
        this.systemMessage = true;
        switch (type) {
            case USER_LOGGED_IN:
                this.text = user + " has logged in";
                break;
            case USER_LOGGED_OUT:
                this.text = user + " has logged out";
                break;
            default:
                this.text = "<invalid system message>";
                break;
        }
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public boolean isSystemMessage() {
        return systemMessage;
    }

    @Override
    public String toString() {
        if (systemMessage) {
            return text;
        } else {
            return String.format("%s <%s>: %s", author, created, text);
        }
    }
}
