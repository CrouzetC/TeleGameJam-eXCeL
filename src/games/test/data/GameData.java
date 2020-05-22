package games.test.data;

import games.test.week.Week;
import games.test.week.actions.Action;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameData {

    ArrayList<Project> allProjects;
    ArrayList<Action> allActions;
    ArrayList<UE> allUE;
    Player player;
    ArrayList<Week> weeks;

    public GameData(String dataFileName) {

        // UE
        allUE = new ArrayList<UE>();
        allUE.add(new UE("SFA"));
        allUE.add(new UE("STIC"));
        allUE.add(new UE("SEHS"));

        //  Player
        player = new Player();

        // We create the Weeks
        weeks = new ArrayList<Week>();

        // Loading from file

        // Projects
        allProjects = new ArrayList<Project>();
        Loader.loadProjects(allProjects, dataFileName);

        // All possible Actions
        allActions = new ArrayList<Action>();
        Loader.loadActions(allActions, dataFileName);

    }

}
