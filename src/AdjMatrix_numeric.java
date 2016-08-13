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

	private static final int gSize = 1;

	private HashMap<String, Boolean> indexer = new HashMap<String, Boolean>();
	private sArray[] adjMatRows;
	/**
	 * Contructs empty graph.
	 */
    public AdjMatrix() {
    	adjMatRows = new sArray[gSize];
		for (int i=0; i<(gSize); i++) {
			adjMatRows[i] = new sArray((gSize));
		}
		output();
    } // end of AdjMatrix()
    
    /**
     * We are assuming the Adjacency Matrix is a square.
     */
    public void addVertex(T vertLabel) {
    	//e.g. 5
    	int s = getIntVal(vertLabel);//s = 6 - becomes label and position
    	if (s < 0) System.err.println("Value must convert to a positive integer.");
    	
    	/*if (indexer.get(String.valueOf(vertLabel))) {
    		System.err.println("Vertex already exists.");
    		return;
    	}*/
    	
    	//if 6 > 5 add vertex
    	if ((s+1) > adjMatRows.length) {
    		System.out.println("ADD VERTEX @ " + (s+1) + " : " + (adjMatRows.length));
    		for (int i=0; i<adjMatRows.length; i++) {
    			adjMatRows[i].resize((s+1));
    		}
    		enlarge(s);
    	} else {
    		System.err.println("Vertex already exists.");
    		return;
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
    	String edgeVal = adjMatRows[getIntVal(srcLabel)].getHasEdgeValue();
    	adjMatRows[getIntVal(srcLabel)].setEdge(getIntVal(tarLabel), edgeVal);
    	adjMatRows[getIntVal(tarLabel)].setEdge(getIntVal(srcLabel), edgeVal);    	
    	output();    	
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        if (getIntVal(vertLabel) > adjMatRows.length) {
        	System.err.println("Vertex does not exist.");
        	return neighbours;
        }
        
        sArray row = adjMatRows[getIntVal(vertLabel)];
        int z = row.getSize();

       	for (int i=0; i<z; i++) {
        	
        	System.out.println("VAL: " + row.getEdge(i));
        	
        	if (row.getEdge(i) != row.getNoEdgeValue()) {
        		//System.out.println("ADDING: " + row[i]);
        		//String label = String.valueOf(row[i]);
        		//neighbours.add(row);
        	}       		
        }
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
    	int newI = 0;		
    	int v = getIntVal(vertLabel);
    	if (v < 0 || v > adjMatRows.length) {
    		System.err.println("Vertex does not exist.");
    		return;
    	}
    	//delete corresponding value from 'column' index...
    	for (int i=0; i<adjMatRows.length; i++) {
    		//adjMatRows[i].remove(v);
    	}
    	
    	//remove corresponding matrix 'row'
    	sArray[] newrows = new sArray[adjMatRows.length-1];
    	for (int i=0; i<adjMatRows.length; i++) {			
    		adjMatRows[i].remove(v);
    		if (i != v) {
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
    	String edgeVal = adjMatRows[getIntVal(srcLabel)].getNoEdgeValue();
    	adjMatRows[getIntVal(srcLabel)].setEdge(getIntVal(tarLabel), edgeVal);
    	adjMatRows[getIntVal(tarLabel)].setEdge(getIntVal(srcLabel), edgeVal);
    	output();
    } // end of removeEdges()	
    
    public void printVertices(PrintWriter os) {
        // Implement me!
    	
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
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
	/**
	 * Adjacency Matrix helpers
	 * Written by
	 * Mark Sciclunanewrow.
	 * 
	 */
    
    private int getIntVal(T vertLabel) {
    	
    	System.out.println(String.valueOf(vertLabel));    	
    	
    	if (Character.isLetter(((String) vertLabel).charAt(0))) {
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
				newrow[i] = new sArray((s+1));
			}
		}
		adjMatRows = new sArray[s+1];
		adjMatRows = newrow;
    }

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
    
    static String intToString(int num, int digits) {
    	StringBuffer s = new StringBuffer(digits);
    	int zeroes = digits - (int) (Math.log(num) / Math.log(10)) - 1; 
    	for (int i = 0; i < zeroes; i++) {
    		s.append(0);
    	}
    	return s.append(num).toString();
    }

    
} // end of class AdjMatrix