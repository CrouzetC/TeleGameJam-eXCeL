package games.test.data;

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

    public Player() {
        this.statistics = new int[nb_stats];
        this.name = name;
        for (int i = 0; i < nb_stats; i++)
            statistics[i] = 30;
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

}
