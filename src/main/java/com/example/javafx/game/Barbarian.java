package com.example.javafx.game;

public class Barbarian extends Character {

    public Barbarian(int ID, int power, int hp, boolean mage) {
        super(ID, power, hp, mage);
    }

    @Override
    public int attack() {
        int power = getPower();
        System.out.println("Barbarian Attack: " + power + " dmg");
        return power;
    }

}
