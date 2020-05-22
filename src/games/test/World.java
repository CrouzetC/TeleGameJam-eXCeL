package games.test;

import games.test.week.ActionMenu;
import games.test.week.Week;
import games.test.week.actions.Action;
import games.test.week.actions.Sleep;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

public class World extends BasicGameState {

	// basic
	private int ID;
	private int state;

	// game
	private static int nbWeeks = 10;
	private ArrayList<Week> weeks;
	int currentWeek;
	private int gameState; // 0 : menu initial ; 1 : ActionMenu ; 2 : action
	ActionMenu actionMenu;
	Action currentAction;

	public World(int ID) {

		// basic
		this.ID = ID;
		this.state = 0;

		// game
		gameState = 2;
		actionMenu = new ActionMenu();

		// week
		weeks = new ArrayList<Week>();
		for (int i = 0; i < nbWeeks; i++)
			weeks.add(new Week());
		currentAction = null;
		currentAction = new Sleep();

		// start a new week
		newWeek();
	}

	public void newWeek() {

	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();

		// leaving game
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}

		// other updates (required for animations)

		switch (gameState) {
			case 0:
				break;

			case 1:
				break;

			case 2:
				currentAction.update(container, game, delta);
				break;

			default:
				System.out.println("Error in World.update()");
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */

		switch (gameState) {
			case 0:
				break;

			case 1:
				break;

			case 2:
				currentAction.render(container, game, context);
				break;

			default:
				System.out.println("Error in World.render()");
		}

	}

	@Override
	public void keyPressed(int key, char c) {
		/* Méthode exécutée environ 60 fois par seconde */

		switch (gameState) {
			case 0:
				break;

			case 1:
				break;

			case 2:
				currentAction.keyPressed(key,c);
				checkEndOfAction();
				break;

			default:
				System.out.println("Error in World.keyPressed()");
		}

	}

	@Override
	public void mousePressed(int button, int x, int y) {

		switch (gameState) {
			case 0:
				break;

			case 1:
				break;

			case 2:
				currentAction.mousePressed(button, x, y);
				checkEndOfAction();
				break;

			default:
				System.out.println("Error in World.mousePressed()");
		}

	}

	public void checkEndOfAction() {
		if (currentAction.isOver()) {

			// action suivante, ou fin de la semaine
			//TODO
			System.out.println("Action terminée !");

		}
	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

}
