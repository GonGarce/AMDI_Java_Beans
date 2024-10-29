package io.gongarce.ud2_components;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author gag
 */
public class Serializer {

    public static void save(String where, Serializable what) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(where);
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(what);
            } finally {
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T read(String where, Class<T> whatClass) {
        try {
            FileInputStream fis = new FileInputStream(where);
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fis)) {
                return whatClass.cast(objectInputStream.readObject());
            } finally {
                fis.close();
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
