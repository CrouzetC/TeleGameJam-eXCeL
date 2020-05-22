package games.test.week;


import games.test.data.GameData;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import app.AppLoader;

public class ActionMenu {

    private GameData data;
    private Week week;

// Rendering :
    private Image background;
    private Image clubs;
    private Image sleep;
    private Image socialize;
    private Image study;
    private Image dateTime;


    public ActionMenu(GameData data) {

    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public void init() {
        try {
            this.background =  AppLoader.loadPicture ("/images/home.png");
        }catch(Exception e){
            System.out.printf("PROBLEM !");
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {
        if(this.background != null){
            this.background.draw();
        }

    }

    public void keyPressed(int key, char c) {

    }

    public void mousePressed(int button, int x, int y) {

    }

}
