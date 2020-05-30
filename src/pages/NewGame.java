package pages;

import app.ui.Button;
import games.test.data.Appro;
import org.lwjgl.Sys;
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
	private Button iamd, il, iss, le, sie;
	private Button selectedButton = null;

	public NewGame(int ID) {
		super(ID);
	}

	public void init(GameContainer container, StateBasedGame game) {
		super.initSize(container, game, 600, 400);
		super.init(container, game);

		this.setTitle("Nouvelle partie");
		this.setSubtitle("Entrez votre nom et choisissez votre appro :");

		this.tf = new TextField(container,
				this.contentX + (int) (this.subtitleBoxWidth/3.), this.subtitleBoxY + this.subtitleBoxHeight + AppPage.gap,
				(int) (this.subtitleBoxWidth/3.), this.subtitleBoxHeight);
		this.tf.setMaxNumberOfLetter(10);

		this.iamd = new Button("IAMD", container,
				contentX, tf.getY() + tf.getHeight() + AppPage.gap,
				tf.getWidth(), tf.getHeight());
		this.iamd.setSelectable(true);
		this.iamd.setBackgroundColorSelected(new Color(164,19,19));
		this.iamd.setBackgroundColorPressed(iamd.getBackgroundColorSelected());
		this.il = new Button("IL", container,
				tf.getX(), iamd.getY(),
				iamd.getWidth(), iamd.getHeight());
		this.il.setSelectable(true);
		this.il.setBackgroundColorSelected(new Color(255, 244, 0));
		this.il.setTextColorSelected(new Color(0, 0, 0));
		this.il.setTextColorPressed(il.getTextColorSelected());
		this.il.setBackgroundColorPressed(il.getBackgroundColorSelected());
		this.iss = new Button("ISS", container,
				tf.getX() + tf.getWidth(), iamd.getY(),
				iamd.getWidth(), iamd.getHeight());
		this.iss.setSelectable(true);
		this.iss.setBackgroundColorSelected(new Color(244,15,15));
		this.iss.setBackgroundColorPressed(iss.getBackgroundColorSelected());
		this.le = new Button("LE", container,
				iamd.getX() + (int) (iamd.getWidth()/2.), iamd.getY() + iamd.getHeight(),
				iamd.getWidth(), iamd.getHeight());
		this.le.setSelectable(true);
		this.le.setBackgroundColorSelected(new Color(0,195,255));
		this.le.setBackgroundColorPressed(le.getBackgroundColorSelected());
		this.sie = new Button("SIE", container,
				le.getX() + le.getWidth(), le.getY(),
				iamd.getWidth(), iamd.getHeight());
		this.sie.setSelectable(true);
		this.sie.setBackgroundColorSelected(new Color(250,159,0));
		this.sie.setBackgroundColorPressed(sie.getBackgroundColorSelected());

		/*this.setMenu(Arrays.asList(new MenuItem[] {
				new MenuItem("Confirmer") {
					public void itemSelected() {
						game.enterState(2, new FadeOutTransition(), new FadeInTransition(Color.black, Welcome.fadeTransitionTime));
					}
				}
			}));*/

		this.setHint("Ces choix seront définitifs !");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int  delta) {
		super.update(container, game, delta);
		Input input = container.getInput();
		iamd.update(container, game, delta);
		il.update(container, game, delta);
		iss.update(container, game, delta);
		le.update(container, game, delta);
		sie.update(container, game, delta);
		if (input.isMousePressed(0)) {
			int x = input.getMouseX();
			int y = input.getMouseY();

			if (iamd.contains(x, y)) setSelectedButton(iamd);
			else if (il.contains(x, y)) setSelectedButton(il);
			else if (iss.contains(x, y)) setSelectedButton(iss);
			else if (le.contains(x, y)) setSelectedButton(le);
			else if (sie.contains(x, y)) setSelectedButton(sie);
		}
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			game.enterState(1, new FadeOutTransition(Color.black,Welcome.fadeTransitionTime), new FadeInTransition(Color.black,Welcome.fadeTransitionTime));
		} else if (input.isKeyDown(Input.KEY_ENTER) && selectedButton != null && !this.tf.getText().matches("^\\s*$")) {
			Player.name = this.tf.getText();
			Player.appro = Appro.valueOf(this.selectedButton.getText());
			game.enterState(3, new FadeOutTransition(Color.black,Welcome.fadeTransitionTime), new FadeInTransition(Color.black,Welcome.fadeTransitionTime));
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
		this.tf.render(container, game, context);
		this.iamd.render(container, game, context);
		this.il.render(container, game, context);
		this.iss.render(container, game, context);
		this.le.render(container, game, context);
		this.sie.render(container, game, context);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		super.enter(container, game);
		this.tf.setText("");
		reinitiateButton(iamd);
		reinitiateButton(il);
		reinitiateButton(iss);
		reinitiateButton(le);
		reinitiateButton(sie);
		this.selectedButton = null;
	}

	public void setSelectedButton(Button b) {
		selectedButton = b;
		reinitiateButton(iamd);
		reinitiateButton(il);
		reinitiateButton(iss);
		reinitiateButton(le);
		reinitiateButton(sie);
		// The selected button will be set to selected after mouse clicked
	}

	public void reinitiateButton(Button b) {
		b.setSelected(false);
		b.setBackgroundColor(new Color(0,0,0));
		b.setTextColor(new Color(255,255,255));
	}
}
