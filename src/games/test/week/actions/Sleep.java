package games.test.week.actions;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Sleep {

    Font font;
    TrueTypeFont trueTypeFont;

    public Sleep() {
        font = new Font("vt323", Font, 20);
        trueTypeFont = new TrueTypeFont(font, true);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {

    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {

        trueTypeFont.drawString(20, 20, "You are sleeping", Color.red);
    }

    public void keyPressed(int key, char c) {

    }

    public void mousePressed(int button, int x, int y) {

    }

}
