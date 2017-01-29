/*----------------------------------------------------------------
 *  Author:        <Candidate no: 27224>
 *  Written:       05/15/2014
 *  Last updated:  05/16/2014
 *
 *  Compilation:   javac RMinCut.java
 *  Execution:     java RMinCut [v_0 v_1 v_2 ...]
 *
 *
 *  % java RMinCut 0 1 1 2 2 3 3 0
 *
 *  Implements Karger's Randomised Minimum Cut algorithm, for error probability
 *  at most 0.01. User input should be integers from 0 to n inclusive, n even,
 *  and v_0 the first vertex.       
 *----------------------------------------------------------------*/
//
//
// the class RMinCut
// for the assessed coursework for MA421, 2014

public class RMinCut{    
    
    public static void main(String args[]) {
                
        int maxValue=0;
        int[] A = new int[(args.length/2)*2]; // Store parsed args
        
        for(int i=0; i<A.length; i++) {               
            A[i] = Integer.parseInt(args[i]);           
            if(maxValue<A[i]) {            
                maxValue = A[i];                
            }
        }
                    
        int times = (int)(Math.log(1/0.01)*maxValue*maxValue/2) + 1; // For error prob <=0.01
        int minCut = args.length/2;
        int minCutTemp = 0;
        int[] minList = new int[maxValue+1];

        for(int j=0; j<times; j++) { // Repeating Karger preset # of times
       
            for(int i=0; i<A.length; i++) {  // Reset A                 
                A[i] = Integer.parseInt(args[i]);
                if(maxValue<A[i]) {
                    maxValue = A[i];
                }
            }
        
            Graph graph = new Graph(maxValue+1);  // Create graph with n vertices

            for(int i=0; i<(args.length/2); i++) { // Add in edges                   
                graph.addEdge(A[2*i], A[2*i+1]);
           }
        
            // Running Karger
            for(int i=0; i<(graph.vertexCount-2); i++){  // Contract all edges until 2 supernodes are left
                int x = (int)(Math.random()*A.length);    // Pick a random entry of A
                while(A[x] == -1) {     // If A[i] has been already been picked previously,
                    x = (int)(Math.random()*A.length);    // repick another A[i]                    
                }
                if(x%2==0){
                    graph.contract(A[x], A[x+1]);
                    for(int m=0; m<graph.vertexList.length; m++) { // Update vertexList
                        if(A[x+1]==graph.vertexList[m]) {
                            graph.vertexList[m]=A[x];
                        }
                    }
                    for(int m=0; m<A.length; m++) { // Mark all other edges from A[x+1]
                        if(A[m] == A[x+1]) {
                            if(m%2==0) {
                                A[m] = -1;
                                A[m+1] = -1;
                            } else {
                                A[m-1] = -1;
                                A[m] = -1;
                            }
                        }
                    }
                    A[x] = -1;  // Mark this edge as picked
                    A[x+1] = -1;
                } else {
                    graph.contract(A[x-1], A[x]);                    
                    for(int m=0; m<graph.vertexList.length; m++) {
                        if(A[x]==graph.vertexList[m]) {
                            graph.vertexList[m]=A[x-1];
                        }
                    }
                    for(int m=0; m<A.length; m++) { // Mark all other edges from A[x]
                        if(A[m] == A[x]) {
                            if(m%2==0) {
                                A[m] = -1;
                                A[m+1] = -1;
                            } else {
                                A[m-1] = -1;
                                A[m] = -1;
                            }
                        }
                    }
                    A[x-1] = -1;
                    A[x] = -1;
                }
            }
                            
            for(int k=0; k<graph.vertexCount; k++) { //min-cut for this run of Karger
                for(int l=0; l<graph.vertexCount; l++) {
                    if(minCutTemp<graph.adjacencyMatrix[k][l]) {                        
                        minCutTemp = graph.adjacencyMatrix[k][l];                        
                    }
                }    
            }    
            
            if(minCut > minCutTemp) {   // min-cut over all runs Karger
                minCut = minCutTemp;
                minList = graph.vertexList;
            }    
        }
        
        System.out.println("Size of min-cut: " + minCut);
        System.out.print("Vertex set 1: ");
        for(int i=0; i<minList.length; i++) {
            if(minList[i]==minList[0]) {
                System.out.print(i + ", ");
            }
        }
        System.out.println();
        System.out.print("Vertex set 2: ");
        for(int i=0; i<minList.length; i++) {
            if(minList[i]!=minList[0]) {
                System.out.print(i + ", ");
            }
        }
        System.out.println();
        for(int i=0; i<minList.length; i++) {
            System.out.print(minList[i] + ", ");
        }
        System.out.println();
        System.out.print("A: ");
        for(int i=0; i<A.length; i++) {
            System.out.print(A[i] + ", ");
        }
    }
}

class Graph {
        public int adjacencyMatrix[][];
        public int vertexCount;
        public int vertexList[];   // Keeps track of vertices grouped under a
                                    // supernode: e.g. vertexList[1] = 2 means
                                    // v_1 has been absorbed into v_2

        public Graph(int vertexCount) {
            this.vertexCount = vertexCount;
            adjacencyMatrix = new int[vertexCount][vertexCount];
            vertexList = new int[vertexCount];
            for(int i=0; i<vertexCount; i++){
                vertexList[i] = i;
            }            
        }

        public void addEdge(int i, int j) {
          if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount) {
              adjacencyMatrix[i][j] = 1;
              adjacencyMatrix[j][i] = 1;
          }
        }

        public void removeEdge(int i, int j) {
            if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount) {
                adjacencyMatrix[i][j] = 0;
                adjacencyMatrix[j][i] = 0;
            }
        }

        public int isEdge(int i, int j) {
            if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount)
                return adjacencyMatrix[i][j];
            else
                return 0;
        }

        public void contract(int i, int j) {
            adjacencyMatrix[i][j] = 0;  //Remove edges between v_i & v_j first
            adjacencyMatrix[j][i] = 0;
            for(int x=0; x<vertexCount; x++) {  //Setting v_i as supernode

                adjacencyMatrix[i][x] += adjacencyMatrix[j][x]; //add v_j's edges to v_i
                adjacencyMatrix[x][i] += adjacencyMatrix[x][j];

                adjacencyMatrix[j][x] = 0;  //"delete" v_j
                adjacencyMatrix[x][j] = 0;

            }
                vertexList[j] = i;  // v_j has been absorbed into v_i's supernode
        }    
}



