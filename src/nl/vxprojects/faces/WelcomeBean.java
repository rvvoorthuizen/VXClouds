package nl.vxprojects.faces;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "welcome")
public class WelcomeBean {
	private String message="#{msg['messages.welcomemessage']}";
	
    public WelcomeBean() {
        System.out.println("Klasse WelcomeBean ge�nstantieerd.");
    }
    public String getMessage() {
        return message;
    }
    
}