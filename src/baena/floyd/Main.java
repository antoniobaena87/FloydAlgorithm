package baena.floyd;

import java.io.File;

public class Main {
	
	public static final String HELP_PARAM = "-h";
	public static final String TRACE_PARAM = "-t";
	
	public static void main(String[] args) {
		
		boolean trace = false;
		boolean saveToDisk = false;
		String dataIn = null;
		String dataOut = null;
		
		for(int i = 0; i < args.length; i++) {
			String arg = args[i];
			
			if(arg.equals(HELP_PARAM)) {
				Main.printHelp();
			} else if(arg.equals(TRACE_PARAM)) {
				trace = true;
			} else {
				// this is either the read data or write data file
				// read data file has to exist in disk already, but write data file has to not exist
				String filePath = arg;
				
				File f = new File(filePath);
				if(f.exists()) {
					if(dataIn != null) {
						// we already have a file with the data in, so this file should not exist
						throw new IllegalArgumentException("Error. El fichero de salida debe no existir.");
					}
					// data to read
					dataIn = filePath;
					// if empty, this is an error
				} else {
					// file to write solution
					saveToDisk = true;
					dataOut = filePath;
				}
			}
		}
		
		Floyd floyd;
		
		if(args.length == 1 && args[0].equals(HELP_PARAM)) {
			return;
		}
		
		if (dataIn == null) {
			floyd = new Floyd(new Table(), trace, saveToDisk);
		} else {
			floyd = new Floyd(dataIn, trace, saveToDisk);
		}
		
		floyd.solve();
		
		floyd.saveSolution(dataOut);
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
