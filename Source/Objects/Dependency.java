package Objects;

public class Dependency {
	
	public String type;
	public String governor;
	public String dependent;
	
	public Dependency(String type, String governor,String dependent) {
		this.type = type;
		this.governor = governor;
		this.dependent = dependent;
	}
	
	public String toString(){
		return ("[Type: "+type+"] [Governor: "+governor+"] [Dependent: "+dependent+"]");
	}
}
