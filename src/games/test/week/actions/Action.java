package games.test.week.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public interface Action {

    public void update(GameContainer container, StateBasedGame game, int delta);

    public void render(GameContainer container, StateBasedGame game, Graphics context);

    public void keyPressed(int key, char c);

    public void mousePressed(int button, int x, int y);

    boolean isOver(); // pour savoir si une action est terminée

}
