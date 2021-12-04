package baena.floyd;

public class Floyd {
	
	Table M;		// initially, the adjacency matrix
	Table route;	// solution
	
	public Floyd(String dataPath) {
		
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
					
					tmp = M.getLength(i, k) + M.getLength(k, j);
					
					if(tmp < M.getLength(i, j)) {
						M.setLength(i, j, tmp);
						route.setLength(i, j, k);
					}
				}
			}
		}
	}
	
	public Table getRouteTable() {
		return route;
	}
	
	public static void printRoutes(Table t) {
		
		int n = t.getNumberOfNodes();

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				
				if(t.getLength(i, j) == Table.INFINITE) {
					System.out.printf("Ruta de {} a {}: ", i, j);
					System.out.printf("{}, ", i);
					printRoute(t, i, j);
					System.out.println(j);
				}
			}
		}
	}
	
	private static void printRoute(Table t, int i, int j) {
		
		int k = t.getLength(i, j);
		
		if(k != 0) {
			printRoute(t, i, k);
			System.out.printf("{},", k);
			printRoute(t, k, j);
		}
	}
	
}
