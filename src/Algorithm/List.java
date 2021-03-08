package Algorithm;

abstract class List {

    protected int size;
    protected Cell origin;
    protected Cell cursor;

    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }

    public List(){
        size = 0;
    }

    abstract void add( Object o );
    abstract Object remove();

    protected static class Cell{

        private Object elements;
        private Cell next;

        public Object getElements() { return elements; }
        public Cell getNext() { return next; }
        public void setNext( Cell next ) { this.next = next; }

        public Cell(Object elements , Cell next ){
            this.elements = elements;
            this.next = next;
        }

    }

}
