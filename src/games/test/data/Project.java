package games.test.data;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Project {
	
	private String name;
	private UE ue;
	private int nbPoints;
	private int threshold;
	private int qtyWork;
	private int workedDays;
	private int progression;
	private double[] statCriteria;

	private Image icon;
	private static Image whiteHeart, redHeart;
	private Image activeHeart;
	
	
    public Project(String name, UE ue, int t, int w, double[] stats, String file) {
    	this.name = name;
    	this.ue = ue;
        this.threshold = t;
        this.nbPoints = 0;
    	this.qtyWork = w;   // In days
        this.workedDays = 0;
    	this.progression = 0;
    	this.statCriteria = stats;
		try {
			this.icon = new Image(file);
			this.whiteHeart = new Image("images/white_heart.png");
			this.redHeart = new Image("images/red_heart.png");
			this.activeHeart = whiteHeart;
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public Image getActiveHeart() { return activeHeart; }

	public Image getIcon() { return icon; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UE getUE() {
		return ue;
	}

	public void setUE(UE ue) {
		this.ue = ue;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getQtyWork() {
		return qtyWork;
	}

	public void setQtyWork(int qtyWork) {
		this.qtyWork = qtyWork;
	}

    public int getWorkedDays() {
        return workedDays;
    }

    public void addWorkedDay() {
        this.workedDays++;
    }

    public int getProgression() {
		return progression;
	}

	public void setProgression(int progression) {
		// Mettre a jour le coeur en fonction de la progression
		this.progression = progression;
		if (this.progression >= 100) {
			this.progression = 100;
			this.activeHeart = redHeart;
		} else {
			this.activeHeart = whiteHeart;
		}
	}

	public void addProgression(int added_progression) {
    	// Mettre a jour le coeur en fonction de la progression
		this.progression += added_progression;
		if (this.progression >= 100) {
			this.progression = 100;
			this.activeHeart = redHeart;
		} else {
			this.activeHeart = whiteHeart;
		}
	}

	// Harmoniser pour que ce soit au pire des vecteurs stochastiques
    public double[] getStatCriteria() {
        // Met en avant les statistiques du joueur les plus importantes de la matiere
        return statCriteria;
    }

    public void setStatCriteria(double[] statCriteria) {
        this.statCriteria = statCriteria;
    }

}
