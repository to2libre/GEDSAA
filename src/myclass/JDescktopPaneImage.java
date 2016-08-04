/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclass;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author carlos860920
 */
public class JDescktopPaneImage extends JDesktopPane {

    private Image img;

    public JDescktopPaneImage() {
    }

    public JDescktopPaneImage(String nombreImagen) {
        if (nombreImagen != null) {
            img = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        }
    }

    public JDescktopPaneImage(Image imagenInicial) {
        if (imagenInicial != null) {
            img = imagenInicial;
        }
    }

    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            img = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        } else {

            img = null;
        }

        repaint();
    }

    public void setImagen(Image nuevaImagen) {
        img = nuevaImagen;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(),this);
            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }
}
