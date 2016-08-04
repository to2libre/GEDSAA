/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclass;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author carlos860920
 */
public class JPanelImage extends JPanel {

    private Image imagen;    

    public JPanelImage() {
    }   

    public JPanelImage(String nombreImagen) {
        if (nombreImagen != null) {
            //imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
            imagen = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(nombreImagen));
        }
    }

    public JPanelImage(Image imagenInicial) {
        if (imagenInicial != null) {
            imagen = imagenInicial;
        }
    }

    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
            imagen = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(nombreImagen));
        } else {
            imagen = null;
        }

        repaint();
    }

    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        } else {
            setOpaque(true);
        }
        super.paint(g);
    }
}
