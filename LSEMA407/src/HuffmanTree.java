// Julia Boettcher, 2012; updated 2013
//
// A class for Huffman trees, implemented as binary trees whose nodes are of
// type HuffmanNode.

public class HuffmanTree {

    public HuffmanNode root;  // the root of the Huffman tree
    
    // builds the Huffman tree for character frequencies as in the given word
    public HuffmanTree (String word) {
        // count the number of occurences of each character in word
        CharacterCounts counts = new CharacterCounts();
        for (int i=0; i<word.length(); i++) {
            counts.increment( word.charAt(i) );
        }
        // according to these character counts, generate Huffman nodes
        // (without children) for each of these characters with
        // corresponding frequencies and insert each of them into a Huffman
        // list; all this is done by the method toHuffmanList of
        // CharacterCounts
        HuffmanList list = counts.toHuffmanList();
        // the nodes now in the list are the leaves of the Huffman tree;
        // construct the internal nodes
        while( ! list.containsAtMostOneItem() ) {
            // for constructing an internal node choose two nodes with
            // smallest frequency in list as children of this new node,
            // sum their frequencies to get the frequency of the new node
            // delete these two nodes from the list, and insert the new node
            HuffmanNode node = new HuffmanNode();
            node.left = list.extractMin();  // this automatically deletes
                                            // this node frm the list
            node.right = list.extractMin();
            node.label = node.left.label + node.right.label;
            node.frequency = node.left.frequency + node.right.frequency;
            list.insert(node);
        } 
        // now only the root is left in the list
        root = list.extractMin();
    }
        
    // prints the Huffman tree
    public void print() {
        printRecursively(root, 0);	
    }
    
    // recursive printing of the Huffman tree; depth is the depth in the
    // tree
    private void printRecursively(HuffmanNode node, int depth) {
        if (node == null) {
            return;
        }
        // print 4*depth spaces
        for ( int k=0; k < depth; k++) {
            System.out.print("    ");
        }
        // print the node frequency and its label
        System.out.print (node.frequency);
        System.out.println ( " (" + node.label + ")");
        // recursively print the children of the node
        printRecursively(node.left, depth+1);
        printRecursively(node.right, depth+1);
    }

}