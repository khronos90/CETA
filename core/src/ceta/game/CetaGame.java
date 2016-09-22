package ceta.game;

import ceta.game.game.Assets;
import ceta.game.screens.DirectedGame;
import ceta.game.screens.Level1Screen;
import ceta.game.screens.MenuScreen;
import ceta.game.screens.TestScreen;
import ceta.game.transitions.ScreenTransition;
import ceta.game.transitions.ScreenTransitionFade;
import ceta.game.util.AudioManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class CetaGame extends DirectedGame {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		// Set Libgdx log level
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Load assets
		Assets.instance.init(new AssetManager());
		// load music
		AudioManager.instance.play(Assets.instance.music.song01);
		ScreenTransition transition = ScreenTransitionFade.init(1);
		//setScreen(new MenuScreen(this),transition);
		//setScreen(new TestScreen(this),transition);
		setScreen(new Level1Screen(this),transition);
	}

}
