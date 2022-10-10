package models.gui;

import models.chatClients.ChatClient;
import models.chatClients.InMemoryChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private ChatClient chatClient;
    public MainFrame(int width, int height) {
        super("PRO2 2022 ChatClient");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatClient = new InMemoryChatClient();

        initGui();
        setVisible(true);
    }

    private void initGui() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(initLoginPanel(), BorderLayout.NORTH);
        mainPanel.add(initChatPanel(), BorderLayout.CENTER);
        mainPanel.add(initMessagePanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel initLoginPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Username: "));

        JTextField usernameInputField = new JTextField("", 30);
        panel.add(usernameInputField);
        JButton loginButton = new JButton("Let me in!");
        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatClient.login(usernameInputField.getText());
            }
        });
        panel.add(loginButton);
        return panel;
    }

    private JPanel initChatPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JTextArea chat = new JTextArea();
        chat.setName("chatArea");
        chat.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chat);

        panel.add(scrollPane);

        return panel;
    }

    private JPanel initMessagePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField messageTextField = new JTextField("", 50);
        panel.add(messageTextField);
        JButton sendButton = new JButton("Send it!");
        panel.add(sendButton);

        sendButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatClient.sendMessage(messageTextField.getText());
            }
        });

        return panel;
    }
}
