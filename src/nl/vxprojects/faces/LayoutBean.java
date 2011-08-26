package nl.vxprojects.faces;

import javax.faces.bean.ManagedBean;



@ManagedBean
public class LayoutBean {

	private String tableBackground = "#CCCCCC";

	public LayoutBean(){
		System.out.println("Klasse LayoutBean instantiated");
	}
	
	public String getTableBackground() {
		return tableBackground;
	}

	public void setTableBackground(String tableBackground) {
		this.tableBackground = tableBackground;
	}
}
