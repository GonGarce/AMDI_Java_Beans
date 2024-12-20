package io.gongarce.ud2_components.info_message;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorManager;
import java.beans.PropertyVetoException;
import java.util.HashSet;
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
    private InfoMessageButton[] buttons;
    private StateMessage state;

    private Color messageColor;
    private Set<PropertyChangeListener> myListeners;
    private Set<InfoMessageCloseListener> closeListeners;
    private Set<InfoMessageButtonListener> buttonsListeners;
    
    // Ejemplo una sola interfaz para todos los eventos
    private Set<InfoMessageEventListener> eventListeners;

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
        fireMyPropertyChange(PROP_STATE, oldValue, state);
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
        fireMyPropertyChange(PROP_MESSAGE, oldValue, message);
    }

    public InfoMessageButton[] getButtons() {
        return buttons;
    }

    public void setButtons(InfoMessageButton[] buttons) {
        this.buttons = buttons;
        panelButtons.removeAll();
        if (Objects.nonNull(buttons)) {
            for (InfoMessageButton button : buttons) {
                addButton(button);
            }
        }
    }

    public InfoMessageButton getButton(int position) {
        if (position > buttons.length - 1) {
            return null;
        }
        return buttons[position];
    }

    public void setButton(int position, InfoMessageButton button) {
        if (Objects.nonNull(this.buttons) && position < this.buttons.length) {
            this.buttons[position] = button;
            addButton(position, button);
        }
    }

    private JButton createButton(InfoMessageButton infoButton) {
        JButton button = new JButton(infoButton.getLabel());
        if (Objects.nonNull(infoButton.getListener())) {
            button.addActionListener(infoButton.getListener());
        }
        button.addActionListener((e) -> {
            callButtonsListeners(infoButton.getLabel());
        });
        return button;
    }

    private void addButton(InfoMessageButton button) {
        panelButtons.add(createButton(button));
    }

    private void addButton(int position, InfoMessageButton button) {
        panelButtons.remove(position);
        panelButtons.add(createButton(button), position);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getMyListeners().add(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getMyListeners().remove(listener);
    }

    public void fireMyPropertyChange(String propertyName, Object oldValue, Object newValue) {
        super.firePropertyChange(propertyName, oldValue, newValue);
        for (var listener : getMyListeners()) {
            listener.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
        }
    }

    private Set<PropertyChangeListener> getMyListeners() {
        if (Objects.isNull(myListeners)) {
            myListeners = new HashSet<>();
        }
        return myListeners;
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
        jButton1 = new javax.swing.JButton();

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
        gridBagConstraints.gridwidth = 2;
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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        add(panelButtons, gridBagConstraints);

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        add(jButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    private Set<InfoMessageEventListener> getEventListeners() {
        if (Objects.isNull(eventListeners)) {
            eventListeners = new HashSet<>();
        }
        return eventListeners;
    }

    public void addEventListener(InfoMessageEventListener listener) {
        // Save listener
        getEventListeners().add(listener);
    }

    public void removeEventListener(InfoMessageEventListener listener) {
        getEventListeners().remove(listener);
    }
    
    private Set<InfoMessageButtonListener> getButonsListeners() {
        if (Objects.isNull(buttonsListeners)) {
            buttonsListeners = new HashSet<>();
        }
        return buttonsListeners;
    }

    public void addButtonsListener(InfoMessageButtonListener listener) {
        // Save listener
        getButonsListeners().add(listener);
    }

    public void removeButtonsListener(InfoMessageButtonListener listener) {
        getButonsListeners().remove(listener);
    }

    private void callButtonsListeners(String button) {
        // Here we will call 'onClose()'
        for (var listener : eventListeners) {
            if (Objects.nonNull(listener)) {
                listener.onAction(button);
            }
        }
    }

    private Set<InfoMessageCloseListener> getCloseListeners() {
        if (Objects.isNull(closeListeners)) {
            closeListeners = new HashSet<>();
        }
        return closeListeners;
    }

    public void addCloseListener(InfoMessageCloseListener listener) {
        // Save listener
        getCloseListeners().add(listener);
    }

    public void removeCloseListener(InfoMessageCloseListener listener) {
        getCloseListeners().remove(listener);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Here we will call 'onClose()'
        for (var listener : eventListeners) {
            if (Objects.nonNull(listener)) {
                listener.onClose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelButtons;
    // End of variables declaration//GEN-END:variables
}
