package Package;
import Utils.HelperFunctions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.lang.reflect.*;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isnt
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class DBDemo {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "CS174a";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "test";
	
	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST";
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	

	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		//"DROP TABLE IF EXISTS messages;" + 
		// Create a table
		//made changes
		try {
			HelperFunctions HF = new HelperFunctions();
			HF.executeUpdate(conn, "DROP TABLE IF EXISTS messages;");
			System.out.println("Drop table if it exists");
		    String createString =
		    		
		    "CREATE TABLE messages (" + 
		    "  MsgId varchar (100) DEFAULT NULL," + 
		    "  Last_Accessed varchar(100) DEFAULT NULL," + 
		    "  patientId varchar(100) DEFAULT NULL," + 
		    "  GivenName varchar(100) DEFAULT NULL," + 
		    "  FamilyName varchar(100) DEFAULT NULL," + 
		    "  BirthTime varchar(100) DEFAULT NULL," + 
		    "  providerId varchar(100) DEFAULT NULL," + 
		    "  GuardianNo varchar(100) DEFAULT NULL," + 
		    "  Relationship varchar(100) DEFAULT NULL," + 
		    "  FirstName varchar(100) DEFAULT NULL," + 
		    "  LastName varchar(100) DEFAULT NULL," + 
		    "  phone varchar(100) DEFAULT NULL," + 
		    "  address varchar(100) DEFAULT NULL," + 
		    "  city varchar(100) DEFAULT NULL," + 
		    "  state varchar(100) DEFAULT NULL," + 
		    "  zip varchar(100) DEFAULT NULL," + 
		    "  AuthorId varchar(100) DEFAULT NULL," + 
		    "  AuthorTitle varchar(100) DEFAULT NULL," + 
		    "  AuthorFirstName varchar(100) DEFAULT NULL," + 
		    "  AuthorLastName varchar(100) DEFAULT NULL," + 
		    "  ParticipatingRole varchar(100) DEFAULT NULL," + 
		    "  PayerId varchar(100) DEFAULT NULL," + 
		    "  Name varchar(100) DEFAULT NULL," + 
		    "  PolicyHolder varchar(100) DEFAULT NULL," + 
		    "  PolicyType varchar(100) DEFAULT NULL," + 
		    "  Purpose varchar(100) DEFAULT NULL," + 
		    "  RelativeId varchar(100) DEFAULT NULL," + 
		    "  Relation varchar(100) DEFAULT NULL," + 
		    "  age varchar(100) DEFAULT NULL," + 
		    "  Diagnosis varchar(100) DEFAULT NULL," + 
		    "  Id varchar(100) DEFAULT NULL," + 
		    "  Substance varchar(100) DEFAULT NULL," + 
		    "  Reaction varchar(100) DEFAULT NULL," + 
		    "  Status varchar(100) DEFAULT NULL," + 
		    "  LabTestResultId varchar(100) DEFAULT NULL," + 
		    "  PatientVisitId varchar(100) DEFAULT NULL," + 
		    "  LabTestPerformedDate varchar(100) DEFAULT NULL," + 
		    "  LabTestType varchar(100) DEFAULT NULL," + 
		    "  TestResultValue varchar(100) DEFAULT NULL," + 
		    "  ReferenceRangeHigh varchar(100) DEFAULT NULL," + 
		    "  ReferenceRangeLow varchar(100) DEFAULT NULL," + 
		    "  PlanId varchar(100) DEFAULT NULL," + 
		    "  Activity varchar(100) DEFAULT NULL," + 
		    "  ScheduledDate varchar(100) DEFAULT NULL" + ");";
			HF.executeUpdate(conn, createString);
			System.out.println("Created a table");
			HF.executeUpdate(conn, "LOCK TABLES `messages` WRITE;");
			String insertString = "INSERT INTO `messages` VALUES ('c123',NULL,'12345','Adam','Smith','6/18/1963 12:00:00 AM','2.16.840','45678','Grandfather','Ralph','Finnes','781-555-1212','12, Finnegan st.,','Boston','MA','2318','KP00017','Mr.','Henry','Seven','InsuranceCompany','3456','Good Health Insurance','Patientís father','Extended healthcare','Kidney failure','45564','Grandmother','67','Diabetes','4556','Codeine','Hives','Active','200203','2043','2/16/2003 12:00:00 AM','HGB','23','18 g/dl','13 g/dl','456','Colonoscopy','5/5/2000 12:00:00 AM'),('c124',NULL,'12346','Henry','Graham','12/12/2004 12:00:00 AM','2.16.841','45679','father','Thu','Barlowe','(855) 503-1247','948 Adams Avenue','Miami Gardens','FL ','33056','KP00018',NULL,'Justin','Powell','InsuranceCompany','3457','AARP','Patient','Health Savings','Influenza','45565','aunt','45','Tuberculosis','4557','Pollen ','Hayfever','Active','200204','2044','7/6/1988 12:00:00 AM','prolactin','4','3 ng/mL','15 ng/mL','457','Cardiac rehabilitation','5/19/2000 12:00:00 AM'),('c125',NULL,'12347','John','Graham','11/24/2003 12:00:00 AM','2.16.842','45680','son','Theron','Forsberg','(833) 348-1853','84 Cambridge Court','Hightstown','NJ','8520','KP00019',NULL,'Latoya','Clark','InsuranceCompany','3458','Aetna','Patient','Health Savings','Tuberculosis','45566','uncle','34','Diabetes','4558','Eggs','Stomach pain','Inactive','200205','2045','5/17/1985 12:00:00 AM','prolactin','56','34 ng/mL','386 ng/mL','458','Haemodialysis','4/18/2001 12:00:00 AM'),('c126',NULL,'12348','Robert','Graham','8/19/1993 12:00:00 AM','2.16.843','45681','husand','Hiroko','Anspach','(811) 838-7760','557 Pearl Street','Westland','MI ','48185','KP00020',NULL,'Annie','Wagner','InsuranceCompany','3459','American Family Insurance','Patient','Health Savings','Tuberculosis','45567','Grandfather','65','Tuberculosis','4559','Peanuts','Wheezing','Active','200206','2046','7/8/2012 12:00:00 AM','phosphate','3','2.7 mg/dl','4.5 mg/dl','459','Radiology','11/20/2001 12:00:00 AM'),('c127',NULL,'12349','John','Greely','7/8/1955 12:00:00 AM','2.16.844','45682','brother','Harley','Roehl','(811) 395-3416','826 South Street','Joliet','IL ','60435','KP00021',NULL,'Gregg','Jenkins','InsuranceCompany','3460','American National Insurance','Patient','Health Savings','Tuberculosis','45568','Grandmother','67','Brain tumor','4560',NULL,NULL,NULL,'200207','2047','1/12/2008 12:00:00 AM','Rheumatoid','34','60','0','460','Pharmacy','9/2/2002 12:00:00 AM'),('c128',NULL,'12350','Earl','Grey','6/19/2000 12:00:00 AM','2.16.845','45683','grandfather','Lenna','Nicoletti','(811) 190-3480','504 Main Street East','Dracut','MA ','1826','KP00022',NULL,'Tonya','Tate','InsuranceCompany','3461','Amerigroup','Patient','Health Savings','Tuberculosis','45569','aunt','45','Diabetes','4561',NULL,NULL,NULL,'200208','2048',NULL,NULL,NULL,NULL,NULL,'461','Colonoscopy','12/16/2003 12:00:00 AM'),('c129',NULL,'12351','Frank','Griffith','9/5/1962 12:00:00 AM','2.16.846','45684','grandson','Bill','Squillace','(822) 299-0430','203 Washington Avenue','Egg Harbor Township, ','NJ','8234','KP00023',NULL,'Martin','Hines','InsuranceCompany','3462','Anthem Blue Cross and Blue Shield','Patient','Health Savings','Tuberculosis','45570','Grandmother','34','Tuberculosis','4562',NULL,NULL,NULL,'200209','2049',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c130',NULL,'12352','George','Guthrie','7/26/2007 12:00:00 AM','2.16.847','45685','uncle','Christinia','Lebeau','(855) 689-3401','103 Union Street','Akron, ','OH','44312','KP00024',NULL,'Pam','Erickson','InsuranceCompany','3463','Assurant','Patient','Health Savings','Kidney failure','45571','aunt','65','Brain tumor','4563',NULL,NULL,NULL,'200210','2050',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c131',NULL,'12353','Bert','Hackney','11/6/1981 12:00:00 AM','2.16.840','45686','nephew','Janette','Senecal','(822) 061-4784','206 Edgewood Road','Simpsonville, ','SC','29680','KP00025',NULL,'Patti','Dunn','InsuranceCompany','3464','Blue Cross and Blue Shield Association','Patient','Health Savings','Influenza','45572','uncle','67','Diabetes','4564',NULL,NULL,NULL,'200211','2051',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c132',NULL,'12354','Charles','Hall','2/11/1976 12:00:00 AM','2.16.841','45687','cousin','Joane','Ovellette','(811) 843-2330','994 North Avenue','Sarasota, ','FL ','34231','KP00026',NULL,'Edna','Flores','InsuranceCompany','3465','Cambia Health Solutions','Patient','Extended healthcare','Tuberculosis','45573','Grandfather','45','Tuberculosis','4565',NULL,NULL,NULL,'200212','2052',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c133',NULL,'12355','Frank','Harris','7/3/1994 12:00:00 AM','2.16.842','45688','daughter','Tennie','Cowens','(833) 207-6379','530 William Street','Lansdowne, ','PA ','19050','KP00027',NULL,'Kimberly','Simmons','InsuranceCompany','3466','Centene Corporation','Patient','Extended healthcare','Tuberculosis','45574','Grandmother','34','Brain tumor','4566','Codeine','Hives','Active','200213','2053',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c134',NULL,'12356','Nora','Harry','12/1/2002 12:00:00 AM','2.16.843','45689','mother','Marylouise','Tracy','(811) 693-0958','448 Market Street','Camas, ','WA ','98607','KP00028',NULL,'Sheri','Obrien','InsuranceCompany','3467','Cigna','Patient','Extended healthcare','Tuberculosis','45575','aunt','65','Diabetes','4567','Pollen ','Hayfever','Active','200214','2054',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c135',NULL,'12357','Guy','Hawk','5/18/2010 12:00:00 AM','2.16.844','45690','wife','Lisha','Dowler','(899) 204-0989','1 Woodland Drive','Paterson, ','NJ ','7501','KP00029',NULL,'Jeremy','Holland','Guardian','3468','Coventry Health Care','Patient','Extended healthcare','Tuberculosis','45576','Grandmother','67','Tuberculosis','4568','Eggs','Stomach pain','Inactive','200215','2055',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c136',NULL,'12358','Henry','Heine','12/28/1995 12:00:00 AM','2.16.845','45691','sister','Usha','Matt','(822) 330-7614','526 4th Street South','West Babylon, ','NY ','11704','KP00030',NULL,'Olivia','Barton','Guardian','3469','EmblemHealth','Patient','Extended healthcare','Tuberculosis','45577','aunt','45','Brain tumor','4569','Peanuts','Wheezing','Active','200216','2056','7/11/1986 12:00:00 AM','HGB','45','18 g/dl','13 g/dl','469','Radiology','3/14/2008 12:00:00 AM'),('c137',NULL,'12359','Pete','Hiebert','1/31/1997 12:00:00 AM','2.16.846','45692','grandmother','Yuette','Bowens','(811) 115-4954','566 Penn Street','Winona, ','MN','55987','KP00031',NULL,'Carmen','Becker','Guardian','3470','Fortis','Patient','Extended healthcare','Kidney failure','45578','Grandmother','34','Diabetes','4570',NULL,NULL,NULL,'200217','2057','3/8/2008 12:00:00 AM','prolactin','2','3 ng/mL','15 ng/mL','470','Pharmacy','9/1/2008 12:00:00 AM'),('c138',NULL,'12360','George','Holcomb','2/22/1977 12:00:00 AM','2.16.847','45693','granddaughter','Lakeesha','Quist','(833) 476-4244','995 6th Street','Crystal Lake, ','IL ','60014','KP00032',NULL,'Luis','Gardner','Guardian','3471','Golden Rule Insurance Company','Patient','Extended healthcare','Kidney failure','45579','aunt','65','Tuberculosis','4571',NULL,NULL,NULL,'200218','2058','12/24/2010 12:00:00 AM','prolactin','57','34 ng/mL','386 ng/mL','471','Colonoscopy','9/11/2008 12:00:00 AM'),('c139',NULL,'12361','Claude','Homer','3/14/1964 12:00:00 AM','2.16.840','45694','aunt','Donetta','Hance','(833) 595-4842','241 Laurel Drive','Baton Rouge,','LA','70806','KP00033',NULL,'Winifred','Lynch','Guardian','3472','Group Health Cooperative','Patient','Extended healthcare','Influenza','45580','uncle','67','Brain tumor','4572',NULL,NULL,NULL,'200219','2059','4/10/2009 12:00:00 AM','phosphate','2','2.7 mg/dl','4.5 mg/dl','472','Cardiac rehabilitation','11/20/2009 12:00:00 AM'),('c140',NULL,'12362','Will','Houck','11/12/2004 12:00:00 AM','2.16.841','45695','niece','Chet','Burris','(855) 829-4434','128 Garfield Avenue','Hightstown, ','NJ ','8520','KP00034',NULL,'Maryann','Osborne','Guardian','3473','GHI','Patient','Extended healthcare','Tuberculosis','45581','Grandfather','45','Diabetes','4573',NULL,NULL,NULL,'200220','2060','11/23/2012 12:00:00 AM','Rheumatoid','45','60','0','473','Haemodialysis','8/5/2010 12:00:00 AM'),('c141',NULL,'12363','Robert','Leunier','11/8/1986 12:00:00 AM','2.16.842','45696','cousin','Lean','Hepner','(833) 490-4549','311 Country Lane','Rego Park, ','NY ','11374','KP00035',NULL,'Daisy','Santos','Guardian','3474','Health Net','Patient','Health Savings','Tuberculosis','45582','Grandmother','34','Tuberculosis','4574',NULL,NULL,NULL,'200221','2061',NULL,NULL,NULL,NULL,NULL,'474','Radiology','4/15/2011 12:00:00 AM'),('c142',NULL,'12364','Charles','Lindawood','11/1/1980 12:00:00 AM','2.16.843','45697','grandchild','Shanita','Gathings','(811) 253-5080','873 Overlook Circle','Lincolnton, ','NC ','28092','KP00036',NULL,'Max','Grant','Guardian','3475','HealthMarkets','Guardian','Health Savings','Tuberculosis','45583','aunt','65','Brain tumor','4575',NULL,NULL,NULL,'200222','2062',NULL,NULL,NULL,NULL,NULL,'475','Pharmacy','3/9/2012 12:00:00 AM'),('c143',NULL,'12365','Jacob','Longacre','5/10/1992 12:00:00 AM','2.16.844','45698','Grandfather','Iona','Hairston','(833) 750-5027','265 Canterbury Drive','New Bern, ','NC ','28560','KP00037',NULL,'Ben','Santiago','Guardian','3476','HealthPartners','Guardian','Health Savings','Tuberculosis','45584','Grandmother','67','Diabetes','4576','Codeine','Hives','Active','200223','2063',NULL,NULL,NULL,NULL,NULL,'476','Colonoscopy','4/30/2012 12:00:00 AM'),('c144',NULL,'12366','John','Loudon','2/11/1972 12:00:00 AM','2.16.845','45699','father','Santina','Rakestraw','(811) 563-9075','175 Church Street','Prattville, ','AL ','36067','KP00038',NULL,'Dolores','Phillips','Guardian','3477','HealthSpring','Guardian','Health Savings','Tuberculosis','45585','aunt','45','Tuberculosis','4577','Pollen ','Hayfever','Active','200224','2064',NULL,NULL,NULL,NULL,NULL,'477','Cardiac rehabilitation','6/1/2012 12:00:00 AM'),('c145',NULL,'12367','Clyde','Luellen','4/12/1995 12:00:00 AM','2.16.846','45700','son','Breanne','Gapinski','(833) 759-2992','998 Cross Street','Barrington, ','IL ','60010','KP00039',NULL,'Wilma','Bailey','Guardian','3478','Highmark','Guardian','Health Savings','Kidney failure','45586','uncle','34','Brain tumor','4578','Eggs','Stomach pain','Inactive','200225','2065',NULL,NULL,NULL,NULL,NULL,'478','Haemodialysis','5/13/2013 12:00:00 AM'),('c146',NULL,'12368','Jacob','Luellen','6/1/1981 12:00:00 AM','2.16.847','45701','husand','Roman','Bray','(855) 457-5381','755 Route 9','Richmond, ','VA','23223','KP00040',NULL,'Olga','Allison','Guardian','3479','Humana','Guardian','Health Savings','Influenza','45587','Grandfather','65','Diabetes','4579','Peanuts','Wheezing','Active','200226','2066',NULL,NULL,NULL,NULL,NULL,'479','Radiology','1/14/2014 12:00:00 AM'),('c147',NULL,'12369','James','McAlester','6/9/1997 12:00:00 AM','2.16.840','45702','brother','Marty','Shatzer','(899) 316-4433','807 West Avenue','Saint Cloud, ','MN ','56301','KP00041',NULL,'Rudolph','Mcbride','Guardian','3480','Independence Blue Cross','Guardian','Health Savings','Tuberculosis','45588','Grandmother','67','Tuberculosis','4580',NULL,NULL,NULL,'200227','2067',NULL,NULL,NULL,NULL,NULL,'480','Pharmacy','8/31/2015 12:00:00 AM'),('c148',NULL,'12370','Cecil','McArthur','2/12/1961 12:00:00 AM','2.16.841','45703','grandfather','Elisa','Stroh','(899) 079-0446','615 Race Street','Webster, ','NY ','14580','KP00042',NULL,'Armando','Cobb','Guardian','3481','Kaiser Permanente','Guardian','Health Savings','Kidney failure','45589','aunt','45','Brain tumor','4581',NULL,NULL,NULL,'200228','2068',NULL,NULL,NULL,NULL,NULL,'481','Colonoscopy','5/5/2000 12:00:00 AM'),('c149',NULL,'12371','Edward','McCord','4/8/1976 12:00:00 AM','2.16.842','45704','grandson','Melany','Gouveia','(855) 955-9471','671 Valley Drive','Springboro,','OH ','45066','KP00043',NULL,'Karen','Maxwell','Guardian','3482','Kaleida Health','Guardian','Health Savings','Influenza','45590','Grandmother','34','Diabetes','4582',NULL,NULL,NULL,'200229','2069',NULL,NULL,NULL,NULL,NULL,'482','Cardiac rehabilitation','5/19/2000 12:00:00 AM'),('c150',NULL,'12372','Wesley','McCord','4/15/1950 12:00:00 AM','2.16.843','45705','uncle','Samantha','Fleurant','(811) 395-7768','416 Lake Street','Oxford,','MS','38655','KP00044',NULL,'Malcolm','Park','Guardian','3483','LifeWise Health Plan of Oregon','Guardian','Health Savings','Tuberculosis','45591','aunt','65','Tuberculosis','4583',NULL,NULL,NULL,'200230','2070',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c151',NULL,'12373','Carl','McCulley','8/23/1969 12:00:00 AM','2.16.844','45706','nephew','Cara','Killingsworth','(899) 845-2883','767 Elm Street','Uniondale, ','NY','11553','KP00045',NULL,'Darla','Arnold','Guardian','3484','Medica','Guardian','Health Savings','Tuberculosis','45592','Grandmother','67','Brain tumor','4584',NULL,NULL,NULL,'200231','2071','1/22/1990 12:00:00 AM','HGB','6','18 g/dl','13 g/dl',NULL,NULL,NULL),('c152',NULL,'12374','Walter','McFarlane','6/25/1985 12:00:00 AM','2.16.845','45707','cousin','Christi','Allain','(811) 529-0763','443 White Street','Fall River, ','MA ','2720','KP00046',NULL,'Orlando','Reyes','Guardian','3485','Medical Mutual of Ohio','Patient''s son','Extended healthcare','Tuberculosis','45593','aunt','45','Diabetes','4585','Codeine','Hives','Active','200232','2072','2/12/1973 12:00:00 AM','prolactin','56','3 ng/mL','15 ng/mL',NULL,NULL,NULL),('c153',NULL,'12375','Will','McQuown','6/24/2004 12:00:00 AM','2.16.846','45708','daughter','Venetta','Kunz','(822) 187-6696','527 Main Street South','Littleton,','CO','80123','KP00047',NULL,'Clint','Bates','Guardian','3486','Molina Healthcare','Patient''s son','Extended healthcare','Tuberculosis','45594','uncle','34','Tuberculosis','4586','Pollen ','Hayfever','Active','200233','2073','7/18/2010 12:00:00 AM','prolactin','4','34 ng/mL','386 ng/mL',NULL,NULL,NULL),('c154',NULL,'12376','Ellis','Merrell','12/24/1968 12:00:00 AM','2.16.847','45709','mother','Shonna','Hensley','(899) 220-4587','846 Route 2','New York, ','NY ','10002','KP00048',NULL,'Jeffery','Burgess','Guardian','3487','Premera Blue Cross','Patient''s son','Extended healthcare','Tuberculosis','45595','Grandfather','65','Brain tumor','4587','Eggs','Stomach pain','Inactive','200234','2074','9/1/2000 12:00:00 AM','phosphate','5','2.7 mg/dl','4.5 mg/dl','487','Cardiac rehabilitation','12/24/2003 12:00:00 AM'),('c155',NULL,'12377','Walter','Metsch','12/8/1954 12:00:00 AM','2.16.840','45710','wife','Arthur','Bowler','(811) 441-1992','644 Creekside Drive','Murfreesboro, ','TN ','37128','KP00049',NULL,'Anita','Walker','Guardian','3488','Principal Financial Group','Patient''s son','Extended healthcare','Kidney failure','45596','Father','67','Diabetes','4588','Peanuts','Wheezing','Active','200235','2075','6/8/2000 12:00:00 AM','Rheumatoid','64','60','0','488','Haemodialysis','4/7/2004 12:00:00 AM'),('c156',NULL,'12378','Dan','Miller','10/5/1994 12:00:00 AM','2.16.841','45711','sister','Carmine','Vetrano','(833) 934-9626','148 11th Street','Shirley, ','NY ','11967','KP00050',NULL,'Johnathan','Bell','Guardian','3489','Shelter Insurance','Patient''s son','Extended healthcare','Influenza','45597','Mother','45','Tuberculosis','4589',NULL,NULL,NULL,'200236','2076',NULL,NULL,NULL,NULL,NULL,'489','Radiology','8/4/2004 12:00:00 AM'),('c157',NULL,'12379','Rollie','Moore','1/19/1962 12:00:00 AM','2.16.842','45712','grandmother','Letty','Frank','(833) 435-4774','977 1st Avenue','Port Richey, ','FL ','34668','KP00051',NULL,'Katrina','Brady','Guardian','3490','State Farm','Patient''s son','Extended healthcare','Tuberculosis','45598','uncle','34','Brain tumor','4590',NULL,NULL,NULL,'200237','2077',NULL,NULL,NULL,NULL,NULL,'490','Pharmacy','9/17/2004 12:00:00 AM'),('c158',NULL,'12380','Harry','Myers','3/17/1974 12:00:00 AM','2.16.843','45713','granddaughter','Kendall','Scheer','(811) 954-5414','463 Sheffield Drive','Portland,','ME ','4103','KP00052',NULL,'Celia','Padilla','Guardian','3491','Thrivent Financial for Lutherans','Patient''s son','Extended healthcare','Tuberculosis','45599','Grandfather','65','Diabetes','4591',NULL,NULL,NULL,'200238','2078',NULL,NULL,NULL,NULL,NULL,'491','Colonoscopy','9/30/2004 12:00:00 AM'),('c159',NULL,'12381','Isaac','Myers','2/16/1977 12:00:00 AM','2.16.844','45714','aunt','Kylee','Macarthur','(811) 560-5530','410 Parker Street','East Lansing,','MI ','48823','KP00053',NULL,'Lynne','Cole','Guardian','3492','UnitedHealth Group','Patient''s son','Extended healthcare','Tuberculosis','45600','Father','67','Tuberculosis','4592',NULL,NULL,NULL,'200239','2079',NULL,NULL,NULL,NULL,NULL,'492','Cardiac rehabilitation','10/19/2004 12:00:00 AM'),('c160',NULL,'12382','Orrie','Myers','10/7/1976 12:00:00 AM','2.16.845','45715','niece','Jessia','Pontius','(833) 655-3719','913 Green Street','Hillsborough,','NJ ','8844','KP00054',NULL,'Mercedes','Turner','Guardian','3493','Unitrin','Patient''s son','Primary care','Tuberculosis','45601','Mother','45','Brain tumor','4593',NULL,NULL,NULL,'200240','2080',NULL,NULL,NULL,NULL,NULL,'493','Haemodialysis','2/5/2007 12:00:00 AM'),('c161',NULL,'12383','Bernard','Neufelt','10/17/1960 12:00:00 AM','2.16.846','45716','cousin','Aurea','Bickle','(855) 446-1111','995 Maple Lane','Duluth,','GA ','30096','KP00055',NULL,'Rene','Newman','Guardian','3494','Universal American Corporation','Patient''s son','Primary care','Tuberculosis','45602','uncle','34','Diabetes','4594',NULL,NULL,NULL,'200241','2081',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c162',NULL,'12384','Tom','Norris','12/8/1990 12:00:00 AM','2.16.847','45717','grandchild','Terrance','Bellah','(899) 756-2610','987 Willow Drive','Murfreesboro, ','TN','37128','KP00056',NULL,'Garry','Ward','Guardian','3495','WellCare Health Plans','Patient''s son','Primary care','Kidney failure','45603','Grandfather','65','Tuberculosis','4595',NULL,NULL,NULL,'200242','2082',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c163',NULL,'12385','Antone','Oskiwi','8/23/1995 12:00:00 AM','2.16.840','45718','Grandfather','Guillermina','Roudebush','(855) 718-3498','997 Lincoln Avenue','Cranston, ','RI','2920','KP00057',NULL,'Kayla','Mann','Guardian','3496','WellPoint','Patient''s son','Primary care','Kidney failure','45604','Father','67','Brain tumor','4596',NULL,NULL,NULL,'200243','2083',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c164',NULL,'12386','James','Owens','1/13/1985 12:00:00 AM','2.16.841','45719','father','Kristie','Bode','(899) 434-1444','799 Westminster Drive','Albany, ','NY ','12203','KP00058',NULL,'Roman','Dennis','Guardian','3497','AARP','Patient''s son','Primary care','Influenza','45605','Mother','45','Diabetes','4597',NULL,NULL,NULL,'200244','2084',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c165',NULL,'12387','Peutz','Catharine','2/17/1960 12:00:00 AM','2.16.842','45720','son','Arlyne','Kolar','(811) 800-0211','543 Route 32','Selden, ','NY ','11784','KP00059',NULL,'Virginia','Ellis','Guardian','3498','Aetna','Patient''s son','Primary care','Tuberculosis','45606','uncle','34','Tuberculosis','4598',NULL,NULL,NULL,'200245','2085','11/27/1992 12:00:00 AM','HGB','23','18 g/dl','13 g/dl',NULL,NULL,NULL),('c166',NULL,'12388','Eva','Powell','9/19/2001 12:00:00 AM','2.16.843','45721','husand','Janyce','Deherrera','(899) 682-3936','69 Grand Avenue','Mansfield, ','MA ','2048','KP00060',NULL,'Damon','Dean','Guardian','3499','American Family Insurance','Patient''s son','Primary care','Tuberculosis','45607','Grandfather','65','Brain tumor','4599','Codeine','Hives','Active','200246','2086','8/29/2000 12:00:00 AM','prolactin','4','3 ng/mL','15 ng/mL','499','Radiology','4/15/2011 12:00:00 AM'),('c167',NULL,'12389','Frank','Rosefelt','5/5/1955 12:00:00 AM','2.16.844','45722','brother','Lasonya','Mclaughlin','(822) 440-9480','119 4th Street South','Taunton, ','MA ','2780','KP00061',NULL,'Kirk','Terry','Guardian','3500','American National Insurance','Patient''s son','Primary care','Tuberculosis','45608','Father','67','Diabetes','4600','Pollen ','Hayfever','Active','200247','2087','4/5/1998 12:00:00 AM','prolactin','56','34 ng/mL','386 ng/mL','500','Pharmacy','3/9/2012 12:00:00 AM'),('c168',NULL,'12390','John','Rosefelt','1/25/2011 12:00:00 AM','2.16.845','45723','grandfather','Kelvin','Bartmess','(822) 150-6945','945 Briarwood Drive','Zeeland,','MI ','49464','KP00062',NULL,'Marc','Malone','Guardian','3501','Amerigroup','Patient''s son','Primary care','Tuberculosis','45609','Mother','45','Tuberculosis','4601','Eggs','Stomach pain','Inactive','200248','2088','3/14/2013 12:00:00 AM','phosphate','3','2.7 mg/dl','4.5 mg/dl','501','Colonoscopy','4/30/2012 12:00:00 AM'),('c169',NULL,'12391','Dan','Royston','5/25/1995 12:00:00 AM','2.16.846','45724','grandson','Carlyn','Richburg','(822) 458-7707','965 Briarwood Drive','New Rochelle, ','NY ','10801','KP00063',NULL,'Melody','Olson','Guardian','3502','Anthem Blue Cross and Blue Shield','Patient''s son','Primary care','Tuberculosis','45610','uncle','34','Brain tumor','4602','Peanuts','Wheezing','Active','200249','2089','12/2/1988 12:00:00 AM','Rheumatoid','34','60','0','502','Cardiac rehabilitation','6/1/2012 12:00:00 AM'),('c170',NULL,'12392','Miss','Russell','6/13/1978 12:00:00 AM','2.16.847','45725','uncle','Henrietta','Ahern','(844) 898-8345','965 Linden Street','Tualatin, ','OR','97062','KP00064',NULL,'Deanna','Wright','Guardian','3503','Assurant','Patient''s son','Primary care','Kidney failure','45611','Grandfather','65','Diabetes','4603',NULL,NULL,NULL,'200250','2090',NULL,NULL,NULL,NULL,NULL,'503','Haemodialysis','5/13/2013 12:00:00 AM'),('c171',NULL,'12393','Fred','Sattler','10/22/1995 12:00:00 AM','2.16.840','45726','nephew','Delmer','Beesley','(822) 967-3472','822 Old York Road','Nicholasville,','KY','40356','KP00065',NULL,'Alexander','Herrera','Guardian','3504','Blue Cross and Blue Shield Association','Patient''s son','Primary care','Influenza','45612','Father','67','Tuberculosis','4604',NULL,NULL,NULL,'200251','2091',NULL,NULL,NULL,NULL,NULL,'504','Radiology','1/14/2014 12:00:00 AM'),('c172',NULL,'12394','Abraham','Schmidt','9/21/2006 12:00:00 AM','2.16.841','45727','cousin','Ralph','Monaghan †','(822) 223-1689','160 George Street','Hanover,','PA ','17331','KP00066',NULL,'Homer','Estrada','Guardian','3505','Cambia Health Solutions','Patient''s son','Primary care','Tuberculosis','45613','Mother','45','Brain tumor','4605',NULL,NULL,NULL,'200252','2092',NULL,NULL,NULL,NULL,NULL,'505','Pharmacy','8/31/2015 12:00:00 AM'),('c173',NULL,'12395','Cornelius','Schmidt','9/20/1999 12:00:00 AM','2.16.842','45728','daughter','Donnie','Schulenberg †','(899) 488-1865','140 4th Street West','San Angelo,','TX ','76901','KP00067',NULL,'Matt','Lane','Guardian','3506','Centene Corporation','Patient''s son','Primary care','Kidney failure','45614','uncle','34','Diabetes','4606',NULL,NULL,NULL,'200253','2093',NULL,NULL,NULL,NULL,NULL,'506','Colonoscopy','5/5/2000 12:00:00 AM'),('c174',NULL,'12396','David','Schmidt','4/11/2011 12:00:00 AM','2.16.843','45729','mother','Lucas','Yamashita †','(822) 545-9916','697 Westminster Drive','Maineville, ','OH ','45039','KP00068',NULL,'Jill','Perkins','Guardian','3507','Cigna','Patient''s son','Primary care','Influenza','45615','Grandfather','65','Tuberculosis','4607',NULL,NULL,NULL,'200254','2094',NULL,NULL,NULL,NULL,NULL,'507','Cardiac rehabilitation','5/19/2000 12:00:00 AM'),('c175',NULL,'12397','Henry','Schmidt','5/17/1957 12:00:00 AM','2.16.844','45730','wife','Eldora','Metzger †','(811) 732-2149','931 Main Street East','Hillsborough, ','NJ ','8844','KP00069',NULL,'May','Rodriquez','Guardian','3508','Coventry Health Care','Patient''s son','Primary care','Tuberculosis','45616','Father','67','Brain tumor','4608',NULL,NULL,NULL,'200255','2095',NULL,NULL,NULL,NULL,NULL,'508','Haemodialysis','4/18/2001 12:00:00 AM'),('c176',NULL,'12398','Jacob','Schmidt','7/4/1956 12:00:00 AM','2.16.845','45731','sister','Isobel','Son †','(844) 626-2164','991 Buckingham Drive','Havertown,','PA ','19083','KP00070',NULL,'Harold','Owen','Patient Information','3509','EmblemHealth','Patient''s son','Primary care','Tuberculosis','45617','Mother','45','Diabetes','4609',NULL,NULL,NULL,'200256','2096',NULL,NULL,NULL,NULL,NULL,'509','Radiology','11/20/2001 12:00:00 AM'),('c177',NULL,'12399','John','Seaman','6/2/1999 12:00:00 AM','2.16.846','45732','grandmother','Fernando','Kunkle †','(833) 426-2820','410 Hilltop Road','Roselle, ','IL ','60172','KP00071',NULL,'Marion','Miller','Patient Information','3510','Fortis','Patient','Medicare','Tuberculosis','45618','uncle','34','Tuberculosis','4610',NULL,NULL,NULL,'200257','2097','5/6/2006 12:00:00 AM','HGB','23','18 g/dl','13 g/dl','510','Pharmacy','9/2/2002 12:00:00 AM'),('c178',NULL,'12400','David','Shirk','5/8/1955 12:00:00 AM','2.16.847','45733','granddaughter','Jammie','Dana †','(844) 753-6705','307 Clark Street','Essex, ','MD ','21221','KP00072',NULL,'Bessie','Sandoval','Patient Information','3511','Golden Rule Insurance Company','Patient','Medicare','Tuberculosis','45619','Grandfather','65','Brain tumor','4611',NULL,NULL,NULL,'200258','2098','7/7/2007 12:00:00 AM','prolactin','4','3 ng/mL','15 ng/mL',NULL,NULL,NULL),('c179',NULL,'12401','Ben','Shomber','3/9/1950 12:00:00 AM','2.16.840','45734','aunt','Joaquina','Beasley †','(833) 478-5270','415 Valley View Road','Port Chester, ','NY ','10573','KP00073',NULL,'Dwight','Blake','Patient Information','3512','Group Health Cooperative','Patient','Medicare','Tuberculosis','45620','Father','67','Diabetes','4612','Codeine','Hives','Active','200259','2099','11/26/1998 12:00:00 AM','prolactin','56','34 ng/mL','386 ng/mL',NULL,NULL,NULL),('c180',NULL,'12402','James','Shomber','11/26/1962 12:00:00 AM','2.16.841','45735','niece','Janise','Southwell †','(811) 072-1366','213 12th Street','Branford, ','CT ','6405','KP00074',NULL,'Marion','Norton','Patient Information','3513','GHI','Patient','Medicare','Kidney failure','45621','Mother','45','Tuberculosis','4613','Pollen ','Hayfever','Active','200260','2100','8/26/1993 12:00:00 AM','phosphate','3','2.7 mg/dl','4.5 mg/dl',NULL,NULL,NULL),('c181',NULL,'12403','David','Shroeder','11/14/1989 12:00:00 AM','2.16.842','45736','cousin','Deandrea','Desrochers †','(822) 830-7601','253 Hickory Street','Taylor, ','MI','48180','KP00075',NULL,'Clark','Lewis','Patient Information','3514','Health Net','Patient','Medicare','Influenza','45622','uncle','34','Brain tumor','4614','Eggs','Stomach pain','Inactive','200261','2101','4/13/1979 12:00:00 AM','Rheumatoid','34','60','0',NULL,NULL,NULL),('c182',NULL,'12404','William','Shroyer','10/4/1998 12:00:00 AM','2.16.843','45737','grandchild','Lorenzo','Soto †','(844) 649-4549','927 Beech Street','Bountiful, ','UT ','84010','KP00076',NULL,'Carrie','Sharp','Patient Information','3515','HealthMarkets','Patient','Medicare','Tuberculosis','45623','Grandfather','65','Diabetes','4615','Peanuts','Wheezing','Active','200262','2102',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c183',NULL,'12405','George','Shutz','1/15/1957 12:00:00 AM','2.16.844','45738','Grandfather','Beata','Sperber †','(811) 275-4553','434 Market Street','Brunswick, ','GA','31525','KP00077',NULL,'Nathaniel','Kennedy','Patient Information','3516','HealthPartners','Patient','Medicare','Tuberculosis','45624','Father','67','Tuberculosis','4616',NULL,NULL,NULL,'200263','2103',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c184',NULL,'12406','William','Skinner','6/22/1962 12:00:00 AM','2.16.845','45739','father','Exie','Chee †','(811) 240-6539','278 Rose Street','West Springfield,','MA ','1089','KP00078',NULL,'Bryant','Armstrong','Patient Information','3517','HealthSpring','Patient','Medicare','Tuberculosis','45625','Mother','45','Brain tumor','4617',NULL,NULL,NULL,'200264','2104',NULL,NULL,NULL,NULL,NULL,'517','Cardiac rehabilitation','10/19/2004 12:00:00 AM'),('c185',NULL,'12407','Emmanuel','Smith','7/3/1973 12:00:00 AM','2.16.846','45740','son','Kina','Lieberman †','(822) 092-6189','881 Colonial Avenue','Avon,','IN ','46123','KP00079',NULL,'Mark','Jimenez','Patient Information','3518','Highmark','Patient','Medicare','Tuberculosis','45626','uncle','34','Diabetes','4618',NULL,NULL,NULL,'200265','2105',NULL,NULL,NULL,NULL,NULL,'518','Haemodialysis','2/5/2007 12:00:00 AM'),('c186',NULL,'12408','Isaac','Swick','9/18/1976 12:00:00 AM','2.16.847','45741','husand','Lahoma','Berney †','(855) 674-9127','670 Harrison Street','Norwich, ','CT','6360','KP00080',NULL,'Geneva','Crawford','Patient Information','3519','Humana','Patient','Medicare','Tuberculosis','45627','Grandfather','65','Tuberculosis','4619',NULL,NULL,NULL,'200266','2106',NULL,NULL,NULL,NULL,NULL,'519','Radiology','3/14/2008 12:00:00 AM'),('c187',NULL,'12409','Will','Taylor','7/23/1997 12:00:00 AM','2.16.840','45742','brother','Tatiana','Topp †','(844) 083-1561','331 Valley Drive','Land O Lakes, ','FL ','34639','KP00081',NULL,'Bobby','Webster','Patient Information','3520','Independence Blue Cross','Patient','Medicare','Kidney failure','45628','Father','67','Brain tumor','4620',NULL,NULL,NULL,'200267','2107',NULL,NULL,NULL,NULL,NULL,'520','Pharmacy','9/1/2008 12:00:00 AM'),('c188',NULL,'12410','George','Thomas','3/30/1954 12:00:00 AM','2.16.841','45743','grandfather','Ben','Salguero †','(811) 025-5643','350 Mulberry Street','Champaign,','IL ','61821','KP00082',NULL,'Tamara','Moody','Patient Information','3521','Kaiser Permanente','Patient','Medicare','Kidney failure','45629','Mother','45','Diabetes','4621',NULL,NULL,NULL,'200268','2108',NULL,NULL,NULL,NULL,NULL,'521','Colonoscopy','9/11/2008 12:00:00 AM'),('c189',NULL,'12411','Harvey','Thompson','9/14/2003 12:00:00 AM','2.16.842','45744','grandson','Karlene','Danielson †','(822) 452-5328','644 Heritage Drive','Beloit, ','WI ','53511','KP00083',NULL,'Clarence','Robbins','Patient Information','3522','Kaleida Health','Patient','Medicare','Influenza','45630','uncle','34','Tuberculosis','4622',NULL,NULL,NULL,'200269','2109',NULL,NULL,NULL,NULL,NULL,'522','Cardiac rehabilitation','11/20/2009 12:00:00 AM'),('c190',NULL,'12412','John','Thompson','5/11/1992 12:00:00 AM','2.16.843','45745','uncle','Tambra','Voelker †','(811) 929-1565','440 Clay Street','Shepherdsville, ','KY ','40165','KP00084',NULL,'Lynda','Paul','Patient Information','3523','LifeWise Health Plan of Oregon','Patient','Medicare','Tuberculosis','45631','Grandfather','65','Brain tumor','4623',NULL,NULL,NULL,'200270','2110',NULL,NULL,NULL,NULL,NULL,'523','Haemodialysis','8/5/2010 12:00:00 AM'),('c191',NULL,'12413','Richard','Thompson','9/2/1996 12:00:00 AM','2.16.844','45746','nephew','Georgette','Addis †','(833) 597-0599','994 Route 27','Lawrence, ','MA ','1841','KP00085',NULL,'Marcia','Morales','Patient Information','3524','Medica','Patient','Medicare','Tuberculosis','45632','Father','67','Diabetes','4624',NULL,NULL,NULL,'200271','2111',NULL,NULL,NULL,NULL,NULL,'524','Radiology','4/15/2011 12:00:00 AM'),('c192',NULL,'12414','Bertha','Trego','8/20/2004 12:00:00 AM','2.16.845','45747','cousin','Shannon','Monroig †','(833) 141-8944','393 Elm Street','Toms River,','NJ ','8753','KP00086',NULL,'Heather','Hanson','Patient Information','3525','Medical Mutual of Ohio','Patient','Medicare','Tuberculosis','45633','Mother','45','Tuberculosis','4625',NULL,NULL,NULL,'200272','2112','8/27/2010 12:00:00 AM','HGB','23','18 g/dl','13 g/dl','525','Pharmacy','3/9/2012 12:00:00 AM'),('c193',NULL,'12415','Perry','Turner','3/10/1987 12:00:00 AM','2.16.846','45748','daughter','Robin','Lagrange †','(844) 079-3973','785 Linden Street','Palm Harbor,','FL ','34683','KP00087',NULL,'Lamar','Hardy','Patient Information','3526','Molina Healthcare','Patient','Medicare','Tuberculosis','45634','uncle','34','Brain tumor','4626',NULL,NULL,NULL,'200273','2113','10/11/1995 12:00:00 AM','prolactin','4','3 ng/mL','15 ng/mL','526','Colonoscopy','4/30/2012 12:00:00 AM'),('c194',NULL,'12416','Florence','Vasquez','3/24/2001 12:00:00 AM','2.16.847','45749','mother','Delcie','Spruill †','(811) 021-5971','820 Laurel Lane','Grandville, ','MI ','49418','KP00088',NULL,'Norma','Moreno','Patient Information','3527','Premera Blue Cross','Patient','Medicare','Tuberculosis','45635','Grandfather','65','Diabetes','4627','Codeine','Hives','Active','200274','2114','1/25/1996 12:00:00 AM','prolactin','56','34 ng/mL','386 ng/mL','527','Cardiac rehabilitation','6/1/2012 12:00:00 AM'),('c195',NULL,'12417','Frank','Wakeman','1/31/1960 12:00:00 AM','2.16.840','45750','wife','Glynda','Mcphillips †','(844) 695-2958','848 Heritage Drive','Ephrata,','PA ','17522','KP00089',NULL,'Jackie','Gibson','Patient Information','3528','Principal Financial Group','Patient','Medicare','Kidney failure','45636','Father','67','Tuberculosis','4628','Pollen ','Hayfever','Active','200275','2115','5/5/1985 12:00:00 AM','phosphate','3','2.7 mg/dl','4.5 mg/dl','528','Haemodialysis','5/13/2013 12:00:00 AM'),('c196',NULL,'12418','Charles','Wallace','9/11/2001 12:00:00 AM','2.16.841','45751','sister','Catherina','Coffelt †','(899) 909-9542','119 Linda Lane','Coatesville, ','PA ','19320','KP00090',NULL,'Shari','Lopez','Patient Information','3529','Shelter Insurance','Guardian','Medicare','Influenza','45637','Mother','45','Brain tumor','4629','Eggs','Stomach pain','Inactive','200276','2116','1/7/2011 12:00:00 AM','Rheumatoid','34','60','0','529','Radiology','1/14/2014 12:00:00 AM'),('c197',NULL,'12419','Elmer','Weeks','1/27/1993 12:00:00 AM','2.16.842','45752','grandmother','Deborah','Verges †','(833) 128-1921','791 Linden Avenue','Marshalltown, ','IA ','50158','KP00091',NULL,'Gerardo','Collins','Patient Information','3530','State Farm','Guardian','Medicare','Tuberculosis','45638','uncle','34','Diabetes','4630','Peanuts','Wheezing','Active','200277','2117',NULL,NULL,NULL,NULL,NULL,'530','Pharmacy','8/31/2015 12:00:00 AM'),('c198',NULL,'12420','Albert','Will','1/19/2007 12:00:00 AM','2.16.843','45753','granddaughter','Tifany','Kensinger †','(899) 535-4079','616 Magnolia Drive','Wellington,','FL ','33414','KP00092',NULL,'Ralph','Powers','Patient Information','3531','Thrivent Financial for Lutherans','Guardian','Medicare','Kidney failure','45639','Grandfather','65','Tuberculosis','4631',NULL,NULL,NULL,'200278','2118',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c199',NULL,'12421','William','Will','5/25/1983 12:00:00 AM','2.16.844','45754','aunt','Shaunte','Smiddy †','(822) 740-6987','58 2nd Street East','Osseo, ','MN ','55311','KP00093',NULL,'Louis','Moran','Patient Information','3532','UnitedHealth Group','Guardian','Medicare','Influenza','45640','Father','67','Brain tumor','4632',NULL,NULL,NULL,'200279','2119',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c200',NULL,'12422','Walter','Wilson','4/17/1992 12:00:00 AM','2.16.845','45755','niece','Mayola','Curnutte †','(811) 868-3019','182 Cambridge Road','Snohomish,','WA ','98290','KP00094',NULL,'Wade','Clarke','Patient Information','3533','Unitrin','Guardian','Medicare','Tuberculosis','45641','Mother','45','Diabetes','4633',NULL,NULL,NULL,'200280','2120',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c201',NULL,'12423','Frank','Wise','11/4/2003 12:00:00 AM','2.16.846','45756','cousin','Ivey','Caulder †','(855) 374-2062','473 12th Street East','Glenview, ','IL','60025','KP00095',NULL,'Elbert','Walton','Patient Information','3534','Universal American Corporation','Guardian','Medicare','Tuberculosis','45642','uncle','34','Tuberculosis','4634',NULL,NULL,NULL,'200281','2121',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c202',NULL,'12424','Harvey','Wright','7/17/1960 12:00:00 AM','2.16.847','45757','grandchild','Latoyia','Sterba †','(833) 989-9687','540 Hillcrest Avenue','Port Chester, ','NY ','10573','KP00096',NULL,'Beatrice','Coleman','Patient Information','3535','WellCare Health Plans','Guardian','Medicare','Tuberculosis','45643','Grandfather','65','Brain tumor','4635',NULL,NULL,NULL,'200282','2122',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c203',NULL,'12425','O.L.','Mayhugh','5/11/1997 12:00:00 AM','2.16.840','45758','Grandfather','Mervin','Burg †','(844) 370-3672','8 Fairview Avenue','Southampton, ','PA ','18966','KP00097',NULL,'Marilyn','Strickland','Patient Information','3536','WellPoint','Guardian','Medicare','Tuberculosis','45644','Father','67','Diabetes','4636',NULL,NULL,NULL,'200283','2123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c204',NULL,'12426','James','McClain','7/1/2011 12:00:00 AM','2.16.841','45759','father','Ester','Mirza †','(844) 418-4277','760 Tanglewood Drive','Jackson,','NJ','8527','KP00098',NULL,'Gwen','Richardson','Patient Information','3537','AARP','Guardian','Medicare','Tuberculosis','45645','Mother','45','Tuberculosis','4637',NULL,NULL,NULL,'200284','2124',NULL,NULL,NULL,NULL,NULL,'537','Cardiac rehabilitation','5/5/2000 12:00:00 AM'),('c205',NULL,'12427','Richard','McClure','8/1/1961 12:00:00 AM','2.16.842','45760','son','Terrie','Fredricks †','(833) 518-6711','922 Grant Street','Ringgold, ','GA','30736','KP00099',NULL,'Kenny','Newton','Patient Information','3538','Aetna','Guardian','Medicare','Kidney failure','45646','uncle','34','Brain tumor','4638',NULL,NULL,NULL,'200285','2125',NULL,NULL,NULL,NULL,NULL,'538','Haemodialysis','5/19/2000 12:00:00 AM'),('c206',NULL,'12428','Henry, Sr.','Goertz','9/27/1971 12:00:00 AM','2.16.843','45761','husand','Cammy','Hilgefort †','(822) 017-6040','34 Route 41','Roseville, ','MI ','48066','KP00100',NULL,'Roger','Wallace','Patient Information','3539','American Family Insurance','Guardian','Medicare','Influenza','45647','Grandfather','65','Diabetes','4639',NULL,NULL,NULL,'200286','2126',NULL,NULL,NULL,NULL,NULL,'539','Radiology','4/18/2001 12:00:00 AM'),('c207',NULL,'12429','Jacob','Glenn','12/3/2007 12:00:00 AM','2.16.844','45762','brother','Milly','Marshell †','(844) 207-8803','837 Route 6','Cordova,','TN ','38016','KP00101',NULL,'Joann','Daniel','Patient Information','3540','American National Insurance','Guardian','Medicare','Tuberculosis','45648','Father','67','Tuberculosis','4640',NULL,NULL,NULL,'200287','2127',NULL,NULL,NULL,NULL,NULL,'540','Pharmacy','11/20/2001 12:00:00 AM'),('c208',NULL,'12430','Walter','Wilson','11/13/1996 12:00:00 AM','2.16.845','45763','grandfather','Amberly','Marguez †','(855) 117-3949','640 Willow Street','Hickory, ','NC ','28601','KP00102',NULL,'Priscilla','Burns','Patient Information','3541','Amerigroup','Guardian','Medicare','Tuberculosis','45649','Mother','45','Brain tumor','4641',NULL,NULL,NULL,'200288','2128',NULL,NULL,NULL,NULL,NULL,'541','Colonoscopy','9/2/2002 12:00:00 AM'),('c209',NULL,'12431','Frank','Wise','8/31/1995 12:00:00 AM','2.16.846','45764','grandson','Christin','Freeze †','(833) 571-3830','965 Atlantic Avenue','Florence, ','SC ','29501','KP00103',NULL,'Krista','Holloway','Patient Information','3542','Anthem Blue Cross and Blue Shield','Guardian','Medicare','Tuberculosis','45650','uncle','34','Diabetes','4642','Codeine','Hives','Active','200289','2129','11/13/2002 12:00:00 AM','HGB','23','18 g/dl','13 g/dl','542','Cardiac rehabilitation','12/16/2003 12:00:00 AM'),('c210',NULL,'12432','David','Worthington','4/7/1967 12:00:00 AM','2.16.847','45765','uncle','Rosana','Madeiros †','(811) 485-4258','458 Ridge Road','Nampa, ','ID','83651','KP00104',NULL,'Winston','Brewer','Patient Information','3543','Assurant','Guardian','Medicare','Tuberculosis','45651','Grandfather','65','Tuberculosis','4643','Pollen ','Hayfever','Active','200290','2130','4/6/1992 12:00:00 AM','prolactin','4','3 ng/mL','15 ng/mL','543','Haemodialysis','12/24/2003 12:00:00 AM'),('c211',NULL,'12433','Harvey','Wright','4/26/2005 12:00:00 AM','2.16.840','45766','nephew','Leonila','Bauder †','(844) 652-4557','640 Church Street','Mount Juliet, ','TN ','37122','KP00105',NULL,'Jaime','Curry','Patient Information','3544','Blue Cross and Blue Shield Association','Guardian','Medicare','Tuberculosis','45652','Father','67','Brain tumor','4644','Eggs','Stomach pain','Inactive','200291','2131','12/13/1976 12:00:00 AM','prolactin','56','34 ng/mL','386 ng/mL','544','Radiology','4/7/2004 12:00:00 AM'),('c212',NULL,'12434','W.E.','Glenn','3/9/1959 12:00:00 AM','2.16.841','45767','cousin','Agatha','Ogburn †','(822) 740-7893','385 10th Street','Bethpage,','NY ','11714','KP00106',NULL,'Alexandra','Miles','Patient Information','3545','Cambia Health Solutions','Guardian','Medicare','Kidney failure','45653','Mother','45','Diabetes','4645','Peanuts','Wheezing','Active','200292','2132','7/5/2008 12:00:00 AM','phosphate','3','2.7 mg/dl','4.5 mg/dl',NULL,NULL,NULL),('c213',NULL,'12435','A.E.','McClain','10/25/1996 12:00:00 AM','2.16.842','45768','daughter','Monica','Corrigan †','(844) 757-5884','972 Devon Court','Elizabethtown,','PA ','17022','KP00107',NULL,'Michelle','Buchanan','Patient Information','3546','Centene Corporation','Patient','Medicare','Kidney failure','45654','uncle','34','Tuberculosis','4646',NULL,NULL,NULL,'200293','2133','9/30/1980 12:00:00 AM','Rheumatoid','34','60','0',NULL,NULL,NULL),('c214',NULL,'12436','Miss Ella','McClain','8/15/1951 12:00:00 AM','2.16.843','45769','mother','Loan','Stubbs †','(833) 281-6533','190 James Street','Mount Pleasant, ','SC','29464','KP00108',NULL,'Irene','Massey','Patient Information','3547','Cigna','Patient','Medicare','Influenza','45655','Grandfather','65','Brain tumor','4647',NULL,NULL,NULL,'200294','2134',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c215',NULL,'12437','Mrs. Henrietta','McClain','3/2/1950 12:00:00 AM','2.16.844','45770','wife','Dixie','Southwood †','(844) 402-8895','505 West Avenue','Lawrence, ','MA ','1841','KP00109',NULL,'Lillian','Hughes','Patient Information','3548','Coventry Health Care','Patient','Medicare','Tuberculosis','45656','Father','67','Diabetes','4648',NULL,NULL,NULL,'200295','2135',NULL,NULL,NULL,NULL,NULL,'548','Haemodialysis','10/19/2004 12:00:00 AM'),('c216',NULL,'12438','Mr. J.','Wetschuski','7/14/1985 12:00:00 AM','2.16.845','45771','sister','Kristal','Lu †','(899) 931-5758','185 Cross Street','Tualatin, ','OR','97062','KP00110',NULL,'George','Fernandez','Patient Information','3549','EmblemHealth','Patient','Medicare','Tuberculosis','45657','Mother','45','Tuberculosis','4649',NULL,NULL,NULL,'200296','2136',NULL,NULL,NULL,NULL,NULL,'549','Radiology','2/5/2007 12:00:00 AM'),('c217',NULL,'12439','Mr.Wiede',NULL,'8/9/1956 12:00:00 AM','2.16.846','45772','grandmother','Frida','Tozier †','(833) 961-2888','820 Wall Street','Longwood, ','FL','32779','KP00111',NULL,'David','Russell','Patient Information','3550','Fortis','Patient','Medicare','Tuberculosis','45658','uncle','34','Brain tumor','4650',NULL,NULL,NULL,'200297','2137',NULL,NULL,NULL,NULL,NULL,'550','Pharmacy','3/14/2008 12:00:00 AM'),('c218',NULL,'12440','Perry','Will','3/28/2008 12:00:00 AM','2.16.847','45773','granddaughter','Kimberly','Tony †','(899) 707-2317','504 Colonial Drive','Norcross,','GA ','30092','KP00112',NULL,'Noah','Patrick','Patient Information','3551','Golden Rule Insurance Company','Patient','Medicare','Tuberculosis','45659','Grandfather','65','Diabetes','4651','Codeine','Hives','Active','200298','2138','1/28/2007 12:00:00 AM','HGB','23','18 g/dl','13 g/dl','551','Colonoscopy','9/1/2008 12:00:00 AM'),('c219',NULL,'12441','Mrs. W.J.','Myers','5/15/1954 12:00:00 AM','2.16.840','45774','aunt','Willis','Scot †','(822) 107-9930','450 Elm Avenue','Brainerd,','MN','56401','KP00113',NULL,'Vanessa','Rice','Patient Information','3552','Group Health Cooperative','Patient','Medicare','Tuberculosis','45660','Father','67','Tuberculosis','4652','Pollen ','Hayfever','Active','200299','2139','3/28/2014 12:00:00 AM','prolactin','4','3 ng/mL','15 ng/mL','552','Cardiac rehabilitation','9/11/2008 12:00:00 AM'),('c220',NULL,'12442','Mr.','Orner','12/14/1996 12:00:00 AM','2.16.841','45775','niece','Cecily','Heidenreich †','(811) 724-5720','330 Evergreen Drive','North Kingstown, ','RI ','2852','KP00114',NULL,'Irvin','Fowler','Patient Information','3553','GHI','Patient','Medicare','Kidney failure','45661','Mother','45','Brain tumor','4653','Eggs','Stomach pain','Inactive','200300','2140','2/16/2013 12:00:00 AM','prolactin','56','34 ng/mL','386 ng/mL','553','Haemodialysis','11/20/2009 12:00:00 AM'),('c221',NULL,'12443','Mrs. Tilly','Osborne','7/28/1977 12:00:00 AM','2.16.842','45776','cousin','Arthur','Honaker †','(833) 919-8089','682 Willow Avenue','Drexel Hill, ','PA ','19026','KP00115',NULL,'Craig','Thornton','Patient Information','3554','Health Net','Patient','Medicare','Influenza','45662','uncle','34','Diabetes','4654','Peanuts','Wheezing','Active','200301','2141','10/2/1987 12:00:00 AM','phosphate','3','2.7 mg/dl','4.5 mg/dl','554','Radiology','8/5/2010 12:00:00 AM'),('c222',NULL,'12444','Miss Mary','Patton','9/14/2006 12:00:00 AM','2.16.843','45777','grandchild','Michaela †','Smiddy †','(811) 971-3891','443 Meadow Lane','Upper Marlboro,','MD ','20772','KP00116',NULL,'Cecil','Warren','Patient Information','3555','HealthMarkets','Patient','Medicare','Tuberculosis','45663','Grandfather','65','Tuberculosis','4655',NULL,NULL,NULL,'200302','2142','11/23/2004 12:00:00 AM','Rheumatoid','34','60','0',NULL,NULL,NULL);";
			HF.executeUpdate(conn, insertString);
			HF.executeUpdate(conn, "UNLOCK TABLES;");
			System.out.println("Inserted info");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
		
		// Drop the table
		try {
		    String dropString = "DROP TABLE " + this.tableName;
			//this.executeUpdate(conn, dropString);
			//System.out.println("Dropped the table");
	    } catch (Exception e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
				return;
			}
		}
		
		/**
		 * Connect to the DB and do some stuff
		 * @throws SQLException 
		 * @throws ParseException 
		 * @throws IllegalAccessException 
		 * @throws IllegalArgumentException 
		 */
		public static void main(String[] args) throws SQLException, ParseException, IllegalArgumentException, IllegalAccessException {
			DBDemo app = new DBDemo();
			app.run();
			System.out.println(args[0]);
			HelperFunctions HF = new HelperFunctions();
			//patient TODO: add gaurdian
			if(args[0].contains("1"))
			{
				System.out.println("inside");
			
					String userID = System.console().readLine("Enter Patient ID: ");
					
					Patient p = HF.getPatient(app.getConnection(), userID);
					if(p == null)
					{
						System.out.println("Error: Could not find patient");
					}
					else
					{
						Field[] fields = p.getClass().getDeclaredFields();
						for(Field f: fields)
						{
							System.out.println(f.getName() +": " + f.get(p));
						}
						
						System.out.println("Hi " + p.getGivenName() +", Would you like to edit? (yes/no) ");
						String edit = System.console().readLine();
						while(!(edit.contentEquals("yes") || edit.contentEquals("no") || edit.contentEquals("guardian")))
							edit = System.console().readLine("(Incorrect Response) Hi " + p.getGivenName() +", Would you like to edit? (enter: yes/no, if you want to view/edit guardian enter 'guardian') ");
						if(edit.contentEquals("yes"))
						{
							
							SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
							System.out.println("\nWhich Column/Input do you want to change (if there is space in your input add %20 instead): ");
							String stringOfChanges = System.console().readLine();
							String[] arrayOfChanges = stringOfChanges.split(" ");
							for(String s: arrayOfChanges)
							{
								String[] input = s.split("/");
								switch(input[0])
								{
						
								case "PatientRole":
									p.setPatientrole(input[1]);
									break;
								case "GivenName":
									p.setGivenName(input[1]);
									break;
								case "FamilyName":
									p.setFamilyName(input[1]);
									break;
								case "Suffix":
									p.setSuffix(input[1]);
									break;
								case "Gender":
									p.setGender(input[1]);
									break;
								case "BirthTime":
									p.setBirthTime(input[1]);
									break;
								case "ProviderId":
									p.setProviderId(input[1]);
									break;
								case "XMLHealth":
									p.setXmlCreationdate(input[1]);
									break;
								default:
									break;
							}
							}
							HF.updatePatient(app.getConnection(), p.PatientId, p.PatientRole, p.GivenName, p.FamilyName, p.Suffix, p.Gender, p.Birthtime, p.ProviderId, p.XMLHealth);
							//
						}
						else if(edit.contentEquals("gaurdian"))
						{
							//lookup guardian based on PatientRole attribute
							//p.patientrole1
						}
					}
					
					
				
			}
			//Doctor and Author
			else if(args[0].contains("2"))
			{
				//String doctorID = System.console().readLine("Enter Doctor or Author ID: ");
				//TODO: show all the patients
				String pID = System.console().readLine("Which patient do you want to view or edit (Enter patientId)?");
				//TODO:  Show that patient's info
				String editOrView  = System.console().readLine("Would you like to edit the patient's info or view his/her Plan and Allergin (edit/view)?");
				while(!(editOrView.contentEquals("edit") || editOrView.contentEquals("view")))
					editOrView  = System.console().readLine("Would you like to edit the patient's info or view his/her Plan and Allergin (edit/view)?");
				if(editOrView.contentEquals("view"))
				{
					//use the patientID to view the Allergin and Plan
					String edit = System.console().readLine("Edit Allergin or Plan (yes/no)");
					while(!(edit.contentEquals("yes") || edit.contentEquals("no")))
						edit  = System.console().readLine("Edit Allergin or Plan (yes/no)");
					
					if(edit.contentEquals("yes"))
					{
						System.out.println("\nWhich Column/Input do you want to change (if there is space in your input add %20 instead): ");
						String stringOfChanges = System.console().readLine();
						String[] arrayOfChanges = stringOfChanges.split(" ");
					}
					else
					{
						
					}
				}
				else
				{
					//edit the patients info
				}
				
			}
			//Admin
			else
			{
				
				String adminID = System.console().readLine("Enter Admin ID: ");

				
			}
			
			}
		}

