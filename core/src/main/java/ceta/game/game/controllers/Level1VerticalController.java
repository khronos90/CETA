package ceta.game.game.controllers;

import ceta.game.game.levels.Level1Vertical;
import ceta.game.game.objects.BrunoVertical;
import ceta.game.managers.BrunosManager;
import ceta.game.screens.DirectedGame;
import ceta.game.util.Constants;
import ceta.game.util.GamePreferences;
import ceta.game.managers.VirtualBlocksManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by ewe on 10/19/16.
 */
public class Level1VerticalController extends NoCvController {
    private static final String TAG = Level1VerticalController.class.getName();
//    protected VirtualBlocksManager virtualBlocksManager;
    protected BrunosManager brunosManager;


    public Level1VerticalController(DirectedGame game, Stage stage, int levelNr) {
        super(game, stage, levelNr);
    }

    @Override
    protected void localInit () {
        Gdx.app.log(TAG," local init with last level: "+GamePreferences.instance.lastLevel);
        level = new Level1Vertical(stage, levelParams, this);
        virtualBlocksManager = new VirtualBlocksManager(stage);
        brunosManager = new BrunosManager(stage);

        score = 0;
        virtualBlocksManager.init();
        brunosManager.init();

    }

    @Override
    public void update(float delta){
        super.update(delta);
        virtualBlocksManager.adaptXposition(-180);
    }

    @Override
    protected void updateDigitalRepresentations() {
        brunosManager.update(virtualBlocksManager.getNewDetected(), virtualBlocksManager.getToRemove());
    }


    @Override
    protected void countdownMove() {
        ((Level1Vertical)(level)).getTube().shake();
    }

    @Override
    protected void testCollisionsInController (boolean isDynamic) {
      //  Gdx.app.log(TAG," testCollisionsInController ");
        BrunoVertical lastBruno = getLastBruno();
        if (!brunosManager.isUpdatingBrunosPositions()) { // we have to be sure that the move finished
            // we set 4px x 4px box at the middle end (X), in the top (Y)
            if(isDynamic)
                testCollisionsVerticalDynamic(lastBruno);
            else
                testCollisionsVerticalStatic(lastBruno);
        }
    }


    @Override
    protected void testCollisionsVerticalDynamic(BrunoVertical objectToCheck){
        if(objectToCheck != null ) {
            if ((level.price.getX() < 190) //not very right
                    && level.price.getX() > objectToCheck.getX()) {
                r1.set(objectToCheck.getX(),
                        objectToCheck.getY() + objectToCheck.getHeight() - 4, // two pixels below the middle
                        objectToCheck.getWidth(), 4);
                r2.set(-Constants.VIEWPORT_WIDTH/2,
                        level.price.getY(),
                        Constants.VIEWPORT_WIDTH, level.price.getHeight() / 2);

                if (r1.overlaps(r2)) {
                    onCollisionBrunoWithPriceOpenMouth(level.price, objectToCheck);
                    moveMade = false;
                }
            }
        }
    }


    public BrunoVertical getLastBruno(){
        return brunosManager.getLastBruno();
    }





}
