package com.example.javafx;

import com.example.javafx.game.Character;
import com.example.javafx.game.Service;
import com.example.javafx.game.mage.Spell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DuelController {

    @FXML
    public Button closeButton;

    @FXML
    public Button attackButton1;

    @FXML
    public Button attackButton2;
    public Button spellBookButton1;
    public Button spellBookButton2;
    public Button cureButton1;
    public Button cureButton2;

    @FXML
    ProgressBar hpProgress1;

    @FXML
    ProgressBar hpProgress2;

    @FXML
    private Label turn;

    @FXML
    private Label characterInfo1;

    @FXML
    private Label characterInfo2;

    @FXML
    private Label fighter1hp;

    @FXML
    private Label fighter2hp;

    @FXML
    private ImageView firstPhoto;

    @FXML
    private ImageView secondPhoto;

    List<Character> characters = Service.addCharactersTest(Service.addSpellToSpellbook());

    Character firstCharacter = getCharacter(BattleController.firstFighterId, characters);
    Character secondCharacter = getCharacter(BattleController.secondFighterId, characters);

    public void attackButton1(ActionEvent actionEvent) {
        double actualProgress = changeProgressToHp(hpProgress2.getProgress(), secondCharacter.getHp());
        double firstHp = actualProgress - firstCharacter.attack();
        double lastProgress = changeHpToProgress(firstHp, secondCharacter.getHp());
        hpProgress2.setProgress(lastProgress);
        blockFirstCharacter();
        checkHpProgress();
    }

    public void checkHpProgress() {
        BattleController battleController = new BattleController();
        fighter1hp.setText(changeProgressToHp(hpProgress1.getProgress(), firstCharacter.getHp()) + "/" + firstCharacter.getHp());
        fighter2hp.setText(changeProgressToHp(hpProgress2.getProgress(), secondCharacter.getHp()) + "/" + secondCharacter.getHp());
        characterInfo2.setText(firstCharacter.getClass().getSimpleName()+" "+firstCharacter.getID());
        characterInfo1.setText(secondCharacter.getClass().getSimpleName()+" "+secondCharacter.getID());
        firstPhoto.setImage(battleController.checkAvatar(firstCharacter.getClass().getSimpleName()));
        secondPhoto.setImage(battleController.checkAvatar(secondCharacter.getClass().getSimpleName()));

        if (hpProgress2.getProgress() <= 0.0) {
            hpProgress2.setDisable(true);
            fighter2hp.setText("0/" + secondCharacter.getHp());
            turn.setText(firstCharacter.getClass().getSimpleName() + " " + firstCharacter.getID() + " Wins");
            turn.setStyle("-fx-text-alignment: center");
            secondPhoto.setStyle(" -fx-effect: innershadow( gaussian , red , 100,0,0,0 );");
            blockAllButtons();

        } else if (hpProgress1.getProgress() <= 0.0) {
            hpProgress1.setDisable(true);
            fighter1hp.setText("0/" + firstCharacter.getHp());
            turn.setText(secondCharacter.getClass().getSimpleName() + " " + secondCharacter.getID() + " Wins");
            firstPhoto.setStyle(" -fx-effect: innershadow( gaussian , red , 100,0,0,0 );");
            blockAllButtons();
        }
    }

    private void blockAllButtons() {
        attackButton1.setDisable(true);
        spellBookButton1.setDisable(true);
        cureButton1.setDisable(true);
        attackButton2.setDisable(true);
        spellBookButton2.setDisable(true);
        cureButton2.setDisable(true);
    }

    public void blockSecondCharacter() {
        attackButton1.setDisable(false);
        spellBookButton1.setDisable(false);
        cureButton1.setDisable(false);
        attackButton2.setDisable(true);
        spellBookButton2.setDisable(true);
        cureButton2.setDisable(true);
        turn.setText("First Character Turn");
        turn.setTextFill(Color.WHITE);
    }

    public void blockFirstCharacter() {
        attackButton1.setDisable(true);
        spellBookButton1.setDisable(true);
        cureButton1.setDisable(true);
        attackButton2.setDisable(false);
        spellBookButton2.setDisable(false);
        cureButton2.setDisable(false);
        turn.setText("Second character Turn");
        turn.setTextFill(Color.WHITE);
    }

    public void attackButton2(ActionEvent actionEvent) {
        double actualProgress = changeProgressToHp(hpProgress1.getProgress(), firstCharacter.getHp());
        double firstHp = actualProgress - secondCharacter.attack();
        double lastProgress = changeHpToProgress(firstHp, firstCharacter.getHp());
        hpProgress1.setProgress(lastProgress);
        blockSecondCharacter();
        checkHpProgress();
    }

    public void cureButton1(ActionEvent actionEvent) {
        if (firstCharacter.getClass().getSimpleName().equals("Cleric")) {
            double actualProgress = changeProgressToHp(hpProgress1.getProgress(), firstCharacter.getHp());
            double firstHp = actualProgress + firstCharacter.cure();
            double lastProgress = changeHpToProgress(firstHp, firstCharacter.getHp());
            hpProgress1.setProgress(lastProgress);
            blockFirstCharacter();
            checkHpProgress();
        } else {
            turn.setText("Your Character can not cure by himself");
            turn.setTextFill(Color.RED);
        }
    }

    public void cureButton2(ActionEvent actionEvent) {
        if (secondCharacter.getClass().getSimpleName().equals("Cleric")) {
            double actualProgress = changeProgressToHp(hpProgress2.getProgress(), secondCharacter.getHp());
            double firstHp = actualProgress + secondCharacter.cure();
            double lastProgress = changeHpToProgress(firstHp, secondCharacter.getHp());
            hpProgress2.setProgress(lastProgress);
            blockSecondCharacter();
            checkHpProgress();
        } else {
            turn.setText("Your Character can not cure by himself");
            turn.setTextFill(Color.RED);
        }
    }

    public int changeProgressToHp(double actualProgress, int characterHp) {
        return (int) ((actualProgress * characterHp) / 1.0);
    }

    public double changeHpToProgress(double hp, int characterHp) {
        return ((hp * 1.0) / characterHp);
    }

    public void spellBookButton1(ActionEvent actionEvent) {
        if (firstCharacter.getClass().getSimpleName().equals("Mage")) {
            VBox vbox = new VBox();
            List<Spell> spellBook = Service.addSpellToSpellbook();
            File file3 = new File("src/main/resources/paper1.png");
            Image image3 = new Image(file3.toURI().toString(), 500, 80 * spellBook.size(), false, true);
            BackgroundImage backgroundImage = new BackgroundImage(image3, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
            Stage stage = new Stage();
            Group root = new Group();
            Scene scene = new Scene(root, 500, spellBook.size() * 80, Color.AZURE);
            vbox.setBackground(new Background(backgroundImage));
            vbox.setMinSize(500, spellBook.size() * 80);

            for (int i = 0; i < spellBook.size(); i++) {
                Button btn = new Button();

                btn.setMinWidth(200);
                btn.setMinHeight(40);
                btn.setAlignment(Pos.CENTER);
                btn.setText(spellBook.get(i).getSpellName() + " | Dmg: " + spellBook.get(i).getDamage());
                int finalI = i;
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Spell spell = spellBook.get(finalI);
                        double actualProgress = changeProgressToHp(hpProgress2.getProgress(), secondCharacter.getHp());
                        double firstHp = actualProgress - spell.getDamage();
                        double lastProgress = changeHpToProgress(firstHp, secondCharacter.getHp());
                        hpProgress2.setProgress(lastProgress);
                        blockFirstCharacter();
                        checkHpProgress();
                        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    }
                });
                Pane pane = new Pane();
//                String element = spellBook.get(i).getElement();


                pane.setBackground(new Background(backgroundImage));
                pane.setStyle("-fx-background-size: cover;");
                if(spellBook.get(i).getElement().toString().equals("FIRE")){
                    btn.setStyle(" -fx-effect: innershadow( gaussian , red , 50,0,0,0 );");
                }else if(spellBook.get(i).getElement().toString().equals("WATER")){
                    btn.setStyle(" -fx-effect: innershadow( gaussian , blue , 50,0,0,0 );");
                }else if(spellBook.get(i).getElement().toString().equals("EARTH")){
                    btn.setStyle(" -fx-effect: innershadow( gaussian , saddlebrown , 50,0,0,0 );");
                }else{
                    btn.setStyle(" -fx-effect: innershadow( gaussian , cyan , 50,0,0,0 );");
                }
                btn.setLayoutX(150);
                btn.setLayoutY(30);
                pane.getChildren().add(btn);
                vbox.getChildren().add(pane); //add button to your VBox
            }
            root.getChildren().add(vbox);
            stage.setScene(scene); //primaryStage is your stage, scene is the current scene
            stage.show();
        } else {
            turn.setText("Your Character can not use spellbook");
            turn.setTextFill(Color.RED);
        }
    }

    public void spellBookButton2(ActionEvent actionEvent) {
        if (secondCharacter.getClass().getSimpleName().equals("Mage")) {
            VBox vbox = new VBox();
            Stage stage = new Stage();
            Group root = new Group();
            List<Spell> spellBook = Service.addSpellToSpellbook();
            Scene scene = new Scene(root, 500, spellBook.size() * 80, Color.AZURE);
            File file3 = new File("src/main/resources/paper1.png");
            Image image3 = new Image(file3.toURI().toString(), 500, 80 * spellBook.size(), false, true);
            BackgroundImage backgroundImage = new BackgroundImage(image3, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
            vbox.setBackground(new Background(backgroundImage));
            vbox.setMinSize(500, spellBook.size() * 80);

            for (int i = 0; i < spellBook.size(); i++) {

                Button btn = new Button();
                btn.setMinWidth(200);
                btn.setMinHeight(40);
                btn.setText(spellBook.get(i).getSpellName() + " | Dmg: " + spellBook.get(i).getDamage());
                int finalI = i;
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Spell spell = spellBook.get(finalI);
                        double actualProgress = changeProgressToHp(hpProgress1.getProgress(), firstCharacter.getHp());
                        double firstHp = actualProgress - spell.getDamage();
                        double lastProgress = changeHpToProgress(firstHp, firstCharacter.getHp());
                        hpProgress1.setProgress(lastProgress);
                        blockSecondCharacter();
                        checkHpProgress();
                        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    }
                });
                Pane pane = new Pane();
                pane.setBackground(new Background(backgroundImage));
                pane.setStyle("-fx-background-size: cover;");
                if(spellBook.get(i).getElement().toString().equals("FIRE")){
                    btn.setStyle(" -fx-effect: innershadow( gaussian , red , 50,0,0,0 );");
                }else if(spellBook.get(i).getElement().toString().equals("WATER")){
                    btn.setStyle(" -fx-effect: innershadow( gaussian , blue , 50,0,0,0 );");
                }else if(spellBook.get(i).getElement().toString().equals("EARTH")){
                    btn.setStyle(" -fx-effect: innershadow( gaussian , saddlebrown , 50,0,0,0 );");
                }else{
                    btn.setStyle(" -fx-effect: innershadow( gaussian , cyan , 50,0,0,0 );");
                }
                btn.setLayoutX(150);
                btn.setLayoutY(30);
                pane.getChildren().add(btn);
                vbox.getChildren().add(pane); //add button to your VBox
            }
            root.getChildren().add(vbox); //root of your pane
            stage.setScene(scene); //primaryStage is your stage, scene is the current scene
            stage.show();
        } else {
            turn.setText("Your Character can not use spellbook");
            turn.setTextFill(Color.RED);
        }

    }

    public Character getCharacter(int id, List<Character> characters) {
        Character character = characters.get(id);
        return character;
    }

    @FXML
    public void closeButton(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
    }

}
