package com.example.javafx.game;

public class Cleric extends Character{
    public Cleric(int ID, int power, int hp, boolean mage) {
        super(ID, power, hp, mage);
    }

    @Override
    public int attack() {
        int power =  getPower();
        System.out.println("Cleric Attack: " + power + " dmg");
        return power;
    }

    @Override
    public int cure() {
        int power = getPower();
        System.out.println("Cured: " + power + " HP");
        return power;
    }


}
