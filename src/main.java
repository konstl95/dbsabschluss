import java.sql.DriverManager;
import java.sql.*;
import oracle.jdbc.driver.*;
import java.util.Random;
import java.util.Vector;

public class main {
// db connection 
	private final static String DB_CONNECTION_URL = "";
	private final static String USER = "01568073";
	private final static String PASS = "";
	
	
//class loaded from the ojdbc14    noch zu 8er version aus cewebs ändern 
	private final static String CLASSNAME = "oracle.jdbc.driver.OracleDriver";
	

public static void main (String args[]) {
	Statement stat;
	Connection con; 
	try {
		Class.forName(CLASSNAME);
		con = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASS);
		
		
		
	}
	catch{
		return;
	}
	
	
	
	//make classes databasehelper   data generator und random helper for example 
	
	/*data generator
	 * 
	 * neues random helper element erstellen 
	 * */
	//random helper 
	/*
	 * zb arrayliste mit namen 
	 * sollte funktionen haben wie 
	 * get random int/string etc also alle methoden die ich in 
	 * data generator verwenden will 
	 * 
	 * es gibt auch java klassen die fake daten zur verfügung stellen 
	 * aber muss gekennzeichnet werden obvs 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	/*Database helper
	 * connection zu db herstellen und alle db funktionen 
	 * hier kapseln 
	 * 
	 * 
	 * */
	
	
}	// end void main 
	
	
}


