package games.test.week.actions.dialogue;

import games.test.week.actions.Dialogue;

import java.util.ArrayList;

public class Choice {

    // TODO : effet du choix ??

    private String txt; // réponse du joueur principal

    private ArrayList<DialoguePiece> followingDialogue; // les prochaines répliques des NPC

    public Choice(String txt) {
        followingDialogue = new ArrayList<DialoguePiece>();
        this.txt = txt;
    }

    public String getText() {
        return txt;
    }

    public ArrayList<DialoguePiece> getFollowingDialoguePiece() {
        return followingDialogue;
    }

    public void addFollowingDialoguePiece(DialoguePiece d) {
        this.followingDialogue.add(d);
    }

}
