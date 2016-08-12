package model;

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
			values[i] = new Node();
			values[i].setValue(NO_EDGE_VALUE);
		}
	}
	public void resize(int newsize) {
		Node[] newrow = new Node[newsize];
		for (int i=0; i<newsize; i++) {
			newrow[i] = new Node();
			if (i < values.length) {
				newrow[i].setValue(values[i].getValue());
			} else {
				newrow[i].setValue(NO_EDGE_VALUE);
			}
		}
		values = new Node[newsize];
		values = newrow;
	}
	
	public void remove(int del) {
		Node[] newrow = new Node[values.length-1];
		int newI = 0;
		for (int i=0; i<values.length; i++) {
			if (i != del) {
				newrow[newI] = new Node();
				newrow[newI].setValue(values[i].getValue());
				newI++;
			}
		}
		values = new Node[newrow.length];
		values = newrow;		
	}

	public void setEdge(int t, String val) {
		values[t].setValue(val);
	}
	
	public String getEdge(int s) {
		return values[s].getValue();
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

        public Node() {
        	
        }

        public String getValue() {
            return mValue;
        }

        public void setValue(String value) {
            mValue = value;
        }

    } // end of inner class Node


}

