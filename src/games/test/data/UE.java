package games.test.data;

public class UE {

    private int nbPoints;
    private int threshold;
    private String name;
    private boolean validated;

    public UE (String name, int t) {
        this.name = name;
        this.nbPoints = 0;
        this.threshold = t;
        this.validated = false;
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
        if (nbPoints >= threshold) {
            validated = true;
        }
    }

    public void addPoints(int points) {
        this.nbPoints += points;
        if (nbPoints >= threshold) {
            validated = true;
        }
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

}
