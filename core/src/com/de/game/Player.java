package com.de.game;

public class Player {
    private int damage;
    private int leben;
    private int speed;
    private int ruestung;
    public Player(int damage, int leben, int speed, int ruestung){
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
    
}
