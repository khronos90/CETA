package ceta.game.game.levels;

import ceta.game.game.controllers.AbstractWorldController;
import ceta.game.game.objects.Tube;
import ceta.game.game.objects.Price;
import ceta.game.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by ewe on 11/11/16.
 */
public class Level1Vertical extends AbstractLevel {
    public static final String TAG = Level1Vertical.class.getName();
    public Tube tube;
    private int adjustX;

    public Level1Vertical(Stage stage, LevelParams levelParams, AbstractWorldController worldController) {
        super(stage, levelParams, worldController);
    }

    @Override
    public void init() {
        adjustX = -40;
        Gdx.app.log(TAG,"init Level 1 Vertical");
        price = new Price(false, 1, levelParams, worldController);

        tube = new Tube();
        tube.setPosition(Constants.VERTICAL_MIDDLE_X-tube.getWidth()/2 + adjustX,Constants.GROUND_LEVEL -tube.getHeight());

        // add actors
        stage.addActor(price);
        stage.addActor(tube);

    }
    @Override
    public void render(SpriteBatch batch) {
        price.toBack();
        tube.toFront();
        stage.draw();
    }

    public Tube getTube(){
        return tube;
    }

}
