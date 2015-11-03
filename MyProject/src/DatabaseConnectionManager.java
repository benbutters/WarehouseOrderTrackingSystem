import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseConnectionManager 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.50.15.9:3306/john";
	static final String USER = "root";
	static final String PASS = "netbuilder";

	public static void main(String[] args)
	{
		accessDBOrder();														//returns arraylists read from the SQL database
		accessDBOrderLine();
		accessDBProduct();
		accessDBEmployee();
	}

	//------------------------------------------------------------------------------------------------------------------------------------------------
	//CUSTOMER METHODS BELOW

	public static ArrayList<Order> accessDBOrder()								//returns arraylist of orders read from SQL database
	{		
		ArrayList<Order> customerorders = new ArrayList<Order>();				
		Connection conn = null;
		Statement stmt = null;
		try 																	//try statement connects to database
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn =
					DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			stmt = conn.createStatement();										//define what columns to import from the SQL database, in the same order as the order class
			String sql2 = "SELECT OrderID, Status, Employee, Price, DatePlaced, CheckedOut FROM customerorders";
			ResultSet rs = stmt.executeQuery(sql2);

			while (rs.next()) 
			{

				int OrderID = rs.getInt("OrderID");								//read in all the columns to an int or string variable
				String Status = rs.getString("Status");
				String Employee = rs.getString("Employee");
				int Price = rs.getInt("Price");
				String DatePlaced = rs.getString("DatePlaced");
				boolean CheckedOut = rs.getBoolean("CheckedOut");

				//start new object of order class to contain the read information
				Order customerorder = new Order(OrderID, Status, Employee, Price, DatePlaced, CheckedOut);


				customerorders.add(customerorder);								//add each order object to the arraylist

			}
			rs.close();
		}


		catch (SQLException sqle)												//catch exceptions
		{	sqle.printStackTrace();	} 
		catch (Exception e)
		{	e.printStackTrace();	} 
		finally 																//have finally statement to complete the body
		{	try
		{	if (stmt != null)
			stmt.close();
		}
		catch (SQLException se)
		{ }
		try 
		{	if (conn != null)
			conn.close();
		}
		catch (SQLException se)
		{	se.printStackTrace();
		}
		}
		//System.out.println("Goodbye!");
		return customerorders;
	}

	public static ArrayList<OrderLine> accessDBOrderLine()						//returns arraylist of orderlines read from SQL database
	{		
		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		Connection conn = null;
		Statement stmt = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn =
					DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}

		try
		{	
			stmt = conn.createStatement();										//define what columns to import from the SQL database, in the same order as the order class
			String sql2 = "SELECT OrderID, ProductID, Quantity FROM orderline";
			ResultSet rs = stmt.executeQuery(sql2);

			while (rs.next()) 
			{

				int OrderID = rs.getInt("OrderID");								//read in all the columns to an int or string
				int ProductID = rs.getInt("ProductID");
				int Quantity = rs.getInt("Quantity");

				//start new object of order class to contain the read information
				OrderLine orderline = new OrderLine(OrderID, ProductID, Quantity);
				

				orderlines.add(orderline);										//add each order object to the arraylist

			}
			rs.close();
		}

		

		catch (SQLException sqle)
		{	sqle.printStackTrace();	} 
		catch (Exception e)
		{	e.printStackTrace();	} 
		finally 
		{	try
		{	if (stmt != null)
			stmt.close();
		}
		catch (SQLException se)
		{ }
		try 
		{	if (conn != null)
			conn.close();
		}
		catch (SQLException se)
		{	se.printStackTrace();
		}
		}
		//System.out.println("Goodbye!");
		return orderlines;
	}

	public static ArrayList<Product> accessDBProduct()							//returns arraylist of products read from SQL database
	{		
		ArrayList<Product> products = new ArrayList<Product>();
		Connection conn = null;
		Statement stmt = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn =
					DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			stmt = conn.createStatement();										//define what columns to import from the SQL database, in the same order as the order class
			String sql2 = "SELECT ProductID, ProductName, Description, LocationInWarehouse FROM product";
			ResultSet rs = stmt.executeQuery(sql2);

			while (rs.next()) 
			{

				int ProductID = rs.getInt("ProductID");							//read in all the columns to an int or string
				String ProductName = rs.getString("ProductName");
				String Description = rs.getString("Description");
				String LocationInWarehouse = rs.getString("LocationInWarehouse");

				//start new object of order class to contain the read information
				Product product = new Product(ProductID, ProductName, Description, LocationInWarehouse);

				products.add(product);											//add each order object to the arraylist

			}
			rs.close();
		}


		catch (SQLException sqle)
		{	sqle.printStackTrace();	} 
		catch (Exception e)
		{	e.printStackTrace();	} 
		finally 
		{	try
		{	if (stmt != null)
			stmt.close();
		}
		catch (SQLException se)
		{ }
		try 
		{	if (conn != null)
			conn.close();
		}
		catch (SQLException se)
		{	se.printStackTrace();
		}
		}
		//System.out.println("Goodbye!");
		return products;
	}

	public static ArrayList<employee> accessDBEmployee()				//method to get the employee information from the database
	{		
		ArrayList<employee> employees = new ArrayList<employee>();	
		Connection conn = null;
		Statement stmt = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn =
					DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			stmt = conn.createStatement();								//define what columns to import from the SQL database, in the same order as the order class
			String sql2 = "SELECT employeename, employeeid FROM employee";
			ResultSet rs = stmt.executeQuery(sql2);

			while (rs.next()) 
			{

				String employeename = rs.getString("employeename");		//read in all the columns to an int or string
				int employeeid = rs.getInt("employeeid");

				employee employee = new employee(employeename, employeeid);

				employees.add(employee);								//add each order object to the arraylist

			}
			rs.close();
		}


		catch (SQLException sqle)
		{	sqle.printStackTrace();	} 
		catch (Exception e)
		{	e.printStackTrace();	} 
		finally 
		{	try
		{	if (stmt != null)
			stmt.close();
		}
		catch (SQLException se)
		{ }
		try 
		{	if (conn != null)
			conn.close();
		}
		catch (SQLException se)
		{	se.printStackTrace();
		}
		}
		return employees;
	}

	public static void updatestatus(String status, int OrderID)			//method to update the customer orders table with the new status of a customer order
	{

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName( "com.mysql.jdbc.Driver");							//connect to database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();										//update statement as string in java executes update command in sql 
			String sql3 = "UPDATE customerorders " + "SET Status = '"+status+"' WHERE OrderID = '"+OrderID+"' ";
			stmt.executeUpdate(sql3);

		} catch (SQLException sqle) {											//catch exceptions
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	} 

	public static void updatecheckedout(int CheckedOut, int OrderID)			// method to update an order to have checked out status
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName( "com.mysql.jdbc.Driver");	
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			String sql3 = "UPDATE customerorders " + "SET CheckedOut = '"+CheckedOut+"' WHERE OrderID = '"+OrderID+"' ";
			stmt.executeUpdate(sql3);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	} 

	public static void updateemployee(String employeename, int OrderID)				//method to update the customer orders table in the database with the employee working on a checked out order
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql3 = "UPDATE customerorders " + "SET Employee = '"+employeename+"' WHERE OrderID = '"+OrderID+"' ";
			stmt.executeUpdate(sql3);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}


	//-------------------------------------------------------------------------------------------------------------------------------------------------
	//STOCK METHODS BELOW
	//
	// repeat same methods as above but for stock orders, with updating and inputting new orders
	

	public static ArrayList<StockOrder> accessDBStockOrder()	//method to get the relevant information from a stock order
	{	
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();
		Connection conn = null;
		Statement stmt = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");						//connect to database
			conn =
					DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			stmt = conn.createStatement();								//define what columns to import from the SQL database, in the same order as the order class
			String sql2 = "SELECT OrderID, Status, Supplier, Price, DatePlaced FROM stockorders";
			ResultSet rs = stmt.executeQuery(sql2);

			while (rs.next()) 
			{

				int OrderID = rs.getInt("OrderID");						//read in all the columns to an int or string
				String Status = rs.getString("Status");
				String Supplier = rs.getString("Supplier");
				int Price = rs.getInt("Price");
				String DatePlaced = rs.getString("DatePlaced");

				StockOrder stockorder = new StockOrder(OrderID, Status, Supplier, Price, DatePlaced);


				stockorders.add(stockorder);						//add each order object to the arraylist

			}
			rs.close();
		}


		catch (SQLException sqle)
		{	sqle.printStackTrace();	} 
		catch (Exception e)
		{	e.printStackTrace();	} 
		finally 
		{	try
		{	if (stmt != null)
			stmt.close();
		}
		catch (SQLException se)
		{ }
		try 
		{	if (conn != null)
			conn.close();
		}
		catch (SQLException se)
		{	se.printStackTrace();
		}
		}
		//System.out.println("Goodbye!");
		return stockorders;
	}

	public static ArrayList<StockOrderLine> accessDBStockOrderLine()	//method for getting the information from a desired stock order line
	{		
		ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
		Connection conn = null;
		Statement stmt = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn =
					DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			stmt = conn.createStatement();								//define what columns to import from the SQL database, in the same order as the order class
			String sql2 = "SELECT OrderID, ProductID, Quantity FROM stockorderline";
			ResultSet rs = stmt.executeQuery(sql2);

			while (rs.next()) 
			{

				int OrderID = rs.getInt("OrderID");		//read in all the columns to an int or string
				int ProductID = rs.getInt("ProductID");
				int Quantity = rs.getInt("Quantity");

				StockOrderLine stockorderline = new StockOrderLine(OrderID, ProductID, Quantity);

				stockorderlines.add(stockorderline);						//add each order object to the arraylist

			}
			rs.close();
		}


		catch (SQLException sqle)
		{	sqle.printStackTrace();	} 
		catch (Exception e)
		{	e.printStackTrace();	} 
		finally 
		{	try
		{	if (stmt != null)
			stmt.close();
		}
		catch (SQLException se)
		{ }
		try 
		{	if (conn != null)
			conn.close();
		}
		catch (SQLException se)
		{	se.printStackTrace();
		}
		}
		//System.out.println("Goodbye!");
		return stockorderlines;
	}

	public static int accessDBgetstock(int productid)
	{
		int stocklevel = 0;
		Connection conn = null;
		Statement stmt = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn =
					DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			stmt = conn.createStatement();								//define what columns to import from the SQL database, in the same order as the order class
			String sql2 = "SELECT ProductID, StockLevel FROM product";
			ResultSet rs = stmt.executeQuery(sql2);

			while (rs.next()) 
			{
				int ProductID = rs.getInt("ProductID");
				
				if (productid == ProductID)
				{
					stocklevel = rs.getInt("StockLevel");		//read in all the columns to an int or string
				}

			}
			rs.close();
		}


		catch (SQLException sqle)
		{	sqle.printStackTrace();	} 
		catch (Exception e)
		{	e.printStackTrace();	} 
		finally 
		{	try
		{	if (stmt != null)
			stmt.close();
		}
		catch (SQLException se)
		{ }
		try 
		{	if (conn != null)
			conn.close();
		}
		catch (SQLException se)
		{	se.printStackTrace();
		}
		}
		//System.out.println("Goodbye!");
		return stocklevel;
	}
		
	public static void updatestockstatus(String status, int OrderID)		//method for updating a stock order's status
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql3 = "UPDATE stockorders " + "SET Status = '"+status+"' WHERE OrderID = '"+OrderID+"' ";
			stmt.executeUpdate(sql3);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void updatestocklevel(int StockLevel, int ProductID)		//method for updating a stock order's status
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql3 = "UPDATE products " + "SET StockLevel = '"+StockLevel+"' WHERE ProductID = '"+ProductID+"' ";
			stmt.executeUpdate(sql3);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public static void createnewstockorder(int OrderID, String Status, String Supplier, int Price, String DatePlaced)		//method for creating a new stock order
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();								//insert statement with sql syntax to put the relevant new values in the new order
			String sql = "INSERT INTO stockorders "+"VALUES('"+OrderID+"', '"+Status+"', '"+Supplier+"', '"+String.valueOf(Price)+"', '"+DatePlaced+"')";
			stmt.executeUpdate(sql);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void createnewstockorderline(int OrderID, int ProductID, int Quantity)		//method for creating a new stock order
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName( "com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();								//insert statement with sql syntax to put the relevant new values in the new order
			String sql = "INSERT INTO stockorderline "+"VALUES('"+OrderID+"', '"+String.valueOf(ProductID)+"', '"+String.valueOf(Quantity)+"')";
			stmt.executeUpdate(sql);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) { }
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
