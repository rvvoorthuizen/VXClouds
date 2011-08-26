package nl.vxprojects.faces;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class NaamBean {
	private String message = "Voornaam";
	private String message2 = "Achternaam";
	private String[] namen = { "Hans", "Hendrik", "Joep", "Frits", "Machteld", "Roderick", "Ahmed", "Pim", "Ton"}; 
	private String fotoUrl = "../resources/vx_logo.jpg";
	private int fotoHeight=100;
	private int fotoWidth=100;
	
	public NaamBean(){
		System.out.println("NaamBean instantiated");
	}
	
	public String[] getNamen() {
		return namen;
	}

	public void setNamen(String[] namen) {
		this.namen = namen;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
		setFotoUrl(this.message, this.message2);
	}
	
	public String getFotoUrl() {
		return fotoUrl;
	}
	
	public void setFotoUrl(String message){
		this.message = message;
	}
	
	private void setFotoUrl(String message, String message2){
		if("mr.".equals(message) || "Mr.".equals(message)){
			if("bean".equals(message2) || "Bean".equals(message2)){
				fotoUrl = "../resources/mrbean.jpg";
				setFotoWidth(300);
				setFotoHeight(300);
			}
		}
	}

	public int getFotoHeight() {
		return fotoHeight;
	}

	public void setFotoHeight(int fotoHeight) {
		this.fotoHeight = fotoHeight;
	}

	public int getFotoWidth() {
		return fotoWidth;
	}

	public void setFotoWidth(int fotoWidth) {
		this.fotoWidth = fotoWidth;
	}
}