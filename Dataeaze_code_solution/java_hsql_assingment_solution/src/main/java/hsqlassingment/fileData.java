package hsqlassingment;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class fileData implements fileUtils {
Connection con=null;	
public void connectDatabase() throws ClassNotFoundException, SQLException {
	//Connection con=null;
	Class.forName("org.hsqldb.jdbc.JDBCDriver");
	con=DriverManager.getConnection("jdbc:hsqldb:mem://localhost/testdb", "SA", "");
}

boolean flag = false;

	public void inserData() throws SQLException{
		try {	
			connectDatabase();
			this.connectDatabase();
			

			if (flag == false)
			{		
			String sql1 = "create table startup (Sr_No int, Date varchar(30), "
					+ "Startup_Name varchar(30),Industry_Vertical varchar(30),"
					+ "SubVertical varchar(30),City varchar(30),Investors_Name varchar(30),"
					+ "InvestmentnType varchar(30),Amount_in_USD varchar(30), Remarks varchar(30));"
					+ "";
			
			 con.createStatement().execute(sql1);
			 
			 flag = true;
			
			}
			
			  String sql = "insert into startup (Sr_No, Date, Startup_Name, Industry_Vertical,SubVertical,City,Investors_Name,InvestmentnType,Amount_in_USD,Remarks)"
			  		+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			  

	            PreparedStatement statement = con.prepareStatement(sql);
	            FileReader filereader=new FileReader("/home/sunbeam/Downloads/startup.csv");
	           
	            CSVReader lineReader=new CSVReader(filereader);
	            String[] lineText = null;
	 
	            lineReader.readNext();  //skip header line
	            while ((lineText = lineReader.readNext()) != null)
	            {
	              
	            	String [] data=lineText;
	                int Sr_No  = Integer.parseInt(data[0].trim());
	                String Date = data[1];
	                String Startup_Name = data[2];
	                String Industry_Vertical = data[3];
	                String SubVertical  = data[4];
	                String City = data[5];
	                String Investors_Name = data[6];
	                String InvestmentnType = data[7];
	                String Amount_in_USD  = data[8];
	                String Remarks = data[9];
	                
	 
	                statement.setLong(1, Sr_No);
	                statement.setString(2, Date);
	                
	                statement.setString(3, Startup_Name);
	                statement.setString(4, Industry_Vertical);
	                
	                statement.setString(5, SubVertical);
	                statement.setString(6, City);
	                
	                statement.setString(7, Investors_Name);
	                statement.setString(8, InvestmentnType);
	                
	                statement.setString(9, Amount_in_USD);
	                statement.setString(10, Remarks);
	
	                statement.addBatch();
	               
	                int[] count=statement.executeBatch();
	            }
	            
	 
	            lineReader.close();
	           
	            statement.close();

		}
		catch(Exception e){
			System.out.println(e);
		}
		
	     con.commit();
		con.close();


	}

	public void retriveData() throws ClassNotFoundException, SQLException, IOException {
		CSVWriter writer_q1=new CSVWriter(new FileWriter("/home/sunbeam/Desktop/ans/question_1.csv"));
		CSVWriter writer_q2=new CSVWriter(new FileWriter("/home/sunbeam/Desktop/ans/question_2.csv"));
		
		connectDatabase();
		String query = "select count (*) from startup where City = 'pune'";
	      //Executing the query
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(query);
	      //Retrieving the result
	      rs.next();
	      String count = rs.getString(1);
	      String [] data= {count};
  	      writer_q1.writeNext(data);
  	      writer_q1.flush();


	}

	

}
