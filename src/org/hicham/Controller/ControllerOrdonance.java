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
import org.hicham.View.GestionStockView;
import org.hicham.View.HomePanel;
import org.hicham.View.InfoPatient;
import org.hicham.View.MainFrame;
import org.hicham.View.MenuBar;
import org.hicham.View.OdfPatient;
import org.hicham.View.Ordonance;
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
	MainFrame mainFrame= new MainFrame( homePanel,patient,gestionStockView
			,rendezVousView,registerView,menuBar);
	PatientQueries patientQueries= new PatientQueries();
	ActPatientView actPatientView= new ActPatientView();
	ControllerInfoPatient controllerInfoPatient= new ControllerInfoPatient(infoPatient
			,patientQueries,recherchePatientView,actPatientView,odfPatient,ordonance);
	
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
				//code for showing ordonance
				//temporarily for adding records to the database
				try{
					
				List<String>med =medicamentQueries.listOfMeds();
				medicamentQueries.addBatchMedicament(med);
				DefaultComboBoxModel dcm=medicamentQueries.comboBoxModel(med);
				ordonance.getNomMed().setModel(dcm);
				
				//report
				//print ordonance rows into textArea
				
				
				printReport();
				
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			if(e.getSource()== ordonance.getAdd()){
				String nom=controllerInfoPatient.getCurrentPatient().getName();
				String prenom=controllerInfoPatient.getCurrentPatient().getPrenom();
				String age= controllerInfoPatient.getCurrentPatient().getAge();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String date=sdf.format(System.currentTimeMillis());
				
				ArrayList<String> med= new ArrayList<>();
				String medAndSituation= ordonance.getNomMed().getSelectedItem().toString()+" "
				               + ordonance.getSituation().getText();
				med.add(medAndSituation);
				putReportInfo(nom, prenom, age, date, med);
				
			}
			}

		}
		private  JRDataSource getDataSource() {
		    Collection<OrdonanceReportBean> coll = new ArrayList<OrdonanceReportBean>();

		    OrdonanceReportBean bean = new OrdonanceReportBean(1,"21/01","Gherbi"
		    		,"mohamed Amine","22",Arrays.asList("London", "Paris","zesdds","ssdd"));

		    coll.add(bean);

		    bean = new  OrdonanceReportBean(2,"dsfdf","dfdff","dff","dfdff"
		    		,Arrays.asList("vc", "wxw"));
		    coll.add(bean);

		    bean =  new OrdonanceReportBean(3,"fhggfgfh","dfdf","ssdsd","erer"
		    		,Arrays.asList("rr", "r"));
		    coll.add(bean);

		    return new JRBeanCollectionDataSource(coll);
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
					JasperViewer.viewReport(jasperPrint);

			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		

	}



