package UI;

import Algorithm.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Game extends JPanel {

    public static int getHorizontalOffset(){ return (int)horizontalOffset; }

    private static Game component;
    private static double horizontalOffset = 0;
    private static double scale;
    private static int processScale( int value ){ return (int)(scale*(double)value); }

    public static final int BOAT_Y = 240;

    public Game(){

        component = this;
        loadSprite();

        this.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized( ComponentEvent e) { update(); }
            @Override public void componentMoved(ComponentEvent e) {}
        });

    }

    private void loadSprite(){
        Sprite.load( "background" );
        Sprite.load( "boat" );
        Sprite.load( "chicken" );
        Sprite.load( "wolf" );
        Sprite.load( "chicken_r" );
        Sprite.load( "wolf_r" );
    }

    public static void update(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { component.repaint(); }
        });
    }

    public void paintComponent( Graphics g ){

        scale = (double)getHeight() / 363;

        Sprite.updateSpriteSize( scale );

        horizontalOffset = ((double)getWidth() - Sprite.get( "background" ).getWidth())/2;

        super.paintComponent(g);
        g.drawImage(Sprite.get( "background" ).getImage(), getHorizontalOffset(),0, this);
        g.drawImage(Sprite.get( "boat" ).getImage(), getHorizontalOffset() + processScale(120 + (int)Animation.boatPos) , processScale( BOAT_Y ) ,this );
        for( int i=0; i<3; i++ ){
            Image sprite = (Animation.isLeft[i])?Sprite.get( "wolf" ).getImage():Sprite.get( "wolf_r" ).getImage();
            g.drawImage(sprite, getHorizontalOffset() + processScale(Animation.positions[i].X()) , processScale(Animation.positions[i].Y()) ,this );
        }
        for( int i=3; i<6; i++ ) {
            Image sprite = (Animation.isLeft[i])?Sprite.get( "chicken" ).getImage():Sprite.get( "chicken_r" ).getImage();
            g.drawImage( sprite, getHorizontalOffset() + processScale( Animation.positions[i].X() ), processScale( Animation.positions[i].Y() ), this );
        }
    }

}
