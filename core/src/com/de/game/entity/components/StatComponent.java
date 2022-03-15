package com.de.game.entity.components;

import com.badlogic.ashley.core.Component;

public class StatComponent implements Component{
    private int damage;
    private int leben;
    private int speed;
    private int ruestung;
    public void setStats(int damage, int leben, int speed, int ruestung){
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
    public boolean nehmeschaden(int schaden){
        leben -= schaden;
        if (leben <= 0){
            return false;
        }
        else{
            return true;
        }
    }

}
