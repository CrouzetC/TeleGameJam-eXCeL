package games.test.week;

import games.test.week.actions.Action;

import java.util.ArrayList;

public class Day {

    Action task;
    Action event; // si y'en a un

    public Day() {
    }

    public void setAction(ActionEvent action) {
        this.task = action;
    }
    public void setEvent(Action action) {
        this.event = action;
    }

    public boolean hasEvent(){
        if(this.event != null){
            return true;
        }
        return false;
    }
    public Action getTask(){
        return this.task;
    }
    public Action getEvent() {
        return event;
    }
}