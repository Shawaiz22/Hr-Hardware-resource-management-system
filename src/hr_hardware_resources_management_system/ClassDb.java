package hr_hardware_resources_management_system;
import java.sql.*;
import java.util.*;


public class ClassDb {
		
		private String driver="com.mysql.cj.jdbc.Driver",user="root",password="",
				dburl="jdbc:mysql://localhost:3306/hrmanagement";
		
		private Connection con=null;

		
		// Function for establishing connection with sql database through jdbc driver
		
		public void openConnection() {
			try {
				Class.forName(driver);
				con=DriverManager.getConnection(dburl,user,password);

			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		
		// Function for terminating/ closing connection
		
		public void closeConnection() {
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		// Defining functions for query
		
			//1. Function for dml queries(insert, update, delete)
		
		public String dmlQuery(String query) {
			String res="";
			try {
				openConnection();
				
				Statement stmt = con.createStatement();
				int result=stmt.executeUpdate(query);
//				res=result+" record/s affected in database";
			}
			catch(Exception e) {
				res=e.toString();
			}
			return res;
		}
		
			// 2. Function for select query
		
		public String selectQuery(String query) {
			String res="";
			try {
				
				openConnection();
				
				Statement stmt = con.createStatement();
				ResultSet rs= stmt.executeQuery(query);
				ResultSetMetaData rsmd= rs.getMetaData();
				int columncount=rsmd.getColumnCount();
				
				System.out.println("=============================================================================");
					while(rs.next()==true) {		
						for(int i=1;i<=columncount;i++) {
							res+=rs.getString(i)+"\t ";
						}
						res+="\n";
					}
					
					rs.close();
					stmt.close();
					
			}
			catch(Exception e) {
				res=e.toString();
			}
			closeConnection();
			return res;
		}
		
			//3. Function which fetches the value of 'currentstatus' column from table
			
		public String getCurrentStatus(String selectquery) {
			String currentstatus="not found";

			try {
			openConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(selectquery);
			if(rs.next()==true)
			currentstatus=rs.getString("currentstatus");

			rs.close();
			stmt.close();

			}
			catch(Exception e) {
			System.out.println(e);
			}
			finally {
			closeConnection();
			}
			return currentstatus;
			}
		
		// 4. Function which verifies presence of data/record according to the given query
		
		public boolean checkRecordExists(String selectquery) {
			boolean res=false;
			try {
				openConnection();
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(selectquery);
				if(rs.next()==true) res=true;
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
			finally {
				closeConnection();
			}
			return res;
			
		}
		
	

// 5. In this function, First the query is written to fetch machine details according to that particular employee by taking employee id as input.
// then column name whose value is required is passed in the argument and it returns that column value from the query result.
		/* Query Example: 
		 * String query="select * from machingbooking where emp_id=? "
		 * int machineid=obj.getIdByQuery(query, "machine_id");
		 */
	
		public int getIdByQuery(String selectquery, String idcolumnname) {
			int res=-1;
			try {
				openConnection();
				Statement stmt=con.createStatement();							
				ResultSet rs=stmt.executeQuery(selectquery);
				if(rs.next()==true) {
					res=rs.getInt(idcolumnname);
				}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			finally {
				closeConnection();
			}
			return res;
		}
		


		public Map<String, String> getOneRecordValue(String selectquery) {
		
		//	6. Map interface for getting the older record value 
			Map<String, String> map=new HashMap<>();
			try {
				openConnection();
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(selectquery);
				ResultSetMetaData rsmd=rs.getMetaData();
				int columncount=rsmd.getColumnCount();
				
				if(rs.next()==true) {
					for(int i=1;i<=columncount;i++) {
						
						String colname=rsmd.getColumnName(i).toLowerCase();
						String colvalue=rs.getString(i);
						map.put(colname,  colvalue);
					}
				}
				
				rs.close();
				stmt.close();
				
				
			}
			catch(Exception e ){
				System.out.println(e.toString());
			}
			finally {
				closeConnection();
			}
			return map;
			
		}
	
}