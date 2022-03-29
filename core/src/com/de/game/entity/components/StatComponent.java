package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;

public class StatComponent implements Component{
    private int damage;
    private int maxleben;
    private int leben;
    private int speed;
    private int ruestung;
    //Nicht im construktor da dieser nicht aufgerufen wird 
    public void setStats(int damage, int leben, int maxleben, int speed, int ruestung){
        this.damage = damage;
        this.leben = leben;
        this.speed = speed;
        this.ruestung = ruestung;
    }
    //get Methoden
    public int getDamage() {
        return damage;
    }
    public int getLeben() {
        return leben;
    }
    public int getRuestung() {
        return ruestung;
    }
    public int getSpeed() {
        return speed;
    }
    public void nehmeschaden(int schaden){
        leben -= schaden;
    }
    public void schadenerhoehen(int schaden){
        this.damage += schaden;
    }
    public void ruestungerhoehen(int ruestung){
        this.ruestung += ruestung;
    }
    //Methode die heilt, wenn die heilung höher als das maxleben gehen würde wird das leben auf maxleben gesetzt
    public void heilen(int heilung){
        leben += heilung;
        if (leben > maxleben){
            leben = maxleben;
        }
    }
    public int getMaxleben() {
        return maxleben;
    }
    //Set Methoden
    public void setMaxleben(int maxleben) {
        this.maxleben = maxleben;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setLeben(int leben) {
        this.leben = leben;
    }
    public void setRuestung(int ruestung) {
        this.ruestung = ruestung;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
