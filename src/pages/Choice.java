package pages;

import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;

public class Choice extends AppMenu {

	public Choice(int ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.initSize(container, game, 600, 400);
		super.init(container, game);
		this.setTitle("TeleGame Jam");
		this.setSubtitle("WAIFU PROJECTS");
		this.setMenu(Arrays.asList(new MenuItem[] {
			new MenuItem("Nouvelle partie") {
				public void itemSelected() {
					game.enterState(4, new FadeOutTransition(Color.black,Welcome.fadeTransitionTime), new FadeInTransition(Color.black,Welcome.fadeTransitionTime));

				}
			},
			new MenuItem("Retour") {
				public void itemSelected() {
					game.enterState(0, new FadeOutTransition(Color.black,Welcome.fadeTransitionTime), new FadeInTransition(Color.black,Welcome.fadeTransitionTime));
				}
			}
		}));
		this.setHint("Choisissez un mode de jeu.");
	}

}
