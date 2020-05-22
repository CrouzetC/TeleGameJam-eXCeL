package games.test.data;

public class Player {
    String name;

    int[] statistics;
    /* statistics[0] : Stress, Fatigue;
    * statistics[1] : Algo, Théorie;
    * statistics[2] : java, POO;
    * statistics[3] : C, C++, RS;
    * statistics[4] : SQL, Excel, IA;
    * statistics[5] : Charisme, Social, GdP;
    */

    /*  GETTER ET SETTER */
    public void setName(String name) {
        this.name = name;
    }
    public void setStatistics(int[] statistics) {
        this.statistics = statistics;
    }
    public void setStatisticsIndex(int index, int value){
        if( index < statistics.length ) {
            this.statistics[index] = value;
        }
    }

    public String getName() {
        return name;
    }
    public int[] getStatistics() {
        return statistics;
    }
    public void getStatisticsIndex(int index){
        if( index < statistics.length ) {
            return this.statistics[index];
        }else{
            return 101; // Erreur on renvoit une valeur supérieur à 100
        }
    }
}
