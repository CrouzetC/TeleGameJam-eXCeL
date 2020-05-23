package games.test.week.actions;

import games.test.data.GameData;
import games.test.data.Loader;
import games.test.data.Player;
import games.test.week.actions.dialogue.Choice;
import games.test.week.actions.dialogue.Choices;
import games.test.week.actions.dialogue.DialoguePiece;
import games.test.week.actions.dialogue.Line;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

import app.AppFont;
import app.AppLoader;

public class Dialogue implements ActionEvent {

    // font
    GameData gameData;
    TrueTypeFont font;

    // game
    boolean isOver;

    // song
    Sound foot_steps;

    // dialogue pieces
    private ArrayList<DialoguePiece> dialoguePieces;
    int current_piece;
    int selectedLine; // pour savoir quelle est la réplique sélectionnée par le joueur
    int NPC_position;
    int NPC_final_position;
    String lastNPCName;
    int width = 1;
    int height = 1;
    int pictureWidth;
    int pictureHeight;
    static int pictureSlidingSpeed = 50;

    public Dialogue(GameData gameData, String filename) {

        // font
        font = AppLoader.loadFont(null, AppFont.BOLD, 12);

        // song
        try {
            foot_steps = new Sound("songs/foot_steps.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // loading data
        this.gameData = gameData;
        dialoguePieces = new ArrayList<DialoguePiece>();
        Loader.loadDialogue(this, filename);
        current_piece = 0;
        isOver = false;

        NPC_position = 0;
        NPC_final_position = 0;
        if (dialoguePieces.get(current_piece) instanceof Line)
            lastNPCName = ((Line)(dialoguePieces.get(current_piece))).getCharacterName();
        else
            lastNPCName = "unknown";
    }

    public Dialogue() {  // uniquement pour getDialogueDemo

        // font (celle par défaut)
        font = AppLoader.loadFont(null, AppFont.BOLD, 12);
        isOver = false;

        // song
        try {
            foot_steps = new Sound("songs/foot_steps.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // loading data
        dialoguePieces = new ArrayList<DialoguePiece>();
        current_piece = 0;

        // placement du premier personnage
        NPC_position = 0;
        NPC_final_position = 0;
        if (dialoguePieces.get(current_piece) instanceof Line) {
            lastNPCName = ((Line) (dialoguePieces.get(current_piece))).getCharacterName();
        }
        else {
            lastNPCName = "unknown";
        }
    }

    public void add(DialoguePiece piece) {
        this.dialoguePieces.add(piece);
    }

    public ArrayList<DialoguePiece> getDialoguePieces() {
        return this.dialoguePieces;
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {

    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {

        width = container.getWidth();
        height = container.getHeight();
        pictureWidth = (int) (0.2 * width);
        pictureHeight = (int) (0.7 * height);
        DialoguePiece dialoguePiece = dialoguePieces.get(current_piece);

        // police de caractères
        // (on prend celle par défaut)
        font = AppLoader.loadFont(null, AppFont.BOLD, (int)(0.03*height));

        // images de fond
        Image bckground = gameData.getPictures().getDialogue_background();
        float bgWidth = bckground.getWidth();
        float scale = bgWidth/width ;

        bckground.draw(0, 0, width, height);

        // affichage des NPC qui parlent
        if (dialoguePieces.get(current_piece) instanceof Line) {
            if (lastNPCName.equals("MC") == false) {

                Image image = gameData.getCharacters().getNPCPicture(lastNPCName);

                // cas de la première réplique
                if (current_piece == 0) {
                    boolean isLeft = ((int)(lastNPCName.charAt(0) - 'A')) % 2 == 0;
                    NPC_final_position = isLeft ? (int)(0.2*width) : (int)(0.8*width) - pictureWidth;
                    NPC_position = NPC_final_position;
                    image.draw(NPC_final_position, (int) (0.3 * height), pictureWidth, pictureHeight);
                }
                // cas des répliques suivantes
                else {
                    if (NPC_position > NPC_final_position) {
                        NPC_position += NPC_position - NPC_final_position < pictureSlidingSpeed ? - (NPC_position - NPC_final_position) : -pictureSlidingSpeed;
                    }
                    if (NPC_position < NPC_final_position) {
                        NPC_position += NPC_final_position - NPC_position < pictureSlidingSpeed ? NPC_final_position - NPC_position : pictureSlidingSpeed;
                    }
                    image.draw(NPC_position, (int) (0.3 * height), pictureWidth, pictureHeight);
                }
            }
        }

        // image de la boîte de dialogue
        Image text_bckground = gameData.getPictures().getText_field_image();
        text_bckground.draw(0, (int)(0.8*height), width, (int)(0.2*height));

        // textes du dialogue
        if (dialoguePiece instanceof Line) {
            // réplique d'un personnage
            String characterName = ((Line)dialoguePiece).getCharacterName();
            if (characterName.equals("MC"))
                characterName = gameData.getPlayer().getName();

            String text = ((Line)dialoguePiece).getText();

            font.drawString(60, (int)(0.825*height), characterName, org.newdawn.slick.Color.black);
            font.drawString(40, (int)(0.875*height), text, org.newdawn.slick.Color.black);

        }
        if (dialoguePiece instanceof Choices) {
            // réplique du joueur

            // nom du joueur
            font.drawString(30,  (int)(0.825*height), gameData.getPlayer().getName(), org.newdawn.slick.Color.red);

            // indication de la réplique actuellement sélectionnée
            font.drawString(30,  (int)(0.875*height) + selectedLine*20, ">>", org.newdawn.slick.Color.red);

            // affichage des différentes répliques possibles
            int nbChoices = ((Choices)dialoguePiece).getNbChoices();
            for (int i = 0; i < nbChoices; i++) {
                Choice choice = ((Choices)dialoguePiece).getChoice(i);
                font.drawString(45, (int)(0.875*height) + i*20, choice.getText(), org.newdawn.slick.Color.black);
            }
        }

    }

    public void keyPressed(int key, char c) {

        // Choix d'une réplique du joueur
        if (dialoguePieces.get(current_piece) instanceof Choices) {
            int nb_choices = ((Choices)dialoguePieces.get(current_piece)).getNbChoices();

            if (key == Input.KEY_UP) {
                selectedLine -= 1;
                selectedLine = (selectedLine+nb_choices) % nb_choices;
            }
            if (key == Input.KEY_DOWN) {
                selectedLine += 1;
                selectedLine = selectedLine % nb_choices;
            }
        }

        if (key == Input.KEY_ENTER) {

            if (dialoguePieces.get(current_piece) instanceof Line) {

                current_piece++;

                if (current_piece < dialoguePieces.size())
                    if (dialoguePieces.get(current_piece) instanceof Choices)
                        selectedLine = 0;

            } else if (dialoguePieces.get(current_piece) instanceof Choices) {
                Choice choice = ((Choices) dialoguePieces.get(current_piece)).getChoice(selectedLine);

                // déclenchement de l'action associée au choix

                // modification des statistiques
                int[] effects = choice.getEffects();
                for (int i = 0; i < Player.nb_stats; i++) {
                    gameData.getPlayer().modifyStatistic(i, effects[i]);
                }
                // avancement du projet
                int project_advancement = effects[Player.nb_stats];
                for (int i = 0; i < gameData.getPlayer().getProjects().size(); i++) {
                    gameData.getPlayer().getProjects().get(i).addProgression(project_advancement);
                }

                // on insère les prochains éléments de dialogue
                ArrayList<DialoguePiece> followingPieces = choice.getFollowingDialoguePieces();
                for (int i = 0; i < followingPieces.size(); i++) {
                    dialoguePieces.add(current_piece + 1 + i, followingPieces.get(i));
                }

                // on passe à l'élément de dialogue suivant
                current_piece ++;
            }

            // on vérifie s'il y a un nouveau NPC à l'écran
            if (current_piece < dialoguePieces.size()) {
                if (dialoguePieces.get(current_piece) instanceof Line) {
                    String name = ((Line)(dialoguePieces.get(current_piece))).getCharacterName();
                    if (name.equals(lastNPCName) == false) {

                        // on passe sur un nouveau NPC
                        lastNPCName = name;
                        boolean isLeft = ((int)(name.charAt(0) - 'A')) % 2 == 0;
                        NPC_position = isLeft ? - pictureWidth : width;
                        NPC_final_position = isLeft ? (int)(0.2*width) : (int)(0.8*width) - pictureWidth;

                        // on lance le bruitage des pas
                        foot_steps.play();
                    }
                }
            }

            // checking if the Dialogue is over
            if (current_piece == dialoguePieces.size())
                isOver = true;

        }

    }

    public void mousePressed(int button, int x, int y) {

    }

    public boolean isOver() {
        return isOver;
    }

    public static Dialogue getDialogueDemo() {
        Dialogue d = new Dialogue();

        d.add(new Line("John","Yo yo yo"));
        d.add(new Line("Maria","Hello !!"));
        d.add(new Line("John","How are you ?"));

        Choices choices = new Choices();
        int[] effects = {0,0,0,0,0,0,0};

        Choice choiceA = new Choice("Fine !", effects);
        choiceA.addFollowingDialoguePiece(new Line("John","coool"));
        choiceA.addFollowingDialoguePiece(new Line("Maria","See you !"));

        Choice choiceB = new Choice("Better than Thomas Ahah", effects);
        choiceB.addFollowingDialoguePiece(new Line("John","lmaooo"));
        choiceB.addFollowingDialoguePiece(new Line("Maria","omg"));

        choices.addChoice(choiceA);
        choices.addChoice(choiceB);
        d.add(choices);

        d.add(new Line("John","Let's go back to work now"));
        d.add(new Line("Maria","Yes"));

        return d;
    }

}
