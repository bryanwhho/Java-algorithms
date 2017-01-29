/*----------------------------------------------------------------
 *  Author:        <Candidate no: 27224>
 *  Written:       01/24/2014
 *  Last updated:  01/24/2014
 *
 *  Compilation:   javac BinomialDistribution.java
 *  Execution:     java BinomialDistribution [p p2 m]
 *
 *
 *  % java BinomialDistribution .2 .6 100000
 *
 *  Models two binomial distributions with probabilities p and p2 respectively,
 *  and prints out a string representation of the results sorted by value,
 *  as well as a graph representation of the two distributions, with values
 *  (number of heads) on the x-axis, and counts (number of experiments with x
 *  heads) on the y-axis.
 *----------------------------------------------------------------*/
// 
//
// the class BinomialDistribution
// for the assessed coursework for MA407, 2013

public class BinomialDistribution {

    // constants
    public static final int COIN_TOSSES = 100;
    public static final int LINES_IN_DIAGRAM = 30;

    // instance variables
    private int num;
    private double prob;
    private int result;
    
    // the class Coin 
    // toss method returns head=true with probability p
    public class Coin {
        private boolean head;
        
        public boolean toss() {
            double random = Math.random();
            if (prob >= random) {
                head = true;
            }
            return head;
        }
    }

    // constructor for new binomial dist
    public BinomialDistribution(int n, double p) {
        num = n;
        prob = p;
        result = 0;
    }

    // returns the result of a random experiment with Bin(n,p)
    public int experiment() {
        Coin coin = new Coin();
        result = 0;
        for (int i=0; i<num; i++) {
            coin.head = false;
            coin.toss();
            if (coin.head == true) {
                result++;
            }
        }
        return result;         
    }

    public static void main(String[] args) {
        // read in three arguments: p, p2, m
        if (args.length<3) return;
        double p = Double.parseDouble(args[0]);
        double p2 = Double.parseDouble(args[1]);
        int m = Integer.parseInt(args[2]);
        // perform m experiments with binomial distribution Bin(COIN_TOSSES,p)
        // and store the results in a data list
        BinomialDistribution dist = new BinomialDistribution(COIN_TOSSES,p);
        DataList data = new DataList();
        for (int i=0; i<m; i++) {
            data.insert( dist.experiment() );
        }
        // perform m experiments with binomial distribution Bin(COIN_TOSSES,p)
        // and store the results in a data list
        BinomialDistribution dist2 = new BinomialDistribution(COIN_TOSSES,p2);
        DataList data2 = new DataList();
        for (int i=0; i<m; i++) {
            data2.insert( dist2.experiment() );
        }
        // add the two data lists
        data = data.add( data2 );
        // print the result and a corresponding diagram
        System.out.println( data );
        data.printDiagram(LINES_IN_DIAGRAM, COIN_TOSSES);
    }

}