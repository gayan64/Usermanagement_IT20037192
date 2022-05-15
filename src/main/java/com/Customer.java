package com; 
import java.sql.*; 

public class Customer 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer_database?useSSL=false","root",""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		//String output = cusObj.insertCustomer(cusname, accno, mobile, add); 
		public String insertCustomer(String cusname, String accno, String mobile, String add)
		{ 
		 String output = ""; 
		try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database"; 
		 } 
		 // create a prepared statement
		 String query = " insert into customer (`userid`,`username`,`accountno`,`phonenumber`,`address`)" + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, cusname); 
		 preparedStmt.setString(3, accno); 
		 preparedStmt.setString(4, mobile); 
		 preparedStmt.setString(5, add); 
		  //execute the statement
		 preparedStmt.execute(); 
			con.close(); 
			
			String newCustomers = readCustomers(); 
			output = "{\"status\":\"success\",\"data\":\""+newCustomers+"\"}"; 
		 } 
		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer.\"}"; 
			System.err.println(e.getMessage()); 
		 } 
		return output; 
		}
		
		
		
			public String readCustomers()		
			{ 
				 String output = ""; 
				try
				 { 
				 Connection con = connect(); 
				 if (con == null) 
				 { 
				 return "Error while connecting to the database for reading."; 
				 }
				 // Prepare the html table to be displayed
				 output ="<table border='3'>"
				 		+ "<tr>"
					 		+"<th>Customer Name</th>" 
					 		+"<th>Account number</th>"
					 		+ "<th>Mobile</th>" 
					 		+ "<th>Address</th>" 
					 		+ "<th>Update</th>"
					 		+ "<th>Remove</th>"
				 		+ "</tr>"; 
				 String query = "select * from customer"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
				 String userid = Integer.toString(rs.getInt("userid")); 
				 String cusname = rs.getString("username"); 
				 String accno = rs.getString("accountno"); 
				 String mobile = rs.getString("phonenumber"); 
				 String add = rs.getString("address"); 

				
				 // Add a row into the html table
				 output += "<tr><td>" + cusname + "</td>"; 
				 output += "<td>" + accno + "</td>"; 
				output += "<td>" + mobile + "</td>";
				output += "<td>" + add + "</td>";

				
				
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-userid='" + userid + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-userid='" + userid + "'></td></tr>"; 
				 
				 } 
				 
				 con.close(); 
				 // Complete the html table
				 output += "</table>"; 
				 } 
				catch (Exception e) 
				 { 
				 output = "Error while reading the Account Details."; 
				 System.err.println(e.getMessage()); 
				 } 
				return output; 
				}
			
			public String updateCustomer(String ID, String cusname, String accno, String mobile, String add){



				String output = "";

				try{
				Connection con = connect();
				if (con == null){
				return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE customer SET username=?,accountno=?,phonenumber=?,address=? WHERE userid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, cusname);
				preparedStmt.setString(2, accno);
				preparedStmt.setString(3, mobile);
				preparedStmt.setString(4, add);
				preparedStmt.setInt(5, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newCustomers = readCustomers(); 
				output = "{\"status\":\"success\",\"data\":\""+newCustomers+"\"}"; 

				}

				catch (Exception e){

					output = "{\"status\":\"error\",\"data\":\"Error while updating the customer.\"}"; 

				System.err.println(e.getMessage());

				}

				return output;
				}
			
			
			
			public String deleteCustomer(String userid) 
			{ 
				 String output = ""; 
				try
				 { 
				 Connection con = connect(); 
				 if (con == null) 
				 { 
				 return "Error while connecting to the database for deleting."; 
				 } 
				 // create a prepared statement
				 String query = "delete from customer where userid=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setString(1, userid); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
					con.close(); 
					String newCustomers = readCustomers(); 
					 output = "{\"status\":\"success\",\"data\":\""+newCustomers+"\"}"; 
				 } 
				catch (Exception e) 
				 { 
					output = "{\"status\":\"error\",\"data\":\"Error while deleting the customer.\"}";
					System.err.println(e.getMessage()); 
				 } 
				return output; 
				}


			
			
			
} 
