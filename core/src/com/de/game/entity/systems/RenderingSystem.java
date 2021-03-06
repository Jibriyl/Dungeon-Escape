package com.de.game.entity.systems;

import com.de.game.entity.components.TextureComponent;
import com.de.game.entity.components.TransformComponent;
import com.de.game.entity.components.TypeComponent;
import com.de.game.entity.components.B2dBodyComponent;
import com.de.game.entity.components.StatComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

//import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    static final float PPM = 10.0f; // sets the amount of pixels each metre of box2d objects contains

    // this gets the height and width of our camera frustrum based off the width and height of the screen and our pixel per meter ratio
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth()/PPM;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight()/PPM;

    public static final float PIXELS_TO_METRES = 1.0f / PPM; // get the ratio for converting pixels to metres

    // static method to get screen width in metres
    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();
    public static Vector2 getScreenSizeInMeters(){
        meterDimensions.set(Gdx.graphics.getWidth()*PIXELS_TO_METRES, Gdx.graphics.getHeight()*PIXELS_TO_METRES);
        return meterDimensions;
    }

    // static method to get screen size in pixels
    public static Vector2 getScreenSizeInPixesl(){
        pixelDimensions.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return pixelDimensions;
    }

    // convenience method to convert pixels to meters
    public static float PixelsToMeters(float pixelValue){
        return pixelValue * PIXELS_TO_METRES;
    }

    private SpriteBatch batch;
    private Array<Entity> renderQueue; //Sortiert alle ENtitys in ein array ein
    //private Comparator<Entity> comparator; //
    private OrthographicCamera cam; //Startet die Camera

    //Initialisiert alle ComponentMapper
    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<TransformComponent> transformM;
    private ComponentMapper<B2dBodyComponent> bodyM;
    private ComponentMapper<StatComponent> statm;
    private ComponentMapper<TypeComponent> tm;

    //Initialisiert die backgroud textur und anderer Variablen
    private Texture background;
    private BitmapFont font;
    private int leben;

	public RenderingSystem(SpriteBatch batch) {
        //Sorgt daf??r das nur entitys mit TransofmComponent und TextureComponent genutzt werden
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());
        font = new BitmapFont();
        //Setzt die scale der Font
        font.getData().setScale(0.2f);
        //creates out componentMappers
        textureM = ComponentMapper.getFor(TextureComponent.class);
        transformM = ComponentMapper.getFor(TransformComponent.class);
        bodyM = ComponentMapper.getFor(B2dBodyComponent.class);
        statm = ComponentMapper.getFor(StatComponent.class);
        tm = ComponentMapper.getFor(TypeComponent.class);

        //Deklarieren der Lebensvariablen
        leben = 0;

        //Erstellt Array mit allen Entitys
        renderQueue = new Array<Entity>();
     
        this.batch = batch;  //Setzt den batch f??r diese methode dem batch des games

        //Setzt die Kamera so das die zur Bildschirmgr????e passt
        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH / 2f, FRUSTUM_HEIGHT / 2f, 0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        //W??rde die liste der Texturen sortieren funktioniert aber mit den gegner texturen nicht und sorgt f??r game crash
        //renderQueue.sort(comparator);
        
        //Updaten der Kamera
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        batch.draw(background, 0, 0, 192, 108);

        //loop der jede entity der queue durchgeht
        for (Entity entity : renderQueue) {
            TextureComponent tex = textureM.get(entity);
            TransformComponent t = transformM.get(entity);
            B2dBodyComponent body = bodyM.get(entity);
            TypeComponent type = tm.get(entity);
            if (type.getType() == TypeComponent.PLAYER){
                //Jedes mal wenn die spieler entity abgefragt wird. wird die hp von diesem ermittelt
                StatComponent stats = statm.get(entity);
                leben = stats.getLeben();
            }

            //Lebensanzeige des Spielers
            font.draw(batch, "HP:  " + leben, 3, 100);
            //Sollte keine Textur vorhanden sein oder die Entity hidden sein wird sie nicht angezeigt
            if (tex.getRegion() == null || t.getisHidden()) {
                continue;
            }
            //Plaziert die Textur an der stelle wo sich auch der body der entity befindet
            batch.draw(tex.getRegion(), (t.position.x - (body.getWidth()/2)), (t.position.y - (body.getHeight()/2)), body.getWidth(), body.getHeight());
        }

        batch.end();
        renderQueue.clear();
    }

    //Updated jeden tick alle texturen
    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
    //Methode um aus andern klassen auf die Kamera zuzugreifen
    public OrthographicCamera getCamera() {
        return cam;
    }
    //Methode um den background des levels zu setzten
    public void setBackground(Texture background){
        this.background = background;
    }
}