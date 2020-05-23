package games.test.week.actions.dialogue;

import java.util.ArrayList;

public class Choice {

    // i < nb_stats : effects[i] = modification de statistics[i]
    // i = nb_stats : effects[i] = avancement du projet
    int[] effects;

    private String txt; // réponse du joueur principal

    private ArrayList<DialoguePiece> followingDialogue; // les prochaines répliques des NPC

    public Choice(String txt, int[] effects) {
        followingDialogue = new ArrayList<DialoguePiece>();
        this.txt = txt;
        this.effects = effects;
    }

    public String getText() {
        return txt;
    }

    public ArrayList<DialoguePiece> getFollowingDialoguePieces() {
        return followingDialogue;
    }

    public void addFollowingDialoguePiece(DialoguePiece d) {
        this.followingDialogue.add(d);
    }

    public ArrayList<DialoguePiece> getFollowingDialogue() {return this.followingDialogue;}

    public int[] getEffects() {return this.effects;}

}
