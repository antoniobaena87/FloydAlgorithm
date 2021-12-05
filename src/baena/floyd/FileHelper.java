package baena.floyd;

public class FileHelper {
	
	public String getResourcePath(String resource) {
		String res = getClass().getClassLoader().getResource(resource).getPath();
		return res;
	}
}
