package ceta.game.screens;

import ceta.game.game.controllers.Level1VerticalController;
import ceta.game.game.renderers.WorldRenderer1VerticalMoving;
import ceta.game.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by ewe on 1/17/17.
 */

public class Level1VerticalMovingScreen extends AbstractGameScreen {
    private static final String TAG = Level1VerticalScreen.class.getName();
    private boolean paused;

    public Level1VerticalMovingScreen(DirectedGame game, int levelNr,TextureAtlas.AtlasRegion  regtex) {

        super(game,levelNr,regtex);


    }

    @Override
    public void render(float deltaTime) {
        // Do not update game world when paused.
        if (!paused) {
            worldController.update(deltaTime);
        }
        // Render game world to screen
        ((WorldRenderer1VerticalMoving)worldRenderer).updateMovingBackground(deltaTime);
        worldRenderer.render(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void show() {
        Gdx.app.log(TAG," we start the SHOW! "+Gdx.graphics.getWidth());
        stage = new Stage(new FitViewport(Constants.VIEWPORT_WIDTH , Constants.VIEWPORT_HEIGHT));
        worldController = new Level1VerticalController(game, stage,levelJson);
        worldRenderer = new WorldRenderer1VerticalMoving(worldController,stage,false,regTex);
        // android back key
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG," we start the HIDE of the screen ! " +Gdx.graphics.getWidth()+" h "+Gdx.graphics.getHeight());

//        worldController.dispose();
//        worldRenderer.dispose();
//        stage.dispose();
        // Gdx.input.setCatchBackKey(false);
        dispose();
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG," we start the PAUSE!");
        paused =true;
    }

    @Override
    public void resume () {
        Gdx.app.log(TAG," we start the RESUME!");
        super.resume();
        // Only called on Android!
        paused = false;
    }

    @Override
    public void dispose(){
        Gdx.app.log(TAG," we start the DISPOSE!");
        worldController.dispose();
        worldRenderer.dispose();
        stage.dispose();

    }


    @Override
    public InputProcessor getInputProcessor() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(worldController);
        return multiplexer;
    }

}

