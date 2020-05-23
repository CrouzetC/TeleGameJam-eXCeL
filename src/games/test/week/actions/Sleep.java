package games.test.week.actions;

import java.awt.Font;

import org.newdawn.slick.*;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;


public class Sleep implements ActionEvent {

    Font awtFont;
    TrueTypeFont font;
    boolean isOver;

    public Sleep() {
        awtFont = new Font("vt323", java.awt.Font.BOLD, 12);
        font = new TrueTypeFont(awtFont, true);
        isOver = false;
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {

    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {

        font.drawString(20, 20, "You are sleeping", Color.red);

    }

    public void keyPressed(int key, char c) {
        if (c=='a')
            isOver = true;
    }

    public void mousePressed(int button, int x, int y) {
        isOver = true;
    }

    public boolean isOver() {
        return isOver;
    }

}
