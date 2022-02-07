package com.example.javafx.game;

public abstract class Character implements Skills{
    private int ID;
    private int power;
    private int hp;
    private boolean mage;

    public Character(int ID, int power, int hp, boolean mage) {
        this.ID = ID;
        this.power = power;
        this.hp = hp;
        this.mage = mage;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isMage() {
        return mage;
    }

    public void setMage(boolean mage) {
        this.mage = mage;
    }

    @Override
    public String toString() {
        return "Character{" +
                "ID=" + ID +
                ", power=" + power +
                ", hp=" + hp +
                ", mage=" + mage +
                '}';
    }


}

