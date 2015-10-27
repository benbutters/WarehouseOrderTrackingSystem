// class order for managing customer and stock orders in the warehouse of NB gardens
public class Order 
{
	// define attributes
	private int OrderID;
	private String Status;
	private String Employee;
	private int Price;
	private String DatePlaced;
	private boolean CheckedOut;

	// define methods (getters and setters) for attributes
	public int getOrderID() 
	{ return OrderID; }
	public void setOrderID(int orderID) 
	{ OrderID = orderID; }
	public String getStatus() 
	{ return Status; }
	public void setStatus(String status) 
	{ Status = status; }
	public String getEmployee() 
	{ return Employee; }
	public void setEmployee(String employee) 
	{ Employee = employee; }
	public int getPrice() 
	{ return Price; }
	public void setPrice(int price) 
	{ Price = price; }
	public String getDatePlaced()
	{ return DatePlaced; }
	public void setDatePlaced(String datePlaced) 
	{ DatePlaced = datePlaced; }
	public boolean isCheckedOut() 
	{ return CheckedOut; }
	public void setCheckedOut(boolean checkedOut) 
	{ CheckedOut = checkedOut; }




	// define default object
	public Order()
	{
		OrderID = "";
		Status = "";
		Employee = "";
		Price = 0;
		DatePlaced = "";
		CheckedOut = false;
	}


	//define constructor object
	public Order(int orderID, String status, String employee, int price, String datePlaced, boolean checkedOut) 
	{
		super();
		OrderID = orderID;
		Status = status;
		Employee = employee;
		Price = price;
		DatePlaced = datePlaced;
		CheckedOut = checkedOut;
	}


}