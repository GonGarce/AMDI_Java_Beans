package io.gongarce.ud2_components.info_message;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorManager;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 *
 * @author Gonzalo
 */
public class InfoMessage extends javax.swing.JPanel {

    public static final String PROP_TITLE = "title";
    public static final String PROP_STATE = "state";
    public static final String PROP_MESSAGE = "message";

    private String title;
    private String message;
    private String[] buttons;
    private StateMessage state;

    private Color messageColor;
    private Set<PropertyChangeListener> myListeners = new HashSet<>();

    /**
     * Creates new form InfoMessage
     */
    public InfoMessage() {
        // Relacionar el tipo de mi propiedad con el editor de propiedades personalizado
        PropertyEditorManager.registerEditor(StateMessage.class, StateMessagePropertyEditor.class);

        // Create UI
        initComponents();

        // Set default values
        setState(StateMessage.INFO);
        setTitle("Title");
        setMessage("Message");
    }

    public StateMessage getState() {
        return state;
    }

    public void setState(StateMessage state) {
        StateMessage oldValue = this.state;
        this.state = state;
        Color borderColor, bgColor;
        Icon icon;
        switch (state) {
            case ERROR -> {
                bgColor = new Color(239, 83, 80, 75);
                borderColor = new Color(198, 40, 40);
                icon = new ImageIcon(getClass().getResource("/images/icon_error.png"));
            }
            case SUCCESS -> {
                bgColor = new Color(76, 175, 80, 75);
                borderColor = new Color(27, 94, 32);
                icon = new ImageIcon(getClass().getResource("/images/icon_ok.png"));
            }
            case WARNING -> {
                bgColor = new Color(255, 152, 0, 75);
                borderColor = new Color(230, 81, 0);
                icon = new ImageIcon(getClass().getResource("/images/icon_warning.png"));
            }
            default -> {
                bgColor = new Color(3, 169, 244, 75);
                borderColor = new Color(1, 87, 155);
                icon = new ImageIcon(getClass().getResource("/images/icon_info.png"));
            }
        }
        setBackground(bgColor);
        setBorder(new LineBorder(borderColor, 1, true));
        lblIcon.setIcon(icon);
        firePropertyChange(PROP_STATE, oldValue, state);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        try {
            fireVetoableChange(PROP_TITLE, this.title, title);
        } catch (PropertyVetoException ex) {
            System.out.println("[setTitle] VetoableChange: " + ex.getMessage());
            return;
        }
        this.title = title;
        lblTitle.setText(title);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        String oldValue = this.message;
        this.message = message;
        lblMessage.setText(message);
        firePropertyChange(PROP_MESSAGE, oldValue, message);
    }

    public String[] getButtons() {
        return buttons;
    }

    public void setButtons(String[] buttons) {
        this.buttons = buttons;
        for (String button : buttons) {
            createButton(button);
        }
    }

    public String getButton(int position) {
        if (position > buttons.length - 1) {
            return null;
        }
        return buttons[position];
    }

    public void setButton(int position, String button) {
        if (Objects.nonNull(this.buttons) && position < this.buttons.length) {
            this.buttons[position] = button;
            createButton(position, button);
        }
    }

    private void createButton(String text) {
        JButton button = new JButton(text);
        panelButtons.add(button);
    }

    private void createButton(int position, String text) {
        JButton button = new JButton(text);
        panelButtons.remove(position);
        panelButtons.add(button, position);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (Objects.isNull(myListeners)) {
            myListeners = new HashSet<>();
        }
        myListeners.add(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (Objects.isNull(myListeners)) {
            myListeners = new HashSet<>();
        }
        myListeners.remove(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblIcon = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        panelButtons = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblIcon, gridBagConstraints);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        add(lblTitle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        add(lblMessage, gridBagConstraints);

        panelButtons.setBackground(new java.awt.Color(255, 255, 255));
        panelButtons.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panelButtons.setOpaque(false);
        panelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        add(panelButtons, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelButtons;
    // End of variables declaration//GEN-END:variables
}
