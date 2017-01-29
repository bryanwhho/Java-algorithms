/*----------------------------------------------------------------
 *  Author:        <Candidate no: 27224>
 *  Written:       05/15/2014
 *  Last updated:  05/15/2014
 *
 *  Compilation:   javac Graph.java
 *  Execution:     java RMinCut [v_0 v_1 v_2 ...]
 *
 *
 *  % java RMinCut 0 1 0 3 1 2 3 2
 *
 *  Provides the data structure for creating and storing the graph, used for 
 *  Karger's Randomised Minimum Cut algorithm. Also includes the functionality
 *  for contracting edges, and creating the resultant supernodes. 
 *----------------------------------------------------------------*/
//
//
// the class Graph
// for the assessed coursework for MA421, 2014    

public class Graph {
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