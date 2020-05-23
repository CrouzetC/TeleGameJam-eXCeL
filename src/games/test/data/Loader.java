package games.test.data;

import app.AppLoader;
import games.test.week.actions.ActionEvent;
import games.test.week.actions.Dialogue;
import games.test.week.actions.dialogue.Choice;
import games.test.week.actions.dialogue.Choices;
import games.test.week.actions.dialogue.DialoguePiece;
import games.test.week.actions.dialogue.Line;

import java.io.StringReader;
import java.io.BufferedReader;
import java.util.ArrayList;

import app.AppLoader;

public class Loader {

    public static int nb_dialogues = 1;

    public static void loadProjects(ArrayList<Project> projects, GameData data, String filename) {

        projects.add(new Project("Compil", data.getUE("STIC"), 30, 10, new double[]{0.3, 0.3, 0, 0, 0.3, 0.1}, "images/projets/compil-sama.png"));
        projects.add(new Project("PIDR", data.getUE("SFA"), 20, 14, new double[]{0.1,0.2,0.2,0.2,0.2,0.1}, "images/projets/pidr-chan.png"));
        projects.add(new Project("Anglais", data.getUE("SEHS"), 15, 14, new double[]{0.1,0.2,0.2,0.2,0.2,0.1}, "images/projets/anglais.png"));
        projects.add(new Project("P2I", data.getUE("SFA"), 15, 14, new double[]{0.1,0.2,0.2,0.2,0.2,0.1}, "images/projets/p2i-kohai.png"));
        projects.add(new Project("PI", data.getUE("SFA"), 30, 14, new double[]{0.1,0.2,0.2,0.2,0.2,0.1}, "images/projets/pi-senpai.png"));

    }

    public static void loadActions(ArrayList<ActionEvent> actions, GameData data, String filename) {

        int nb_weeks = 5;

        for (int i = 0; i < nb_dialogues; i++) {
            // filename
            String dialogueFile = "/data/dialogue"+(1+i)+".txt";

            // creation of action
            actions.add(new Dialogue(data, dialogueFile));
        }

    }

    public static void loadNPCs(NPCs npcs) {

        ArrayList<String> names = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new StringReader(AppLoader.loadData("/data/NPC_names.txt")));
            String name;
            while ((name = reader.readLine()) != null) {
                names.add(name);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String name : names) {
            npcs.addNPC(name);
        }

    }

    public static void loadDialogue(Dialogue d, String filename) {

        ArrayList<String> lines = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new StringReader(AppLoader.loadData(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<DialoguePiece> pieces = d.getDialoguePieces();
        int index = 0;
        index = analyseDialogue(pieces, lines, index);

    }

    public static int analyseDialogue(ArrayList<DialoguePiece> pieces, ArrayList<String> lines, int index) {

        boolean end = false;
        while (end == false) {

            if (lines.get(index).charAt(0) == '\"') {
                String name = lines.get(index).substring(1,lines.get(index).length()-1);
                index++;
                String sentence = lines.get(index).substring(1,lines.get(index).length()-1);
                index++;
                pieces.add(new Line(name, sentence));
            }
            else if (lines.get(index).equals("END")) {
                index++;
                end = true;
            } else if (lines.get(index).equals("CHOICE")) {
                index++;
                index = analyseChoice(pieces, lines, index);
            } else if (lines.get(index).length()==1) {
                index++;
                end = true;
            } else {
                System.out.println("Error in analyseDialogue() : unknown data : " + lines.get(index));
            }

        }

        return index;
    }

    public static int analyseChoice(ArrayList<DialoguePiece> pieces, ArrayList<String> lines, int index) {

        // Ligne : CHOICE,[nombre de phrases],"[phrase A]",[effects],"[phrase B]",[effects],"[phrase C]",[effects]
        String[] parts = lines.get(index).split(",");

        int nb_choices = Integer.valueOf(parts[0]);
        ArrayList<Choice> choices = new ArrayList<Choice>();

        for (int i = 0; i < nb_choices; i++) {
            String sentence = parts[1+2*i].substring(1,parts[1+2*i].length()-1);
            int[] effects = new int[Player.nb_stats+1];
            String[] effects_str = parts[1+2*i+1].split(" ");
            for (int j = 0; j < Player.nb_stats+1; j++) {
                effects[j] = Integer.valueOf(effects_str[j]);
            }
            choices.add(new Choice(sentence, effects));
        }
        index += 1; // ligne CHOICE
        index += 1; // ligne du choice A

        // lignes de choix
        for (int i = 0; i < nb_choices; i++) {
            ArrayList<DialoguePiece> choice_pieces = choices.get(i).getFollowingDialoguePieces();
            index = analyseDialogue(choice_pieces, lines, index);
        }
        Choices c = new Choices();
        for (Choice ch : choices)
            c.addChoice(ch);

        pieces.add(c);

        return index;
    }

}
