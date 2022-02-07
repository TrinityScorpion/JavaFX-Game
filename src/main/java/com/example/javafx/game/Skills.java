package com.example.javafx.game;

public interface Skills {
    public int attack();
//    public int spell();
    public default void showAllSpells(){
        System.out.println("Your Character can not using the spells!!!");
    }

    public default int cure(){
        System.out.println("Your Character can not cure by himself!!!");
        return 0;
    }

    public default int spell(){
        System.out.println("Your Character can not use the spells!!!");
        return 0;
    }


}
