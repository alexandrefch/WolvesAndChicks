package Algorithm;

public class Queue extends List {

    private List.Cell last;

    @Override
    void add(Object o) {
        List.Cell cell = new List.Cell(o, null);
        if( last != null )
            last.setNext( cell );
        else
            origin = cell;
        last = cell;
        this.size++;
    }

    @Override
    Object remove() {
        List.Cell result = null;
        if( size != 0 ) {
            result = origin;
            origin = origin.getNext();
            if( size == 1 )
                last = null;
            size--;
        }
        assert result != null;
        return result.getElements();
    }
}
