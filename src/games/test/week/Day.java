package games.test.week;

import games.test.week.actions.ActionEvent;

public class Day {

    ActionEvent action;
    ActionEvent event; // si y'en a un

    public Day() {
    }

    public void setAction(ActionEvent action) {
        this.action = action;
    }
    public void setEvent(ActionEvent event) {
        this.event = event;
    }

    public boolean hasEvent(){
        if(this.event != null){
            return true;
        }
        return false;
    }
    public ActionEvent getAction(){
        return this.action;
    }
    public ActionEvent getEvent() {
        return this.event;
    }
}
