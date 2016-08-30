
public class sArray {
	
	private static final int NO_EDGE_VALUE = 0;
	public int getNoEdgeValue() {
		return NO_EDGE_VALUE;
	}

	private static final int HAS_EDGE_VALUE = 1;
	public int getHasEdgeValue() {
		return HAS_EDGE_VALUE;
	}
	
	private int[] values;
	private String label;

	public sArray (int s) {
		values = new int[s];
		for (int i=0; i<s; i++) {
			values[i] = getNoEdgeValue();
		}
	}
	public void expand() {
		int[] newrow = new int[values.length];
		newrow = values;
		values = new int[(newrow.length+1)];
		for (int i=0; i<newrow.length; i++) {
			values[i] = newrow[i];
		}
		values[values.length-1] = getNoEdgeValue();
	}
	
	public void remove(int del) {
		int[] newrow = new int[values.length-1];
		int newI = 0;
		for (int i=0; i<values.length; i++) {
			if (i != del) {
				newrow[newI] = values[i];
				newI++;
			}
		}
		values = newrow;		
	}

	public void setEdge(int i, int val) {
		values[i] = val;
	}
	
	public int getEdge(int s) {
		return values[s];
	}
	
	public int getSize() {
		return values.length;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

}

