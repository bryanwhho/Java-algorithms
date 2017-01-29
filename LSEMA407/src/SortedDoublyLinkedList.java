class SortedDoublyLinkedList {

    private Item start = null;
    private Item end = null;
    private String s = toString();
    private String t = reverseString();

    private class Item {
        private int value;
        private Item prev;
        private Item next;
    }

    public Item findFirstBiggerEqual(int x) {
        Item firstBiggerEqual = start;      
        while (firstBiggerEqual != null && firstBiggerEqual.value < x) {
            firstBiggerEqual = firstBiggerEqual.next;
        }
        return firstBiggerEqual;
    }

    
    public Item findFirstBigger(int x) {
        Item firstBigger = start;      
        while (firstBigger != null && firstBigger.value <= x) {
            firstBigger = firstBigger.next;
        }        
        return firstBigger;
    }
        
    public Item findLastSmaller(int x) {
        Item lastSmaller = start;      //Why no constructor needed here...
        while (lastSmaller != null && lastSmaller.value < x) {
            lastSmaller = lastSmaller.next;
        }
        return lastSmaller.prev;        
    }       

    public void insert(int x) {
        Item p = new Item();    //...but constructor needed here?
        p.value = x;
        Item y = findFirstBiggerEqual(x);
        //System.out.println(y);
        if (start == null) {
            start = p;
            end = p;
        } else if (y == null) {
            p.prev = end;
            end.next = p;
            end = p;           
        } else if (y.prev == null) {
            p.next = start;
            start.prev = p;
            start = p;
        } else {
            p.prev = y.prev;
            p.next = y;
            y.prev.next = p;
            y.prev = p;

        }
    }

    public void delete(int x) {
        if (findLastSmaller(x)==null) {
            start = findFirstBigger(x);
        } else if (findFirstBigger(x)==null) {
            end = findLastSmaller(x);
            end.next = null;
        } else {
            findLastSmaller(x).next = findFirstBigger(x);
            findFirstBigger(x).prev = findLastSmaller(x);
        }
    }

    public String toString() {
        String s = "null <- ";
        for (Item p=start; p!=null; p=p.next) {
            if (p.next==null) {
                s += p.value + " -> ";
            } else {
                s += p.value + " <-> ";
                }
            }
        s += "null";
        return s;
    }

    public String reverseString() {
        String t = "null <- ";
        for (Item p=end; p!=null; p=p.prev) {
            if (p.prev==null) {
                t += p.value + " -> ";
            } else {
                t += p.value + " <-> ";
            }
        }
        t += "null";
        return t;
    }

    // Interpret command line arguments as follows:
    // >0: push  -1: pop 0: print
    public static void main (String[] args) {
        SortedDoublyLinkedList S = new SortedDoublyLinkedList();
        for (int i=0; i < args.length; i++) {
            int x = Integer.parseInt(args[i]);
            if (x>0) {
                S.insert(x);
            }
            else if (x<0) {                
                if (S.start==null)
                    System.out.println ("delete not possible: list empty");
                else
                    S.delete(-x);
                    System.out.println( "delete all " + -x);
            } else { // x==0
                System.out.println(S.toString());
                System.out.println(S.reverseString());
            }
        }
    }


}