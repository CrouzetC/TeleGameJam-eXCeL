package games.test.week.actions;

import games.test.data.Loader;
import games.test.week.actions.dialogue.Choices;
import games.test.week.actions.dialogue.DialoguePiece;
import games.test.week.actions.dialogue.Text;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

public class Dialogue implements ActionEvent {

    // font
    Font awtFont;
    TrueTypeFont font;

    // game
    boolean isOver;

    // dialogue pieces
    private ArrayList<DialoguePiece> dialoguePieces;
    int current_piece;
    int selectedLine; // pour savoir quelle est la réplique sélectionnée par le joueur

    public Dialogue(String filename) {

        // font
        awtFont = new Font("vt323", java.awt.Font.BOLD, 12);
        font = new TrueTypeFont(awtFont, true);
        isOver = false;

        // loading data
        dialoguePieces = new ArrayList<DialoguePiece>();
        Loader.loadDialogue(this, filename);
        current_piece = 0;
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {

    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {


    }

    public void keyPressed(int key, char c) {

        if (dialoguePieces.get(current_piece) instanceof Text) {

            if (key == Input.KEY_ENTER) {
                current_piece++;
                if (dialoguePieces.get(current_piece) instanceof Choices)
                    selectedLine = 0;
            }
        }
        else if (dialoguePieces.get(current_piece) instanceof Choices) {


        }
    }

    public void mousePressed(int button, int x, int y) {

    }

    public boolean isOver() {
        return isOver;
    }

}
