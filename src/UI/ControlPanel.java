package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ControlPanel extends JPanel {

    public static int currentSelected() { return comboBox.getSelectedIndex(); }
    private static JComboBox<String> comboBox;

    public static int getMaxDepth() { return Integer.parseInt(depth); }
    private static String depth = "14";

    public static void setTempsExe( int time ){ tempsExe.setText( Integer.toString(time) + " ms");}
    private static JLabel tempsExe = new JLabel("0 ms");

    public static void setNbNoeuds( int nb ){ nbNoeuds.setText(Integer.toString(nb));}
    private static JLabel nbNoeuds = new JLabel("0");

    public static void setDeroulement( int x , int y ){ deroulement.setText(Integer.toString(x)+"/"+Integer.toString(y));}
    private static JLabel deroulement = new JLabel("0");

    public ControlPanel() {
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setMaximumSize(new Dimension(999, 50));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel p = new JPanel(new SpringLayout());

        p.add(new JLabel("Choix de parcour", JLabel.TRAILING));
        comboBox = new JComboBox<String>(new String[]{"Largeur", "Profondeur"});
        p.add(comboBox);

        JTextField maxdepth = new JTextField();
        maxdepth.setText(depth);
        maxdepth.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {SwingUtilities.invokeLater(thread);}

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {}

            public void changedUpdate(DocumentEvent e) {

            }

            Runnable thread = new Runnable() {
                @Override
                public void run() {
                    if(!maxdepth.getText().equals(depth)) {
                        maxdepth.setText(maxdepth.getText().replaceAll("[^0-9]", ""));
                        if( Integer.parseInt(maxdepth.getText()) > 99 )
                            maxdepth.setText("99");
                        depth = maxdepth.getText();
                    }
                }
            };
        });

        p.add(new JLabel("Profondeur maximal", JLabel.TRAILING));
        p.add(maxdepth);

        p.add(new JLabel("Temps d'execution", JLabel.TRAILING));
        p.add(tempsExe);

        p.add(new JLabel("Nombre de noeuds parcouru", JLabel.TRAILING));
        p.add(nbNoeuds);

        p.add(new JLabel("Deroulement", JLabel.TRAILING));
        p.add(deroulement);

        SpringUtilities.makeCompactGrid(p,
                5, 2,
                6, 6,
                6, 10);

        this.add(p);
    }
}
