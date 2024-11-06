package io.gongarce.ud2_components.info_message;

import java.io.Serializable;

/**
 *
 * @author gag
 */
public class InfoMessageModel implements Serializable {
    private String title;
    private String message;
    private StateMessage state;

    public InfoMessageModel() {
        
    }
    
    public InfoMessageModel(String title, String message, StateMessage state) {
        this.title = title;
        this.message = message;
        this.state = state;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StateMessage getState() {
        return state;
    }

    public void setState(StateMessage state) {
        this.state = state;
    }
        
}
