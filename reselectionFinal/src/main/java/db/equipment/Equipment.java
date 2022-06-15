package db.equipment;

// класс, создающий устройство

public class Equipment {
	
	public Long eq_id;
	
	public int id_all;
	
	public String model;
	public String vc;
	public String price; //потом можно перевести в мани 
	public String manufacturer;
	public String purpose;
	public String replacementComments;
	
	public String weight;
	public String warranty;
	//public int warranty;
	public String country;
	public String typeDac;
	public String returnSpring;
	public String voltage;
	//public int effort;
	public String effort;
	//public double damperArea;
	public String damperArea;
	public String controlSignal;
	//public int timePosition;
	//public int timeOpen;
	//public int timeClose;
	public String timePosition;
	public String timeOpen;
	public String timeClose;
	public String addSwitch; //потом можно в интеджер
	public String stockPerimeter;
	public String stockDiameter;
	public String connectionType;
	//public int ipClass;
	public String ipClass;
	
	public String thermalDevice;
	public String thermalDeviceTemperature;
	
	//public String price;
	public String smnsAn;
	public String damper_area;
	public String signal;

	public Equipment() {
	}	
	
	public Equipment(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	// конструктор для экрана сравнения
	public Equipment (String model,
						String vc,
						String price,
						String manufacturer,
						String purpose,
						String replacementComments,
						//int warranty,
						String weight,
						String warranty,
						String country,
						String typeDac,
						String returnSpring,
						String voltage,
						//int effort,
						//double damperArea,
						String effort,
						String damperArea,
						String controlSignal,
						//int timePosition,
						//int timeOpen,
						//int timeClose,
						String timePosition,
						String timeOpen,
						String timeClose,
						String addSwitch,
						String stockPerimeter,
						String stockDiameter,
						String connectionType,
						//int ipClass
						String ipClass,
						String thermalDevice,
						String thermalDeviceTemperature){
		this.model = model;
		this.vc = vc;
		this.price = price;
		this.manufacturer = manufacturer;
		this.purpose = purpose;
		this.replacementComments = replacementComments;
		this.weight = weight;
		this.warranty = warranty;
		this.country = country;
		this.typeDac = typeDac;
		this.returnSpring = returnSpring;
		this.voltage = voltage;
		this.effort = effort;
		this.damperArea = damperArea;
		this.controlSignal = controlSignal;
		this.timePosition = timePosition;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.addSwitch = addSwitch;
		this.stockPerimeter = stockPerimeter;
		this.stockDiameter = stockDiameter;
		this.connectionType = connectionType;
		this.ipClass = ipClass;
		this.thermalDevice = thermalDevice;
		this.thermalDeviceTemperature = thermalDeviceTemperature;
		
	}
	
	
	//конструктор для формирования поискового запроса
	public Equipment (String manufacturer, String model, String vc) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.vc = vc;
	}
	
	public Equipment (String manufacturer, String model) {
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	
	
	/**
	public Equipment(String model, String vc, String smnsAn) {
		this.model = model;
		this.smnsAn = smnsAn;
		this.vc = vc;
	}
	*/

	public Equipment(Long eq_id, int id_all, String model, String vc, String smnsAn) {
		this.eq_id = eq_id;
		this.id_all = id_all;
		this.model = model;
		this.vc = vc;
		this.smnsAn = smnsAn;
	}
	
	public Equipment (String manufacturer, String model, String vc, String smnsAn) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.vc = vc;
		this.smnsAn = smnsAn;
	}
	
	public Equipment (int id_all, String manufacturer, String model, String vc, String smnsAn) {
		this.id_all = id_all;
		this.manufacturer = manufacturer;
		this.model = model;
		this.vc = vc;
		this.smnsAn = smnsAn;
	}
	
	
	@Override
	public String toString() {
		return manufacturer + " - " + model + " - " + vc;
	}
	

	public Long getEq_id() {
		return eq_id;
	}

	public int getId_all() {
		return id_all;
	}

	public void setId_all(int id_all) {
		this.id_all = id_all;
	}

	public void setEq_id(Long i) {
		this.eq_id = i;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVc() {
		return vc;
	}

	public void setVc(String vc) {
		this.vc = vc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSmnsAn() {
		return smnsAn;
	}

	public void setSmnsAn(String smnsAn) {
		this.smnsAn = smnsAn;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getReplacementComments() {
		return replacementComments;
	}

	public void setReplacementComments(String replacementComments) {
		this.replacementComments = replacementComments;
	}

	/**
	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}
	*/
	
	public String getEffort() {
		return effort;
	}

	public void setEffort(String effort) {
		this.effort = effort;
	}


	/**
	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	*/
	
	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTypeDac() {
		return typeDac;
	}

	public void setTypeDac(String typeDac) {
		this.typeDac = typeDac;
	}

	public String getReturnSpring() {
		return returnSpring;
	}

	public void setReturnSpring(String returnSpring) {
		this.returnSpring = returnSpring;
	}

	/**
	public double getDamperArea() {
		return damperArea;
	}

	public void setDamperArea(double damperArea) {
		this.damperArea = damperArea;
	}
	*/
	
	public String getDamperArea() {
		return damperArea;
	}

	public void setDamperArea(String damperArea) {
		this.damperArea = damperArea;
	}

	public String getControlSignal() {
		return controlSignal;
	}

	public void setControlSignal(String controlSignal) {
		this.controlSignal = controlSignal;
	}

	/**
	public int getTimePosition() {
		return timePosition;
	}

	public void setTimePosition(int timePosition) {
		this.timePosition = timePosition;
	}

	public int getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(int timeOpen) {
		this.timeOpen = timeOpen;
	}

	public int getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(int timeClose) {
		this.timeClose = timeClose;
	}
	*/
	
	public String getTimePosition() {
		return timePosition;
	}

	public void setTimePosition(String timePosition) {
		this.timePosition = timePosition;
	}

	public String getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(String timeOpen) {
		this.timeOpen = timeOpen;
	}

	public String getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(String timeClose) {
		this.timeClose = timeClose;
	}

	public String getAddSwitch() {
		return addSwitch;
	}

	public void setAddSwitch(String addSwitch) {
		this.addSwitch = addSwitch;
	}

	public String getStockPerimeter() {
		return stockPerimeter;
	}

	public void setStockPerimeter(String stockPerimeter) {
		this.stockPerimeter = stockPerimeter;
	}

	public String getStockDiameter() {
		return stockDiameter;
	}

	public void setStockDiameter(String stockDiameter) {
		this.stockDiameter = stockDiameter;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getIpClass() {
		return ipClass;
	}

	public void setIpClass(String ipClass) {
		this.ipClass = ipClass;
	}

	public String getThermalDevice() {
		return thermalDevice;
	}

	public void setThermalDevice(String thermalDevice) {
		this.thermalDevice = thermalDevice;
	}

	public String getThermalDeviceTemperature() {
		return thermalDeviceTemperature;
	}

	public void setThermalDeviceTemperature(String thermalDeviceTemperature) {
		this.thermalDeviceTemperature = thermalDeviceTemperature;
	}

	public String getDamper_area() {
		return damper_area;
	}

	public void setDamper_area(String damper_area) {
		this.damper_area = damper_area;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
}
