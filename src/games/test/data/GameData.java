package games.test.data;

import games.test.week.Week;
import games.test.week.actions.ActionEvent;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameData {

    private ArrayList<Project> allProjects;
    private ArrayList<Project> remainingProjects;
    private ArrayList<ActionEvent> allActions;
    private ArrayList<UE> allUE;
    private Player player;
    private ArrayList<Week> weeks;
    private NPCs characters;
    private Pictures pictures;

    public GameData(String dataFileName) {

        // UE
        allUE = new ArrayList<UE>();
        allUE.add(new UE("SFA", 20));
        allUE.add(new UE("STIC", 30));
        allUE.add(new UE("SEHS", 10));

        //  Player, NPCs and pictures
        player = new Player(this);
        characters = new NPCs();
        Loader.loadNPCs(characters);
        pictures = new Pictures();

        // We create the Weeks
        weeks = new ArrayList<Week>();

        // Loading from file

        // Projects
        allProjects = new ArrayList<Project>();
        Loader.loadProjects(allProjects, this, dataFileName);
        remainingProjects = new ArrayList<Project>();
        for (int i = 0; i < allProjects.size(); i++)
            remainingProjects.add(allProjects.get(i));

        // All possible Actions
        allActions = new ArrayList<ActionEvent>();
        Loader.loadActions(allActions, this, dataFileName);

    }

    public Project attributeRandomProject() {
        if (remainingProjects.size() == 0) {
            System.out.println("Error in attributeRandomProject() : no more projects");
            return null;
        }
        int rand = ThreadLocalRandom.current().nextInt(0, remainingProjects.size());
        Project p = remainingProjects.remove(rand);
        return p;
    }

    public UE getUE(String title) {
        int index = 0;
        for (int i = 0; i < allUE.size(); i++) {
            if (allUE.get(i).getName().equals(title))
                index = i;
        }
        return allUE.get(index);
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
