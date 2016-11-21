package org.hicham.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.hibernate.Session;
import org.hicham.Model.PatientQueries.MyClass;

public class ProduitQueries {
	
	public void addProduct(String nomProduit,double prix, int qte){
		Session session = SessionsDB.getFactory().openSession();
		try {

			//save patient object
			session.beginTransaction();
			session.save( new Produit(nomProduit,prix,qte) );
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
	public List<Produit> findAllProducts(){
		Session session = SessionsDB.getFactory().openSession();
		try {

			List<Produit> listProduit= session.createQuery("from Produit").list();

			return listProduit;
		} finally {
			session.close();
		}	
	}
	public List<String> productNames(){
		List<String> listProductNames= new ArrayList<>();
        List<Produit> listOfallProducts= findAllProducts();
        for (int i = 0; i < listOfallProducts.size(); i++) {
        	listProductNames.add(listOfallProducts.get(i).getProduitNom());
		}
        System.out.println(listProductNames);
		return listProductNames ;
	}
	public List<Integer>findProductIds(){
		List<Integer> listProductIds= new ArrayList<>();
        List<Produit> listOfallProducts= findAllProducts();
        for (int i = 0; i < listOfallProducts.size(); i++) {
        	listProductIds.add(listOfallProducts.get(i).getId());
		}
		return listProductIds ;
	}
	//this the combobox model that contains products names
	public DefaultComboBoxModel comboBoxModel(List<String> listProductNames){
		
		DefaultComboBoxModel comboModel= new DefaultComboBoxModel<>();
		for(int i=0;i<listProductNames.size();i++){
			comboModel.addElement(new MyClass(listProductNames.get(i)));	
		}
		return comboModel;
	}
	public Produit getProduct(int id){
        List<Produit> listOfallProducts= findAllProducts(); 
        return listOfallProducts.get(id);
	}
	
	public void addQteToProduct(int idProduct,int qteAdded){
	    
	}
	public void sousQteToProduct(int idProduct,int qtesubtracted){
		
	}
	public class MyClass{

		private String myName;

		public MyClass(String name){
			this.myName = name;
		}
		@Override
		public String toString(){
			return myName;
		}

	}
	
	public DefaultComboBoxModel getComboModel(){
		return comboBoxModel(productNames());
	}

	
	

}
