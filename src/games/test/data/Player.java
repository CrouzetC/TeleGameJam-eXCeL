package games.test.data;

import java.util.ArrayList;

import static java.lang.Math.max;

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
        if (ue.contains(p.getUE())) {
            this.projects.add(p);
        }
    }

    public ArrayList<UE> getUE() {
        return this.ue;
    }

    public void addPointsToUE(int index, int points) {
        this.ue.get(index).setNbPoints(points + this.ue.get(index).getNbPoints());
    }

    public void passProject(Project p) {
        if (projects.contains(p)) {
            if (p.getProgression() == 100) {
                p.getUE().addPoints(p.getThreshold());
            }
        }
    }

    public void endProject(Project p) {
        if (projects.contains(p)) {
            if (p.getWorkedDays() == p.getQtyWork()) {
                passProject(p);
            }
        }
    }

    public void dayProgress(Project p) {
        double step = ((double) p.getWorkedDays() / (double) p.getQtyWork());
        if (step < 1) {
            double progress = p.getProgression();
            double[] crit = p.getStatCriteria();
            for (int j = 0; j < 6; j++) {
                // Si on s'interesse a l'influence du stress et de la fatigue, la progression diminue
                if (j == 0) {
                    double evol = max(0.0, progress-crit[j]*step);
                    progress = evol;
                } else {
                    progress += crit[j] * step;
                }
            }
            p.setProgression((int) progress);
        }
    }

}
