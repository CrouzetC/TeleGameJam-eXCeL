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
    private float scale;
    private Image background;
    private Image clubs;
    private Image sleep;
    private Image socialize;
    private Image study;
    private Image dateTime;
    private Image select;
    private Image theWeek;

    public ActionMenu(GameData data) {
        initImages();
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public void initImages() {

        try {
            this.background =  new Image("images/home.png");
            this.clubs = new Image("images/clubs.png");
            this.sleep = new Image("images/sleep.png");
            this.socialize = new Image("images/socialize.png");
            this.study = new Image("images/study.png");
            this.dateTime = new Image("images/dateTime.png");
            this.select = new Image("images/select.png");
            this.theWeek = new Image("images/theWeek.png");

        }catch(Exception e){
            System.out.printf("PROBLEM !");
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {
        if(this.background != null){

            float width = container.getWidth();
            float height = container.getHeight();

            float b = this.background.getWidth();
            this.scale = width/b ;
            this.background.draw(0,0,this.scale);

            float smallScale = scale/2;
            int h = (int) height*2/3;
            int step = (int) width/100;
            int w2 = (int) (study.getWidth()*smallScale);
            int h2 = (int) (study.getHeight()*smallScale);
            int w = (int) (width - w2*3 - 2*step) /2;

            this.clubs.draw(w,h,smallScale);
            this.socialize.draw(w+w2+step,h,smallScale);
            this.study.draw(w+2*w2+2*step,h,smallScale);
            this.sleep.draw(w,h+h2+step,smallScale);
            this.dateTime.draw(w+w2+step,h+h2+step,smallScale);

            int midW =(int)(width-theWeek.getWidth()*scale)/2;
            int midH =(int)(height-theWeek.getHeight()*scale)/2;
            int step2 = (int) (theWeek.getWidth()*scale)/7;
            int midW2 = (int) (midW + step2/2 - (select.getWidth()*smallScale)/2);
            this.theWeek.draw(midW, midH, scale);

            int i = 1; // on a sélectionné le deuxième jour ici
            this.select.draw(midW2+i*step2,midH-select.getHeight()*smallScale,smallScale);

        }


    }

    public void keyPressed(int key, char c) {

    }

    public void mousePressed(int button, int x, int y) {

    }

}
