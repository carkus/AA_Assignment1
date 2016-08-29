import java.io.*;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;
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
	private static final boolean printOutput = false;

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
        
    	//If Vertex already exists, do not add it again;
    	if (checkForVertex(vertLabel)) {
    		//System.err.println("Vertex already exists, not being added. Nothing done.");
        	return;
        }
    	//Array needs to be dynamic so initially unset
    	//But here we check for it beuing set, if not create it
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
    	
    	if (adjMatRows[srcI].getEdge(tarI) == "1" && adjMatRows[tarI].getEdge(srcI) == "1") {
    		System.err.println("Edge already exists, not being added. Nothing done.");
        	return;
    	}
    	
    	//Set connection in corresponding vertex
    	String edgeVal = String.valueOf(adjMatRows[srcI].getHasEdgeValue());
    	adjMatRows[srcI].setEdge(tarI, edgeVal);
    	adjMatRows[tarI].setEdge(srcI, edgeVal);    	
    	output();
    } // end of addEdge()
	

    @SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
    	ArrayList<T> neighbours = new ArrayList<T>();
        if (!checkForVertex(vertLabel)) {
        	//System.err.println("Vertex does not exist or is invalid.");
        	return neighbours;       
        }
        int vertI = getVertexIndex(String.valueOf(vertLabel));
        if (vertI == -1) return null;

        for (int i=0; i<adjMatRows[vertI].getSize(); i++) {
        	if (String.valueOf(adjMatRows[vertI].getEdge(i)).equals("1")) {
        		neighbours.add((T) String.valueOf(i));
        	}        	
        }    

        neighbours = getNeighbours(String.valueOf(vertLabel));
        return neighbours;
    } // end of neighbours()
    
    public void removeVertex(T vertLabel) {
    	int newI = 0;
    	if (!checkForVertex(vertLabel)) {
    		System.err.println("Vertex does not exist or is invalid.");
    		return;
    	}
    	int tarI = getVertexIndex(String.valueOf(vertLabel));
    	//remove lookup reference in hashmap
    	indexer.remove(String.valueOf(vertLabel));
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
    	//get indexes of corresponding vertex
    	int srcI = getVertexIndex(String.valueOf(srcLabel));
    	int tarI = getVertexIndex(String.valueOf(tarLabel));
    	
    	if (adjMatRows[srcI].getEdge(tarI) == "0" && adjMatRows[tarI].getEdge(srcI) == "0") {
    		System.err.println("No relationship exists. Nothing done.");
        	return;
    	}    	
    	
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
        					vp = false;
        				}
        				os.print(adjMatRows[j].getLabel() + " ");
        			}
        		}
        	}
        }
        
    } // end of printEdges()
    
	public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	
    	int spd = 0;
    	//If Start vertex and End vertex are same. return 0; 
		if (String.valueOf(vertLabel1).equals(String.valueOf(vertLabel2))) {
			return spd;    			
		}		
		HashMap<String, Integer> visited = new HashMap<String, Integer>();
		HashMap<String, Integer> referenced = new HashMap<String, Integer>();
		Queue<String> daQ = new ArrayDeque<>();
		Boolean incremented = false;
		
		//Prime Queue with source vertex; 
		daQ.add(String.valueOf(vertLabel1));
		referenced.put(String.valueOf(vertLabel1), spd);
		visited.put(String.valueOf(vertLabel1), 1);
		String current = String.valueOf(vertLabel1);
		
		while(daQ.size() != 0) {
            
        	current = daQ.poll();
        	
        	//Check if current node is the target 
            if(current.equals(String.valueOf(vertLabel2))) {
                return spd;
            } 

        	//Get neighbours of currently selected node;        	
            ArrayList<T> neighbours = new ArrayList<>(getNeighbours(current));
        	
        	if(neighbours.size() == 0) {
             	return spd;
        	}
        	
        	//ADD to hashmap of current and children so as to not increment path distance per beighbour
        	incremented = false;
        	for (int a=0; a<neighbours.size(); a++) {
        		if (referenced.get(String.valueOf(current)) == null || referenced.get(String.valueOf(neighbours.get(a))) == null) {
        			referenced.put(String.valueOf(neighbours.get(a)), spd);
        			if (!incremented) {
        				spd++;
        				incremented = true;
        			}
        		}
        	}

        	for (int a=0; a<neighbours.size(); a++) {
        		//EXIT IF Neighbour is the target node. Clear queue and return path distance.
        		if(String.valueOf(neighbours.get(a)).equals(String.valueOf(vertLabel2))) {
        			daQ.clear();
                    return spd;                    
                }        		
        		//ADD current neighbour to visited list 
        		if (visited.get(String.valueOf(neighbours.get(a))) == null) {
        			visited.put(String.valueOf(neighbours.get(a)), spd);
        			daQ.add(String.valueOf(neighbours.get(a)));
        		}
        	}
        	
        }
		
		return disconnectedDist;
    }
    
	/**
	 * Adjacency Matrix helpers
	 * Written by
	 * Mark Scicluna
	 * 
	 */
    
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
    private ArrayList<T> getNeighbours(String vertLabel) {
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
    	for (int i=0; i<adjMatRows.length; i++) {    		
    		String c = (String) adjMatRows[i].getLabel();    		
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
		return avg ;
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