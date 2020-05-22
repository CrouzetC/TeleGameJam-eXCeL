package games.test.data;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Project {
	
	private String name;
	private UE ue;
	private int nbPoints;
	private int qtyWork;
	private int progression;

	private Image icon;
	private static Image whiteHeart, redHeart;
	private Image activeHeart;
	
	
    public Project(String name, UE ue, int w, String file) {
    	this.name = name;
    	this.ue = ue;
    	this.nbPoints = 0;
    	this.qtyWork = w;
    	this.progression = 0;
		try {
			this.icon = new Image(file);
			this.whiteHeart = new Image("res/images/white_heart.png");
			this.redHeart = new Image("res/images/red_heart.png");
			this.activeHeart = whiteHeart;
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

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

	public int getQtyWork() {
		return qtyWork;
	}

	public void setQtyWork(int qtyWork) {
		this.qtyWork = qtyWork;
	}

	public int getProgression() {
		return progression;
	}

	public void setProgression(int progression) {
    	// Mettre a jour le coeur en fonction de la progression
		this.progression = progression;
		if (this.progression == 100) {
			this.activeHeart = redHeart;
		} else {
			this.activeHeart = whiteHeart;
		}
	}



}
