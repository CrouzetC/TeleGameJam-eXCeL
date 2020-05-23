package games.test.data;

import java.util.ArrayList;

public class Player {
    public static String name;

    public static int nb_stats = 6;
    private int[] statistics;
    /* statistics[0] : Stress, Fatigue;
    * statistics[1] : Algo, Théorie;
    * statistics[2] : java, POO;
    * statistics[3] : C, C++, RS;
    * statistics[4] : SQL, Excel, IA;
    * statistics[5] : Charisme, Social, GdP;
    */

    private ArrayList<Project> projects;

    private ArrayList<UE> ue;

    public Player() {
        this.statistics = new int[nb_stats];
        this.name = name;
        for (int i = 0; i < nb_stats; i++)
            statistics[i] = 30;
        this.projects = new ArrayList<>();
        this.ue = new ArrayList<>();
        this.ue.add(new UE("SFA", 20));
        this.ue.add(new UE("STIC", 30));
        this.ue.add(new UE("SEHS", 10));
        // Peut-etre qu'il faut les importer depuis GameData
    }

    /*  GETTER ET SETTER */
    public void setName(String name) {this.name = name;}
    public String getName() {
        return name;
    }
    public int[] getStatistics() {
        return statistics;
    }
    public int getStatisticsIndex(int index){
        if( index < statistics.length ) {
            return this.statistics[index];
        }else{
            return 101; // Erreur on renvoit une valeur supérieur à 100
        }
    }

    public void modifyStatistic(int stat_index, int modification){
        this.statistics[stat_index] += modification;
        if (statistics[stat_index] <   0) statistics[stat_index] = 0;
        if (statistics[stat_index] > 100) statistics[stat_index] = 100;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void addProject(Project p) {
        this.projects.add(p);
    }

    public ArrayList<UE> getUE() {
        return this.ue;
    }

    public void addPointsToUE(int index, int points) {
        this.ue.get(index).setNbPoints(points + this.ue.get(index).getNbPoints());
    }
}
