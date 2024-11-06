package io.gongarce.ud2_components;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author gag
 */
public class Encoder {

    public static void save(String where, Serializable what) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(where);
            try (XMLEncoder encoder = new XMLEncoder(fileOutputStream)) {
                encoder.writeObject(what);
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
            try (XMLDecoder  decoder = new XMLDecoder(fis)) {
                return whatClass.cast(decoder.readObject());
            } finally {
                fis.close();
            }
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
