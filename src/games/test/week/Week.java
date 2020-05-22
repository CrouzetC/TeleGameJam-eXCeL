package games.test.week;

import games.test.week.actions.ActionEvent;

import java.util.ArrayList;

public class Week {

    ArrayList<ActionEvent> actions;

    public Week() {
        actions = new ArrayList<ActionEvent>();
    }

    public void addAction(ActionEvent action) {
        actions.add(action);
    }

    public ActionEvent getNextAction() {
        return null;
    }

}
