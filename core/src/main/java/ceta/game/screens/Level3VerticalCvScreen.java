package ceta.game.screens;

import ceta.game.game.controllers.Level3VerticalController;
import ceta.game.game.controllers.Level3VerticalCvController;
import ceta.game.game.renderers.WorldRenderer;
import ceta.game.game.renderers.WorldRendererCV;
import ceta.game.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by ewe on 12/5/16.
 */
public class Level3VerticalCvScreen extends Level1VerticalScreen {
    private static final String TAG = Level3VerticalCvScreen.class.getName();

    public Level3VerticalCvScreen(DirectedGame game, int levelNr) {
        super(game, levelNr);
    }

    @Override
    public void show() {
        Gdx.app.log(TAG," we start the SHOW! "+Gdx.graphics.getWidth());
        // TODO load preferences
        stage = new Stage(new FitViewport(Constants.VIEWPORT_WIDTH , Constants.VIEWPORT_HEIGHT));
        worldController = new Level3VerticalCvController(game,stage,levelJson);
        //worldController = new Level1HorizontalController(game, stage);
        // Todo here we should make camera stuff and fitviewport
        worldRenderer = new WorldRendererCV(worldController,stage,false);
        // android back key
        Gdx.input.setCatchBackKey(true);
    }
}
