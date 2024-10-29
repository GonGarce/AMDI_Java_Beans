package io.gongarce.ud2_components.info_message;

import java.awt.event.ActionListener;

/**
 *
 * @author gag
 */
public class InfoMessageButton {
    private String label;
    private ActionListener listener;

    public InfoMessageButton(String label, ActionListener listener) {
        this.label = label;
        this.listener = listener;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }
    
    
}
