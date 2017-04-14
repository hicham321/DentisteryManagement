package org.hicham.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

public class StatisticsQueries {
	//method to get each patient by month
	public Map<Integer,Integer> getRendezVousByMonth(int year){
		SessionsDB FactoryObject= new SessionsDB();
		Session session= FactoryObject.getFactory().openSession();
		//five types of actions three protheses, odf and act
       List<Date>rendezVousAll;
       Map<Integer,Integer> countByMonth= new HashMap<>();
		try {
			rendezVousAll	=session.createQuery("select date from Act").list();
			rendezVousAll.addAll(session.createQuery("select date from Odf").list());
			rendezVousAll.addAll(session.createQuery("select date from ProtheseFixe").list());
			rendezVousAll.addAll(session.createQuery("select date from ProtheseTotale").list());
			rendezVousAll.addAll(session.createQuery("select date from ProthesePartielle").list());

			for (int i = 0; i < rendezVousAll.size(); i++) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(rendezVousAll.get(i));
				if (countByMonth.get(cal.get(Calendar.MONTH))==null) {
					countByMonth.put(cal.get(Calendar.MONTH), 1);
				}
				else{
				countByMonth.put(cal.get(Calendar.MONTH), countByMonth.get(cal.get(Calendar.MONTH))+1);
				}
			}
			//			Query q = session.createQuery("select count(*) from act where date_field = :now");
			//			//q.setDate("now", 
			//			q.setInteger("now", arg1);		
			//			//Long count = (Long) session.createQuery("select count(*) from  Patient").uniqueResult();
		} finally {
			session.close();
		}		
		return countByMonth;
	}

}
