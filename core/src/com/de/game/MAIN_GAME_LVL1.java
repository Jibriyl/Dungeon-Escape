package com.de.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.de.game.controller.KeyboardController;

public class MAIN_GAME_LVL1 extends ScreenAdapter{

    Main game;
    private LVL1_Model model;
    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;
    private KeyboardController controller;

    private Texture lvl1background;

    private SpriteBatch sb;

    private Texture playerTex;
    private float zeit;
    private float zeit2;
    private String fpsanzeige = "0";

    public MAIN_GAME_LVL1 (Main game){
        this.game = game;
        controller = new KeyboardController();
        model = new LVL1_Model(controller);
        cam = new OrthographicCamera(192,108);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
        sb = new SpriteBatch();
        sb.setProjectionMatrix(cam.combined);

        game.assetManager.queueAddImages();
        game.assetManager.manager.finishLoading();

        lvl1background = game.assetManager.manager.get("lvl1background.png");
        playerTex = game.assetManager.manager.get("player1.png");
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta){
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(lvl1background, -96, -54, 192, 108);
        //Abschnitt der jede sekunde einmal ausgeführt wird
        zeit = zeit + delta;
        if(zeit <= 0.125){
            sb.draw(playerTex, model.playerbody.getPosition().x - 3.5f, model.playerbody.getPosition().y - 8, 7, 16);
        }
        else if(zeit >= 0.125f){
            if(controller.up){

            }
            else if(controller.down){
    
            }
            else if(controller.left){
    
            }
            else if(controller.right){
    
            }
            else{
                sb.draw(playerTex, model.playerbody.getPosition().x - 3.5f, model.playerbody.getPosition().y - 8, 7, 16);
            }
        }
        if(zeit >= 0.25f){
            zeit = 0;
        }
        //Wird alle 0,125 sek ausführt, ist für den wechsel der grafiken beim laufen, und FPS anzeige
        zeit2 = zeit2 + delta;
        if(zeit2 >= 0.125f){
            fpsanzeige = "" + 1/delta;
            zeit2 = 0;
        }
        


        game.font.draw(sb, fpsanzeige, 50, 50); //FPS anzeige, muss noch gefixt werden
        sb.end();
        debugRenderer.render(model.world, cam.combined);


    }
}
