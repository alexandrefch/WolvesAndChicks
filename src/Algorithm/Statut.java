package Algorithm;

import java.util.Arrays;

public class Statut {

    private int[] wolves;
    private int[] chicks;
    private boolean _isBoatLeft;
    private int depth;
    private Statut parent;

    public int[] getWolves() { return wolves; }
    public int[] getChicks() { return chicks; }
    public boolean isBoatLeft() { return _isBoatLeft; }
    public boolean isValid(){ return !( wolves[0] > chicks[0] && chicks[0] != 0 || wolves[1] > chicks[1] && chicks[1] != 0); }
    public int getDepth() { return depth; }
    public Statut getParent() { return parent; }

    public Statut( int[] wolves , int[] chicks ){
        this.wolves = wolves;
        this.chicks = chicks;
        this._isBoatLeft = true;
        this.depth = 0;
        this.parent = null;
    }
    public Statut( int[] wolves , int[] chicks , Statut parent ){
        this.wolves = wolves;
        this.chicks = chicks;
        this._isBoatLeft = !parent._isBoatLeft;
        this.depth = parent.getDepth() + 1;
        this.parent = parent;
    }

    public Queue getAllChild() {
        Queue file = new Queue();

        int i = _isBoatLeft?0:1;

        if ( wolves[i] >= 2)
            file.add(new Statut(new int[]{wolves[0] + 2 * ((i == 0) ? -1 : 1), wolves[1] + 2 * ((i == 1) ? -1 : 1)}, chicks,this));
        if ( wolves[i] >= 1)
            file.add(new Statut(new int[]{wolves[0] + ((i == 0) ? -1 : 1), wolves[1] + ((i == 1) ? -1 : 1)}, chicks,this));

        if ( chicks[i] >= 2)
            file.add(new Statut( wolves, new int[]{chicks[0] + 2 * ((i == 0) ? -1 : 1), chicks[1] + 2 * ((i == 1) ? -1 : 1)},this));
        if ( chicks[i] >= 1)
            file.add(new Statut( wolves, new int[]{chicks[0] + ((i == 0) ? -1 : 1), chicks[1] + ((i == 1) ? -1 : 1)},this));

        if ( chicks[i] >= 1 && wolves[i] >= 1) {
            file.add(new Statut(new int[]{wolves[0] + ((i == 0) ? -1 : 1), wolves[1] + ((i == 1) ? -1 : 1)}, new int[]{chicks[0] + ((i == 0) ? -1 : 1), chicks[1] + ((i == 1) ? -1 : 1)},this));
        }

        return file;
    }

    public int showStep(){
        int i = 1;
        if(parent!=null)
            i += parent.showStep();
        System.out.println( i + " -> " + show() );
        return i;
    }

    public String show(){
        return getArray( wolves[0],'x') + getArray( chicks[0],'o') + "|        |" + getArray( wolves[1],'x') + getArray( chicks[1],'o');
    }

    private String getArray( int x , char y ){
        StringBuilder result = new StringBuilder("[");
        for (int i=0;i<3;i++){
            if( i < x ) result.append(y);
            else result.append(" ");
            if( i < 2 ) result.append(",");
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statut statut = (Statut) o;
        return Arrays.equals( wolves, statut.wolves ) &&
                Arrays.equals( chicks, statut.chicks );
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode( wolves );
        result = 31 * result + Arrays.hashCode( chicks );
        return result;
    }
}
