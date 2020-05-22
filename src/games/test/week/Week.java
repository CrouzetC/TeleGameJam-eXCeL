package games.test.week;

import games.test.week.actions.Action;
import games.test.week.Day;

import java.util.ArrayList;

public class Week {

    private int currentDay; // 0 si on a pas encore commencé la semaine
    private boolean nextIsTask; // true si la nextAction sera une task

    ArrayList<Day> days;

    public Week() {
        this.days = new ArrayList<Day>(7);
        this.currentDay = 0;
        this.nextIsTask = true;
    }

    public setTaskofDayIndex(int index, Action action){
        this.days.get(index).setTask(action);
    }
    public setEventofDayIndex(int index, Action action){
        this.days.get(index).setEvent(action);
    }

    public Action getNextAction(){
        if (this.nextIsTask){
            this.nextIsTask = false;
            return days.get(this.currentDay).getTask();
        }else{
            if(days.get(this.currentDay).hasEvent()){ // On a un event ! on le retourne et prépare le jour suivant
                this.nextIsTask = true;
                this.currentDay += 1;
                return days.get(this.currentDay-1).getEvent();
            }else{ // Y'a pas d'event faut retourner la task du jour suivant
                this.currentDay += 1;
                return days.get(this.currentDay).getTask();
            }
        }
    }

}
