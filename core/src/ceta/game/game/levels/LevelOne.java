package ceta.game.game.levels;

import ceta.game.game.levels.Level;
import ceta.game.game.objects.Bruno;
import ceta.game.game.objects.Latter;
import ceta.game.game.objects.LatterDouble;
import ceta.game.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by ewe on 7/25/16.
 */
public class LevelOne extends Level {
    public static final String TAG = Level.class.getName();
    public Bruno bruno;
    public Latter latter;
    public LatterDouble latterDouble;

    public LevelOne(){
        init();
    };

    private void init(){
        bruno = new Bruno();
        bruno.position.set(-Constants.VIEWPORT_WIDTH/2 + Constants.OFFSET_X ,Constants.GROUND_Y);

        latter = new Latter();
        latter.position.set(bruno.position.x, Constants.GROUND_Y - latter.dimension.y);

        latterDouble = new LatterDouble();
        latterDouble.position.set(bruno.position.x , Constants.GROUND_Y - latterDouble.dimension.y);

    }

    public void update (float deltaTime) {
        bruno.update(deltaTime);
        latter.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        //drawFeedback(batch);

        drawScene(batch);
    }


    public void drawScene(SpriteBatch batch){

        bruno.render(batch);
        latter.render(batch);
        latterDouble.render(batch);
    }

    public void drawFeedback(SpriteBatch batch){

    }

}