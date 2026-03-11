import javafx.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class ChatApp {
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField messageBox = new TextField();
        TextArea history = new TextArea();
        Button send = new Button("Send");

        grid.add(messageBox, 0, 0);
        grid.add(send, 0, 1);
        grid.add(history, 0, 3);
        send.setOnAction(e -> {
            String input = messageBox.getText();
            history.setText(input);
        });

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setTitle("Chat App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
