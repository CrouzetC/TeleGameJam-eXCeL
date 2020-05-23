package games.test.data;

import games.test.week.Week;
import games.test.week.actions.ActionEvent;

import java.util.ArrayList;

public class GameData {

    private ArrayList<Project> allProjects;
    private ArrayList<ActionEvent> allActions;
    private ArrayList<UE> allUE;
    private Player player;
    private ArrayList<Week> weeks;
    private NPCs characters;
    private Pictures pictures;

    public GameData(String dataFileName) {

        // UE
        allUE = new ArrayList<UE>();
        allUE.add(new UE("SFA"));
        allUE.add(new UE("STIC"));
        allUE.add(new UE("SEHS"));

        //  Player NPCs and pictures
        player = new Player();
        characters = new NPCs();
        Loader.loadNPCs(characters);
        pictures = new Pictures();

        // We create the Weeks
        weeks = new ArrayList<Week>();

        // Loading from file

        // Projects
        allProjects = new ArrayList<Project>();
        Loader.loadProjects(allProjects, dataFileName);

        // All possible Actions
        allActions = new ArrayList<ActionEvent>();
        Loader.loadActions(allActions, this, dataFileName);

    }

    public ArrayList<Project> getAllProjects () {
        return this.allProjects;
    }
    public ArrayList<ActionEvent> getAllActions() {
        return this.allActions;
    }
    public ArrayList<UE> getAllUE() {
        return this.allUE;
    }
    public Player getPlayer() {
        return this.player;
    }
    public ArrayList<Week> getWeeks() {
        return this.weeks;
    }
    public NPCs getCharacters() {
        return this.characters;
    }
    public Pictures getPictures() {return this.pictures;}

}
