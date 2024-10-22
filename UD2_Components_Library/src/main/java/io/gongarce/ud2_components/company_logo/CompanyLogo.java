package io.gongarce.ud2_components.company_logo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author Gonzalo
 */
public class CompanyLogo extends JComponent implements Serializable {

    public static final String PROP_SIZE = "logoSize";

    private static final String LOGO_RESOURCE = "/images/logo.png";
    private static BufferedImage image;

    private int logoSize;

    public CompanyLogo() {
        if (Objects.isNull(image)) {
            image = getImage();
        }
        changeSize(48);
    }

    public int getLogoSize() {
        return logoSize;
    }

    public void setLogoSize(int logoSize) {
        if (logoSize >= 1) {
            int oldValue = this.logoSize;
            changeSize(logoSize);
            firePropertyChange(PROP_SIZE, oldValue, logoSize);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRoundRect(0, 0, logoSize + 10, logoSize + 10, 24, 24);
        g.setColor(Color.red);
        g.drawRoundRect(0, 0, logoSize + 10, logoSize + 10, 24, 24);
        if (Objects.nonNull(image)) {
            g.drawImage(image, 6, 6, calculateWidth(), calculateHeight(), this);
        }
    }

    private void changeSize(int size) {
        this.logoSize = size;
        Dimension d = new Dimension(size + 12, size + 12);
        this.setSize(d);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
    }

    private int calculateWidth() {
        if (Objects.isNull(image) || image.getWidth() == -1) {
            return 0;
        }

        if (image.getWidth() < image.getHeight()) {
            return (int) Math.floor(((float) logoSize) / ((float) image.getHeight()) * ((float) image.getWidth()));
        }

        return logoSize;
    }

    private int calculateHeight() {
        if (Objects.isNull(image) || image.getWidth() == -1) {
            return 0;
        }

        if (image.getHeight() < image.getWidth()) {
            return (int) Math.floor(((float) logoSize) / ((float) image.getWidth()) * ((float) image.getHeight()));
        }

        return logoSize;
    }

    private BufferedImage getImage() {
        try {
            return ImageIO.read(getClass().getResource(LOGO_RESOURCE));
        } catch (IOException ex) {
            Logger.getLogger(CompanyLogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
