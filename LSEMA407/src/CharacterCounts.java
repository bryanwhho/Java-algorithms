public class CharacterCounts {
    
    // instance variables
    private CharItem start = null;
    private int totalCount = 0;
    
    public class CharItem {
        // instance variables
        private char c;
        private int counter;
        private CharItem next;
        
        //constructors
        public CharItem(char ch, CharItem item) {
            ch = c;
            next = item;
            counter = 1;
        }
    }
    
    // instance method: determines if there is already an entry for char c;
    // if not, it inserts a new entry for c and sets its counter to 1;
    // otherwise it increases the counter for c by one
    public void increment(char c) {
        CharItem p = start;
        totalCount++;
        while (p != null && p.c != c) {
            p = p.next;
        }
        if (p == null) {
            start = new CharItem(c, start);
        } else {
            p.counter++;
        }        
    }
    
    // creates an empty HuffmanList list,
    // then creates for each entry with character ch and count co a new 
    // HuffmanNode n with label String.valueOf(ch) (and left and right equal to
    // null) and frequency co/(double) totalCount,
    // and then calls list.insert(n)
    public HuffmanList toHuffmanList() {
        HuffmanList list = new HuffmanList();
        
    }
    
}