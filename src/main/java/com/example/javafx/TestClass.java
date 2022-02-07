package com.example.javafx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestClass extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TestClass.class.getResource("startMenu.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Preparation to the Battle!");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
