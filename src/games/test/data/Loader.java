package games.test.data;

import games.test.week.actions.ActionEvent;
import games.test.week.actions.Dialogue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Loader {

    public static void loadProjects(ArrayList<Project> projects, String filename) {


    }

    public static void loadActions(ArrayList<ActionEvent> actions, String filename) {

        int nb_weeks = 5;

        for (int i = 0; i < nb_weeks; i++) {
            // filename
            String dialogueFile = "res/data/dialogue1.txt";
            // creation of action
            actions.add(new Dialogue(dialogueFile));
        }

    }

    public static void loadDialogue(ActionEvent action, String filename) {

    }

}
