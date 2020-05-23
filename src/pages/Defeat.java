package pages;

import app.AppMenu;
import app.elements.MenuItem;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.Arrays;

public class Defeat extends AppMenu {

    private Music music;

    public Defeat(int ID) {
        super(ID);
        try {
            this.music = new Music("res/musics/sadmusic.ogg");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) {
        super.initSize(container, game, 600, 400);
        super.init(container, game);
        this.setTitle("Vous ne passerez pas");
        this.setSubtitle("Direction le redoublement !");
        this.setMenu(Arrays.asList(new MenuItem[] {
                new MenuItem("Quitter") {
                    public void itemSelected() {
                        game.enterState(1, new FadeOutTransition(Color.black,Welcome.fadeTransitionTime), new FadeInTransition(Color.black,Welcome.fadeTransitionTime));
                    }
                }
        }));
        this.setHint("Allez, ouste !");
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        music.play(1, .3f);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) {
        music.stop();
    }

}
