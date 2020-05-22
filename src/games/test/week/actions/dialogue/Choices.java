package games.test.week.actions.dialogue;

import java.util.ArrayList;

public class Choices {

    private String txt;

    private ArrayList<Choice> choices;

    public Choices(String txt) {
        this.txt = txt;
    }

    public String getText() {
        return txt;
    }

    public void addChoice(Choice c) {
        choices.add(c);
    }

    public Choice getChoice(int i) {
        return choices.get(i);
    }

}
