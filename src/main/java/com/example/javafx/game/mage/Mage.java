package com.example.javafx.game.mage;

import com.example.javafx.game.Character;

import java.util.List;

public class Mage extends Character {
    private List<Spell> spellbook;

    public Mage(int ID, int power, int hp, boolean mage, List<Spell> spellbook) {
        super(ID, power, hp, mage);
        this.spellbook = spellbook;
    }

    @Override
    public int attack() {
        int power = getPower();
        System.out.println("Mage Attack: " + power + " dmg");
        return power;
    }

    @Override
    public int spell() {
        int power = getPower();
        System.out.println("Mage spells: " + power + " dmg");
        return power;
    }

    @Override
    public void showAllSpells(){
        for (int i = 0; i < spellbook.size(); i++) {
            if(i==0)
                System.out.println("--------------------------");
            System.out.println("| "+(i+1)+". "+spellbook.get(i).getSpellName()+" dmg: "+spellbook.get(i).getDamage());
            if(i==spellbook.size()-1)
                System.out.println("--------------------------");
        }
    }
}
