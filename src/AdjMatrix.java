import java.io.*;
import java.util.*;

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

	//private static final Boolean allowResize = true; 
	private static final int gSize = 5;
	private static final String NO_EDGE_VALUE = "0";
	private static final String HAS_EDGE_VALUE = "1";
	//private sArray mArray;
	//private sArray adjMatRow;
	private sArray[] adjMatRows;
	/**
	 * Contructs empty graph.
	 */
    public AdjMatrix() {
    	adjMatRows = new sArray[gSize];
		for (int i=0; i<(gSize); i++) {
			adjMatRows[i] = new sArray((gSize), NO_EDGE_VALUE);
		}
		output();
    } // end of AdjMatrix()
    
    /**
     * Debug.
     */
    public void output() {
    	System.out.print("  ");
    	for (int j=0; j<(adjMatRows[0].getSize()); j++) {
    		System.out.print(j + " ");
    	}
    	System.out.println("");
    	for (int i=0; i<(adjMatRows.length); i++) {
    		System.out.print(i + " ");
			for (int j=0; j<(adjMatRows[i].getSize()); j++) {
				System.out.print(adjMatRows[i].getEdge(j) + " ");
			}
			System.out.println("");
		}
    	System.out.println("");
		System.out.println("SIZE: " + adjMatRows.length);
    }
    
    /**
     * We are assuming the Adjacency Matrix is a square.
     */
    public void addVertex(T vertLabel) {
    	//e.g. 5
    	int s = getIntVal(vertLabel);//s = 6 - becomes label and position
    	if (s < 0) System.err.println("Value must convert to a positive integer.");
    	//if (s <= (adjMatRows.length-1)) return;    	
    	
    	//if 6 > 5 add vertex
    	if ((s+1) > adjMatRows.length) {
    		System.out.println("ADD VERTEX @ " + (s+1) + " : " + (adjMatRows.length));
    		for (int i=0; i<adjMatRows.length; i++) {
    			adjMatRows[i].resize((s+1));
    		}
    		enlarge(s);
    	}    	
    	output();    	
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
    	//Ensure vertices exist, else create them
    	if (getIntVal(srcLabel) > (adjMatRows.length-1)) {
    		addVertex(srcLabel);
    	}
    	if (getIntVal(tarLabel) > (adjMatRows.length-1)) {
    		addVertex(tarLabel);
        }  	
    	//Set value in corresponding Vertices
    	adjMatRows[getIntVal(srcLabel)].setEdge(getIntVal(tarLabel), HAS_EDGE_VALUE);
    	adjMatRows[getIntVal(tarLabel)].setEdge(getIntVal(srcLabel), HAS_EDGE_VALUE);    	
    	output();    	
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        //String[] row  = adjMatrix.getRow(getIntVal(vertLabel));
        
        
       	/*for (int i=0; i<row.length; i++) {
        	
        	//System.out.println("VAL: " + row.length);
        	
        	if (row[i] != NO_EDGE_VALUE) {
        		//System.out.println("ADDING: " + row[i]);
        		String label = String.valueOf(row[i]);
        		neighbours.add((T) label);
        	}
        }*/
        
        
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	//adjMatrix.setEdge(getIntVal(srcLabel), getIntVal(tarLabel), NO_EDGE_VALUE);
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        // Implement me!
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    private int getIntVal(T vertLabel) {
    	if (String.valueOf(vertLabel).length() == 1) {
    		char c = ((String) vertLabel).charAt(0);
    		return Character.getNumericValue(c);
    	} else {
    		return Integer.parseInt((String) vertLabel);
    	}  	
    }
    private void enlarge(int s) {
    	sArray[] newrow = new sArray[s+1];		
		for (int i=0; i<(s+1); i++) {
			if (i < adjMatRows.length) {
				newrow[i] = adjMatRows[i];
			} else {
				newrow[i] = new sArray((s+1), NO_EDGE_VALUE);
			}
		}
		adjMatRows = new sArray[s+1];
		adjMatRows = newrow;
    }  
    
} // end of class AdjMatrix