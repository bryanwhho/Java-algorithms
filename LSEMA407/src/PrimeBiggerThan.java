/*----------------------------------------------------------------
 *  Author:        Bryan Ho
 *  Written:       9/10/2013
 *  Last updated:  9/10/2013
 *
 *  Compilation:   javac PrimeBiggerThan.java
 *  Execution:     java PrimeBiggerThan
 *
 *  Determines for a given positive integer n the next prime number
 *  p after n, that is, the prime number p > n such that p - n
 *  is minimised.
 *
 *  % java PrimeBiggerThan [input a natural number n]
 *  [returns p]
 *
 *----------------------------------------------------------------*/

class PrimeBiggerThan {
    
    static boolean isPrime(int m) {     // isPrime method tests if m is prime    
        for (int i = 2; i < m; i++) {        
            int result = m%i;            
            if (result == 0) {            
                return false;      // Will this exit the loop?                
            }            
        }        
        return true;        
    }
    
    public static void main (String[] args) {
        if (args.length < 1) {
            return;
        }

        int n = Integer.parseInt(args[0]);  // Takes user's input as n
        int p = n+1;                        // Initializes p at n+1

        while (isPrime(p) == false) {       // Searches for the next prime in
                p++;                        // increments of 1 until it is found
        }
        System.out.println("The next prime number is " + p );


    }
}
