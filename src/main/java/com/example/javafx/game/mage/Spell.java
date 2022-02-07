package com.example.javafx.game.mage;

public class Spell {
    private Element element;
    private String spellName;
    private int damage;

    public Spell(Element element, String spellName, int damage) {
        this.element = element;
        this.spellName = spellName;
        this.damage = damage;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "element=" + element +
                ", spellName='" + spellName + '\'' +
                ", damage=" + damage +
                '}';
    }
}
