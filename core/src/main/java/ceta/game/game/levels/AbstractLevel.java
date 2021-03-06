package ceta.game.game.levels;

import ceta.game.game.controllers.AbstractWorldController;
import ceta.game.game.objects.Bruno;
import ceta.game.game.objects.Cloud;
import ceta.game.game.objects.Price;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 * Created by ewe on 7/25/16.
 */
public abstract class AbstractLevel {
    public Bruno bruno;
    public Price price;

    protected Stage stage;
    protected LevelParams levelParams;
    protected AbstractWorldController worldController;

    public AbstractLevel(Stage stage, LevelParams levelParams, AbstractWorldController worldController){
        this.stage = stage;
        this.levelParams = levelParams;
        this.worldController = worldController;
        init();
    }


    public abstract void init ();

    public void render(SpriteBatch batch) {
        price.toFront();
        bruno.toFront();

        stage.draw();
    }

    public void update(float deltaTime) {
        price.update(deltaTime);
        stage.act(deltaTime);
    }

    public void addClouds(int howMany){
        for(int i = 0;i<howMany;i++){
            stage.addActor(new Cloud());
        }

    }




}
