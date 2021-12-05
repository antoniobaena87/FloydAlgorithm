package baena.floyd;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Floyd {
	
	Table M;		// initially, the adjacency matrix
	Table route;	// solution
	boolean trace;
	boolean saveToDisk;
	
	public Floyd(String dataPath, boolean trace, boolean saveToDisk) {
		
		this.trace = trace;
		this.saveToDisk = saveToDisk;
		
		// Load M table with adjacency matrix data
		M = new Table(dataPath);
		
		// Initialize route table to zero
		route = new Table(M.getNumberOfNodes());
	}
	
	public void solve() {
		
		int n = M.getNumberOfNodes();
		int tmp;
		
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					
					tmp = M.get(i, k) + M.get(k, j);
					
					if(tmp < M.get(i, j)) {
						if(trace) System.out.printf("Estableciendo distacia %d entre los nodos %d y %d\n", tmp, i+1, j+1);
						M.set(i, j, tmp);
						if(trace) System.out.printf("Añadiendo nodo %d como parte del camino entre %d y %d\n\n", k+1, i+1, j+1);
						route.set(i, j, k);
					}
				}
			}
		}
	}
	
	public Table getRouteTable() {
		return route;
	}
	
	public Table getLengthsTable() {
		return M;
	}
	
	public static void printRoutes(Table t) {
		
		int n = t.getNumberOfNodes();

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				
				if(t.get(i, j) != Table.INFINITE) {
					System.out.printf("Ruta de %d a %d: ", i+1, j+1);
					System.out.printf("%d, ", i+1);
					printRoute(t, i, j);
					System.out.println(j+1);
				}
			}
		}
	}
	
	private static void printRoute(Table t, int i, int j) {
		
		int k = t.get(i, j);
		
		if(k != 0) {
			printRoute(t, i, k);
			System.out.printf("%d, ", k+1);
			printRoute(t, k, j);
		}
	}
	
	public void saveSolution(String pathFile) {
		
		StringBuilder solutionString = new StringBuilder();
		
		int n = route.getNumberOfNodes();

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				
				if(route.get(i, j) != Table.INFINITE) {
					
					solutionString.append("[").append(i+1).append(",").append(j+1).append("] : ");
					solutionString.append(i+1).append(", ");
					getRoute(solutionString, i, j);
					solutionString.append(j+1);
					solutionString.append(": ").append(M.get(i, j));
					if(i < n - 1 || j < n - 1) solutionString.append("\n");
				}
			}
		}
		
		if(saveToDisk) {
			// Save to disk
			try(PrintWriter out = new PrintWriter(pathFile)){
				out.print(solutionString.toString());
			}catch(FileNotFoundException e) {
				System.out.println("El fichero de salida no ha podido ser creado... Excepción lanzada: " + e.getMessage());
			}
		} else {
			System.out.println(solutionString.toString());
		}
	}
	
	private void getRoute(StringBuilder solutionString, int i, int j) {
		int k = route.get(i, j);

		if(k != -1) {
			getRoute(solutionString, i, k);
			solutionString.append(k+1).append(", ");
			getRoute(solutionString, k, j);
		}
	}
}
