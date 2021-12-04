package baena.floyd;

public class Main {
	
	public static final String HELP_PARAM = "-h";
	public static final String TRACE_PARAM = "-t";
	
	public static void main(String[] args) {
		
		for(String arg:args) {
			if(arg.equals(HELP_PARAM)) {
				Main.printHelp();
			}
		}
		
		Floyd floyd = new Floyd("Graph");
		floyd.solve();
		Floyd.printRoutes(floyd.getRouteTable());
	}
	
	public static void printHelp() {
		System.out.println(
				"SINTAXIS: floyd [-t][-h] [fichero entrada] [fichero salida]\n" +
						"\t-t\t\t\tTraza el algoritmo\n" +
						"\t-h \t\t\tMuestra esta ayuda\n" +
						"\t[fichero entrada]\tMatriz de adyacencia que representa el grafo\n" +
						"\t[fichero salida]\tPara cada par de nodos: la lista de nodos\n"
				);
	}
}
