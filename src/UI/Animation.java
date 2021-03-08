package UI;

import Algorithm.Solver;
import Algorithm.Statut;

import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class Animation implements ActionListener {

    public static final int REFRESH_RATE = 30;
    public static final int ANIMATION_DURATION = 1000;

    public Animation() {
        placeAnimals( Solver.etat_init );
    }

    @Override
    public void actionPerformed( ActionEvent actionEvent ) {
        Thread t = new Thread() {
            public void run() {
                Window.setButtonEnable( false );
                Solver.solve( ControlPanel.currentSelected(), ControlPanel.getMaxDepth() );
                Window.setButtonEnable( true );
            }
        };
        t.start();
    }

    public static Vector2[] positions = new Vector2[]{
            new Vector2(),
            new Vector2(),
            new Vector2(),
            new Vector2(),
            new Vector2(),
            new Vector2()
    };

    public static boolean[] isLeft = new boolean[6];
    public static int[] onBoat = new int[] {0,0};

    public static double boatPos = 0;
    public static final int boatDistance = 190;

    private static long tickTime = (long) ( (double) 1000 / REFRESH_RATE );
    private static double speed = (double) boatDistance / ANIMATION_DURATION;

    public static void playAnim( Statut statut ) {

        placeAnimals( Solver.etat_init );
        boolean isBoatLeft = !statut.isBoatLeft();

        if ( statut.getParent() != null ) {
            playAnim( statut.getParent() );
        } else {
            placeAnimals( statut );
            return;
        }

        int j = ( isBoatLeft ) ? 0 : 1;
        onBoat = new int[]{statut.getParent().getWolves()[j] - statut.getWolves()[j], statut.getParent().getChicks()[j] - statut.getChicks()[j]};
        boatPos = ( isBoatLeft ) ? 0 : 190;

        do {

            placeAnimals( statut, isBoatLeft, onBoat );
            boatPos += ( ( isBoatLeft ) ? 1 : -1 ) * speed * tickTime;

            if ( boatPos < 0 ) boatPos = 0;
            if ( boatPos > 190 ) boatPos = 190;

            Game.update();

            try {
                Thread.sleep( tickTime );
            } catch ( InterruptedException ignored ) {
            }

        } while ( boatPos != 0 && boatPos != 190 );

        placeAnimals( statut );

        try {
            Thread.sleep( 250 );
        } catch ( InterruptedException ignored ) {
        }

    }

    public static void placeAnimals( Statut statut ) {

        for ( int i = 0; i < statut.getWolves()[0]; i++ ) {
            positions[i] = new Vector2( 75 - i * 35, 12 * i + 180 );
            isLeft[i] = true;
        }
        for ( int i = 0; i < statut.getWolves()[1]; i++ ) {
            positions[statut.getWolves()[0]+i] = new Vector2( 410 + i * 35, 12 * i + 180 );
            isLeft[statut.getWolves()[0]+i] = false;
        }
        //--------------------------------------

        for ( int i = 0; i < statut.getChicks()[0]; i++ ) {
            positions[i + 3] = new Vector2( 75 - i * 35, 12 * i + 230 );
            isLeft[i + 3] = true;
        }

        for ( int i = 0; i < statut.getChicks()[1]; i++ ) {
            positions[i + 3+statut.getChicks()[0]] = new Vector2( 420 + i * 35, 12 * i + 230 );
            isLeft[i + 3+statut.getChicks()[0]] = false;
        }

        Game.update();

    }

    public static void placeAnimals( Statut statut , boolean isBoatLeft , int[] onBoat ) {

        int amountOnboat = 0;

        for ( int i = 0; i < statut.getParent().getWolves()[0]; i++ ) {
            if( isBoatLeft && i >= statut.getParent().getWolves()[0] - onBoat[0] ) {
                amountOnboat++;
                positions[i] = new Vector2( (int) (110 + boatPos + amountOnboat * 30 ), Game.BOAT_Y-30 );
            } else
                positions[i] = new Vector2( 75 - i * 35, 12 * i + 180 );
            isLeft[i] = true;
        }

        for ( int i = 0; i < statut.getParent().getWolves()[1]; i++ ) {
            if( !isBoatLeft && i >= statut.getParent().getWolves()[1] - onBoat[0] ) {
                amountOnboat++;
                positions[statut.getParent().getWolves()[0]+i] = new Vector2( (int) (110 + boatPos + amountOnboat * 30 ), Game.BOAT_Y-20 );
            } else
                positions[statut.getParent().getWolves()[0]+i] = new Vector2( 410 + i * 35, 12 * i + 180 );
            isLeft[statut.getParent().getWolves()[0]+i] = false;
        }
        //--------------------------------------

        for ( int i = 0; i < statut.getParent().getChicks()[0]; i++ ) {
            if( isBoatLeft && statut.getParent().getChicks()[0] - onBoat[1] <= i ) {
                amountOnboat++;
                positions[i + 3] = new Vector2( (int) ( 120 + boatPos + amountOnboat * 30 ), Game.BOAT_Y );
            }else
                positions[i + 3] = new Vector2( 75 - i * 35, 12 * i + 230 );
            isLeft[i + 3] = true;
        }

        for ( int i = 0; i < statut.getParent().getChicks()[1]; i++ ) {
            if( !isBoatLeft && i >= statut.getParent().getChicks()[1] - onBoat[1] ) {
                amountOnboat++;
                positions[i + 3+statut.getParent().getChicks()[0]] = new Vector2( (int) (120 + boatPos + amountOnboat * 30 ), Game.BOAT_Y );
            } else
                positions[i + 3+statut.getParent().getChicks()[0]] = new Vector2( 420 + i * 35, 12 * i + 230 );
            isLeft[i + 3+statut.getParent().getChicks()[0]] = false;
        }

        Game.update();

    }

}