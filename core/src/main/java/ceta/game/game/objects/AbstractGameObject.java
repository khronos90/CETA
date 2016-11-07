package ceta.game.game.objects;

import ceta.game.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.physics.box2d.Body;


/**
 * Created by ewe on 7/25/16.
 */
public abstract class AbstractGameObject extends Actor {
    public static final String TAG = AbstractGameObject.class.getName();

    public Rectangle bounds;
    public TextureRegion regTex;
    public float rotation;

    public Animation animation;
    public float stateTime;
    public float terminalX;


    public AbstractGameObject () {
        bounds = new Rectangle();

    }

    public void init(){
        // Center image on game object
        this.setOrigin(this.getWidth()/2, this.getHeight()/ 2);
        //Gdx.app.log(TAG,this.getWidth()+" "+this.getHeight());
        this.setScale(1,1);
        rotation = 0;
        this.setRotation(0);
        // Bounding box for collision detection!!
        bounds.set(0, 0, this.getWidth(), this.getHeight());
        this.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        this.setDebug(false);


    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.setProjectionMatrix(camera.combined);
       // batch.draw(regTex,this.getX(),this.getY());
        batch.setColor(this.getColor());
        batch.draw(regTex.getTexture(), this.getX(), this.getY(),
                this.getOriginX(), this.getOriginY(),
                this.getWidth() ,this.getHeight(),
                this.getScaleX(), this.getScaleY(),
                this.getRotation(),
                regTex.getRegionX(), regTex.getRegionY(),
                regTex.getRegionWidth(), regTex.getRegionHeight(), false,false);
        batch.setColor(1,1,1,1);
    }

    public void setAnimation (Animation animation) {
        this.animation = animation;
        stateTime = 0;
    }

    @Override
    public void act(float delta) {

        stateTime += delta;
        super.act(delta);
    }

    public float getTerminalX(){
        return terminalX;
    }

    public void setTerminalX(float newTerminalX){
        terminalX = newTerminalX;
    }





}
