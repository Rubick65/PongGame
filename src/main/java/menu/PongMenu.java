package menu;

import javax.swing.*;
import java.awt.*;

public class PongMenu {

    public PongMenu() {
        JFrame ventanaMenu = new JFrame("Pong Menu");
        ventanaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaMenu.setResizable(false);

        ventanaMenu.setPreferredSize(new Dimension(700, 500));
        ventanaMenu.pack();
        ventanaMenu.setLocationRelativeTo(null);

        addComponents(ventanaMenu);


        ventanaMenu.setVisible(true);
    }


    private void addComponents(JFrame ventanaMenu) {
        createButtonPanel();

        ventanaMenu.add(buttonPanel());
    }


    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(buttonPanel(), BorderLayout.CENTER);
        
    }


    private JPanel buttonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton play = playButton("Play");
        JButton exit = playButton("Exit");

        panel.add(play);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exit);

        JPanel panelButtons = new JPanel(new GridBagLayout());
        panelButtons.add(panel);

        return panelButtons;
    }


    private JButton playButton(String buttonName) {
        JButton button = new JButton(buttonName);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setPreferredSize(new Dimension(150, 50));
        button.setMaximumSize(new Dimension(150, 50));
        button.setMinimumSize(new Dimension(150, 50));
        return button;
    }


}
