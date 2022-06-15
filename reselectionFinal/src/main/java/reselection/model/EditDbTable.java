package reselection.model;

import java.util.List;

import db.equipment.Equipment;

//модель таблицы, показывающей базу данных, которую мы добавили в программу

public class EditDbTable {
	
	int id_all;
	String mnfctrr, model_all, vc_all, model_smns;
	
	//private final List<Equipment> equipmentz;
	
	//public EditDbTable(List<Equipment> equipmentz) {
	//	this.equipmentz = equipmentz;
	//}
	
	/**
	public EditDbTable(int id_all, String mnfctrr, String model_all, String vc_all) {
		this.id_all = id_all;
		this.mnfctrr = mnfctrr;
		this.model_all = model_all;
		this.vc_all = vc_all;
		//this.anlg_smns = anlg_smns;
	}
	*/
	
	public EditDbTable(int id_all, String mnfctrr, String model_all, String vc_all, String model_smns) {
		this.id_all = id_all;
		this.mnfctrr = mnfctrr;
		this.model_all = model_all;
		this.vc_all = vc_all;
		this.model_smns = model_smns;
	}

	public long getId_all() {
		return id_all;
	}

	public void setId(int id_all) {
		this.id_all = id_all;
	}

	public String getMnfctrr() {
		return mnfctrr;
	}

	public void setMnfctrr(String mnfctrr) {
		this.mnfctrr = mnfctrr;
	}

	public String getModel_all() {
		return model_all;
	}

	public void setModel(String model_all) {
		this.model_all = model_all;
	}

	public String getVc_all() {
		return vc_all;
	}

	public void setVc_all(String vc_all) {
		this.vc_all = vc_all;
	}
	
	public String getModel_smns() {
		return model_smns;
	}

	public void setModel_smns(String model_smns) {
		this.model_smns = model_smns;
	}
}
