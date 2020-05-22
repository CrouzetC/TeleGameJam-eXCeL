package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.AppPage;
import app.elements.MenuItem;
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
		
		this.setMenu(Arrays.asList(new MenuItem[] {
				new MenuItem("Confirmer") {
					public void itemSelected() {
						game.enterState(2, new FadeOutTransition(), new FadeInTransition());
					}
				}
			}));
		
		this.setHint("Ce choix sera définitif. Il ne vous sera pas possible de le modifier plus tard.");
	}
	
}
