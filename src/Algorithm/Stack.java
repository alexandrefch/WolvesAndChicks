package Algorithm;

public class Stack extends List {
    @Override
    void add(Object o) {
        if( origin == null )
            origin = new List.Cell(o, null);
        else
            origin = new List.Cell(o, origin);
        size++;
    }

    @Override
    Object remove() {
        Object result = null;
        if( origin != null ){
            result = origin.getElements();
            origin = origin.getNext();
            size--;
        }
        return result;
    }
}
