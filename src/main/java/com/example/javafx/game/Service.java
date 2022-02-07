package com.example.javafx.game;

import com.example.javafx.game.mage.Element;
import com.example.javafx.game.mage.Mage;
import com.example.javafx.game.mage.Spell;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    public static void showMenu() {
        System.out.println("Let's start battle. Choose option");
        System.out.println("1. Show All Characters");
        System.out.println("2. Start the battle");
        System.out.println("3. Add new Character");
        System.out.println("4. Exit");

    }

    static void showAllCharacters(List<Character> characterList) {
        for (int i = 0; i < characterList.size(); i++) {
            System.out.println(characterList.get(i).toString() + " - " + characterList.get(i).getClass().getSimpleName());
        }
    }

    static void printBarbarianFace() {
        System.out.println(" \\\\___// ");
        System.out.println("[| O * |]");
        System.out.println(" |  8  | ");
        System.out.println(" ||***|| ");
        System.out.println(" ||||||| ");
        System.out.println("  |||||  ");
    }

    public static void printMageFace() {
        System.out.println("   / \\ ");
        System.out.println("  /   \\ ");
        System.out.println(" /_____\\ ");
        System.out.println("[| O O |]");
        System.out.println(" |  #  | ");
        System.out.println(" | --- | ");
        System.out.println("  +---+ ");
    }

    static void printClericFace() {
        System.out.println("|||||||||");
        System.out.println("[| - - |]");
        System.out.println("||  @  ||");
        System.out.println("||[---]||");
        System.out.println("|||||||||");
        System.out.println("||     ||");
    }

    static void addNewCharacter(List<Spell> spellBook, List<Character> characterList) {
        System.out.println("Character Menu. Input class.");
        Scanner scanner = new Scanner(System.in);
        try {
            String line = scanner.nextLine();
            if (line.equals("Bar")) {
                System.out.println("Input Barbarian power:");
                int power = Integer.parseInt(scanner.nextLine());
                System.out.println("Input Barbarian HP:");
                int hp = Integer.parseInt(scanner.nextLine());
                Barbarian barbarian = new Barbarian((characterList.size()+1), power, hp, false);
                characterList.add(barbarian);
                System.out.println("Barbarian Added!");
            } else if (line.equals("Mage")) {
                System.out.println("Input Mage power:");
                int power = Integer.parseInt(scanner.nextLine());
                System.out.println("Input Mage HP:");
                int hp = Integer.parseInt(scanner.nextLine());
                Mage mage = new Mage((characterList.size()+1), power, hp, true, spellBook);
                characterList.add(mage);
                System.out.println("Mage Added! Added!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input correct character attribute");
        }
    }

    public static List<Spell> addSpellToSpellbook() {
        List<Spell> spellBook = new ArrayList<>();
        Spell spell1 = new Spell(Element.FIRE, "Fireball", 200);
        Spell spell2 = new Spell(Element.WATER, "Ice Bolt", 150);
        Spell spell3 = new Spell(Element.EARTH, "Implosion", 350);
        Spell spell4 = new Spell(Element.WIND, "Lightning", 250);
        Spell spell5 = new Spell(Element.FIRE, "Inferno", 250);
        Spell spell6 = new Spell(Element.WATER, "Magic Arrow", 150);
        Spell spell8 = new Spell(Element.FIRE, "Magic Arrow", 150);
        Spell spell9 = new Spell(Element.WIND, "Magic Arrow", 150);
        Spell spell7 = new Spell(Element.EARTH, "Magic Arrow", 150);
        spellBook.add(spell1);
        spellBook.add(spell2);
        spellBook.add(spell3);
        spellBook.add(spell4);
        spellBook.add(spell5);
        spellBook.add(spell6);
        spellBook.add(spell7);
        spellBook.add(spell8);
        spellBook.add(spell9);
        return spellBook;
    }

    static void startBattle(Character figher1, Character figher2, List<Spell> spells) {
        int figher1HP = figher1.getHp();
        int figher2HP = figher2.getHp();
        System.out.println("Battle is starting");
        System.out.println("-----------");
        checkCharacter(figher1);
        System.out.println("---VS---");
        checkCharacter(figher2);
        System.out.println("-----------");
        do {
            System.out.println("------");
            System.out.println(figher1.getClass().getSimpleName() + " " + figher1.getID() + " HP: " + figher1HP);
            System.out.println(figher2.getClass().getSimpleName() + " " + figher2.getID() + " HP: " + figher2HP);
            System.out.println("------");

            figher1HP = fighterMove(figher2, figher1HP, spells);
            figher2HP = fighterMove(figher1, figher2HP, spells);

            if (figher1HP <= 0)
                System.out.println(figher2.getClass().getSimpleName() + " " + figher2.getID() + " win");
            else if (figher2HP <= 0)
                System.out.println(figher1.getClass().getSimpleName() + " " + figher1.getID() + " wins");
            System.out.println("-----");
        } while (figher1HP >= 0 && figher2HP >= 0);
    }

    private static void checkCharacter(Character figher) {
        if (figher.getClass().getSimpleName().equals("Barbarian"))
            printBarbarianFace();
        else if(figher.getClass().getSimpleName().equals("Mage"))
            printMageFace();
        else
            printClericFace();
    }

    private static int fighterMove(Character figher, int fighterHp, List<Spell> spells) {
        Scanner scan = new Scanner(System.in);
        System.out.println("-------");
        checkCharacter(figher);
        System.out.println(figher.getClass().getSimpleName() + " " + figher.getID() + " move");
        System.out.println("1. Attack");
        System.out.println("2. Open SpellBook");
        System.out.println("3. Cure");
        String action = scan.nextLine();
        switch (Integer.parseInt(action)) {
            case 1:
                fighterHp = fighterHp - figher.attack();
                break;
            case 2:
                if (figher.isMage()) {
                    fighterHp = attackBySpell(figher, fighterHp, spells);
                } else {
                    System.out.println("You are not magic character. You can not using the spells");
                    fighterHp = fighterMove(figher, fighterHp, spells);
                }
                break;
            case 3:
                if (figher.getClass().getSimpleName().equals("Cleric")) {
                    fighterHp = fighterHp+figher.cure();
                } else {
                    System.out.println("You are not magic character. You can not using the spells");
                    fighterHp = fighterMove(figher, fighterHp, spells);
                }
        }
        return fighterHp;
    }

    private static int attackBySpell(Character figher, int fighterHp, List<Spell> spells) {
        figher.showAllSpells();
        Scanner scan1 = new Scanner(System.in);
        String chooseSpell = scan1.nextLine();
        for (int i = 0; i < spells.size(); i++) {
            if (Integer.parseInt(chooseSpell) == (i + 1)) {
                fighterHp = fighterHp - spells.get(Integer.parseInt(chooseSpell) - 1).getDamage();
                System.out.println(spells.get(Integer.parseInt(chooseSpell) - 1).getSpellName());
            }
        }
        return fighterHp;
    }

    static Character chooseCharacter(List<Character> characterList) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String line = scan.nextLine();
            if (Integer.parseInt(line) <= 0 || Integer.parseInt(line) > characterList.size()) {
                System.out.println("You choose wrong character number! Choose correct");
            } else {
                Character fighter = characterList.get(Integer.parseInt(line) - 1);
                return fighter;
            }
        }
    }

    public static void addCharactersToList(List<Spell> spellBook, List<Character> characterList) {
        Barbarian barbarian1 = new Barbarian(1, 520, 1000, false);
        Barbarian barbarian2 = new Barbarian(2, 540, 1000, false);
        Mage mage1 = new Mage(3, 300, 400, true, spellBook);
        Mage mage2 = new Mage(4, 200, 500, true, spellBook);
        Cleric cleric1 = new Cleric(5, 200, 500, true);
        Cleric cleric2 = new Cleric(6, 200, 500, true);
        characterList.add(barbarian1);
        characterList.add(barbarian2);
        characterList.add(mage1);
        characterList.add(mage2);
        characterList.add(cleric1);
        characterList.add(cleric2);
    }
    public static List<Character> addCharactersTest(List<Spell> spellBook) {
        List<Character> characterList = new ArrayList<>();
        Barbarian barbarian1 = new Barbarian(1, 1020, 2000, false);
        Barbarian barbarian2 = new Barbarian(2, 1040, 2000, false);
        Mage mage1 = new Mage(3, 500, 2000, true, spellBook);
        Mage mage2 = new Mage(4, 500, 2000, true, spellBook);
        Cleric cleric1 = new Cleric(5, 500, 2000, true);
        Cleric cleric2 = new Cleric(6, 500, 2000, true);
        characterList.add(barbarian1);
        characterList.add(barbarian2);
        characterList.add(mage1);
        characterList.add(mage2);
        characterList.add(cleric1);
        characterList.add(cleric2);
        return characterList;
    }
}
