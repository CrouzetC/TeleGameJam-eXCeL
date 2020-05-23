package games.test.data;

public class UE {

    private int nbPoints;
    private int threshold;
    private String name;

    public UE (String name, int t) {
        this.name = name;
        this.nbPoints = 0;
        this.threshold = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public void addPoints(int points) {
        this.nbPoints += points;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}
