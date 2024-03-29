package Utils;

import Package.Allergy;
import Package.Author;
import Package.Guardian;
import Package.Patient;
import Package.Plan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

public class HelperFunctions {

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public String getStringHelper(String attribute, ResultSet rs)
	{
		try{
			if(rs.findColumn(attribute) > -1)
				return rs.getString(attribute);
			else
				return null;
		}
		catch(Exception e)
		{
			return null;
		}	
	}
	
	public Date getDateHelper(String attribute, ResultSet rs)
	{
		try{
			if(rs.findColumn(attribute) > -1)
				return rs.getDate(attribute);
			else
				return null;
		}
		catch(Exception e)
		{
			return null;
		}	
	}

	public Patient getPatient(Connection conn, String pid) throws SQLException {	
		Statement stmt = null;
	    try {	
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE patientId = " + pid); // This will throw a SQLException if it fails
	        Patient p = null;
	        while(rs.next())
	        	p = new Patient(pid,getStringHelper("PatientRole",rs),getStringHelper("GivenName",rs),getStringHelper("FamilyName",rs),getStringHelper("Suffix",rs),getStringHelper("Gender",rs), getStringHelper("Birthtime",rs),getStringHelper("ProviderId",rs),getStringHelper("XMLHealth",rs));
	      
	        return p;
	    } catch (Exception e){
	    	System.out.println(e);
	    	
	    	return null;
	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}

	public Guardian getGuardian(Connection conn, String role) throws SQLException {	
		Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Guardian G WHERE G.GuardianNo = " + role); // This will throw a SQLException if it fails
	        Guardian g = null;
	        while(rs.next())
	        	g = new Guardian(role, getStringHelper("GivenName",rs), getStringHelper("FamilyName",rs), getStringHelper("Phone",rs), getStringHelper("Address",rs), getStringHelper("City",rs), getStringHelper("State",rs), getStringHelper("Zip",rs)); 
	      
	        return g;
	    } catch (Exception e){
	    	System.out.println(e);
	    	
	    	return null;
	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public Boolean updateAllergy(Connection conn, String pid, String reactiontype, String aid, String substance, String status) throws SQLException {	
		java.sql.PreparedStatement stmt = null;
	    try {
	        stmt = conn.prepareStatement("UPDATE Allergy A SET A.ReactionType=?, A.PatientId=?, A.Substance=? A.Status = ? WHERE A.AllerginID= ?"); // This will throw a SQLException if it fails
	        stmt.setString(1,reactiontype);
	        stmt.setString(2,pid);
	        stmt.setString(3,substance);
	        stmt.setString(3,status);
	        stmt.setString(4,aid);
	        int rs = stmt.executeUpdate(); // This will throw a SQLException if it fails
	        
	        conn.close();
	        return true;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return false;
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public Boolean updatePlan(Connection conn, String patientId, String procdate, String desc, String planId) throws SQLException {	
		java.sql.PreparedStatement stmt = null;
	    try {
	        stmt = conn.prepareStatement("UPDATE Plan P SET P.PatientId = ?, P.ProcDate=?, P.Description=? WHERE P.PlanId = ?"); // This will throw a SQLException if it fails
	        stmt.setString(1,patientId);
	        stmt.setString(2,procdate);
	        stmt.setString(3,desc);
	        stmt.setString(4,planId);
	        int rs = stmt.executeUpdate(); // This will throw a SQLException if it fails
	       
	        conn.close();
	        return true;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return false;
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public Boolean updatePatient(Connection conn, String pid, String role, String name, String famname, String suffix, String gender, String birthtime, String provId, String xmlHealth) throws SQLException {	
		java.sql.PreparedStatement stmt = null;
	    try {
	        stmt =  conn.prepareStatement("UPDATE Patient P SET " + 
			        "P.PatientRole= ?, P.GivenName= ?, P.FamilyName= ?, P.Suffix= ?, P.Gender= ?, P.Birthtime = ?, P.ProviderID = ? WHERE P.PatientId = ?" );
	        stmt.setString(1, role);
	        stmt.setString(2, name);
	        stmt.setString(3, famname);
	        stmt.setString(4, suffix);
	        stmt.setString(5, gender);
	        stmt.setString(6, birthtime);
	        stmt.setString(7, provId);
	        stmt.setString(8, pid);
	        int rs = stmt.executeUpdate(); // This will throw a SQLException if it fails
	        
	        conn.close();
	        return true;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return false;
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public Boolean updateGuardian(Connection conn, String gnum, String name, String famname, String phone, String address, String city, String state, String zip) throws SQLException {	
		java.sql.PreparedStatement stmt = null;
	    try {
	        stmt =  conn.prepareStatement("UPDATE Guardian G SET G.GivenName= ?, G.FamilyName= ?, G.Phone= ?, G.Address= ?, G.City = ?, G.State = ?, G.Zip = ? WHERE G.GuardianNo = ?" );
	        stmt.setString(1, name);
	        stmt.setString(2, famname);
	        stmt.setString(3, phone);
	        stmt.setString(4, address);
	        stmt.setString(5, city);
	        stmt.setString(6, state);
	        stmt.setString(7, zip);
	        stmt.setString(8, gnum);
	        int rs = stmt.executeUpdate(); // This will throw a SQLException if it fails
	        
	        conn.close();
	        return true;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return false;
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public List<Plan> getPlan(Connection conn, String pid) throws SQLException {
		Statement stmt = null;
	    try {
	    	List<Plan> l = new ArrayList<Plan>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Plan P WHERE P.PatientId=" + pid + ";"); // This will throw a SQLException if it fails
	        while(rs.next()){
	        	Plan p = new Plan(pid, getStringHelper("PlanId",rs), getStringHelper("Description",rs), getStringHelper("ProcDate",rs));
	        	l.add(p);
	        }
	        return l;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return null;
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }		
	}
	
	public List<Allergy> getAllergy(Connection conn, String pid) throws SQLException {
		Statement stmt = null;
	    try {
	    	List<Allergy> l = new ArrayList<Allergy>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Allergin A WHERE A.AllerginId=" + pid + ";"); // This will throw a SQLException if it fails
	        while(rs.next()){	
	        	Allergy a = new Allergy(pid, getStringHelper("AllerginId", rs), getStringHelper("Substance",rs), getStringHelper("ReactionType",rs), getStringHelper("Status", rs));
	        	l.add(a);
	        }
	        return l;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return null;	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }		
	}

	public HashMap<String, Integer> numPatientsWithAllergy(Connection conn) throws SQLException {
		Statement stmt = null;
	    HashMap<String, Integer> list = new HashMap<String, Integer>();
		try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT A.Substance, COUNT(*) as Num FROM Allergin A Group By A.Substance");
	        while(rs.next())
	        {
	        	list.put(rs.getString("Substance"),rs.getInt("Num"));
	        }
	        return list;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return null;	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }		
	}
	
	public List<Patient> patientsWithMoreThan1Allergy(Connection conn) throws SQLException {
		Statement stmt = null;
	    try {
	    	List<Patient> l = new ArrayList<Patient>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM patient P WHERE P.PatientId IN (SELECT A.PatientId FROM allergin A GROUP BY PatientId HAVING COUNT(*) > 1)"); // This will throw a SQLException if it fails
	        while(rs.next()){	
	        	Patient p = new Patient(getStringHelper("PatientId", rs), getStringHelper("PatientRole", rs), getStringHelper("GivenName",rs), getStringHelper("FamilyName", rs), getStringHelper("Suffix", rs), getStringHelper("Gender", rs), getStringHelper("Birthtime", rs), getStringHelper("ProviderId", rs), getStringHelper("XMLHealth", rs));
	        	l.add(p);
	        }
	        return l;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return null;	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }		
	}
	
	public List<Patient> patientsSurgeryToday(Connection conn) throws SQLException {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
	    String today = sdf.format(cal.getTime());
	    System.out.println("today's date: " +today);
		Statement stmt = null;
	    try {
	    	List<Patient> l = new ArrayList<Patient>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Patient P WHERE P.PatientId IN (SELECT Pl.PatientId FROM Plan Pl WHERE Pl.ProcDate LIKE '" + today+"%')"); // This will throw a SQLException if it fails
	        while(rs.next()){	
	        	Patient p = new Patient(getStringHelper("PatientId", rs), getStringHelper("PatientRole", rs), getStringHelper("GivenName",rs), getStringHelper("FamilyName", rs), getStringHelper("Suffix", rs), getStringHelper("Gender", rs), getStringHelper("Birthtime", rs), getStringHelper("ProviderId", rs), getStringHelper("XMLHealth", rs));
	        	l.add(p);
	        }
	        return l;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return null;	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }		
	}
	
	public List<Author> authorsWithMoreThan1Patient(Connection conn) throws SQLException {
		Statement stmt = null;
	    try {
	    	List<Author> l = new ArrayList<Author>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("Select * From Author A Group By A.PatientId Having count(*) > 1"); // This will throw a SQLException if it fails
	        while(rs.next()){	
	        	Author a = new Author(getStringHelper("AuthorId", rs), getStringHelper("PatientId", rs), getStringHelper("AuthorTitle",rs), getStringHelper("AuthorFirstName", rs), getStringHelper("AuthorLastName", rs), getStringHelper("ParticipatingRole", rs));
	        	l.add(a);
	        }
	        return l;
	    } catch (Exception e){
	    	System.out.println(e);
	    	return null;	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }		
	}
}
