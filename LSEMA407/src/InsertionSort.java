// Bernhard von Stengel; modified by Julia Boettcher 2012, 2013
//
// run with:
// java InsertionSort
//
// an implementation of insertion sort

class InsertionSort {

    // sort the array x with insertion sort
    static void insertionSort (int[] x) {
        for (int i=1; i < x.length; i++) {
            // insert x[i] into the already sorted
            // sequence  x[0]...x[i-1]
            int key = x[i];
            int j = i;
            // find j so that key >= x[j-1]
	    // and move the elements x[j]...x[i-1] one to the right
            while ( j > 0 && key < x[j-1] ) {
                x[j] = x[j-1];
                j-- ;
            }
            // now either j==0 or key >= x[j-1]
            x[j] = key;
        }
    }

    // for test purposes only 
    public static void main (String[] args) {
        int[] A = {33, 5, 2, 1, 20, 15, 3, 9, 11, 7};
        outarray(A, "before sorting:");
        insertionSort (A) ;
        outarray(A, "after sorting:");
        // big test case 1: 10^5 elements 
        A = new int[100000];
        randomfill(A);
        System.out.println("Starting (in place) insertion sort on " + A.length +" elements.");
        long time=System.currentTimeMillis();
        insertionSort(A) ;
        time=System.currentTimeMillis() - time;
        System.out.println("Finished in " + time + " ms.");
        // big test case 2: 2*10^5 elements 
        A = new int[200000];
        randomfill(A);
        System.out.println("Starting (in place) insertion sort on " + A.length +" elements.");
        time=System.currentTimeMillis();
        insertionSort(A) ;
        time=System.currentTimeMillis() - time;
        System.out.println("Finished in " + time + " ms.");
        // big test case 3: 4*10^5 elements 
        A = new int[400000];
        randomfill(A);
        System.out.println("Starting (in place) insertion sort on " + A.length +" elements.");
        time=System.currentTimeMillis();
        insertionSort(A) ;
        time=System.currentTimeMillis() - time;
        System.out.println("Finished in " + time + " ms.");
        // big test case 4: 8*10^5 elements 
        A = new int[800000];
        randomfill(A);
        System.out.println("Starting (in place) insertion sort on " + A.length +" elements.");
        time=System.currentTimeMillis();
        insertionSort(A) ;
        time=System.currentTimeMillis() - time;
        System.out.println("Finished in " + time + " ms.");
    } 

    static void outarray (int[] x, String info) {
        System.out.println(info);
        for (int i=0; i < x.length; i++)
            System.out.print(x[i] + "  ");
        System.out.println();
    }

    static void randomfill (int[] A) { 
        // fill the array A with random numbers
        for (int i=0; i < A.length; i++)
            A[i] = (int) ((double) A.length * Math.random());
    }

}
