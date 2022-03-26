package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;

public class StatComponent implements Component{
    private int damage;
    private int maxleben;
    private int leben;
    private int speed;
    private int ruestung;
    public void setStats(int damage, int leben, int maxleben, int speed, int ruestung){
        this.damage = damage;
        this.leben = leben;
        this.speed = speed;
        this.ruestung = ruestung;
    }
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
    public void heilen(int heilung){
        leben += heilung;
        if (leben > maxleben){
            leben = maxleben;
        }
    }
}
