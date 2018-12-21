import java.sql.*;
import oracle.jdbc.driver.*;
import java.util.Random;
import java.util.Vector;

/*
 * insertFilm
 * insertMitwirkinder
 * instertIstschauspieler
 * insertIstRegisseur
 * insertSerie
 * insertFolge
 * insertSpeicher
 * 
 * insertFilmspeicher
 * insertFolgenspeicher
 * 
 * insertFilmdirected
 * insertFolgedirected
 * 
 * insertFilmstarring
 * insertFolgestarring
 * 
 * 
 * 
 *  Vector ist bereits string   alle vector<integer ändern>
 * 
 * */
//Fragen  wie ist DATE Format wirklich
//wie nimmt man random wörter aus text datei
//deruwe.de/dateien/2009/11/nachnamen.txt
// wie geht das mit den vektoren, werden die ausserhalb der inserts auch kleiner?

//https://stackoverflow.com/questions/12028205/randomly-choose-a-word-from-a-text-file
public class dbsclass {
	
	public static class Insert {
		//Entitäten    episoden fehlt noch
		public static void film(Statement stmt, int num,Vector<String> vk) throws Exception {
			
			Random rnd = new Random();
			
			String filmID;
			int laenge;
			String release;
			String name;
			for(int i = 0; i < num; i++) {
			//name erstellen   mit http://www.baeldung.com/java-random-string
				int leftLimit = 97; // letter 'a'
			    int rightLimit = 122; // letter 'z'
			    int targetStringLength = 10 + rnd.nextInt(20); //name max 30 charecter 
			    Random random = new Random();
			    StringBuilder buffer = new StringBuilder(targetStringLength);
			    for (int a = 0; a < targetStringLength; a++) {
			        int randomLimitedInt = leftLimit + (int) 
			          (random.nextFloat() * (rightLimit - leftLimit + 1));
			        buffer.append((char) randomLimitedInt);
			    }
			    name = buffer.toString();
				
				
			//filmID festlegen
			//filmID = "tt" + Integer.toString(vk.remove(vk.size()-1));
			filmID = "tt" + vk.get(i);	
			//länge bestimmen
			int maxlaenge=900;//longest film Resan 873min
			laenge = 1 + rnd.nextInt(maxlaenge);
			String insertlaenge = Integer.toString(laenge);
			
			//release festsetzen 
			//Format DD-MM-YYYY
			int jahr=2018;
			jahr = jahr - rnd.nextInt(120);//2018-1895=120 
			
			int maxtage;
			int tag = 1;
			String monat;
			switch(rnd.nextInt(12)) {
			case 0:
				monat = "01";
				maxtage = 31;
				break;
			case 1:
				monat = "02";
				maxtage = 28;
				break;
			case 2:
				monat = "03";
				maxtage = 31;
				break;
			case 3:
				monat = "04";
				maxtage = 30;
				break;
			case 4:
				monat = "05";
				maxtage = 31;
				break;
			case 5:
				monat = "06";
				maxtage = 30;
				break;
			case 6:
				monat = "07";
				maxtage = 31;
				break;
			case 7:
				monat = "08";
				maxtage = 31;
				break;
			case 8:
				monat = "09";
				maxtage = 30;
				break;
			case 9:
				monat = "10";
				maxtage = 31;
				break;
			case 10:
				monat = "11";
				maxtage = 30;
				break;
			case 11:
				monat = "12";
				maxtage = 31;
				break;
			default:
				monat = "01";
				maxtage = 31;
				break;
			}//end switch
			
			//Tag bestimmen
			tag = 1 + rnd.nextInt(maxtage);
			
			//to string und merge DD-MM-YYYY
			String taginsert = Integer.toString(tag);
			String jahrinsert = Integer.toString(jahr);
			release = taginsert + "-" + monat + "-" + jahrinsert;
			
			String insert = "insert into film (filmID, laenge, name, release) values ('" + filmID + "', " + insertlaenge + ", '"+name + "', to_date('" + release + "', 'DD-MM-YYYY'))";
		
			 try{
					stmt.executeUpdate(insert);
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
			}//end for
			
			
			
			   
			    
			}//end film
			
		public static void serien(Statement stmt, int num,Vector<String> vk) throws Exception {
			
			Random rnd = new Random();
			
			String serienID;
			String name;
			for(int i = 0; i < num; i++) {
			//name erstellen   mit http://www.baeldung.com/java-random-string
				int leftLimit = 97; // letter 'a'
			    int rightLimit = 122; // letter 'z'
			    int targetStringLength = 10 + rnd.nextInt(15); //name max 30 charecter 
			    Random random = new Random();
			    StringBuilder buffer = new StringBuilder(targetStringLength);
			    for (int a = 0; a < targetStringLength; a++) {
			        int randomLimitedInt = leftLimit + (int) 
			          (random.nextFloat() * (rightLimit - leftLimit + 1));
			        buffer.append((char) randomLimitedInt);
			    }
			    name = buffer.toString();
				
				
			//serienID festlegen
			serienID = "tt" + vk.get(i);
				
			String insert = "insert into serien (name, serienID) values ('" + name + "', '" + serienID + "')";
			
		
			 try{
					//	stmt.executeUpdate(insert);
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
			}//end for
			
			
			
			   
			    
			}//end serie
		
		public static void speicher(Statement stmt, int num,Vector<String> vk) throws Exception {
			
			Random rnd = new Random();
			
			String sprache;
			String pfad;
			String qualitaet;
			for(int i = 0; i < num; i++) {
				
			//pfad festlegen
			
			pfad = "tt" + vk.get(i);
				
			//Sprache festlegen
			switch(rnd.nextInt(2)) {
			case 0: sprache = "Deutsch";
					break;
			case 1: sprache = "Englisch";
					break;
			default:sprache = "Deutsch";
			}
			
			//Quali festlegen
			switch(rnd.nextInt(4)) {
			case 0: qualitaet = "SD";
					break;
			case 1: qualitaet = "HD";
					break;
			case 2: qualitaet = "Full-HD";
					break;
			case 3: qualitaet = "4K";
					break;
			default:qualitaet = "SD";
			}
			
			
			String insert = "insert into speicher (sprache, pfad, qualitaet) values ('" + sprache + "', '" + pfad + "', '"+ qualitaet + "')";
		
			 try{
					stmt.executeUpdate(insert);
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
			}//end for
			
			
			
			   
			    
			}//end pfad
		
		public static void mitwirkende(Statement stmt, int num,Vector<String> vk) throws Exception {
			
			Random rnd = new Random();
			
			String IMDBID;
			String vorname;
			String nachname;
			String herkunft;
			for(int i = 0; i < num; i++) {
			//name erstellen   mit http://www.baeldung.com/java-random-string
				int leftLimit = 97; // letter 'a'
			    int rightLimit = 122; // letter 'z'
			    int targetStringLength = 10 + rnd.nextInt(20); //vorname max 30 charecter 
			    Random random = new Random();
			    StringBuilder buffer = new StringBuilder(targetStringLength);
			    for (int a = 0; a < targetStringLength; a++) {
			        int randomLimitedInt = leftLimit + (int) 
			          (random.nextFloat() * (rightLimit - leftLimit + 1));
			        buffer.append((char) randomLimitedInt);
			    }
			    vorname = buffer.toString();
			    
			    int leftLimit2 = 97; // letter 'a'
			    int rightLimit2 = 122; // letter 'z'
			    int targetStringLength2 = 10 + rnd.nextInt(20); //nachname max 30 charecter 
			    Random random2 = new Random();
			    StringBuilder buffer2 = new StringBuilder(targetStringLength2);
			    for (int a = 0; a < targetStringLength2; a++) {
			        int randomLimitedInt = leftLimit2 + (int) 
			          (random2.nextFloat() * (rightLimit2 - leftLimit2 + 1));
			        buffer2.append((char) randomLimitedInt);
			    }
			    nachname = buffer2.toString();
				
			    int leftLimit3 = 97; // letter 'a'
			    int rightLimit3 = 122; // letter 'z'
			    int targetStringLength3 = 10 + rnd.nextInt(20); //herkunft max 30 charecter 
			    Random random3 = new Random();
			    StringBuilder buffer3 = new StringBuilder(targetStringLength3);
			    for (int a = 0; a < targetStringLength3; a++) {
			        int randomLimitedInt = leftLimit3 + (int) 
			          (random3.nextFloat() * (rightLimit3 - leftLimit3 + 1));
			        buffer3.append((char) randomLimitedInt);
			    }
			    herkunft = buffer3.toString();
			    
			//filmID festlegen
			IMDBID = "nn" + vk.get(i);
			
			
			
			
			String insert = "insert into mitwirkende (IMDBID, vorname, nachname, herkunft) values ('" + IMDBID + "', '" + vorname + "', '"+nachname + "', '" + herkunft + "')";
		
			 try{
					stmt.executeUpdate(insert);
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
			}//end for
			
			
			
			   
			    
			}//end mitwirkende
		
		public static void schauspieler(Statement stmt, int num,Vector<String> vk) throws Exception {
			
			Random rnd = new Random();
			
			String IMDBID;
			String geburtsdatum;
			for(int i = 0; i < num; i++) {
			//geb festsetzen 
			//Format DD-MM-YYYY
			int jahr=2017;
			jahr = jahr - rnd.nextInt(177);//2017-1840=177  geb von schauspieler in ersten film 
			
			int maxtage;
			int tag = 1;
			String monat;
			switch(rnd.nextInt(12)) {
			case 0:
				monat = "01";
				maxtage = 31;
				break;
			case 1:
				monat = "02";
				maxtage = 28;
				break;
			case 2:
				monat = "03";
				maxtage = 31;
				break;
			case 3:
				monat = "04";
				maxtage = 30;
				break;
			case 4:
				monat = "05";
				maxtage = 31;
				break;
			case 5:
				monat = "06";
				maxtage = 30;
				break;
			case 6:
				monat = "07";
				maxtage = 31;
				break;
			case 7:
				monat = "08";
				maxtage = 31;
				break;
			case 8:
				monat = "09";
				maxtage = 30;
				break;
			case 9:
				monat = "10";
				maxtage = 31;
				break;
			case 10:
				monat = "11";
				maxtage = 30;
				break;
			case 11:
				monat = "12";
				maxtage = 31;
				break;
			default:
				monat = "01";
				maxtage = 31;
				break;
			}//end switch
			
			//Tag bestimmen
			tag = 1 + rnd.nextInt(maxtage);
			
			
			//to string und merge DD-MM-YYYY
			String taginsert = Integer.toString(tag);
			String jahrinsert = Integer.toString(jahr);
			geburtsdatum = taginsert + "-" + monat + "-" + jahrinsert;
			    
			//IMDBID festlegen
			IMDBID = "nn" + vk.get(i);
				
			
			
			
			
			String insert = "insert into film (IMDBID, geburtsdatum) values ('" + IMDBID + "', to_date('" + geburtsdatum + "', 'DD-MM-YYYY'))";
		
			 try{
					stmt.executeUpdate(insert);
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
			}//end for
			
			
			
			   
			    
			}//end schauspieler
	
		public static void regisseur(Statement stmt, int num,Vector<String> vk) throws Exception {
			
			Random rnd = new Random();
			
			String IMDBID;
			for(int i = 0; i < num; i++) {			    
			//IMDBID festlegen
			IMDBID = "nn" + vk.get(i);
			
				
			
			
			
			
			String insert = "insert into film (IMDBID) values ('" + IMDBID + "')";
		
			 try{
					stmt.executeUpdate(insert);
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
			}//end for
			
			
			
			   
			    
			}//end regisseur
		
		//zuweisungen
			public static void filmstaring(Statement stmt, int num,Vector<String> vkfilm,Vector<String> vkschau) throws Exception {
			
			Random rnd = new Random();
			
			String IMDBID;
			String filmID;
			for(int i = 0; i < num; i++) {
			
				filmID = vkfilm.get(i);
				int cast = 5 + rnd.nextInt(10);
				for(int b=0; b < cast; b++) {
					
					IMDBID = vkschau.get(rnd.nextInt(vkschau.size()));
					String insert = "insert into filmstaring (filmID, IMDBID) values ('" + filmID + "', '" + IMDBID + "')";
		
					try{
						stmt.executeUpdate(insert);
						}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
				}//end 2nd for
			}//end for
			
			
			
			   
			    
			}//end filmstaring
		}
	
	
	
	public static String randomstring(int anzahl) {
		String inhalt="";
		Random rn = new Random();
		for(int i=0 ; i<anzahl ; i++) {
			inhalt +=Integer.toString(rn.nextInt(10));	
		}
		return inhalt;
		
	}
	
	public static void main(String[] args) {
		try {
			
			
			int maxfilm=2000;
			int maxshows=200;
			int maxep = maxshows * 300;
			
			int maxmitwirkende = 8000;
			int maxschauspieler = (maxmitwirkende * 2) / 3;
			int maxregisseur = maxmitwirkende - maxschauspieler;
			int maxpfade = maxfilm + maxep;
			
			//file kuerzel generator
			/*
			Vector<String> vk 		= 		new Vector<>(maxfilm + maxep + maxshows);
			Vector<String> vkep 	= 		new Vector<>(maxep);
			Vector<String> vkshow	= 		new Vector<>(maxshow);
			
			for(int i=0;i < maxfilm + maxep + maxshows;i++)
			{
				String cache = randomstring(7);
				while(vk.contains(cache)) 
				{
					cache = randomstring(7);
				}
				if(i<maxfilm) {
					vk.add(cache);
				}
				if(i<maxfilm+maxep) {
					vkep.add(cache);
				}
				
				else {
					vkshow.add(cache);
				}
				
			}*/
			//vector filme
			Vector<String> vkfilm = new Vector<>(maxfilm);
			for(int i=0;i < maxfilm;i++)
			{
				String cache = randomstring(7);
				while(vkfilm.contains(cache)) 
				{
					cache = randomstring(7);
				}
				vkfilm.add(cache);
			}
			//vector folgen
			Vector<String> vkfolgen = new Vector<>(maxep);
			for(int i=0;i < maxep;i++)
			{
				String cache = randomstring(7);
				while(vkfolgen.contains(cache)&&vkfilm.contains(cache)) 
				{
					cache = randomstring(7);
				}
				
				vkfolgen.add(cache);
				
			}
			
			Vector<String> vkserien = new Vector<>(maxshows);	
			for(int i=0;i < maxshows;i++)
			{
				String cache = randomstring(7);
				while(vkfolgen.contains(cache) && vkfilm.contains(cache) && vkserien.contains(cache)) 
				{
					cache = randomstring(7);
				}	
				vkfolgen.add(cache);		
			}
			
			//mitwirk kuerzel generator
			Vector<String> vkmit 	= new Vector<>(maxmitwirkende);
			Vector<String> vkschau 	= new Vector<>(maxschauspieler);
			Vector<String> vkreg 	= new Vector<>(maxregisseur);
			for(int i=0;i < maxmitwirkende;i++)
			{
				String cache = randomstring(7);
				while(vkmit.contains(cache)) 
				{
					cache = randomstring(7);
				}
				
				vkmit.add(cache);
				if(i<=maxschauspieler) {
					vkschau.add(cache);
				}
				else {
					vkreg.add(cache);
				}
				
			}
			
			
			
			//pfade generieren
			Vector<String> vkpfad = new Vector<>(maxmitwirkende);
			
			for(int i=0;i < maxmitwirkende;i++)
			{
				Random rnd = new Random();
				String cache = randomstring(1 + rnd.nextInt(119));
				while(vkpfad.contains(cache)) 
				{
					cache = randomstring(7);
				}
				
				vkpfad.add(cache);
				
			}
			
			
			
			//Verbindung aufbauen
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String database = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
			String user = "a01568073";
			String pass = "Ihkk,dimp15";
			Connection con = DriverManager.getConnection(database, user, pass);
			Statement stmt = con.createStatement();
			
			
		
			
			//Verbindungen und Datenströme schließen
			//rs.close();
			stmt.close();
			con.close();
			
			
		    } catch (Exception e) {
		      System.err.println(e.getMessage());
		    }
	}

}

/*Probleme:
 *
 * 
 * 
 * */
