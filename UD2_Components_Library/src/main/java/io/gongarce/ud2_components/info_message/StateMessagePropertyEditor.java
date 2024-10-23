package io.gongarce.ud2_components.info_message;

import java.beans.PropertyEditorSupport;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class StateMessagePropertyEditor extends PropertyEditorSupport {

    private JPanel panel;
    private JComboBox<StateMessage> comboBox;

    public StateMessagePropertyEditor() {
        // Inicializamos el JComboBox con los valores del enum StateMessage
        comboBox = new JComboBox<>(StateMessage.values());

        // Panel para contener el JComboBox
        panel = new JPanel();
        panel.add(comboBox);

        // Listener para actualizar el valor cuando el usuario seleccione un estado
        comboBox.addActionListener(e -> {
            setValue(comboBox.getSelectedItem());
        });
    }

    // Este método indica que se puede proporcionar un editor personalizado
    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    // Devuelve el componente que servirá como editor visual
    @Override
    public java.awt.Component getCustomEditor() {
        return panel;
    }

    // Convierte el valor a texto (para editores de texto)
    @Override
    public String getAsText() {
        Object value = getValue();
        return (value != null) ? value.toString() : StateMessage.INFO.toString();
    }

    // Convierte un texto en un valor
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(StateMessage.valueOf(text));
        } catch (Exception e) {
            setValue(StateMessage.INFO); // Valor por defecto si no coincide el texto
        }
    }

    // Este método actualiza el JComboBox cuando se cambia el valor programáticamente
    @Override
    public void setValue(Object value) {
        super.setValue(value);
        comboBox.setSelectedItem(value);
    }
}
