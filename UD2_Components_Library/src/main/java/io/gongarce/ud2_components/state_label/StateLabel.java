package io.gongarce.ud2_components.state_label;

import java.awt.Color;
import java.beans.PropertyEditorManager;
import javax.swing.JLabel;

/**
 *
 * @author Gonzalo
 */
public class StateLabel extends JLabel {

    private State state;
    private Color textColor;

    public StateLabel() {
        PropertyEditorManager.registerEditor(State.class, StatePropertyEditor.class);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        switch (state) {
            case ERROR ->
                setForeground(Color.red);
            case SUCCESS ->
                setForeground(Color.GREEN);
            case WARNING ->
                setForeground(Color.YELLOW);
            default ->
                setForeground(this.getTextColor());
        }
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        if (State.DEFAULT.equals(this.state)) {
            setForeground(textColor);
        }
    }
}
