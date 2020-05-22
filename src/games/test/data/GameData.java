package games.test.data;

import games.test.week.Week;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameData {

    ArrayList<Project> allProjects;
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

        // Loading from file

        // Projects
        allProjects = new ArrayList<Project>();
        Loader.loadProjects(allProjects, dataFileName);

        // Weeks, Days,...
        weeks = new ArrayList<Week>();
        Loader.loadWeeks(weeks, dataFileName);

    }

}
