import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

import model.sArray;


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
	private static final boolean allowGraphLoops = false;
	private static final boolean printOutput = true;

	private HashMap<String, Integer> indexer = new HashMap<String, Integer>();
	private sArray[] adjMatRows;
	
	/**
	 * Contructs empty graph.
	 */
    public AdjMatrix() {
    	adjMatRows = new sArray[gSize];
		for (int i=0; i<(gSize); i++) {
			adjMatRows[i] = new sArray((gSize));
			adjMatRows[i].setLabel(String.valueOf(i));
			indexer.put(String.valueOf(i), i);
		}
		output();
    } // end of AdjMatrix()
    
    /**
     * We are assuming the Adjacency Matrix is a square.
     */
    public void addVertex(T vertLabel) {
    	if (getIntVal(vertLabel) < 0) System.err.println("Value must convert to a positive integer.");
        if (checkForVertex(vertLabel)) {
        	System.err.println("Vertex already exists.");
        	return;       
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
    	if (!allowGraphLoops && String.valueOf(srcLabel).equals(String.valueOf(tarLabel)) ) {
    		System.err.println("Loops are not allowed for graph.");
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
    	String edgeVal = adjMatRows[srcI].getHasEdgeValue();
    	adjMatRows[srcI].setEdge(tarI, edgeVal);
    	adjMatRows[tarI].setEdge(srcI, edgeVal);    	
    	output();
    } // end of addEdge()
	

    @SuppressWarnings("unchecked")
	public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        if (!checkForVertex(vertLabel)) {
        	System.err.println("Vertex does not exist or is invalid.");
        	return null;       
        }
        int vertI = getVertexIndex(String.valueOf(vertLabel));
        if (vertI == -1) return null;
        for (int i=0; i<adjMatRows[vertI].getSize(); i++) {
        	if (String.valueOf(adjMatRows[vertI].getEdge(i)).equals("1")) {
        		neighbours.add((T) String.valueOf(i));
        	}        	
        }    
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
    	if (!checkForVertex(srcLabel) || !checkForVertex(tarLabel)) {
    		System.err.println("Vertex parameters are invalid.");
    		return;
    	}
    	//get indexes of corresponding edge in vertex
    	int srcI = getVertexIndex(String.valueOf(srcLabel));
    	int tarI = getVertexIndex(String.valueOf(tarLabel));    	
    	//Reset edge value to 'no connection'
    	String edgeVal = adjMatRows[srcI].getNoEdgeValue();
    	adjMatRows[srcI].setEdge(tarI, edgeVal);
    	adjMatRows[tarI].setEdge(srcI, edgeVal);
    	output();
    } // end of removeEdges()	
    
    public void printVertices(PrintWriter os) {
        // Implement me!
    	
    	for (int i=0; i<adjMatRows.length; i++) {
    		System.out.print(adjMatRows[i].getLabel() + " ");
    	}
    	System.out.println("");
    	
    	/*File file = new File("C:/Users/Me/Desktop/directory/file.txt");
        PrintWriter printWriter = null;
        try
        {
            printWriter = new PrintWriter(file);
            printWriter.println("hello");
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
        }*/
    	
    	
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!    	
    	for (int i=0; i<adjMatRows.length; i++) {
    		Boolean vp = true;
    		for (int j=0; j<adjMatRows[i].getSize(); j++) {
    			if (String.valueOf(adjMatRows[i].getEdge(j)).equals("1")) {    				
    				if (vp) {
    					System.out.print("\n" + adjMatRows[i].getLabel() + " ");
    					vp = false;
    				}
    				System.out.print(adjMatRows[j].getLabel() + " ");
    			}
    		}
    	}
    	System.out.println("");    	
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
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
    
    private int getVertexIndex(String label) {    	
    	for (int i=0; i<adjMatRows.length; i++) {
    		String c = adjMatRows[i].getLabel();
    		if (c.equals(label)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
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
			printWriter = new PrintWriter("output.txt");
			printWriter.print("  ");
	    	for (int j=0; j<(adjMatRows[0].getSize()); j++) {
	    		printWriter.print(adjMatRows[j].getLabel() + " ");
	    	}
	    	printWriter.println("");
	    	for (int i=0; i<(adjMatRows.length); i++) {
	    		printWriter.print(adjMatRows[i].getLabel() + " ");
	    		//spaces = String.format("%"+String.valueOf(i).length()+"s", "");
	    		for (int j=0; j<(adjMatRows[i].getSize()); j++) {
	    			printWriter.print(adjMatRows[i].getEdge(j) + " ");
	    		}
	    		printWriter.println("");
	    	}
	    	printWriter.println("");
	    	printWriter.println("SIZE: " + adjMatRows.length);
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