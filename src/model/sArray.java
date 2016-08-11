package model;

public class sArray {
	
	private String[] values;
	
	public sArray (int s, String val) {
		values = new String[s];
		for (int i=0; i<s; i++) {
			values[i] = val;
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
		values[t] = val;
	}
	public String getEdge(int s) {
		return values[s];
	}
	
	public void resize(int newsize) {
		String[] newrow = new String[newsize];
		for (int i=0; i<newsize; i++) {
			if (i < values.length) {
				newrow[i] = values[i];
			} else {
				newrow[i] = "0";
			}
		}
		values = new String[newsize];
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

}
