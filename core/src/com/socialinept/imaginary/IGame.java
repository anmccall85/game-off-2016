package com.socialinept.imaginary;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.socialinept.imaginary.screens.BattleScreen;

import java.awt.*;

public class IGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		BattleScreen screen = new BattleScreen();
		screen.setIGame(this);
		screen.initialize();
		setScreen(screen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

	public Dimension getScreenDimensions(){
		return new Dimension(800, 400);
	}

	public int getScreenPPM(){
		return 1;
	}
}
