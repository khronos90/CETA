package ceta.game.game;

import ceta.game.game.controllers.AbstractWorldController;
import ceta.game.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by ewe on 7/25/16.
 */
public class WorldRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private BitmapFont counterFont;
    private AbstractWorldController worldController;
    private Stage stage;
    private Image imgBackground;


    private short linesRange;

    public WorldRenderer (AbstractWorldController worldController, Stage stage) {
        this.stage = stage;
        this.worldController = worldController;
        init();
    }
    private void init () {
        batch = new SpriteBatch();

        //font = new BitmapFont();
        font = Assets.instance.fonts.defaultSmall;
        counterFont = Assets.instance.fonts.defaultBig;

        shapeRenderer = new ShapeRenderer();
        linesRange = (short)(Constants.VIEWPORT_HEIGHT/Constants.BASE/2)*Constants.BASE;

        //normal
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.setToOrtho(false,Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT);
        camera.position.set(0,0,0);
        camera.update();
        stage.getViewport().setCamera(camera);



//        cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
//        cameraGUI.position.set(0, 0, 0);
//        cameraGUI.setToOrtho(true); // flip y-axis
//        cameraGUI.update();
    }

    public void render () {
        renderBackground();
        renderWorld(batch);
        renderHelperNumberLines();
        renderHelperNumbers(batch);
        if (worldController.getCountdownOn()) {
            renderCounter(batch);
        }
        renderGui(batch);
        if(worldController.getWeWon()){
            renderWin();
            worldController.setWeWon(false);
            worldController.resetScore();
        }
        //renderHelperLines();
    }

    private void renderBackground(){
        // white
        Gdx.gl.glClearColor(1, 1, 1, 1);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // detection zone
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.rect(-Constants.VIEWPORT_WIDTH/2, Constants.DETECTION_LIMIT,Constants.VIEWPORT_WIDTH, -Constants.DETECTION_LIMIT);
        shapeRenderer.end();
    }

    private void renderCounter(SpriteBatch batch){

        // we render countdown!
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        String text = worldController.getCoutdownCurrentTime()+"";
        GlyphLayout layout = new GlyphLayout(counterFont, text);

        counterFont.setColor(Color.RED);
        counterFont.draw(batch, text+"", 0 - layout.width/2, Constants.DETECTION_LIMIT -100);


        batch.end();


    }



    private void renderWorld (SpriteBatch batch) {
        worldController.cameraHelper.applyTo(camera);

        // we draw coin on the top
        worldController.level.price.toFront();
        worldController.level.bruno.toFront();

        batch.begin();
        worldController.level.render(batch);
        batch.end();
    }
    private void renderGui (SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // draw collected gold coins icon + text
        // (anchored to top left edge)
        renderGuiScore(batch);
        batch.end();
    }

    private void renderHelperNumberLines(){
        Gdx.gl.glLineWidth(1);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);

        for(int i = -200; i<=200;i+=Constants.BASE){
            shapeRenderer.line(i , 0, i,Constants.VIEWPORT_HEIGHT/2);
        }
        shapeRenderer.setColor(1, 0, 0, 1);
        //horizontal
        shapeRenderer.line(-Constants.VIEWPORT_WIDTH/2, 0, Constants.VIEWPORT_WIDTH/2,0);
        //vertical
        //shapeRenderer.line(0 , -Constants.VIEWPORT_HEIGHT/2, 0,Constants.VIEWPORT_HEIGHT/2);
        // and detection limit in blue
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer.line(-Constants.VIEWPORT_WIDTH/2, Constants.DETECTION_LIMIT, Constants.VIEWPORT_WIDTH/2,Constants.DETECTION_LIMIT);

        shapeRenderer.end();
    }


    private void renderHelperLines(){
        Gdx.gl.glLineWidth(1);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);

        for(int i = -linesRange; i<=linesRange;i+=Constants.BASE){
            //shapeRenderer.line(i, 20,i,0);
            shapeRenderer.line(-Constants.VIEWPORT_WIDTH/2 , i, Constants.VIEWPORT_WIDTH/2,i);
            shapeRenderer.line(i , -Constants.VIEWPORT_HEIGHT/2, i,Constants.VIEWPORT_HEIGHT/2);
        }
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.line(-Constants.VIEWPORT_WIDTH/2, 0, Constants.VIEWPORT_WIDTH/2,0);
        shapeRenderer.line(0 , -Constants.VIEWPORT_HEIGHT/2, 0,Constants.VIEWPORT_HEIGHT/2);
        // and detection limit
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer.line(-Constants.VIEWPORT_WIDTH/2, Constants.DETECTION_LIMIT, Constants.VIEWPORT_WIDTH/2,Constants.DETECTION_LIMIT);

        shapeRenderer.end();
    }
    private void renderHelperNumbers(SpriteBatch batch){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        int counter  = 0;
        for(int i = -200; i<240;i+=Constants.BASE){
            String text = counter+"";
            GlyphLayout layout = new GlyphLayout(font, text);
            //font.draw(batch, counter+"", i - layout.width/2, 0);
            font.draw(batch, counter+"", i, 0,0,Align.center,false);

            counter+=1;
        }
        batch.end();

    }

    private void renderGuiScore (SpriteBatch batch) {
        float x =  -Constants.VIEWPORT_WIDTH/2 + 10;
        float y = Constants.VIEWPORT_HEIGHT/2 - 50;

        batch.draw(Assets.instance.toCollect.screw,x,y,40,40);
        // TODO hardcoded position!
        font.draw(batch, worldController.score+"",x+50,y+30);
    }

    public void resize (int width, int height) {
//        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
//        camera.update();
//        cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
//        cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float)height) * (float)width;
//        cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
//        cameraGUI.update();

        // called from TestScreen on resize
        stage.getViewport().update(width, height, true);
        camera.position.set(0,0,0);
    }

    private void renderWin(){
        imgBackground = new Image(Assets.instance.finishBackGround.finishBack);
        stage.addActor(imgBackground);
        imgBackground.setOrigin(imgBackground.getWidth() / 2, imgBackground.getHeight() / 2);
        imgBackground.setPosition(-Constants.VIEWPORT_WIDTH/2,-400);
        imgBackground.addAction(sequence(
//                moveTo(135, -20),
                scaleTo(0, 0),
//                fadeOut(0),
                delay(0.5f),
                parallel(moveBy(0, 100, 1.5f, Interpolation.swingOut),
                        scaleTo(1.0f, 1.0f, 0.25f, Interpolation.linear),
                        alpha(1.0f, 0.5f)),
                alpha(0,1f),
                removeActor()
        ));
    }


    @Override public void dispose () {
        batch.dispose();
    }
}
