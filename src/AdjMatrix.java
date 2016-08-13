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
	private static final boolean showOutput = true;

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
    	if (indexer.get(String.valueOf(vertLabel)) != null) {
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
        /*if (getIntVal(vertLabel) > adjMatRows.length) {
        	System.err.println("Vertex does not exist.");
        	return neighbours;
        }*/        
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
    	//int v = getIntVal(vertLabel);
    	if (getIntVal(vertLabel) < 0 || indexer.get(String.valueOf(vertLabel)) == null) {
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
    	//Set value in corresponding Vertices
    	
    	//Ensure vertices exist, else create them
    	if (indexer.get(String.valueOf(srcLabel)) == null) {
    		System.err.println("Source edge does not exist.");
    		return;
    	}
    	if (indexer.get(String.valueOf(tarLabel)) == null) {
    		System.err.println("Target edge does not exist.");
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
    		for (int j=0; j<adjMatRows[i].getSize(); j++) {
    			if (String.valueOf(adjMatRows[i].getEdge(j)).equals("1")) {
    				
    				
    				System.out.print(adjMatRows[i].getLabel() + " ");
    				System.out.print(adjMatRows[j].getLabel() + " ");
    				
    				
    			}
    		}
    		System.out.println("");
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
    	//String.valueOf(vertLabel)
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
    	if (!showOutput) return;
    	
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("output.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.setOut(out);
    	
	    //String spaces = String.format("%"+String.valueOf(gSize).length()+"s", "");
		
		System.out.print("  ");
    	for (int j=0; j<(adjMatRows[0].getSize()); j++) {
    		System.out.print(adjMatRows[j].getLabel() + " ");
    	}
    	System.out.println("");
    	for (int i=0; i<(adjMatRows.length); i++) {
    		System.out.print(adjMatRows[i].getLabel() + " ");
    		//spaces = String.format("%"+String.valueOf(i).length()+"s", "");
    		for (int j=0; j<(adjMatRows[i].getSize()); j++) {
    			System.out.print(adjMatRows[i].getEdge(j) + " ");
    		}
    		System.out.println("");
    	}
    	System.out.println("");
    	System.out.println("SIZE: " + adjMatRows.length);
    }

    
} // end of class AdjMatrix