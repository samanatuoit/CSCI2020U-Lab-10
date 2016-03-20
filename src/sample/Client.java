package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Application {

    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Lab 10");
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));


        Label userNameLbl = new Label("Username: ");
        TextField userNameFld = new TextField();
        Label messageLbl = new Label("Message: ");
        TextField messageFld = new TextField();
        Button sendBtn = new Button("Send");
        sendBtn.setOnAction(evt -> sendMessage(messageFld.getText()));
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(evt -> System.exit(0));

        gridPane.add(userNameLbl, 0, 0);
        gridPane.add(userNameFld, 1, 0);
        gridPane.add(messageLbl, 0, 1);
        gridPane.add(messageFld, 1, 1);
        gridPane.add(sendBtn, 0, 2);
        gridPane.add(exitBtn, 0, 3);


        root.getChildren().addAll(gridPane);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage(String message) {
        try {
            Socket socket = new Socket("127.0.0.1", 7000);
            PrintWriter out = new PrintWriter(new ObjectOutputStream(socket.getOutputStream()));
            out.println(message);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
