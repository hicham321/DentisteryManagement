package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;

import org.hicham.Model.MedicamentQueries;
import org.hicham.Model.OrdonanceReportBean;
import org.hicham.Model.PatientQueries;
import org.hicham.View.ActPatientView;
import org.hicham.View.ChangeMotPassView;
import org.hicham.View.ExamenComplimentaireView;
import org.hicham.View.GestionStockView;
import org.hicham.View.HomePanel;
import org.hicham.View.InfoPatient;
import org.hicham.View.JustificationAbsenceView;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
import org.hicham.View.OrdonanceMenuView;
import org.hicham.View.PatientView;
import org.hicham.View.ProtheseFixeView;
import org.hicham.View.ProthesePartielleView;
import org.hicham.View.ProtheseTotaleView;
import org.hicham.View.ProtheseView;
import org.hicham.View.RecherchePatientView;
import org.hicham.View.RegisterView;
import org.hicham.View.RendezVousView;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class ControllerOrdonance {


	//If we don't need the main frame or any of the other frames 
	//than we can just delete the corresponding fields
	InfoPatient infoPatient= new InfoPatient();
	ActPatientView actPatient= new ActPatientView();
	OdfPatient odfPatient= new OdfPatient();
	ProthesePartielleView prothesePartielleView= new ProthesePartielleView();
	ProtheseFixeView protheseFixeView= new ProtheseFixeView();
	ProtheseTotaleView protheseTotaleView= new ProtheseTotaleView();
	ProtheseView protheseView= new ProtheseView(protheseTotaleView,prothesePartielleView
			,protheseFixeView);
	Ordonance ordonance= new Ordonance();
	RecherchePatientView recherchePatientView= new RecherchePatientView();

	PatientView patient= new PatientView(infoPatient,actPatient,odfPatient
			,protheseView, ordonance,recherchePatientView);
	MenuBar menuBar= new MenuBar();
	GestionStockView gestionStockView= new GestionStockView();
	MedicamentQueries medicamentQueries= new MedicamentQueries();
	RegisterView registerView= new RegisterView();
	ChangeMotPassView changeMotPassView= new ChangeMotPassView();

	HomePanel homePanel= new HomePanel(registerView,changeMotPassView);
	RendezVousView rendezVousView= new RendezVousView();
    JustificationAbsenceView justificationAbsenceView= new JustificationAbsenceView();
    public OrdonanceMenuView ordonanceMenuView= new OrdonanceMenuView();
    public ExamenComplimentaireView examenComplimentaireView= new ExamenComplimentaireView();

	MainFrame mainFrame= new MainFrame( homePanel,patient,gestionStockView
			,rendezVousView,justificationAbsenceView,registerView
			,ordonanceMenuView,examenComplimentaireView,menuBar);
	PatientQueries patientQueries= new PatientQueries();
	ActPatientView actPatientView= new ActPatientView();
	ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient
			,patientQueries,recherchePatientView
			,actPatientView,odfPatient,protheseFixeView,prothesePartielleView,protheseTotaleView,ordonance);

	Collection<OrdonanceReportBean> collBean = new ArrayList<OrdonanceReportBean>();
	int id=1;
	
	
	public ControllerOrdonance(MainFrame mainFrame,HomePanel homePanel,PatientView patient
			,Ordonance ordonance ,MedicamentQueries medicamentQueries
			,ControllerInfoPatient controllerInfoPatient){
		this.patient= patient;
		this.ordonance= ordonance;
		this.homePanel=homePanel;
		this.mainFrame= mainFrame;
		this.medicamentQueries= medicamentQueries;
		this.controllerInfoPatient= controllerInfoPatient;
		this.ordonance.addOrdonanceActionListener(new OrdonanceActionListener());
	}

	class OrdonanceActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== ordonance.getOk()){
				try{
					//report
					//print ordonance rows into textArea
					readTextArea();
					printReport();
					setOrdonanceEmpty();
					id=1;
					collBean.clear();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			if(e.getSource()== ordonance.getAdd()){
				String medAndSituation= ordonance.getNomMed().getSelectedItem().toString()+" "
						+ ordonance.getSituation().getText();

				//textArea
				String medLine= "->"+medAndSituation; 
				ordonance.getMedList().append(medLine + "\n");
				ordonance.getSituation().setText("");


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
			InputStream stream= new FileInputStream("src/resources/ordonanceReport.jasper");
			//JasperReport jasperReport = JasperCompileManager.compileReport(stream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(stream
					,params, getData());
			
			JRViewer v= new JRViewer(jasperPrint);
			v.setZoomRatio(0.55f);
			ordonance.cards.add(v, "Card 2");
			CardLayout cardLayout = (CardLayout) ordonance.cards.getLayout();
			cardLayout.show(ordonance.cards, "Card 2");

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setOrdonanceEmpty(){
		ordonance.getNomMed().setSelectedIndex(0);
		ordonance.getSituation().setText("");;
		ordonance.getMedList().setText("");

	}
	public void showPatientInfoPanel(){
		CardLayout cardLayout = (CardLayout) ordonance.cards.getLayout();
		cardLayout.show(ordonance.cards, "Card 1");
	}
	
	public void readTextArea(){
		String nom=controllerInfoPatient.getCurrentPatient().getName();
		String prenom=controllerInfoPatient.getCurrentPatient().getPrenom();
		String age= controllerInfoPatient.getCurrentPatient().getAge();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date=sdf.format(System.currentTimeMillis());
		
		String[] parts = ordonance.getMedList().getText().split("->");
        for (int i = 1; i < parts.length; i++) {
        	ArrayList<String> list= new ArrayList<>();
        	list.add(parts[i]);
    		putReportInfo(nom, prenom, age, date, list);
		}
			
	}

}



