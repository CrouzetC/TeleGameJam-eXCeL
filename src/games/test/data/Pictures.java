package games.test.data;

import org.newdawn.slick.Image;

public class Pictures {

    Image text_field_image;
    Image dialogue_background;

    public Pictures() {
        try {
            text_field_image = new Image("res/images/dialogue/text_zone.png");
            dialogue_background = new Image("res/images/dialogue/corridores.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getText_field_image() {
        return this.text_field_image;
    }
    public Image getDialogue_background() {
        return this.dialogue_background;
    }

}
