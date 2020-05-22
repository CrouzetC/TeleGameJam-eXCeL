package games.test.week.actions.dialogue;

public class Line implements DialoguePiece {

    private String characterName;
    private String txt;

    public Line(String characterName, String txt) {
        this.characterName = characterName;
        this.txt = txt;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getText() {
        return txt;
    }

}
