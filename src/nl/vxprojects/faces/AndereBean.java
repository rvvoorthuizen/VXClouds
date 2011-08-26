package nl.vxprojects.faces;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class AndereBean {
	private String message = "";
	
	public AndereBean(){
		System.out.println("Klasse AndereBean instantiated");
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}