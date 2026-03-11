import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatApp extends Application {
    TextField messageBox = new TextField();
    TextArea history = new TextArea();
    Button submit = new Button("Send");
    TextField ipField = new TextField();
    TextField nameField = new TextField();
    Label promptIP = new Label("Input IP");
    Label promptName = new Label("Input Name");
    Button send = new Button("Send");
    PrintWriter out;

    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        history.setEditable(false);


        grid.add(promptName, 0, 1);
        grid.add(nameField, 0, 2);
        grid.add(submit, 0, 3);
        grid.add(messageBox, 0, 4);
        grid.add(send, 0, 5);
        grid.add(history, 0, 6);
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setTitle("Chat App");
        primaryStage.setScene(scene);
        primaryStage.show();
        gridInit(primaryStage);
    }

    private void handleConnect() {
        String ip = ipField.getText();
        String name = nameField.getText();

        try {
            Socket socket = new Socket(ip, 59001);
            out = new PrintWriter(socket.getOutputStream(), true);

            ConsoleClient task = new ConsoleClient(socket, message -> {
                Platform.runLater(() -> history.appendText(message + "\n"));
            });

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            out.println(name);

            submit.setDisable(true);
            send.setDisable(false);
            ipField.setEditable(false);
            nameField.setEditable(false);

        } catch (IOException ex) {
            history.appendText("SERVER: Could not connect to " + ip + "\n");
        }
    }

    private void sendMessage() {
        String text = messageBox.getText();
        if (out != null && !text.isEmpty()) {
            out.println(text);
            messageBox.clear();
        }
    }

    private void gridInit(Stage stage) {
        submit.setOnAction(e -> handleConnect());
        send.setOnAction(e -> sendMessage());
        messageBox.setOnAction(e -> sendMessage());
    }

    public static void main(String[] args) { launch(args); }
}