package imageviewer.ui.swing;

import imageviewer.model.Image;
import imageviewer.ui.ImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;

    public SwingImageDisplay() {
        super(new BorderLayout());
    }
    
    @Override
    public Image currentImage() {
        return image;
    }

    @Override
    public void display(Image image) {
        this.image = image;
        this.removeAll();
        this.add(panel());
        this.updateUI();
    }

    private Component panel() {
        return new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {                
                try {
                    g.drawImage(ImageIO.read(image.inputStream()), 0, 0, this.getWidth(), this.getHeight(), null);
                } 
                catch (IOException ex) {
                    
                }
            }
        };
    }
}
