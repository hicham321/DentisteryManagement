package org.hicham.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.MarshalledObject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationID;
import java.rmi.activation.ActivationInstantiator;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hicham.Model.JustificationReportBean;
import org.hicham.Model.OrdonanceReportBean;
import org.hicham.Model.Patient;
import org.hicham.Model.PatientQueries;
import org.hicham.View.JustificationAbsenceView;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

public class ControllerJustification {


	PatientQueries patientQueries= new PatientQueries();
	JustificationAbsenceView justificationAbsenceView= new JustificationAbsenceView();
	Collection<JustificationReportBean> collBean = new ArrayList<JustificationReportBean>();
	int id=1;
	public ControllerJustification(JustificationAbsenceView justificationAbsenceView){


		this.justificationAbsenceView=justificationAbsenceView;
		this.justificationAbsenceView.addJustificationActionListener(
				new JustificationActionListener());
	}
	class JustificationActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource()== justificationAbsenceView.getGenerer()) {
				//get the data from the text field and sex combo box 
				String nom=justificationAbsenceView.getNomText().getText();
				String sex=justificationAbsenceView.getSex().getSelectedItem().toString();
				String sujet=justificationAbsenceView.getSujet().getText();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String date=sdf.format(System.currentTimeMillis());
				int time=LocalDateTime.now().getHour();

				String timeLabel="";
				System.out.println(time);
				if(time>6 & time<12){
					timeLabel="matin";
				}
				else{
					timeLabel="aprés midi";
				}
				String sexLabel="";
				if("Femele".equals(sex)){
					sexLabel="la patiente";
				}
				else{
					sexLabel="le patient";
				}
                //use the data in the bean
				putInfoReport(nom, date, sujet, sexLabel, timeLabel);
				printReport();
				justificationAbsenceView.setFieldsEmpty();
				id=1;
				collBean.clear();
			}
			if (e.getSource()== justificationAbsenceView.getNomPatient()) {
				int selecteditem=justificationAbsenceView.getNomPatient().getSelectedIndex();
				List<Patient>patients=patientQueries.findAllPatients();
				Patient selectedPatient=patients.get(selecteditem);
				
				String nom=selectedPatient.getName();
				String prenom= selectedPatient.getPrenom();
				String sex= selectedPatient.getSex();
				
				justificationAbsenceView.getNomText().setText(nom+" "+prenom);
				justificationAbsenceView.getSex().setSelectedItem(sex);
			}
		}

	}

	public void putInfoReport(String nom, String date, String sujet,String sexLabel
			,String timeLabel){
		JustificationReportBean beanInfo= new JustificationReportBean(date,timeLabel,sexLabel,sujet,nom);
		collBean.add(beanInfo);
		id++;
	}
	public JRDataSource getData(){
		return new JRBeanCollectionDataSource(collBean);

	}
	public void printReport(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			InputStream stream= new FileInputStream("src/resources/justification.jasper");
			//JasperReport jasperReport = JasperCompileManager.compileReport(stream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(stream
					,params, getData());
			
			JRViewer v= new JRViewer(jasperPrint);
			v.setZoomRatio(0.55f);
			justificationAbsenceView.cards.add(v, "Card 2");
			CardLayout cardLayout = (CardLayout) justificationAbsenceView.cards.getLayout();
			cardLayout.show(justificationAbsenceView.cards, "Card 2");

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
