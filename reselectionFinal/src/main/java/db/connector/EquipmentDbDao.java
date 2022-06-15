package db.connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import db.equipment.Equipment;

import db.connectionPool.ConnectionBuilder;
import db.connectionPool.ConnectionBuilderFactory;
import db.exceptions.*;

// класс для отправки sql-запросов в бд

public class EquipmentDbDao implements EquipmentDao {
	
	//private static final String SELECT
    //= "SELECT id_all, mnfctrr, model_all, vc_all FROM allDac ORDER BY id_all";
	
	private static final String SELECT
    = "SELECT id_all, mnfctrr, model_all, vc_all, sd.model_smns FROM allDac AS ad LEFT JOIN siemensDac AS sd ON ad.anlg_smns = sd.id_smns ORDER BY id_all";
	
	private static final String SELECT_ONE
    = "SELECT mnfctrr, model_all, vc_all, sd.model_smns FROM allDac ad LEFT JOIN siemensDac sd ON ad.anlg_smns = sd.model_smns WHERE id_all =?";
	
	private static final String INSERT
    = "INSERT INTO allDac (id_all, mnfctrr, model_all, vc_all, voltage) VALUES (?, ?, ?, ?, ?)";
	
	private static final String UPDATE
    = "UPDATE allDac SET mnfctrr=?, model_all=?, vc_all=?, sd.model_smns =? WHERE id_all=?";
	
	private static final String DELETE
    = "DELETE FROM allDac WHERE id_all=?";
	
	//private static final String SEARCH
	//= "SELECT * FROM allDac AS ad LEFT JOIN siemensDac AS sd ON sd.id_smns = ad.anlg_smns WHERE mnfctrr = ? AND (model_all = ? OR vc_all = ?)";
	
	/**
	private static final String SEARCH
	= "SELECT * FROM allDac AS ad LEFT JOIN SiemensDacNew AS sd ON sd.id_smns = ad.anlg_smns WHERE mnfctrr = ? AND model_all = ? AND vc_all = ?";
	
	private static final String SEARCH_NO_FIRM
	= "SELECT * FROM allDac AS ad LEFT JOIN SiemensDacNew AS sd ON sd.id_smns = ad.anlg_smns WHERE model_all =? AND vc_all = ?";
	
	private static final String DROPLIST
	= "SELECT DISTINCT mnfctrr, model_all, vc_all FROM allDac";
	
	private static final String DROPLIST_FIRM_FILTER
	= "SELECT DISTINCT mnfctrr, model_all, vc_all FROM allDac WHERE mnfctrr = ?";
	
	*/
	
	private static final String SEARCH
	= "SELECT * FROM AllDacNew AS ad LEFT JOIN SiemensDacNew AS sd ON sd.id_smns = ad.siemensAnalogueNumber WHERE manufacturerSearch = ? AND modelAll = ? AND vcAll = ?";
	
	private static final String SEARCH_NO_FIRM
	= "SELECT * FROM AllDacNew AS ad LEFT JOIN SiemensDacNew AS sd ON sd.id_smns = ad.siemensAnalogueNumber WHERE modelAll = ? AND vcAll = ?";
	
	private static final String DROPLIST
	= "SELECT DISTINCT manufacturerAll, modelAll, vcAll FROM AllDacNew";
	
	private static final String DROPLIST_FIRM_FILTER
	= "SELECT DISTINCT manufacturerAll, modelAll, vcAll FROM AllDacNew WHERE manufacturerSearch = ?";
	
	
	private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();
	
	private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
	
	@Override
    public Long addEquipment(Equipment equipment) throws EquipmentDaoException {
        try (Connection con = getConnection();
        		PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id_all"})) {
            Long eq_id = -1L;
            pst.setInt(1, equipment.getId_all());
            pst.setString(2, equipment.getManufacturer());
            pst.setString(3, equipment.getModel());
            pst.setString(4, equipment.getVc());
            pst.setString(5, equipment.getSmnsAn());
            pst.executeUpdate();
            //ResultSet gk = pst.getGeneratedKeys();
            //if (gk.next()) {
            //    eq_id = gk.getLong("id_all");
            //}
            //gk.close();
            return eq_id;
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }
    }
	
	@Override
    public void updateEquipment(Equipment equipment) throws EquipmentDaoException {
        try (Connection con = getConnection();
                PreparedStatement pst = con.prepareStatement(UPDATE)) {
        	pst.setString(1, equipment.getManufacturer());
        	pst.setString(2, equipment.getModel());
            pst.setString(3, equipment.getVc());
            pst.setString(4, equipment.getSmnsAn());
            pst.setLong(5, equipment.getEq_id());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }
    }
	
	@Override
    public void deleteEquipment(Long eq_id) throws EquipmentDaoException {
        try (Connection con = getConnection();
                PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, eq_id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }
    }
	
	@Override
    public Equipment getEquipment(Long eq_id) throws EquipmentDaoException {
        Equipment equipment = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ONE);
            pst.setLong(1, eq_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                equipment = fillEquipment(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }
        return equipment;
    }
	
	@Override
    public List<Equipment> findEquipmentz() throws EquipmentDaoException {
        List<Equipment> list = new LinkedList<>();
        try (Connection con = getConnection();
                PreparedStatement pst = con.prepareStatement(SELECT);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillEquipment(rs));
            }
            rs.close();
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }
        return list;
    }
	
	// поиск с выбранным производителем
	@Override
    public Map<String, ArrayList<Equipment>> searchEquipment(Equipment equipment) throws EquipmentDaoException {
		
		Map<String, ArrayList<Equipment>> foundEquipment = new HashMap();
		
		ArrayList<Equipment> foundDac = new ArrayList();
		ArrayList<Equipment> foundSiemens = new ArrayList();
		
		
		//List<Equipment> list = new LinkedList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SEARCH);
            pst.setString(1, equipment.getManufacturer().toString());
        	pst.setString(2, equipment.getModel().toString());
            pst.setString(3, equipment.getVc().toString());
            ResultSet rs = pst.executeQuery(); 
  			
            while (rs.next()){
            	
            	foundDac.add(createFoundEquipment(rs));
            	foundSiemens.add(createSiemensEquipment(rs));
            	
            }
            
            foundEquipment.put("foundDac", foundDac);
            foundEquipment.put("foundSiemens", foundSiemens);
            
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }
        
        return foundEquipment;
    }
	
	// поиск без выбранного производителя
	@Override
    public Map<String, ArrayList<Equipment>> searchEquipmentNoFirm(Equipment equipment) throws EquipmentDaoException {
		
		Map<String, ArrayList<Equipment>> foundEquipment = new HashMap();
		
		ArrayList<Equipment> foundDac = new ArrayList();
		ArrayList<Equipment> foundSiemens = new ArrayList();
		
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SEARCH_NO_FIRM);
            pst.setString(1, equipment.getModel().toString());
            pst.setString(2, equipment.getVc().toString());
            ResultSet rs = pst.executeQuery(); 
  			
            while (rs.next()){
            	
            	foundDac.add(createFoundEquipment(rs));
            	foundSiemens.add(createSiemensEquipment(rs));
            	
            }
            
            foundEquipment.put("foundDac", foundDac);
            foundEquipment.put("foundSiemens", foundSiemens);
            
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }
        
        return foundEquipment;
    }
	
	//заполнение выпадающего листа с артикулами/моделями
	@Override
    public List<Equipment> searchEquipmentForComboBox() throws EquipmentDaoException {
		
		List<Equipment> foundForComboBox = new ArrayList();
		
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(DROPLIST);
            ResultSet rs = pst.executeQuery(); 
            
            while (rs.next()) {
            	
            	Equipment eq = fillSearchBox(rs);
            	
            	if(eq != null) {
            		
            	foundForComboBox.add(eq);
            	}
            }
            
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }

		return foundForComboBox;
	
    }
	
	//заполнение выпадающего листа с артикулами/моделями при включенном фильтре по производителю
	@Override
    public List<Equipment> searchEquipmentForComboBoxWithFirm(String manufacturer) throws EquipmentDaoException {
		
		List<Equipment> foundForComboBox = new ArrayList();
		
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(DROPLIST_FIRM_FILTER);
            pst.setString(1, manufacturer);
            ResultSet rs = pst.executeQuery(); 
            
            while (rs.next()) {
            	
            	Equipment eq = fillSearchBox(rs);
            	
            	if(eq != null) {
            		
            	foundForComboBox.add(eq);
            	}
            }
            
            System.out.println("Наш лист после фильтра!" + foundForComboBox);
            
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new EquipmentDaoException(e);
        }

		return foundForComboBox;
	
    }
	
	//заполнение таблички с данными из бд
	private Equipment fillEquipment(ResultSet rs) throws SQLException {
        Equipment equipment = new Equipment();
        equipment.setId_all(rs.getInt("id_all"));
        equipment.setManufacturer(rs.getString("mnfctrr"));
        equipment.setModel(rs.getString("model_all"));
        equipment.setVc(rs.getString("vc_all"));
        equipment.setSmnsAn(rs.getString("model_smns"));
        return equipment;
    }
	
	//модель, которой заполняется выпадающий список
	private Equipment fillSearchBox(ResultSet rs) throws SQLException {
		
		 return new Equipment (rs.getString("manufacturerAll"), 
					rs.getString("modelAll"), 
					rs.getString("vcAll"));
		}
	
	//модель для создания искомого устройства
	private Equipment createFoundEquipment(ResultSet rs) throws SQLException {
		return new Equipment (rs.getString("modelAll"), 
				rs.getString("vcAll"), 
				rs.getString("priceAll"), 
				rs.getString("manufacturerAll"),
				rs.getString("purposeAll"),
				rs.getString("idAll"),
				rs.getString("weightAll"),
				rs.getString("warrantyAll"), 
				rs.getString("countryAll"), 
				rs.getString("typeDacAll"),
				rs.getString("returnSpringAll"), 
				rs.getString("voltageAll"), 
				rs.getString("effortAll"), 
				rs.getString("damperAreaAll"),
				rs.getString("controlSignalAll"), 
				rs.getString("timePositionAll"), 
				rs.getString("timeOpenAll"), 
				rs.getString("timeCloseAll"),
				rs.getString("addSwitchAll"), 
				rs.getString("stockPerimeterAll"), 
				rs.getString("stockDiameterAll"), 
				rs.getString("connectionTypeAll"),
				rs.getString("ipClassAll"),
				rs.getString("thermalDeviceAll"),
				rs.getString("thermalDeviceTemperatureAll"));
	}
	
	//модель для создания аналогов от Сименса
	private Equipment createSiemensEquipment(ResultSet rs) throws SQLException {
		return new Equipment (rs.getString("model"), 
					rs.getString("vc"), 
					rs.getString("priceSiemens"), 
					rs.getString("manufacturer"),
					rs.getString("purpose"),
					rs.getString("replacementComments"),
					rs.getString("weight"),
					rs.getString("warranty"), 
					rs.getString("country"), 
					rs.getString("typeDac"),
					rs.getString("returnSpring"), 
					rs.getString("voltage"), 
					rs.getString("effort"), 
					rs.getString("damperArea"),
					rs.getString("controlSignal"), 
					rs.getString("timePosition"), 
					rs.getString("timeOpen"), 
					rs.getString("timeClose"),
					rs.getString("addSwitch"), 
					rs.getString("stockPerimeter"), 
					rs.getString("stockDiameter"), 
					rs.getString("connectionType"),
					rs.getString("ipClass"),
					rs.getString("thermalDevice"),
					rs.getString("thermalDeviceTemperature"));
	}

}
