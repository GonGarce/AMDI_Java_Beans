package io.gongarce.ud2_components.info_message;

/**
 *
 * @author gag
 */
public interface InfoMessageEventListener {
    default void onClose(){};
    default void onAction(String button){};
}
