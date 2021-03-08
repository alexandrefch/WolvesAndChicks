package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Title extends JLabel {

    public Title(){
        super("Problème de la traversée");
        this.setBorder(new EmptyBorder(10,0,10,0));
        this.setFont(new Font("Lucida",Font.BOLD, 16));
        this.setAlignmentX(CENTER_ALIGNMENT);
    }

}
