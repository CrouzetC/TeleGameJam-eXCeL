package games.test.week.actions;

import games.test.data.GameData;
import games.test.data.Project;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.concurrent.ThreadLocalRandom;


public class Date implements ActionEvent {

    Font awtFont;
    TrueTypeFont font;
    boolean isOver;
    Image image;
    Project project;
    Image projectImage;
    GameData data;

    public Date(GameData data) {
        awtFont = new Font("vt323", Font.BOLD, 12);
        font = new TrueTypeFont(awtFont, true);
        isOver = false;
        try {
            image = new Image("images/dialogue/corridores.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.data = data;
        project = null;
        projectImage = null;
    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
        if (project == null) {
            // récupération d'un projet
            project = data.attributeRandomProject();
            try {
                projectImage = project.getIcon();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // attribution au joueur
            data.getPlayer().addProject(project);
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics context) {

        image.draw(0, 0, container.getWidth(), container.getHeight());

        if (projectImage != null)
            projectImage.draw((int)(0.4*container.getWidth()), (int)(0.4*container.getWidth()), (int)(0.2*container.getWidth()), (int)(0.2*container.getHeight()));

        font.drawString(20, 20, "You received a new project !", Color.red);

    }

    public void keyPressed(int key, char c) {
        project = null;
        projectImage = null;
        if (key == Input.KEY_ENTER)
            isOver = true;
    }

    public void mousePressed(int button, int x, int y) {
        isOver = true;
    }

    public boolean isOver() {
        return isOver;
    }

}
