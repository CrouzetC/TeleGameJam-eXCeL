package games.test.week.actions;

import org.newdawn.slick.*;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import app.AppFont;
import app.AppLoader;

public class Sleep implements ActionEvent {

    Font awtFont;
    TrueTypeFont font;
    boolean isOver;
    Image image;

    public Sleep() {
        font = AppLoader.loadFont("/fonts/vt323.ttf", AppFont.BOLD, 12);
        isOver = false;
        try {
            image = new Image("images/dialogue/sleeping.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {

    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {

        image.draw(0, 0, container.getWidth(), container.getHeight());
        font.drawString(20, 20, "You are sleeping...", Color.red);

    }

    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER)
            isOver = true;
    }

    public void mousePressed(int button, int x, int y) {
        isOver = true;
    }

    public boolean isOver() {
        return isOver;
    }

}
