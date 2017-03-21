package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;

import org.hicham.Controller.ControllerOrdonanceMenu.OrdonanceMenuActionListener;
import org.hicham.Model.OrdonanceReportBean;
import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.View.ExamenComplimentaireView;
import org.hicham.View.OrdonanceMenuView;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

public class ControllerExamenComplimentaire {
	ExamenComplimentaireView examenComplimentaireView= new ExamenComplimentaireView();
	PatientQueries patientQueries= new PatientQueries();
	
	
	Collection<OrdonanceReportBean> collBean = new ArrayList<OrdonanceReportBean>();
	int id=1;
	
	public ControllerExamenComplimentaire(ExamenComplimentaireView examenComplimentaireView
			,PatientQueries patientQueries){
		this.patientQueries= patientQueries;
		this.examenComplimentaireView= examenComplimentaireView;
		this.examenComplimentaireView.addExamenActionListener(new ExamenActionListener());
	}
	
	class ExamenActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource()== examenComplimentaireView.getOk()){
				try{
					
					//report
					//print ordonance rows into textArea
					readTextArea();
					printReport();
					examenComplimentaireView.setOrdonanceEmpty();
					id=1;
					collBean.clear();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			if(arg0.getSource()== examenComplimentaireView.getAdd()){
				String medAndSituation= examenComplimentaireView.getNomMed().getSelectedItem().toString()+" "
						+ examenComplimentaireView.getSituation().getText();

				//textArea
				String medLine= "->"+medAndSituation; 
				examenComplimentaireView.getMedList().append(medLine + "\n");
				examenComplimentaireView.getSituation().setText("");


			}	
			if (arg0.getSource()== examenComplimentaireView.getNomPrenomCombo()) {
				//get selected patient and then diplay data 
				int selectedItem= examenComplimentaireView.getNomPrenomCombo().getSelectedIndex();
				List<Patient>patients=patientQueries.findAllPatients();
				Patient selectedPatient=patients.get(selectedItem);
				String nom=selectedPatient.getName();
				String prenom= selectedPatient.getPrenom();
				String age= selectedPatient.getAge();
				
				examenComplimentaireView.getNomText().setText(nom);
				examenComplimentaireView.getPrenomText().setText(prenom);
				examenComplimentaireView.getAgeText().setText(age);

				
			}
		}
		
	}
	public JRDataSource getData(){
		return new JRBeanCollectionDataSource(collBean);

	}
	public void putReportInfo(String nom, String prenom
			, String age, String date,ArrayList<String> med){
		//patient info is the first to be written

		OrdonanceReportBean beanInfo= new OrdonanceReportBean(id, date, nom, prenom
				, age, med);
		collBean.add(beanInfo);
		id++;
	}
	public void printReport(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			InputStream stream= new FileInputStream("src/resources/Examen.jasper");
			//JasperReport jasperReport = JasperCompileManager.compileReport(stream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(stream
					,params, getData());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JRViewer v= new JRViewer(jasperPrint);
			v.setZoomRatio(0.55f);
			examenComplimentaireView.cards.add(v, "Card 2");
			CardLayout cardLayout = (CardLayout) examenComplimentaireView.cards.getLayout();
			cardLayout.show(examenComplimentaireView.cards, "Card 2");

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showPatientInfoPanel(){
		CardLayout cardLayout = (CardLayout) examenComplimentaireView.cards.getLayout();
		cardLayout.show(examenComplimentaireView.cards, "Card 1");
	}
	
	public void readTextArea(){
		//get patient from combobox 
		String nom=examenComplimentaireView.getNomText().getText();
		String prenom=examenComplimentaireView.getPrenomText().getText();
		String age= examenComplimentaireView.getAgeText().getText();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date=sdf.format(System.currentTimeMillis());
		
		String[] parts = examenComplimentaireView.getMedList().getText().split("->");
        for (int i = 1; i < parts.length; i++) {
        	ArrayList<String> list= new ArrayList<>();
        	list.add(parts[i]);
    		putReportInfo(nom, prenom, age, date, list);
		}
			
	}

}
