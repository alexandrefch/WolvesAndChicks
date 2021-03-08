package UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Sprite {

    public BufferedImage getImage() { return image; }
    public String getName() { return name; }
    public int getHeight() { return height; }
    public int getWidth() { return width; }

    private BufferedImage image;
    private BufferedImage original_picture;
    private String name;
    private int height;
    private int width;

    private Sprite( String name ) {
        try {
            this.original_picture = ImageIO.read( Sprite.class.getResource("/Resources/" + name + ".png")  );
        }
        catch( IOException e ){ System.out.println( e.getMessage() ); }
        catch ( IllegalArgumentException e ){ System.out.println("[ERROR] No image call : '" + name + ".png' in resources folder"); }
        this.name = name;
        resize(1);
    }

    public void resize(double scale){
        int w = original_picture.getWidth();
        int h = original_picture.getHeight();
        BufferedImage after = new BufferedImage((int)(w*scale), (int)(h*scale), BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        image = scaleOp.filter(original_picture, after);
        height = image.getHeight();
        width = image.getWidth();
    }

    //-----------------------------------------

    private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    public static void load( String name ) {
        sprites.add(new Sprite(name));
    }

    public static void updateSpriteSize( double scale ){

        for ( Sprite img : sprites )
            img.resize( scale );

    }

    public static Sprite get( String name ) {
        for (Sprite sprite : sprites) {
            if (sprite.getName().equals(name))
                return sprite;
        }
        return null;
    }

}
