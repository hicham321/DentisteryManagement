package org.hicham.Model;

import java.util.List;

import org.hibernate.Session;

public class RegisterQueries {
	
	public boolean checkPass(String enteredPass, String selectedUser){
		Session session = SessionsDB.getFactory().openSession();
		try {
			boolean passIsCorrect= false;
			List<Register> listUsers= session.createQuery("from Register").list();
			for (int i = 0; i < listUsers.size(); i++) {
				if(selectedUser.equals(listUsers.get(i).getTypeUtil())){
					if(enteredPass.equals(listUsers.get(i).getPassword())){
						passIsCorrect=true;
					}
				}
			}       
			return passIsCorrect;
		} finally {
			session.close();
		}	
	}
}
