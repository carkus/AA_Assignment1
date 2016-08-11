package model;

import java.util.HashMap;

public class sArray {
	
	private static final String NO_EDGE_VALUE = "0";
	public static String getNoEdgeValue() {
		return NO_EDGE_VALUE;
	}

	private static final String HAS_EDGE_VALUE = "1";
	public static String getHasEdgeValue() {
		return HAS_EDGE_VALUE;
	}
	
	private Node[] values;
	
	public sArray (int s) {
		values = new Node[s];
		for (int i=0; i<s; i++) {
			values[i].setValue(NO_EDGE_VALUE);
		}
	}
	
	public String[] buildMatrix(int s, String val) {
		
		
		/*String[][] matrix = new String[s][s];
		for (int i=0; i<s; i++) {
			for (int j=0; j<s; j++) {
				matrix[i][j] = val;
			}
		}
		System.out.println("VAL: " + matrix[1000][1000]);*/
		return null;
		
	}	
	
	public void setEdge(int t, String val) {
		values[t].setValue(val);
	}
	public String getEdge(int s) {
		return values[s].getValue();
	}
	
	public void resize(int newsize) {
		Node[] newrow = new Node[newsize];
		for (int i=0; i<newsize; i++) {
			if (i < values.length) {
				newrow[i].setValue(values[i].getValue());
			} else {
				newrow[i].setValue(NO_EDGE_VALUE);
			}
		}
		values = new Node[newsize];
		values = newrow;
	}
	
	public String[] getRow(int i) {
		
		/*String[] row = new String[matrix[i].length];
		
		System.out.println("LENGTH: " + matrix[i].length);
		
		//row = matrix[i];
		for (int j=0; j<row.length; j++) {
			
			System.out.println("VAL: " + matrix[i][j]);
			
		}*/
		
		return null;
	}
	
	public int getSize() {
		return values.length;
	}
	

	/** 
	 * Kind of unnecessary but will make code more extensible
	 * If things need to change
	 * 
	 * */
    private class Node
    {
        protected String mValue;

        public Node() {}

        public String getValue() {
            return mValue;
        }

        public void setValue(String value) {
            mValue = value;
        }

    } // end of inner class Node


}
