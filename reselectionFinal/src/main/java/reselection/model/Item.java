package reselection.model;

import java.util.stream.Stream;

//модель устройства, которая появляется после поискового запроса

public class Item {
	
	private String name;
	private String purpose;
	private String price;
	private String weight;
	private String effort;
	private String damperArea;
	private String color;
	private String imgSrc;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String price) {
		this.purpose = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getEffort() {
		return effort;
	}
	public void setEffort(String effort) {
		this.effort = effort;
	}
	public String getDamperArea() {
		return damperArea;
	}
	public void setDamperArea(String damperArea) {
		this.damperArea = damperArea;
	}
	
	public boolean hasAllDetailsEntered() {
		if ((this.name == null && this.damperArea == null) || (this.name.isEmpty() && this.damperArea.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}
	

}