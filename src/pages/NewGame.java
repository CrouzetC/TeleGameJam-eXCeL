package pages;

import org.newdawn.slick.Color;
import games.test.data.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.AppPage;
import app.ui.TextField;

public class NewGame extends AppMenu {

	private TextField tf;

	public NewGame(int ID) {
		super(ID);
	}

	public void init(GameContainer container, StateBasedGame game) {
		super.initSize(container, game, 600, 400);
		super.init(container, game);

		this.setTitle("Nouvelle partie");
		this.setSubtitle("Entrez votre nom :");
		this.tf = new TextField(container,
				this.contentX, this.subtitleBoxY + this.subtitleBoxHeight + AppPage.gap,
				this.contentWidth, this.subtitleBoxHeight);
		this.tf.setMaxNumberOfLetter(10);

		/*this.setMenu(Arrays.asList(new MenuItem[] {
				new MenuItem("Confirmer") {
					public void itemSelected() {
						game.enterState(2, new FadeOutTransition(), new FadeInTransition(Color.black, Welcome.fadeTransitionTime));
					}
				}
			}));*/

		this.setHint("Ce choix sera définitif !");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int  delta) {
		super.update(container, game, delta);
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			game.enterState(1, new FadeOutTransition(Color.black,Welcome.fadeTransitionTime), new FadeInTransition(Color.black,Welcome.fadeTransitionTime));
		} else if (input.isKeyDown(Input.KEY_ENTER) && !this.tf.getText().matches("^\\s*$")) {
			Player.name = this.tf.getText();
			game.enterState(3, new FadeOutTransition(Color.black,Welcome.fadeTransitionTime), new FadeInTransition(Color.black,Welcome.fadeTransitionTime));
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
		this.tf.render(container, game, context);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		super.enter(container, game);
		this.tf.setText("");
	}
}
