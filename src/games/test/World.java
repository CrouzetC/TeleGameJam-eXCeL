package games.test;

import games.test.data.GameData;
import games.test.week.ActionMenu;
import games.test.week.Week;
import games.test.week.actions.ActionEvent;
import games.test.week.actions.Dialogue;
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
	private int gameState; // 1 : ActionMenu ; 2 : action
	private GameData data;
	ActionMenu actionMenu;

	// weeks
	private static int nbWeeks = 3;
	private ArrayList<Week> weeks;
	int currentWeek;
	ActionEvent currentAction;

	public World(int ID) {

		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		// basic
		this.ID = ID;
		this.state = 0;

		// game
		gameState = 1;
		data = new GameData("some file");
		actionMenu = new ActionMenu(data);

		// week
		weeks = new ArrayList<Week>();
		for (int i = 0; i < nbWeeks; i++)
			weeks.add(new Week());
		currentAction = null;
		currentAction = new Dialogue(data, "res/data/dialogue1.txt");

		//actionMenu.setWeek(weeks.get(currentWeek));

		// start a new week
		newWeek();

	}

	public void newWeek() {
		currentWeek += 1;
		if (currentWeek < nbWeeks) {
			// passage à la semaine suivante
			actionMenu.reset();
			actionMenu.setWeek(weeks.get(currentWeek));
			gameState = 1;
		} else {
			// fin du jeu
			gameState = 3;
		}
	}

	public void	checkEndOfMenu() {
		if (actionMenu.isOver()) {
			gameState = 2;
			checkEndOfAction();
		}
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
			case 1:
				actionMenu.update(container, game, delta);
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
			case 1:
				actionMenu.render(container, game, context);
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
				actionMenu.keyPressed(key,c);
	//			checkEndOfAction();
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
				actionMenu.mousePressed(button, x, y);
				checkEndOfMenu();
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
		if (currentAction == null){
			// on est dans une nouvelle semaine faut attendre la fin de la planification de la semaine
			if(actionMenu.isOver()){
				currentAction = weeks.get(currentWeek).getNextActionEvent();
			}
		}
		if (currentAction.isOver()) {

			// action suivante, ou fin de la semaine
			currentAction = weeks.get(currentWeek).getNextActionEvent();

			if (currentAction != null) {
				// action suivante :
				// rien à faire
			}else{
				// avancer dans la semaine
				newWeek();
			}
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
