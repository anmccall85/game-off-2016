package com.socialinept.imaginary.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.socialinept.imaginary.IGame;
import com.socialinept.imaginary.battle.Battle;
import com.socialinept.imaginary.battle.Entity;
import com.socialinept.imaginary.scenes.BattleHUD;

import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lild227 on 11/5/2016.
 */
public class BattleScreen implements Screen{

    IGame game = null;

    private Battle battle;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private BattleHUD hud;
    private SpriteBatch batch;
    private Texture texture;

    public void setIGame(IGame g){
        game = g;
    }

    public void initialize(){
        //Domain stuff
        battle = new Battle();

        //View stuff
        batch = new SpriteBatch();

        //create cam used to follow mario through cam world
        gamecam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        Dimension d = game.getScreenDimensions();
        gamePort = new FitViewport(d.width / game.getScreenPPM(), d.height / game.getScreenPPM(), gamecam);

        //create our game HUD for scores/timers/level info
        hud = new BattleHUD(game, batch);
        hud.setBattle(battle);
        hud.initialize();
        texture = new Texture(Gdx.files.internal("data/blue.png"));

        //create our game HUD for scores/timers/level info
        // hud = new Hud(batch);

        //Load our map and setup our map renderer
//        maploader = new TmxMapLoader();
//        map = maploader.load("level1.tmx");
//        renderer = new OrthogonalTiledMapRenderer(map, 1  / game.getScreenPPM());

        //initially set our gamcam to be centered correctly at the start of of map
        System.out.println(gamePort.getWorldWidth() / 2);
        System.out.println(gamePort.getWorldHeight() / 2);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    public void update(float dt) {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
//        renderer.render();

        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();

        for(Entity e: battle.getEntities()){
            Point p = battle.getEntityPosition(e);
            batch.draw(texture, p.x, p.y);
        }
//        player.draw(batch);
//        for (Enemy enemy : creator.getEnemies())
//            enemy.draw(batch);
//        for (Item item : items)
//            item.draw(batch);
        batch.end();

        //Set our batch to now draw what the Hud camera sees.
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //updated our game viewport
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //dispose of all our opened resources
        map.dispose();
        renderer.dispose();
    }
}
