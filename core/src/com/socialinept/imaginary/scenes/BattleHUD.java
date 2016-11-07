package com.socialinept.imaginary.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.socialinept.imaginary.IGame;
import com.socialinept.imaginary.battle.Battle;
import com.socialinept.imaginary.battle.BattleAddEntityListener;
import com.socialinept.imaginary.battle.Entity;

import java.awt.*;

/**
 * Created by lild227 on 11/5/2016.
 */
public class BattleHUD implements Disposable, BattleAddEntityListener {

    //Scene2D.ui Stage and its own Viewport for HUD
    public IGame game;
    public Stage stage;
    public Battle battle;
    private Viewport viewport;
    private SpriteBatch spriteBatch;

    //Mario score/time Tracking Variables
    private Integer worldTimer;
    private boolean timeUp; // true when the world timer reaches 0
    private float timeCount;
    private static Integer score;

    //Scene2D widgets
    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;

    public BattleHUD(IGame game, SpriteBatch sb){
        this.game = game;
        this.spriteBatch = sb;
    }

    public void initialize(){
        //define our tracking variables
        worldTimer = 300;
        timeCount = 0;
        score = 0;


        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(game.getScreenDimensions().width, game.getScreenDimensions().height, new OrthographicCamera());
        stage = new Stage(viewport, this.spriteBatch);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //make the table fill the entire stage
        table.setFillParent(true);
        //Top-Align table
        table.left();
        table.top();
        for(Entity e: battle.getEntities()){
            countdownLabel = new Label(e.name, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            //add a second row to our table
            table.row();
            table.add(countdownLabel);

        }

        //add our table to the stage
        stage.addActor(table);

    }

    public void setBattle(Battle b){
        battle = b;
    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
            }
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() { stage.dispose(); }

    public boolean isTimeUp() { return timeUp; }

    @Override
    public void onAddEntity(Entity e) {

    }
}