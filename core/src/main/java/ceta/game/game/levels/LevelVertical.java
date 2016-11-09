package ceta.game.game.levels;

import ceta.game.game.objects.Bruno;
import ceta.game.game.objects.Price;
import ceta.game.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by ewe on 11/7/16.
 */
public class LevelVertical extends LevelHorizontal {
    public static final String TAG = LevelVertical.class.getName();


    public LevelVertical(Stage stage, int level){
        super(stage,level);

    };

    @Override
    public void init() {
        Gdx.app.log(TAG,"init Level Vertical");
        initLevelParams();

        bruno = new Bruno();
        bruno.setSize(0,0);
       // bruno.setPosition(-Constants.VIEWPORT_WIDTH/2 + Constants.OFFSET_X , -bruno.getHeight());
        // change default horizontal to horizontal moving by first param "false"
        price = new Price(false,(short)levelParams.priceVelocity,(short)levelParams.numberMin, levelParams.priceReturn);

        // add actors
        //stage.addActor(bruno);
        stage.addActor(price);
    }
}
