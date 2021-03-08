package Algorithm;

import UI.Animation;
import UI.ControlPanel;

public class Solver {

    public static final int BREADTH_FIRST = 0;
    public static final int DEEP_FIRST = 1;

    public static Statut etat_init = new Statut(new int[]{3, 0}, new int[]{3, 0});
    public static Statut etat_final = new Statut(new int[]{0, 3}, new int[]{0, 3});

    public static void solve( int type , int maxDepth ) {

        long startTime = System.currentTimeMillis();
        int nbNoeuds = 0;

        Statut resultat = null;
        List list = new Queue();
        if( type == BREADTH_FIRST ) list = new Queue();
        if( type == DEEP_FIRST ) list = new Stack();
        int lastDepth = 0;

        list.add(etat_init);

        while (!list.isEmpty()) {

            Statut etat = (Statut) list.remove();

            if (etat.isValid()) {
                Queue liste_etat_fils = etat.getAllChild();

                while (!liste_etat_fils.isEmpty()) {
                    Statut e = (Statut) liste_etat_fils.remove();

                    if (e.getDepth() > maxDepth)
                        break;

                    if (!e.equals(etat_init) && e.isValid())
                        list.add(e);

                    nbNoeuds++;
                    ControlPanel.setTempsExe((int)(System.currentTimeMillis()-startTime) );
                    ControlPanel.setNbNoeuds(nbNoeuds);

                    if (etat_final.equals(e)) {
                        resultat = e;
                        break;
                    }
                }

                if (resultat != null) {
                    UI.Animation.playAnim( resultat );
                    return;
                }
            }
        }
    }

}
