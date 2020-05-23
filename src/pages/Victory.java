package pages;

import app.AppMenu;
import app.elements.MenuItem;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.Arrays;

public class Victory extends AppMenu {

    private Music music;

    public Victory(int ID) {
        super(ID);
        try {
            this.music = new Music("res/musics/Phillip_Gross_-_03_-_Optimistic_Bits.ogg");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) {
        super.initSize(container, game, 600, 400);
        super.init(container, game);
        this.setTitle("Validation du semestre !");
        this.setSubtitle("Maintenant, trouve-toi un stage !");
        this.setMenu(Arrays.asList(new MenuItem[] {
                new MenuItem("Quitter") {
                    public void itemSelected() {
                        }
                }
        }));
        this.setHint("Allez, ouste !");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int  delta) {
        super.update(container, game, delta);
        Input input = container.getInput();
        if (input.isKeyDown(Input.KEY_ENTER)) {
            container.exit();
        }
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
