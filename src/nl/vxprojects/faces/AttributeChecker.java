package nl.vxprojects.faces;

import java.io.Serializable;
import java.lang.String;
import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class AttributeChecker implements Serializable {

	private static final long serialVersionUID = -7661255072785344147L;
	private String ldap_provider_url = "ldap://nlses002.vxproject.nl:389";
	private String ldap_achternaam;
	private String ldap_distinguishedName;
	private String ldap_memberOf;

	public AttributeChecker(String achternaam) {

		Hashtable<String, String> omgeving = new Hashtable<String, String>();
		omgeving.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	
		// Gegevens tbv connectie met LDAP-server:
		omgeving.put(Context.PROVIDER_URL, ldap_provider_url);
		omgeving.put(Context.SECURITY_AUTHENTICATION, "simple");
		omgeving.put(Context.SECURITY_PRINCIPAL, "");
		omgeving.put(Context.SECURITY_CREDENTIALS, "");
		
		try {
			DirContext ctx = new InitialDirContext(omgeving);
			//Haal de attributen op behorende bij de achternaam:
			Attributes attrs = ctx.getAttributes("CN=Ronald van Voorthuizen,OU=Users,OU=AMS,OU=User,DC=vxproject,DC=nl");
			//haal achternaam op via LDAP en sla lokaal op:
			ldap_achternaam = (String)attrs.get("sn").get();
			ldap_distinguishedName = (String)attrs.get("distinguishedName").get();
			ldap_memberOf = (String)attrs.get("memberOf").get();
		    ctx.close();
		} 
		
		catch (CommunicationException e){
			System.out.println("Kan geen verbinding maken met LDAP-server.");
		}
		
		catch (NamingException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			System.out.println("Deze uitzondering is echt onverwacht");
			e.printStackTrace();
		}
	}   

	public void setLdap_provider_url(String ldap_provider_url) {
		this.ldap_provider_url = ldap_provider_url;
	}

	public String getLdap_achternaam(){
		return ldap_achternaam;
	}

	public String getLdap_distname() {
		return ldap_distinguishedName;
	}

	public String getLdap_memberOf() {
		return ldap_memberOf;
	}
}