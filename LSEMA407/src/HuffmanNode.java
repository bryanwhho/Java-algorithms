// Julia Boettcher, 2012; updated 2013
//
// a node of a Huffman tree

public class HuffmanNode { 
    public String label;     // a label containing all the characters
                             // represented by this node as a string
                             // hence non-leaf nodes have labels of length > 1
    public double frequency; // the sum of the frequencies of all the
                             // characters represented by this node
    public HuffmanNode left, right;
                             // the left and the right child of this node 
}
