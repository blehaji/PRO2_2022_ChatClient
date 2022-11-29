import models.chatClients.ChatClient;
import models.chatClients.DatabaseChatClient;
import models.chatClients.FileChatClient;
import models.chatClients.InMemoryChatClient;
import models.chatClients.api.ApiChatClient;
import models.chatClients.fileOperations.ChatFileOperations;
import models.chatClients.fileOperations.JsonChatFileOperations;
import models.database.DatabaseOperations;
import models.database.DbInitializer;
import models.database.JdbcDatabaseOperations;
import models.gui.MainFrame;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String databaseDriver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseUrl = "jdbc:derby:ChatClient";

//        DbInitializer initializer = new DbInitializer(databaseDriver, databaseUrl);
//        initializer.init();

//        ChatFileOperations chatFileOperations = new JsonChatFileOperations();
//        ChatClient chatClient = new ApiChatClient();

        ChatClient chatClient;
        try {
            DatabaseOperations databaseOperations = new JdbcDatabaseOperations(databaseDriver, databaseUrl);
            chatClient = new DatabaseChatClient(databaseOperations);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

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