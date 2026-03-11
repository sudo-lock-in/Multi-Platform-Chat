import javafx.application.Application;
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
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField messageBox = new TextField();
        TextArea history = new TextArea();
        history.setEditable(false);
        Button submit = new Button("Submit");
        TextField ipField = new TextField();
        TextField nameField = new TextField();
        Label promptIP = new Label("IP");
        Label promptName = new Label("Name");
        Button send = new Button("Send");
        Label historyLabel = new Label("History");


        grid.add(promptIP, 0, 0);
        grid.add(ipField, 0, 1);
        grid.add(promptName, 0, 2);
        grid.add(nameField, 0, 3);
        grid.add(submit, 0, 4);
        grid.add(messageBox, 0, 5);
        grid.add(send, 0, 6);
        grid.add(historyLabel, 0,  7);
        grid.add(history, 0, 8);

        submit.setOnAction(e -> {
            String IP = ipField.getText();
            String name = nameField.getText();
            nameField.setEditable(false);
            ipField.setEditable(false);
        });

        send.setOnAction(e -> {
            String input = messageBox.getText();
            history.appendText(input + "\n");
        });

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setTitle("Chat App");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
