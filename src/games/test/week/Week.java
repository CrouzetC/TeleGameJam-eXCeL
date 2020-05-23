package games.test.week;

import games.test.week.Day;
import games.test.week.actions.ActionEvent;

import java.util.ArrayList;

public class Week {

    public static int nb_days = 7;

    private int currentDay; // 0 si on a pas encore commencé la semaine
    private boolean nextIsAction; // true si la nextAction sera une task

    ArrayList<Day> days;

    public Week() {
        this.days = new ArrayList<Day>();
        for(int i=0 ; i<nb_days; i++){
            this.days.add(new Day());
        }
        this.currentDay = 0;
        this.nextIsAction = true;
    }

    public void setActionofDayIndex(int index, ActionEvent action){
        Day day = this.days.get(index);
        day.setAction(action);
    }
    public void setEventofDayIndex(int index, ActionEvent event){
        this.days.get(index).setEvent(event);
    }

    public ActionEvent getNextActionEvent(){

        if (this.nextIsAction){
            this.nextIsAction = false;
            return days.get(this.currentDay).getAction();
        }else{
            if(days.get(this.currentDay).hasEvent()){ // On a un event ! on le retourne et prépare le jour suivant
                this.nextIsAction = true;
                this.currentDay += 1;
                return days.get(this.currentDay-1).getEvent();
            }else{ // Y'a pas d'event faut retourner la task du jour suivant
                this.currentDay += 1;
                if (currentDay > nb_days-1)
                    return null;
                else
                    return days.get(this.currentDay).getAction();
            }
        }

    }

}
