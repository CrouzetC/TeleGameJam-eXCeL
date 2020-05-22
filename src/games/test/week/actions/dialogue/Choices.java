package games.test.week.actions.dialogue;

import java.util.ArrayList;

public class Choices implements DialoguePiece {

    private ArrayList<Choice> choices;

    public Choices() {
        choices = new ArrayList<Choice>();
    }

    public void addChoice(Choice c) {
        choices.add(c);
    }

    public Choice getChoice(int i) {
        return choices.get(i);
    }

    public int getNbChoices() {
        return choices.size();
    }

}
