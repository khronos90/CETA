package ceta.game.game.renderers;

import ceta.game.game.Assets;
import ceta.game.game.controllers.AbstractWorldController;
import ceta.game.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

/**
 * Created by ewe on 11/25/16.
 */
public class WorldRendererCV extends WorldRenderer {
    public static final String TAG = WorldRendererCV.class.getName();
   // private FeedbackRenderer feedbackRenderer;


    public WorldRendererCV(AbstractWorldController worldController, Stage stage, boolean numberLineIsHorizontal ,TextureAtlas.AtlasRegion imgBackground) {
        super(worldController, stage, numberLineIsHorizontal, imgBackground);
    }

//    public WorldRendererCV(AbstractWorldController worldController, Stage stage) {
//        this(worldController,stage,true, Assets.instance.staticBackground.tubes5); //default horizontal number line
//
//    }
//
//    public WorldRendererCV(AbstractWorldController worldController, Stage stage,boolean numberLineIsHorizontal ) {
//        this(worldController,stage,numberLineIsHorizontal, Assets.instance.staticBackground.tubes5); //default horizontal number line
//
//    }

    public WorldRendererCV(AbstractWorldController worldController, Stage stage,TextureAtlas.AtlasRegion imgBackground) {
        this(worldController,stage,true,imgBackground); //default horizontal number line

    }

    @Override
    public void render (float delta) {
        super.render(delta);
//        if(worldController.isPlayerInactive() ){
//            if (!feedbackRenderer.getManoImg().hasActions()) {
//                feedbackRenderer.renderClue();
//                shouldRenderClue = true;
//            }
//        }else{
//            // if we didn't notify yet
//            if(shouldRenderClue){
//                stopRenderClue();
//                shouldRenderClue = false;
//            }
//        }

    }



//
//    @Override
//    protected void renderDetectionZone(ShapeRenderer shRenderer){
//        // detection zone in gray
//        Gdx.gl.glEnable(GL20.GL_BLEND);
//        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//        shRenderer.setProjectionMatrix(camera.combined);
//        shRenderer.begin(ShapeRenderer.ShapeType.Filled);
//
//        shRenderer.setColor(Color.LIGHT_GRAY);
//        //shapeRenderer.rect(x, y, width, height);
//        shRenderer.rect(-Constants.CV_DETECTION_EDGE_TABLET/2, -Constants.VIEWPORT_HEIGHT/2,
//                Constants.CV_DETECTION_EDGE_TABLET, Constants.CV_DETECTION_EDGE_TABLET);
//
////        if(worldController.isPlayerInactive()){
////            feedbackRenderer.renderColorChange(shRenderer);
////        }else{
////            feedbackRenderer.resetColorChange();
////        }
//        shRenderer.end();
//        Gdx.gl.glDisable(GL20.GL_BLEND);
//    }
//
//    @Override
//    protected void renderBackgroundImg(SpriteBatch batch) {
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//        batch.draw(imgBackground.getTexture(), -Constants.VIEWPORT_WIDTH / 2, Constants.GROUND_LEVEL,
//                b.getRegionWidth() / 2, b.getRegionHeight() / 2,
//                b.getRegionWidth(), b.getRegionHeight(),
//                1, 1,
//                0,
//                b.getRegionX(), b.getRegionY(),
//                b.getRegionWidth(), b.getRegionHeight(), false, false);
//        batch.end();
//    }

    @Override
    protected void renderDetectionZoneImg(SpriteBatch batch){

        TextureAtlas.AtlasRegion b = Assets.instance.background.feedbackZoneCV;
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(b.getTexture(),
                -Constants.CV_DETECTION_EDGE_TABLET/2, -Constants.VIEWPORT_HEIGHT/2,
                b.getRegionWidth()/2, b.getRegionHeight()/2,
                Constants.CV_DETECTION_EDGE_TABLET, Constants.CV_DETECTION_EDGE_TABLET,
                1, 1,
                0,
                b.getRegionX(), b.getRegionY(),
                b.getRegionWidth(), b.getRegionHeight(), false,false);
        batch.end();



    }



}
