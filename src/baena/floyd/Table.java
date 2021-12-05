package baena.floyd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {
	
	private int[][] matrix;
	private int n;
	
	public static final int INFINITE = Integer.MAX_VALUE/2;
	
	/**
	 * Initializes matrix to -1 instead of 0 because, in our zero based indexes, zero is actally a valid node.
	 * 
	 * @param n
	 */
	public Table(int n) {
		
		this.n = n;
		this.matrix = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				this.matrix[i][j] = -1;
			}
		}
	}
	
	/**
	 * Construct a table from the data stored in a text file.
	 * 
	 * @param pathFile
	 */
	public Table(String pathFile) {
		
		final String INFINITE_CHAR = "-";
		List<String> auxData = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(pathFile))){
			String line = br.readLine();

			while(line != null) {
				
				if(line.isBlank()) {
					line = br.readLine();
					continue;
				}
				
				auxData.add(line);
				this.n++;
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		this.matrix = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String[] data = auxData.get(i).split(" ");
			
			for(int j = 0; j < n; j++) {
				this.matrix[i][j] = data[j].equals(INFINITE_CHAR) ? Table.INFINITE : Integer.parseInt(data[j]);
			}
		}
	}
	
	/**
	 * Gets the current lesser length from the node i to the node j.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int get(int i, int j) {
		return matrix[i][j];
	}
	
	public int getNumberOfNodes() {
		return n;
	}
	
	/**
	 * Sets a new lesser length value path from node i to node j.
	 * 
	 * @param i
	 * @param j
	 * @param length
	 */
	public void set(int i, int j, int length) {
		matrix[i][j] = length;
	}
}
