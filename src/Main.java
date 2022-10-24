import models.chatClients.ChatClient;
import models.chatClients.InMemoryChatClient;
import models.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        ChatClient chatClient = new InMemoryChatClient();

        MainFrame window = new MainFrame(700, 500, chatClient);
        //test();
    }

    private static void test() {
        ChatClient client = new InMemoryChatClient();

        client.login("bot");

        client.sendMessage("Hello there");
        client.sendMessage("Testing 123");

        client.logout();
    };
}