package com.example.javafx.game;

import com.example.javafx.game.mage.Spell;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Spell> spellBook = Service.addSpellToSpellbook();

        List<Character> characterList = new ArrayList<>();
        Service.addCharactersToList(spellBook, characterList);

        Scanner scan = new Scanner(System.in);
        Service.showMenu();
        while (true) {
            try {
                String action = scan.nextLine();

                switch (Integer.parseInt(action)) {
                    case 1:
                        Service.showAllCharacters(characterList);
                        break;
                    case 2:
                        System.out.println("Choose 1st Character...");
                        Character fighter1 = Service.chooseCharacter(characterList);
                        System.out.println("Choose 2nd Character...");
                        Character fighter2 = Service.chooseCharacter(characterList);
                        Service.startBattle(fighter1, fighter2, spellBook);
                        break;
                    case 3:
                        Service.addNewCharacter(spellBook, characterList);
                        break;
                    case 4:
                        System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Input correct number");
            }
        }
    }


}
