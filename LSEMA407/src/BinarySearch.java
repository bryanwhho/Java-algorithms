class BinarySearch {
    static int binsearch (int key, int[] x, int i, int j) {
        int k = (j-i)/2;   // the mid-point integer
        if (k == 0) {
            return k;
        }
        else if (x[k] == key) {
            return k;
        }
        else if (key < x[k]) {
            return binsearch(key, x, i, k);
        }
        return binsearch(key, x, k, j);
    }

    public static void main(String[] args) {
        if (args.length<4) {
            return;
        }
        int key = Integer.parseInt(args[0]);
        int [] x = {
          1, 4, 4, 4, 8, 9
        };
        int i = Integer.parseInt(args[2]);
        int j = Integer.parseInt(args[3]) - 1;
        System.out.println("Testing array " + x);
        System.out.println(binsearch(key, x, i, j));

    }
}