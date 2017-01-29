/*----------------------------------------------------------------
 *  Author:        <Candidate no: 27224>
 *  Written:       01/24/2014
 *  Last updated:  01/24/2014
 *
 *  Compilation:   javac DataList.java
 *  Execution:     java BinomialDistribution [p p2 m]
 *
 *
 *  % java BinomialDistribution .2 .6 100000
 *
 *  Provides the data structures for BinomialDistribution.java, as well as the
 *  methods for printing the string and diagram representations of the results.
 *----------------------------------------------------------------*/
//
//
// the class DataList
// for the assessed coursework for MA407, 2013

public class DataList {

    private Item start = null;          // start of the list

    private class Item {
        private int count;              // instance variables
        private int value;
        private Item next;
        
        private Item (int n) {          // constructor
            count = 1;
            value = n;
            next = null;
        }
    }

    public void insert(int value) {     //insert sorted in increasing value
        Item item = new Item(value);    
        if (start == null) {            // if list is empty
            start = item;
            return;
        } else {                        // if list non-empty
            Item q = null;
            Item p = start;             // p is successor of q
            while(p!=null && p.value < value) {
                q=p;
                p=p.next;
            }
        // now p.value >= value or p==null, insert new item or update count
            if (q==null && p.value > item.value) {  // new item is new start
                item.next = start;
                start = item;
            } else if (p==null) {                   // new item is end of list
                item.next = p;
                q.next = item;
            } else if (value == p.value) {      // value already exists in list
                p.count++;
            } else {                                // new item is in the middle
                q.next = item;
                item.next = p;
            }
        }
    }

    public DataList add(DataList list) {
        for (Item q = list.start; q != null; q = q.next) {
            insert(q.value);    // merge values from 'list' to this list
        }

        // Now all values from 'list' and this list are in this list.
        // Counts are incorrect however, and must be fixed.
        // There are 2 cases: 
        // (1) the count is 1, then it is an item added from 'list' and
        // should have its count equal to the 'list' item; or
        // (2) the count is p.count+1, and should be set to p.count-1 + q.count.

        for (Item q = list.start; q != null; q = q.next) { 
            for (Item p = this.start; p != null; p = p.next) {
                if (q.value == p.value) {   // find items added from 'list'
                    if (p.count == 1) {     // Case 1
                        p.count = q.count;
                    } else {                // Case 2
                        p.count = (p.count - 1 + q.count);
                    }
                }
            }
        }        
        return this;
    }

    // Prints a string representation of the data list with counts in ().
    public String toString() {
        String s = "";
        for (Item p = start; p != null; p = p.next) {
            s += p.value + "(" + p.count + ")" + "->";
        }
        s += "null";
        return s;  
    }

    // prints a diagram with maxCount lines and maxValue columns of the
    // data in this list, where the x-axis represents the values and the
    // y-axis represents the counts
    public void printDiagram(int maxCount, int maxValue) {
        Item[] itemArray = toArray(maxCount,maxValue);
        sort(itemArray);
        printArrayDiagram(itemArray);
    }

    // Creates an array representation of the data list, with counts normalised
    // so that the biggest count is new MaxCount.
    private Item[] toArray(int newMaxCount,int maxValue) {
        int oldMaxCount = 0;    // oldMaxCount = biggest count in list
        for (Item p=start; p!=null; p=p.next) { // find oldMaxCount
            if (p.count > oldMaxCount) {
                oldMaxCount = p.count;  
            }
        }

        Item[] x = new Item[maxValue+1];    // array representation of list
        for (int i=0; i<x.length; i++) {
            x[i] = new Item(i);
            x[i].count = 0;
            
            for (Item p=start; p!=null; p=p.next) {
                if (p.value==i) {
                    x[i].count = (p.count*newMaxCount)/oldMaxCount;
                }
            }
        }         
        return x;
    }

    // Sorts the array first by count, in decreasing order, then by value, in
    // increasing order.
    private static void sort(Item[] x) {  // modified insertion sort                  
        for (int i=1; i < x.length; i++) {
            // insert x[i] into the already sorted
            // sequence  x[0]...x[i-1]
            Item key = x[i];
            int j = i;
            // 1. First sort by count, from biggest to smallest
            // find j so that key.count <= x[j-1].count
	    // and move the elements x[j]...x[i-1] one to the right
            while ( j > 0 && key.count > x[j-1].count ) {
                x[j] = x[j-1];
                j-- ;
            }
            // now either j==0 or key.count <= x[j-1].count
                            
            // 2. Then sort by value, from smallest to biggest
            while ( j > 0 && key.value < x[j-1].value &&
                    key.count == x[j-1].count) {
                    x[j] = x[j-1];
                    j--;
                }
            
            // now either j==0 or key.value >= x[j-1].value
            x[j] = key;
        }
    }

    // Prints a diagram for the items in a sorted array represenation of the
    // data list, together with an x-axis and a y-axis.
    private static void printArrayDiagram(Item[] array) {
        int maxLine = array[0].count;
        System.out.println();
        System.out.println();
        System.out.println("^");
        for (int i=maxLine; i>=0; i--) {
            System.out.print("|");
                            
            int marker = 0;

            for (int j=0; j<array.length; j++) {
                if(array[j].count==i) {                    
                    for (int k=0; k< array[j].value-marker-1; k++) {
                        System.out.print(" ");
                    }
                    System.out.print("*");
                    marker = array[j].value;
                }
            }                            
            System.out.println();
        }
        System.out.print("+");
        for (int i=0; i<array.length; i++) {
            System.out.print("-");
        }
        System.out.print(">");
    }
}

// Task 7: Which part makes this implementation slow for large values of m?

/*  The part which makes implementation slow for large values of m is the
 insertion of results of n coin tosses m times, e.g. lines 62-64 and 69-71 in
 BinomialDistribution.java, as this takes exponential time.

    The efficiency could be improved by splitting up this part as follows:
 
    First, carry out m experiments and store the results in an unsorted data
 list, with possibly "repeated" items of the same value. This takes linear time.

    Then, in a separate step, create a sorted data list based on the unsorted
 list, such that all items of the same value are combined into a single item
 with counts equal to the number of occurrences, i.e. a sorted singly linked
 list as described in Task 2. This takes polynomial time.

    Therefore, by splitting up the insertion of the experiments into a sorted
 list into two parts, the total running time is reduced to polynomial time,
 which should be more efficient. */
