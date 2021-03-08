package UI;

import java.util.Objects;
import java.util.Vector;

public class Vector2 {

    private int _x;
    private int _y;

    public boolean isValid() { return _x >= 0 && _x < 8 && _y >= 0 && _y < 8; }

    public int X() { return _x; }
    public int Y() { return _y; }

    public Vector2(){
        _x=0;
        _y=0;
    }

    public Vector2( int x , int y ){
        _x = x;
        _y = y;
    }

    public void set( int x , int y ){ _x = x; _y = y; }

    public Vector2 add( int x , int y ){
        return new Vector2( _x + x, _y + y );
    }

    @Override
    public String toString(){ return "(" + _x + "," + _y + ")"; }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Vector2 vector2 = (Vector2) o;
        return _x == vector2._x &&
                _y == vector2._y;
    }

    @Override
    public int hashCode() {
        return Objects.hash( _x, _y );
    }
}
