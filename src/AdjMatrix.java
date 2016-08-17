import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{

	private static final int gSize = 10;
	private static final boolean preBuildGraph = false;
	private static final boolean allowGraphLoops = false;
	private static final boolean printOutput = true;

	private HashMap<String, Integer> indexer = new HashMap<String, Integer>();
	private sArray[] adjMatRows;
	
	/**
	 * Contructs prefilled or empty graph.
	 */
    public AdjMatrix() {
    	if (preBuildGraph) {
    		adjMatRows = new sArray[gSize];
    		for (int i=0; i<gSize; i++) {
        		adjMatRows[i] = new sArray(gSize);
        		adjMatRows[i].setLabel(String.valueOf(i));
        		indexer.put(String.valueOf(i), i);
    		}   		
    	} else {
    		adjMatRows = new sArray[0];   		
    	}
    } // end of AdjMatrix()

    /**
     * We are assuming the Adjacency Matrix is a square.
     */
    public void addVertex(T vertLabel) {
    	//if (adjMatRows == null) return;
    	if (getIntVal(vertLabel) < 0) System.err.println("Value must convert to a positive integer.");
        
    	//If Vertex already exists, do not add it asgain;
    	if (checkForVertex(vertLabel)) {
        	return;       
        }
    	if (adjMatRows == null) {
    		adjMatRows = new sArray[1];
    		adjMatRows[0] = new sArray(1);
    		adjMatRows[0].setLabel("0");
    		indexer.put("0", 0);  
    	}
		for (int i=0; i<adjMatRows.length; i++) {
			adjMatRows[i].expand();    			
		}		
		enlarge(String.valueOf(vertLabel));
		indexer.put(String.valueOf(vertLabel), indexer.size());
    	output();    	
    } // end of addVertex()
    
    public void addEdge(T srcLabel, T tarLabel) {
    	//Check for loop allowance
    	//if (adjMatRows == null) return;
    	if (!allowGraphLoops && String.valueOf(srcLabel).equals(String.valueOf(tarLabel)) ) {
    		return;
    	}
    	//Ensure vertices exist, else create them
    	if (indexer.get(String.valueOf(srcLabel)) == null) {
    		addVertex(srcLabel);
    	}
    	if (indexer.get(String.valueOf(tarLabel)) == null) {
    		addVertex(tarLabel);
        }    

    	//get indexes of corresponding edge in vertex
    	int srcI = getVertexIndex(String.valueOf(srcLabel));
    	int tarI = getVertexIndex(String.valueOf(tarLabel));
    	//Set connection in corresponding vertex    	
    	String edgeVal = String.valueOf(adjMatRows[srcI].getHasEdgeValue());
    	adjMatRows[srcI].setEdge(tarI, edgeVal);
    	adjMatRows[tarI].setEdge(srcI, edgeVal);    	
    	output();
    } // end of addEdge()
	
    public ArrayList<T> neighbours(T vertLabel) {
    	ArrayList<T> neighbours = new ArrayList<T>();
        if (!checkForVertex(vertLabel)) {
        	System.err.println("Vertex does not exist or is invalid.");
        	return neighbours;       
        }
        neighbours = getNeighbours(vertLabel);
        return neighbours;
    } // end of neighbours()
    
    public void removeVertex(T vertLabel) {
    	int newI = 0;
    	if (!checkForVertex(vertLabel)) {
    		System.err.println("Vertex does not exist or is invalid.");
    		return;
    	}
    	int tarI = getVertexIndex(String.valueOf(vertLabel));
    	//remove corresponding matrix 'row'
    	sArray[] newrows = new sArray[adjMatRows.length-1];
    	for (int i=0; i<adjMatRows.length; i++) {    		
    		adjMatRows[i].remove(tarI);    		
    		if (i != tarI) {
				newrows[newI] = new sArray(adjMatRows[i].getSize());
				newrows[newI] = adjMatRows[i];
				newI++;
			}
		}		
		adjMatRows = new sArray[newrows.length];
		adjMatRows = newrows;
    	output();
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	//if (adjMatRows == null) return;
    	if (!checkForVertex(srcLabel) || !checkForVertex(tarLabel)) {
    		System.err.println("Vertex parameters are invalid.");
    		return;
    	}
    	//get indexes of corresponding edge in vertex
    	int srcI = getVertexIndex(String.valueOf(srcLabel));
    	int tarI = getVertexIndex(String.valueOf(tarLabel));
    	
    	//Reset edge value to 'no connection'
    	String edgeVal = String.valueOf(adjMatRows[srcI].getNoEdgeValue());
    	adjMatRows[srcI].setEdge(tarI, edgeVal);
    	adjMatRows[tarI].setEdge(srcI, edgeVal);
    	output();
    } // end of removeEdges()	
    
    public void printVertices(PrintWriter os) {
    	if ( os != null ) {
	    	for (int i=0; i<adjMatRows.length; i++) {
	    		os.print(adjMatRows[i].getLabel() + " ");
	    		System.out.print(adjMatRows[i].getLabel() + " ");
	    	}
    	}
    } // end of printVertices()	
    
    public void printEdges(PrintWriter os) {
        if (os != null) {
        	for (int i=0; i<adjMatRows.length; i++) {
        		Boolean vp = true;
        		for (int j=0; j<adjMatRows[i].getSize(); j++) {
        			if (String.valueOf(adjMatRows[i].getEdge(j)).equals("1")) {
        				if (vp) {
        					os.print("\n" + adjMatRows[i].getLabel() + " ");
        					System.out.print("\n" + adjMatRows[i].getLabel() + " ");
        					vp = false;
        				}
        				os.print(adjMatRows[j].getLabel() + " ");
        				System.out.print(adjMatRows[j].getLabel() + " ");
        			}
        		}
        	}
        	System.out.println("");
        }
        
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;
    } // end of shortestPathDistance()
    
    
    /*public String findPath(int v, int w) {
        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visited = new boolean[g.numVertices()];
        String[] pathTo = new String[g.numVertices()];

        q.add(v);
        pathTo[v] = v+" ";
        while(q.peek() != null) {
            if(runBFS(q.poll(),w,visited,q,pathTo))
            break;
        }
        return pathTo[w];
    }*/    
    
    
    
	/**
	 * Adjacency Matrix helpers
	 * Written by
	 * Mark Scicluna
	 * 
	 */
    
    /*private boolean runBFS(int v, int w, boolean[] visited, Queue<Integer> q, String[] pathTo) {
        if(visited[v]) {
        }
        else if(v == w)
            return true; 
        }
        else {
            visited[v] = true;
            VertexIterator vi = g.adjacentVertices(v);
            while(vi.hasNext()) {
                int nextVertex = vi.next();
                pathTo[nextVertex] = pathTo[v] + nextVertex + " ";
                q.add(nextVertex);
            }
        }
        return false;
    }*/
    
    private Boolean checkForVertex(T vertLabel) {
    	if (getIntVal(vertLabel) < 0 || indexer.get(String.valueOf(vertLabel)) == null) {
    		return false;
    	}
    	return true;
    }

    private int getIntVal(T vertLabel) {
    	if (Character.isLetter(((String) vertLabel).charAt(0))) {
    		char c = ((String) vertLabel).charAt(0);
    		return Character.getNumericValue(c);
    	} else {
    		return Integer.parseInt((String) vertLabel);
    	}  	
    }

    @SuppressWarnings("unchecked")
    private ArrayList<T> getNeighbours(T vertLabel) {
    	ArrayList<T> neighbours = new ArrayList<T>();
        int vertI = getVertexIndex(String.valueOf(vertLabel));
        if (vertI == -1) return null;
        for (int i=0; i<adjMatRows[vertI].getSize(); i++) {
        	if (String.valueOf(adjMatRows[vertI].getEdge(i)).equals("1")) {
        		neighbours.add((T) adjMatRows[i].getLabel());
        	}        	
        }  
        return neighbours;
    }
    
    private int getVertexIndex(String label) {
    	//if (adjMatRows == null) return -1;
    	for (int i=0; i<adjMatRows.length; i++) {
    		String c = adjMatRows[i].getLabel();
    		if (c.equals(label)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    private int getDensity() {
    	if (adjMatRows == null) return 0;//-1 ?
    	int edgyCount = 0;
		for (int i=0; i<adjMatRows.length; i++) {
			for (int j=0; j<adjMatRows[i].getSize(); j++) {				
				if (adjMatRows[i].getEdge(j).equals(String.valueOf(adjMatRows[i].getHasEdgeValue()))) {
					edgyCount++;
				}
			}
		}
		int avg = (int) Math.ceil((double)edgyCount / adjMatRows.length);
		return avg ;//!= 0 ? adjMatRows.length/;
    };
    
    private int getSize() {
    	return adjMatRows.length;
    };
    
    private void enlarge(String label) {
    	sArray[] copy = new sArray[adjMatRows.length];    	
    	copy = adjMatRows;    	
    	adjMatRows = new sArray[copy.length+1];
		for (int i=0; i<copy.length; i++) {
			adjMatRows[i] = copy[i];
		}
		adjMatRows[adjMatRows.length-1] = new sArray(adjMatRows.length);
		adjMatRows[adjMatRows.length-1].setLabel(label);
    }

    /**
     * Debug/Visualisation.
     * Can get fairly unwieldy for large samples...
     * Hence intial conditions for allowing output.
     * But good for small samples to test functionality.
     *  
     */
    public void output() {
    	if (!printOutput) return;
		PrintWriter printWriter = null;
		try
        {
			printWriter = new PrintWriter("testing/current_graph.txt");
			printWriter.print("  ");
			System.out.print("  ");
	    	for (int j=0; j<(adjMatRows[0].getSize()); j++) {
	    		printWriter.print(adjMatRows[j].getLabel() + " ");
	    		System.out.print(adjMatRows[j].getLabel() + " ");
	    	}
	    	printWriter.println("");
	    	System.out.println("");
	    	for (int i=0; i<(adjMatRows.length); i++) {
	    		printWriter.print(adjMatRows[i].getLabel() + " ");
	    		System.out.print(adjMatRows[i].getLabel() + " ");
	    		for (int j=0; j<(adjMatRows[i].getSize()); j++) {
	    			printWriter.print(adjMatRows[i].getEdge(j) + " ");
	    			System.out.print(adjMatRows[i].getEdge(j) + " ");
	    		}
	    		printWriter.println("");
	    		System.out.println("");
	    	}
	    	printWriter.println("");
	    	System.out.println("");
	    	printWriter.println("DENSITY (avg): " + getDensity() +"%");
	    	printWriter.println("SIZE: " + getSize());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( printWriter != null ) 
            {
                printWriter.close();
            }
        }

    }

    
} // end of class AdjMatrix