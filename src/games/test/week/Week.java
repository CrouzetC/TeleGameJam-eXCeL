package games.test.week;

import games.test.week.actions.Action;

import java.util.ArrayList;

public class Week {

    ArrayList<Action> actions;

    public Week() {
        actions = new ArrayList<Action>();
    }

    public void addAction(Action action) {
        actions.add(action);
    }


}
