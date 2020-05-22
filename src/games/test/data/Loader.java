package games.test.data;

import games.test.week.actions.ActionEvent;
import games.test.week.actions.Dialogue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

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

    public static void loadNPCs(NPCs npcs) {

        ArrayList<String> names = new ArrayList<String>();
        try (Stream<String> stream = Files.lines(Paths.get("res/data/NPC_names.txt"))) {
            stream.forEach(names::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String name : names) {
            npcs.addNPC(name);
        }

    }

    public static void loadDialogue(ActionEvent action, String filename) {

        ArrayList<String> lines = new ArrayList<String>();
        try (Stream<String> stream = Files.lines(Paths.get("res/data/NPC_names.txt"))) {
            stream.forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int index = 0;
        boolean end = false;

        while (end == false) {

            if (lines.get(index).length() >= 3) {

            }


        }

    }

}
