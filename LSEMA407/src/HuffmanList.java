/* Creates HuffmanList as an unsorted singly-linked list with items consisting
 of HuffmanNodes and a pointer to the next item in the list. */

public class HuffmanList {

    private class HuffmanItem {
        private HuffmanNode node;
        private HuffmanItem next;
        private HuffmanItem (HuffmanNode node, HuffmanItem item) {  //constructor
            this.node = node;
            next = item;
        }
    }

    public HuffmanItem start = null;

    public void insert(HuffmanNode n) {
        start = new HuffmanItem(n, start);
        }
    
    public HuffmanNode extractMin() {
        HuffmanItem min = start;
        HuffmanItem previous = null;
        for (HuffmanItem item = start; item.next!= null; item=item.next) {
            if (min.node.frequency < item.next.node.frequency) { //find minimum
                min = item.next;
                previous = item;
            }
        }            
        if (previous == null) {        // delete minimum
            start=start.next;
            } else {
            previous.next = previous.next.next;                
            }            
            return min.node;                        
        }
    
    // returns true if the list contains zero or one item, and false otherwise
    public boolean containsAtMostOneItem() {
        boolean contains;
        if (start==null | start.next==null) {
            contains = true;
        }
        return contains;
    }

}