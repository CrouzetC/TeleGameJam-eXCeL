package games.test.week.actions;

import games.test.data.GameData;
import games.test.data.Loader;
import games.test.week.actions.dialogue.Choice;
import games.test.week.actions.dialogue.Choices;
import games.test.week.actions.dialogue.DialoguePiece;
import games.test.week.actions.dialogue.Line;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.ArrayList;

public class Dialogue implements ActionEvent {

    // font
    GameData gameData;
    Font awtFont;
    TrueTypeFont font;

    // game
    boolean isOver;

    // dialogue pieces
    private ArrayList<DialoguePiece> dialoguePieces;
    int current_piece;
    int selectedLine; // pour savoir quelle est la réplique sélectionnée par le joueur

    public Dialogue(GameData gameData, String filename) {

        // font
        awtFont = new Font("vt323", java.awt.Font.BOLD, 12);
        font = new TrueTypeFont(awtFont, true);

        // loading data
        this.gameData = gameData;
        dialoguePieces = new ArrayList<DialoguePiece>();
        Loader.loadDialogue(this, filename);
        current_piece = 0;
        isOver = false;
    }

    public Dialogue() {  // uniquement pour getDialogueDemo

        // font
        awtFont = new Font("vt323", java.awt.Font.BOLD, 12);
        font = new TrueTypeFont(awtFont, true);
        isOver = false;

        // loading data
        dialoguePieces = new ArrayList<DialoguePiece>();
        current_piece = 0;
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

        int width = container.getWidth();
        int height = container.getHeight();
        DialoguePiece dialoguePiece = dialoguePieces.get(current_piece);

        // font
        awtFont = new Font("vt323", java.awt.Font.BOLD, (int)(0.03*height));
        font = new TrueTypeFont(awtFont, true);

        // images de fond
        Image bckground = gameData.getPictures().getDialogue_background();
        bckground.draw(0, 0, width, height);
        Image text_bckground = gameData.getPictures().getText_field_image();
        text_bckground.draw(0, (int)(0.8*height), width, (int)(0.2*height));


        // textes du dialogue
        if (dialoguePiece instanceof Line) {
            // réplique d'un personnage

            String characterName = ((Line)dialoguePiece).getCharacterName();
            String text = ((Line)dialoguePiece).getText();

            font.drawString(60, (int)(0.825*height), characterName, org.newdawn.slick.Color.red);
            font.drawString(40, (int)(0.875*height), text, org.newdawn.slick.Color.red);

        }
        if (dialoguePiece instanceof Choices) {
            // réplique du joueur

            // indication de la réplique actuellement sélectionnée
            font.drawString(30,  (int)(0.875*height) + selectedLine*20, ">>", org.newdawn.slick.Color.red);

            // affichage des différentes répliques possibles
            int nbChoices = ((Choices)dialoguePiece).getNbChoices();
            for (int i = 0; i < nbChoices; i++) {
                Choice choice = ((Choices)dialoguePiece).getChoice(i);
                font.drawString(45, (int)(0.875*height) + i*20, choice.getText(), org.newdawn.slick.Color.red);
            }
        }

    }

    public void keyPressed(int key, char c) {

        if (key == Input.KEY_ENTER) {

            if (dialoguePieces.get(current_piece) instanceof Line) {

                current_piece++;
                if (dialoguePieces.get(current_piece) instanceof Choices)
                    selectedLine = 0;

            } else if (dialoguePieces.get(current_piece) instanceof Choices) {
                Choice choice = ((Choices) dialoguePieces.get(current_piece)).getChoice(selectedLine);

                // déclenchement de l'action associée au choix
                // TODO !!!!

                // on insère les prochains éléments de dialogue
                ArrayList<DialoguePiece> followingPieces = choice.getFollowingDialoguePieces();
                for (int i = 0; i < followingPieces.size(); i++) {
                    dialoguePieces.add(current_piece + 1 + i, followingPieces.get(i));
                }

                // on passe à l'élément de dialogue suivant
                current_piece ++;
            }

            // checking if the Dialogue is over
            if (current_piece == dialoguePieces.size())
                isOver = true;
        }

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
