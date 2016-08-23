
public class sArray <T extends Object> {
	
	private static final String NO_EDGE_VALUE = "0";
	public String getNoEdgeValue() {
		return NO_EDGE_VALUE;
	}

	private static final String HAS_EDGE_VALUE = "1";
	public String getHasEdgeValue() {
		return HAS_EDGE_VALUE;
	}
	
	private String[] values;
	private T label;

	public sArray (int s) {
		values = new String[s];
		for (int i=0; i<s; i++) {
			values[i] = getNoEdgeValue();
		}
	}
	public void expand() {
		String[] newrow = new String[values.length];
		newrow = values;
		values = new String[(newrow.length+1)];
		for (int i=0; i<newrow.length; i++) {
			values[i] = newrow[i];
		}
		values[values.length-1] = getNoEdgeValue();
	}
	
	public void remove(int del) {
		String[] newrow = new String[values.length-1];
		int newI = 0;
		for (int i=0; i<values.length; i++) {
			if (i != del) {
				//newrow[newI] = new Node();
				newrow[newI] = values[i];
				newI++;
			}
		}
		//values = new Node[newrow.length];
		values = newrow;		
	}

	public void setEdge(int i, String val) {
		values[i] = val;
	}
	
	public String getEdge(int s) {
		return values[s];
	}
	
	public int getSize() {
		return values.length;
	}
	
	public T getLabel() {
		return label;
	}
	public void setLabel(T label) {
		this.label = label;
	}

}

