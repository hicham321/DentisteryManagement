package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import org.hicham.Model.Act;
import org.hicham.Model.Odf;
import org.hicham.Model.OdfQueries;
import org.hicham.Model.PatientQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.InfoPatient;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.RecherchePatientView;

public class ControllerOdf {
	
     OdfPatient odfPatient= new OdfPatient();
    OdfQueries odfQueries= new OdfQueries();
    Odf currentOdf= new Odf();
    
	ActPatientView actPatientView= new ActPatientView();
	PatientQueries patientQueries= new PatientQueries();
	InfoPatient infoPatient = new InfoPatient();
	RecherchePatientView recherchePatientView = new RecherchePatientView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();

    Ordonance ordonance= new Ordonance();
    ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient
    		,patientQueries,recherchePatientView
    		, actPatientView,odfPatient,protheseFixeView,ordonance);
    
	public ControllerOdf(OdfPatient odfPatient,OdfQueries odfQueries
			,PatientQueries patientQueries,ControllerInfoPatient controllerInfoPatient) {
		this.odfPatient= odfPatient;
		this.odfQueries= odfQueries;
		this.patientQueries= patientQueries;
		this.controllerInfoPatient= controllerInfoPatient;
		
		this.odfPatient.addActPatientViewActionListener(new ActPatientViewActionListener());
			
	}
	class ActPatientViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()== odfPatient.getNouveauAct()) {
				//empty odfPatient
				odfPatient.getOk().setEnabled(true);
				setOdfFieldsEmpty();
			}
            if (e.getSource()== odfPatient.getOk()) {
            	
				//insert new odf
				String odfText=odfPatient.getActText().getText();
				Date odfDate=odfPatient.getDatePicker().getDate();
				String odfTemp= odfPatient.getTimePicker().getValue().toString();
				
				//calculate the rest payement and accumulated payements from the actual payement
				
		        int payementTotal= new Integer(odfPatient.getPayementTotal().getText());
		        int payementActuel= new Integer(odfPatient.getPayementActuelText().getText());

				//calc rest and total patient
				int payementOdf= payementActuel;
		        int payementRest=payementTotal-payementOdf;
				odfPatient.getPayementOdfText().setText(new Integer(payementOdf).toString());
				odfPatient.getPayementRestText().setText(new Integer(payementRest).toString());
				currentOdf= new Odf(odfText, payementOdf, payementTotal, payementRest
						, odfDate, odfTemp);
				//setting patient for oneToMany relationship between Patient and Odf
				currentOdf.setPatient(controllerInfoPatient.getCurrentPatient());
				odfQueries.addOdf(currentOdf);
			}
            if (e.getSource()== odfPatient.getModifie()) {
				//edit current odf
            	modifyFieldsOdf();
            	odfQueries.addOdf(currentOdf);
            	//add payement to total
            	
			}
            if (e.getSource()== odfPatient.getSupp()) {
				//delete odf
            	int input = JOptionPane.showOptionDialog(null
						,"Etes vous sure de vouloir supprimer ce Odf?"
						, "Supprimer l'ODF courant"
						, JOptionPane.OK_CANCEL_OPTION
						, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if(input == JOptionPane.OK_OPTION){
					// do something
					odfQueries.deleteOdf(currentOdf);
				}	
			}
            if (e.getSource()== odfPatient.getListActCombo()) {
				//display data depending on selected odf				
				int selecteditem=odfPatient.getListActCombo().getSelectedIndex();
				setSelectedOdfInfo(selecteditem);
				//calculate the payements 
				odfPatient.getPayementTotal().setEnabled(false);
				odfPatient.getPayementActuelText().setEnabled(false);
		    	Odf odf=controllerInfoPatient.getCurrentPatient().getOdfList().get(selecteditem);
		    	int payTotal=odf.getPayementTotal();
		    	int payPatient= odf.getPayement();
		    	odfPatient.getPayementRestText()
		    	          .setText(new Integer(payTotal-payPatient).toString());
            	
			}
		}
		
		
	}
    public void setSelectedOdfInfo(int selectedOdf){
    	Odf odf=controllerInfoPatient.getCurrentPatient().getOdfList().get(selectedOdf);
    	
		odfPatient.getActText().setText(odf.getOdf());
		/*DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time= odf.getTempRendezVous();
        DateFormat df = new SimpleDateFormat("HH:mm:ss"); 
        Date startDate=new Date();
        try {
            startDate = df.parse(time);
            String newDateString = df.format(startDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
		odfPatient.getTimePicker().setValue(startDate);*/
		odfPatient.getDatePicker().setDate(odf.getDateRendezVous());
		odfPatient.getPayementOdfText().setText(new Integer(odf.getPayement()).toString());
		odfPatient.getPayementRestText().setText(new Integer(odf.getPayementRest()).toString());
		odfPatient.getPayementTotal().setText(new Integer(odf.getPayementTotal()).toString());


		currentOdf= odf;
	}

	public void setOdfFieldsEmpty(){
		odfPatient.getActText().setText("");
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		odfPatient.getTimePicker().setValue(cal.getTime());
		Date date= new Date();
		odfPatient.getDatePicker().setDate(date);
		odfPatient.getPayementOdfText().setText("");
		odfPatient.getPayementRestText().setText("");
		odfPatient.getPayementTotal().setText("");
		odfPatient.getPayementActuelText().setText("");	

	}
	public void modifyFieldsOdf(){
		currentOdf.setOdf(odfPatient.getActText().getText());     
		currentOdf.setDateRendezVous(odfPatient.getDatePicker().getDate());
		currentOdf.setTempRendezVous(odfPatient.getTimePicker().getValue().toString());
		currentOdf.setPayement(Integer.parseInt(odfPatient.getPayementOdfText().getText()));
		currentOdf.setPayementRest(Integer.parseInt(odfPatient.getPayementRestText().getText()));
		currentOdf.setPayementTotal(Integer.parseInt(odfPatient.getPayementTotal().getText()));

	}
    


}
