// Julia Boettcher, 2012; updated 2013
//
// A class for generating a Huffman tree and a corresponding Huffman code
// for a given word.
//
// java HuffmanCode word
// prints a HuffmanTree for the character frequencies as in word, prints
// the corresponding Huffman code, and determines how many bytes the
// encoding of word with this code needs

public class HuffmanCode {

    private HuffmanTree tree;
    private CodeBook book;    // the codebook for the Huffman code
 
    // creates a Huffman tree for character frequencies as given by word
    // and generates the corresponding code
    public HuffmanCode(String word) {
        tree = new HuffmanTree(word);
        book = new CodeBook();
        generateCode();
    }

    // generates the Huffman code for the Huffman tree given by tree and
    // saves this code in book
    public void generateCode () {
        generateCode ("", tree.root);
    }

    // recursively generate the code
    // we assume that we are at a given node in the Huffman tree
    // prefix gives the codeword associated with this node
    // it will be the common prefix of the codewords for all leaves
    // in the subtree with root node
    private void generateCode (String prefix,HuffmanNode node) {
        if (node == null) return;
        if (node.label.length() == 1) { // node is a leaf
            book.insert(node.label.charAt(0), prefix);
        } else {                       // node is an internal node
            generateCode ( prefix+"0", node.left);
            generateCode ( prefix+"1", node.right);
        }
    }
    
    // encodes the given word using the codebook; assumes that all
    // characters in word are in the code
    public String encode(String word) {
        String encoding = "";
        for (int i=0; i<word.length(); i++) {
            encoding = encoding + book.codeFor(word.charAt(i));
        }
        return encoding;
    }
    

    // prints the code
    public void printCode() {
        book.print();
    }

    // prints the Huffman tree
    public void printTree() {
        tree.print();
    }
        
    // test client: reads in the first argument and constructs a Huffman
    // code for this string
    public static void main (String[] args) {
        if (args.length == 0) {
            return;
        }
        String word = args[0];
        HuffmanCode code = new HuffmanCode(word);
        System.out.println();
        System.out.println("The Huffman tree:");
        code.printTree();
        System.out.println();
        System.out.println("The corresponding code:");
        code.printCode();
        int bits = code.encode(word).length();
        System.out.println();
        System.out.println("The Huffman encoding of \"" + word + "\" uses " + bits + " bits.");
        System.out.println();
    }


}

/* Example output:
The Huffman tree:
1.0 (stemcla )
    0.4117647058823529 (ste)
        0.17647058823529413 (s)
        0.23529411764705882 (te)
            0.11764705882352941 (t)
            0.11764705882352941 (e)
    0.5882352941176471 (mcla )
        0.23529411764705882 (mcl)
            0.11764705882352941 (mc)
                0.058823529411764705 (m)
                0.058823529411764705 (c)
            0.11764705882352941 (l)
        0.35294117647058826 (a )
            0.17647058823529413 (a)
            0.17647058823529413 ( )

The corresponding code:
s -> 00
e -> 011
c -> 1001
a -> 110
  -> 111
m -> 1000
l -> 101
t -> 010

The Huffman encoding of "a small test case" uses 50 bits.
*/