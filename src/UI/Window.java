package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {

    public static void setButtonEnable( boolean statut ){ button.setEnabled( statut ); }

    private static JButton button;

    public Window(){
        this.setTitle("Algorithmique avancée");
        this.setSize(700, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        mainPanel.add(new Title());
        mainPanel.add(new Game());
        mainPanel.add(new ControlPanel());

        button = new JButton( "Resoudre" );
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(new Animation());
        mainPanel.add(button);

        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

}
