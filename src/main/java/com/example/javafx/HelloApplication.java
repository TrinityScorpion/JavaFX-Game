package com.example.javafx;

import com.example.javafx.game.Character;
import com.example.javafx.game.Service;
import com.example.javafx.game.mage.Spell;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        List<Spell> spellBook = Service.addSpellToSpellbook();
        List<Character> characterList = Service.addCharactersTest(spellBook);

        //Creating a choice box
        ChoiceBox<String> choiceBox1 = new ChoiceBox<>();
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        choiceBox1.setValue("Choose Character");
        choiceBox2.setValue("Choose Character");
        //Retrieving the observable list
        List<String> list1 = choiceBox1.getItems();
        List<String> list2 = choiceBox2.getItems();

        //Adding items to the list
        Label label1 = selectOneCharacter(stage, characterList, choiceBox1, list1, 150, 15);
        Label label2 = selectOneCharacter(stage, characterList, choiceBox2, list2, 300, 15);

        //Adding the choice box to the group
        Group root = new Group(choiceBox1, choiceBox2, label1, label2);
        //Setting the stage
        Scene scene = new Scene(root, 595, 300, Color.GREY);

        stage.setTitle("Choice Box Example");
        stage.setScene(scene);

        stage.show();
    }

    private Label selectOneCharacter(Stage stage, List<Character> characterList, ChoiceBox<String> choiceBox, List<String> list, int x, int y) {
        for (int i = 0; i < characterList.size(); i++) {
            list.add(characterList.get(i).getClass().getSimpleName() + " " + characterList.get(i).getID());
        }
        //Setting the position of the choice box
        choiceBox.setTranslateX(x);
        choiceBox.setTranslateY(y);
        //Setting the label
        Label label = new Label("Select Character:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);
        label.setTranslateX(20);
        label.setTranslateY(20);

        choiceBox.setOnAction((actionEvent -> {
            selectCharacter(stage, characterList, choiceBox, label);

        }));
        return label;
    }

    private void selectCharacter(Stage stage, List<Character> characterList, ChoiceBox<String> choiceBox, Label label) {
        int index = choiceBox.getSelectionModel().getSelectedIndex();
        Object item = choiceBox.getSelectionModel().getSelectedItem();
        Text text = new Text(25, 80,
                characterList.get(index).getClass().getSimpleName() +
                        " " + characterList.get(index).getID() +
                        " | HP: " + characterList.get(index).getHp() +
                        " | Power: " + characterList.get(index).getHp() +
                        " | Magic: " + characterList.get(index).isMage());
        System.out.println(index);
        System.out.println(item);
        Group root = new Group(choiceBox, label, text);
        //Setting the stage
        Scene scene = new Scene(root, 595, 170, Color.AZURE);

        stage.setTitle("Choice Box Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}