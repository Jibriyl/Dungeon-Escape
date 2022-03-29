package com.de.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.de.game.Main;
import com.de.game.inputs.KeyboardController;

public class GAME_OVER extends ScreenAdapter{
    
    Main game;
    private Texture gameoverbackground;
    private Texture gameover;
    private Texture gameoverretry;
    private Texture gameoverhauptmenu;
    private Texture gameoverrahmen;
    private Texture gameoverleave;
    private int auswahl;
    private KeyboardController controller;
    private float cd;
    private String chartype;

    

    public GAME_OVER (Main game, String chartype){
        this.game = game;
        controller = new KeyboardController();
        //Speichert den ausgewählten charakter typ für die retry funktion
        this.chartype = chartype;
        //Laden der Assets
        gameoverbackground = game.assetManager.manager.get("Input/game/Gameoverbackground.png");
        gameover = game.assetManager.manager.get("Input/game/Gameover.png");
        gameoverretry = game.assetManager.manager.get("Input/game/gameoverneustart.png");
        gameoverhauptmenu = game.assetManager.manager.get("Input/game/gameoverhauptmenu.png");
        gameoverrahmen = game.assetManager.manager.get("Input/game/gameoverrahmen.png");       
        gameoverleave = game.assetManager.manager.get("Input/game/gameoverbeenden.png");

        //Speichert den Cooldown damit der Button nicht einmal pro tick geändert werden kann
        cd = 0;
        //Speichert welcher Button aktuell ausgewählt ist
        auswahl = 0;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta){
        //Vorbereitung des ladens der Assets
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        ScreenUtils.clear(0, 0, 0, 1);

        //Screenlogic
        if (cd <= 0){
            //Entscheidet darüber welcher Button ausgewählt ist, kann mit Up, Down geändert werden
            //Die auswahl des Buttons wird in dern auwahl variable gespeichert, anhand weöcher button ausgewähl ist wird enschieden um welchen der rahmen abgebildet werden muss
            if (controller.up){
                auswahl -= 1;
                cd = 0.2f;
            }
            if (controller.down){
                auswahl +=1;
                cd = 0.2f;
            }
            if (auswahl < 0){
                auswahl = 0;
            }
            if (auswahl > 2){
                auswahl = 2;
            }
        }
        else {
            //Reduziert die zeit für den Button cooldown
            cd -= delta;
        }
        //Laden der Assets
        game.batch.draw(gameoverbackground, 0, 0, 1920, 1080);

        game.batch.draw(gameover, 760, 840, 400, 140);
        game.batch.draw(gameoverretry, 698, 640, 524, 133);
        game.batch.draw(gameoverhauptmenu, 698, 440, 524, 133);
        game.batch.draw(gameoverleave, 698, 240, 524, 133);

        if (auswahl == 0){
            game.batch.draw(gameoverrahmen, 664, 610, 592, 193);
        }
        else if (auswahl == 1){
            game.batch.draw(gameoverrahmen, 664, 410, 592, 193);
        }
        else if (auswahl == 2){
            game.batch.draw(gameoverrahmen, 664, 210, 592, 193);
        }

        game.batch.end();

        //Wenn Enter gedrückt wird, wird anhand der auswahlt entschieden welche aktion ausgeführt wird
        if (controller.enter){
            if (auswahl == 0){
                //Retry game
                game.screenset("LVL_1", chartype);
            }
            else if (auswahl == 1){
                //Zurück zum hauptmenu
                game.screenset("MAIN_MENU", chartype);
            }
            else if (auswahl == 2){
                //Spiel verlassen
                System.exit(0);
            }
        }
    }
}
