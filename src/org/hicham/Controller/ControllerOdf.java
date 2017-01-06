package org.hicham.Controller;

import org.hicham.Model.Odf;
import org.hicham.View.OdfPatient;

public class ControllerOdf {
	
	OdfPatient odfPatient= new OdfPatient();
	Odf odf= new Odf();
	
	public ControllerOdf(OdfPatient odfPatient, Odf odf) {
		this.odfPatient= odfPatient;
		this.odf= odf;
		
	
	}

}
