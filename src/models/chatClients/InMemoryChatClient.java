package models.chatClients;

import models.Message;

import java.util.ArrayList;
import java.util.List;

public class InMemoryChatClient implements ChatClient {
    private String loggedUser;
    private List<String> loggedUsers;
    private List<Message> messages;

    public InMemoryChatClient() {
        loggedUsers = new ArrayList<>();
        messages = new ArrayList<>();
    }

    @Override
    public void sendMessage(String text) {
        if (!isAuthenticated())
            return;
        messages.add(new Message(loggedUser, text));
        System.out.printf("%s sent message \"%s\"%n", loggedUser, text);
    }

    @Override
    public void login(String username) {
        loggedUser = username;
        loggedUsers.add(loggedUser);
        System.out.printf("%s has logged in%n", username);
    }

    @Override
    public void logout() {
        loggedUsers.remove(loggedUser);
        loggedUser = null;
        System.out.println("User logged out");
    }

    @Override
    public boolean isAuthenticated() {
        return loggedUser != null;
    }

    @Override
    public List<String> getLoggedUsers() {
        return loggedUsers;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }
}
