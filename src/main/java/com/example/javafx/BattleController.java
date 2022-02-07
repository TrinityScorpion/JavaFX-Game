package com.example.javafx;

import com.example.javafx.game.Character;
import com.example.javafx.game.Service;
import com.example.javafx.game.mage.Spell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class BattleController implements Initializable {
    List<Spell> spells = Service.addSpellToSpellbook();
    public static int firstFighterId = 0;
    public static int secondFighterId = 0;
    @FXML
    private Label character1;

    @FXML
    private Label character2;

    @FXML
    private Label alert;

    @FXML
    private ImageView firstPhoto;

    @FXML
    private ImageView secondPhoto;

    @FXML
    public ChoiceBox<String> choiceBox1;

    @FXML
    protected ChoiceBox<String> choiceBox2;

    File file1 = new File("src/main/resources/barb.jpg");
    Image image1 = new Image(file1.toURI().toString());
    File file2 = new File("src/main/resources/mage.jpg");
    Image image2 = new Image(file2.toURI().toString());
    File file3 = new File("src/main/resources/cler.jpg");
    Image image3 = new Image(file3.toURI().toString());

    public Image checkAvatar(String characterClass) {

        if (characterClass.equals("Barbarian")) {
            return image1;
        } else if (characterClass.equals("Mage")) {
            return image2;
        } else if (characterClass.equals("Cleric")) {
            return image3;
        } else
            return null;
    }

    @FXML
    public void selectOneCharacter1(ActionEvent actionEvent) {
        List<Character> characterList = Service.addCharactersTest(spells);
        int index = choiceBox1.getSelectionModel().getSelectedIndex();
        character1.setText(characterList.get(index).getClass().getSimpleName() +
                " " + characterList.get(index).getID() +
                " | HP: " + characterList.get(index).getHp() +
                " | Power: " + characterList.get(index).getHp() +
                " | Magic: " + characterList.get(index).isMage());

        firstFighterId = choiceBox1.getSelectionModel().getSelectedIndex();
        firstPhoto.setImage(checkAvatar(characterList.get(index).getClass().getSimpleName()));
    }

    @FXML
    public void selectOneCharacter2(ActionEvent actionEvent) {
        List<Character> characterList = Service.addCharactersTest(spells);
        int index = choiceBox2.getSelectionModel().getSelectedIndex();
        character2.setText(characterList.get(index).getClass().getSimpleName() +
                " " + characterList.get(index).getID() +
                " | HP: " + characterList.get(index).getHp() +
                " | Power: " + characterList.get(index).getHp() +
                " | Magic: " + characterList.get(index).isMage());
        secondFighterId = choiceBox2.getSelectionModel().getSelectedIndex();
        secondPhoto.setImage(checkAvatar(characterList.get(index).getClass().getSimpleName()));
    }

    @FXML
    public void startBattle(ActionEvent actionEvent) throws InterruptedException {
        if (choiceBox1.getValue() == null || choiceBox2.getValue() == null) {
            alert.setAlignment(Pos.CENTER);
            alert.setWrapText(true);
            alert.setText("You need 2 warriors to fight. Please choose two characters.");
        } else {
            alert.setText("Prepare to the battle!!!");

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(BattleController.class.getResource("duel.fxml"));
            Label label1 = new Label("HJVJ");

            Group root = new Group(choiceBox1, choiceBox2, label1);
            Scene scene = null;

//            scene = new Scene(root, 595, 300, Color.AZURE);
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        List<Character> characters = Service.addCharactersTest(Service.addSpellToSpellbook());
        String temp = "";
        for (int i = 0; i < characters.size(); i++) {
            temp = characters.get(i).getClass().getSimpleName() + " " + characters.get(i).getID();
            list.add(temp);
        }
        choiceBox1.setItems(list);
        choiceBox2.setItems(list);
    }

}
