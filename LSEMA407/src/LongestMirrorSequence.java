/*----------------------------------------------------------------
 *  Author:        Bryan Ho
 *  Written:       09/12/2013
 *  Last updated:  09/12/2013
 *
 *  Compilation:   javac LongestMirrorSequence.java
 *  Execution:     java LongestMirror Sequence [string of char values]
 *
 *
 *  % java LongestMirrorSequence ACAGTGTC
 *
 *  Finds the longest mirror sequence in the user-input sequence, prints
 *  its length, and prints the corresponding mirror sequence.
 *----------------------------------------------------------------*/

// PROBLEM: how to find the longest mirror sequence?
// SUB-PROBLEM: how to find the longest mirror sequence for pairs x[i], x[j]?
// ANSWER AND RELATION: find sequence of pairs such that x[i] = x[j], i.e. let
// mirror[i][j] = LMS of x[i]...x[j], i<j.
// Base case: i = j, then mirror[i][i] = 1
// General cases, i != j:
// Case 1: x[i] == x[j], then mirror[i][j] = 2 + mirror[i+1][j-1]
// Case 2: x[i] != x[j], then mirror[i][j] = max{mirror[i+1][j], mirror[i][j-1]}
// RUNNING TIME: since the sub-problems are structured as a matrix-filling 
// problem, the running time to fill the matrix is O(n^2).

class LongestMirrorSequence {

    public static void main (String[] args) {
        if (args.length == 0) {
            return;
        }
        int n = args[0].length();
        char[] x = new char[n];         // sequence of characters
        for (int i=0; i<n; i++) {
            x[i] = args[0].charAt(i);
        }

        int[][] mirror = new int[n][n];
        int[] marker = new int[n];      // record which pairs x[i] = x[j]

        for (int i=0; i<n; i++) {
            mirror[i][i] = 1;           // base case
        }

        // Subproblems:
        for (int j=1; j<n; j++) {
            for (int i=j-1; i>=0; i--) {
                if (x[i] == x[j]) {
                    mirror[j][i] = 2 + mirror[j-1][i+1];
                } else if(x[i] != x[j]) {
                    if (mirror[j-1][i] < mirror[j][i+1]) {
                        mirror[j][i] = mirror[j][i+1];
                    } else {
                        mirror[j][i] = mirror[j-1][i];
                    }
                    
                }
            }
        }

        int maxmirror=0;
        int start=0;
        int end=0;
        for (int j=0; j<n; j++) {
            for (int i=0; i<n; i++) {
                if (maxmirror <= mirror[j][i]) {
                    maxmirror = mirror[j][i];
                    start = i;
                    end = j;
                }
            }
        }

//        for (int i=0; i<n; i++) {           // matrix printer to check results
//            for (int j=0; j<n; j++) {
//                System.out.print(mirror[i][j] + " ");
//            }
//            System.out.println();
//        }

        outerloop:
        for (int i=start; i<end; i++) {
            for (int j=end; j>i; j--) {
                if (x[i] == x[j]) {
                    marker[i] = 1;
                    marker[j] = 1;
                    if (i+2==j) {       // check if x[i] is 2 away from x[j];
                        marker[i+1]=1;  // then there is one element in between
                        break outerloop;// which should be included 
                    }                   
                } 
            }
        }

            System.out.println("length of longest mirror sequence: " + maxmirror);
            System.out.print("corresponding sequence: ");
            for(int i=start; i<=end; i++) {
                if (marker[i] == 1) {
                    System.out.print(x[i] + " ");
                }
            }
    }
}