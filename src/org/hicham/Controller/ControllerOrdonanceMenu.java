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

import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hicham.Model.Dentiste;
import org.hicham.Model.DentisteQueries;
import org.hicham.Model.OrdonanceReportBean;
import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.View.OrdonanceMenuView;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.view.JRViewer;

public class ControllerOrdonanceMenu {
	
	OrdonanceMenuView ordonanceMenuView= new OrdonanceMenuView();
	PatientQueries patientQueries= new PatientQueries();
	
	
	Collection<OrdonanceReportBean> collBean = new ArrayList<OrdonanceReportBean>();
	int id=1;
	
	public ControllerOrdonanceMenu(OrdonanceMenuView ordonanceMenuView
			,PatientQueries patientQueries){
		this.patientQueries= patientQueries;
		this.ordonanceMenuView= ordonanceMenuView;
		this.ordonanceMenuView.addOrdonanceMenuActionListener(new OrdonanceMenuActionListener());
	}
	
	class OrdonanceMenuActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource()== ordonanceMenuView.getOk()){
				try{
					
					//report
					//print ordonance rows into textArea
					readTextArea();
					printReport();
					ordonanceMenuView.setFieldsEmpty();
					id=1;
					collBean.clear();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			if(arg0.getSource()== ordonanceMenuView.getAdd()){
				String medAndSituation= ordonanceMenuView.getNomMed().getSelectedItem().toString()+" "
						+ ordonanceMenuView.getSituation().getText();

				//textArea
				String medLine= "->"+medAndSituation; 
				ordonanceMenuView.getMedList().append(medLine + "\n");
				ordonanceMenuView.getSituation().setText("");


			}	
			if (arg0.getSource()== ordonanceMenuView.getNomPrenomCombo()) {
				//get selected patient and then diplay data 
				int selectedItem= ordonanceMenuView.getNomPrenomCombo().getSelectedIndex();
				List<Patient>patients=patientQueries.findAllPatients();
				Patient selectedPatient=patients.get(selectedItem);
				String nom=selectedPatient.getName();
				String prenom= selectedPatient.getPrenom();
				String age= selectedPatient.getAge();
				
				ordonanceMenuView.getNomText().setText(nom);
				ordonanceMenuView.getPrenomText().setText(prenom);
				ordonanceMenuView.getAgeText().setText(age);

				
			}
		}
		
	}
	public JRDataSource getData(){
		return new JRBeanCollectionDataSource(collBean);

	}
	public void putReportInfo(String nom, String prenom
			, String age, String date,ArrayList<String> med,String nomDentiste
			,String nomArab,String route,String city,String wilaya, String telephone){
		//patient info is the first to be written

		OrdonanceReportBean beanInfo= new OrdonanceReportBean(id, date, nom, prenom
				, age, med,nomDentiste,nomArab,route,city,wilaya,telephone);
		collBean.add(beanInfo);
		id++;
	}
	public void printReport(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
			JRPropertiesUtil jrPropertiesUtil = JRPropertiesUtil.getInstance(jasperReportsContext);
			jrPropertiesUtil.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
			//InputStream stream= new FileInputStream("/src/resources/ordonanceReport.jasper");
			InputStream stream= getClass().getResourceAsStream("/resources/ordonanceReport.jasper");

			
			JasperReport report = (JasperReport) JRLoader.loadObject(stream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, getData());

			
			/*JasperPrint jasperPrint = JasperFillManager.fillReport(stream
					,params, getData());*/

			JRViewer v= ordonanceMenuView.getViewer();
		    v= new JRViewer(jasperPrint);	    
			v.setZoomRatio(0.55f);
			ordonanceMenuView.cards.add(v, "Card 2");
			CardLayout cardLayout = (CardLayout) ordonanceMenuView.cards.getLayout();
			cardLayout.show(ordonanceMenuView.cards, "Card 2");

		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, 
					e.toString(), 
	                "ghghgh", 
	                JOptionPane.ERROR_MESSAGE);		
			}
	}
	
	public void showPatientInfoPanel(){
		CardLayout cardLayout = (CardLayout) ordonanceMenuView.cards.getLayout();
		cardLayout.show(ordonanceMenuView.cards, "Card 1");
	}
	
	public void readTextArea(){
		//get patient from combobox 
		DentisteQueries dentisteQueries= new DentisteQueries();
        Dentiste dentiste=dentisteQueries.getdentiste();
        String nomDentiste= dentiste.getNom();
        String prenomDentiste= dentiste.getPrenom();
		String nomArab= dentiste.getNomPrenomArab();
		String route= dentiste.getRoute();
		String city= dentiste.getCity();
		String wilaya= dentiste.getWilaya();
		String telephone= dentiste.getTel();
		String nom=ordonanceMenuView.getNomText().getText();
		String prenom=ordonanceMenuView.getPrenomText().getText();
		String age= ordonanceMenuView.getAgeText().getText();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date=sdf.format(System.currentTimeMillis());
		
		String[] parts = ordonanceMenuView.getMedList().getText().split("->");
        for (int i = 1; i < parts.length; i++) {
        	ArrayList<String> list= new ArrayList<>();
        	list.add(parts[i]);
    		putReportInfo(nom, prenom, age, date, list,nomDentiste+" "+prenomDentiste,nomArab,route,city,wilaya,telephone);
		}
			
	}
}
