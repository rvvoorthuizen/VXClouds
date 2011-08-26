package nl.vxprojects.faces;

import java.util.regex.*;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("urlvalidator")
public class UrlValidator implements Validator {

	public UrlValidator(){
		System.out.println("Klasse UrlValidator geinstantieerd.");
	}
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String input = (String)arg2;
		if( !input.matches("^([hH][tT][tT][pP][sS]?://)?([\\w\\-?])+\\.([\\w\\-?])+(\\.([\\w\\-?])+)?(\\.(com|nl|net|org|eu|COM|NL|NET|ORG|EU))+[\\/]?$") ) {   
			throw new ValidatorException(new FacesMessage("Dat is geen geldige OpenID Identifier URL"));
		}
	}
}