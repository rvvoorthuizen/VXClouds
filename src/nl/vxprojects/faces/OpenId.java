package nl.vxprojects.faces;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import nl.vxprojects.model.GitHubAdapter;
import nl.vxprojects.model.LdapAdapter;
import nl.vxprojects.model.LdapAttributes;
import nl.vxprojects.model.OpenIdAttributes;
import nl.vxprojects.model.OpenIdHyvesAdapter;
import nl.vxprojects.model.OpenIdVeriSignAdapter;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.message.MessageException;
 
/**
 * Authenticate bij een openid provider
 * @author Ronald
 * @param 
 */

@ManagedBean(name="openid")
@SessionScoped
public class OpenId implements java.io.Serializable {
 
	private static final long serialVersionUID = 8342822939628211667L;
    private String userSuppliedId = "";
	private OpenIdAttributes oiattr;
	private LdapAttributes ldapattr;
	private GitHubAdapter ghapter;
	
	@EJB
    private OpenIdVeriSignAdapter oidapterverisign;
	@EJB
	private OpenIdHyvesAdapter oidapterhyves;
    @EJB
    private LdapAdapter ldapter;

    
    public OpenId() throws ConsumerException {
    	System.out.println("Klasse OpenId instantiated");
    }   

    public void login() throws IOException, OpenIDException, ConsumerException {  	
    	oiattr = new OpenIdAttributes();
    	ldapattr = new LdapAttributes();
    	ghapter = new GitHubAdapter();
    	oiattr.setUserSuppliedId(userSuppliedId);
    	oiattr.setReturnToUrl(returnToUrlBuilder("/openid.xhtml"));
    	String destinationUrl = null;
    	ghapter.getGitHubInfo("rvvoorthuizen");
    	System.out.println("net na getGitHubInfo");
    	if(userSuppliedId.contains("hyves")){
    	destinationUrl = oidapterhyves.authenticate(oiattr);
    	}
    	else if(userSuppliedId.contains("verisign")){
    	destinationUrl = oidapterverisign.authenticate(oiattr);	
    	}
    	    	
        if (destinationUrl != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(destinationUrl);
            ldapattr = ldapter.login(ldapattr);
        }         
    }
       
    private String returnToUrlBuilder(String urlExtension) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String returnToUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + context.getApplication().getViewHandler().getActionURL(context, urlExtension);
        return returnToUrl;
    }
    
	/**
     * hidden inputfield wordt geladen op openid.xhtml en simuleert onLoad/Init event. 
     * @return String "pageLoaded"
	 * @throws MessageException 
     */
    public String getOnLoad() throws MessageException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
    	if(userSuppliedId.contains("hyves")){
        oiattr = oidapterhyves.verifyResponse(request, oiattr);
    	}
    	else if(userSuppliedId.contains("verisign")){
    	oiattr = oidapterverisign.verifyResponse(request, oiattr);
    	}
    	return "pageLoaded";
    }
    
    //tbv functionaliteit openid-invoerveld:
    
    public String getUserSuppliedId() {
		return userSuppliedId;
	}

	public void setUserSuppliedId(String userSuppliedId) {
		this.userSuppliedId = userSuppliedId;
	}
	
	public OpenIdAttributes getOiattr(){
		return oiattr;
	}
	
	public LdapAttributes getLdapattr(){
		return ldapattr;
	}
	
	public GitHubAdapter getGhapter(){
		return ghapter;
	}
}  