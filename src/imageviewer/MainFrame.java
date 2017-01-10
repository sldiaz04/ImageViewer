package imageviewer;

import imageviewer.control.Command;
import imageviewer.ui.ImageDisplay;
import imageviewer.ui.swing.SwingImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    
    private Map<String,Command> commands = new HashMap<>();
    private ImageDisplay imageDisplay;

    public MainFrame() {
        this.setTitle("Image VIewer");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(image());
        this.add(toolbar(), BorderLayout.SOUTH);
        setVisible(true);
    }

    private Component image() {
        SwingImageDisplay display = new SwingImageDisplay();
        imageDisplay=display;
        return display;
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }
    
    public void add(Command command){
        commands.put(command.name(), command);
    }

    private Component toolbar() {
        JPanel panels =  new JPanel();
        panels.add(buttons("Prev"));
        panels.add(buttons("Next"));
        return panels;
    }

    private Component buttons(String name) {
        JButton button =  new JButton(name);
        button.addActionListener(execute(name));
        return button;
    }

    private ActionListener execute(String name) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        };
    }
    
}
