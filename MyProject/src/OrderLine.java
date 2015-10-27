
public class OrderLine 
{
	//define attributes
	private int OrderID;
	private int ProductID;
	private int Quantity;
	
	//generate getters and setters
	public int getOrderID() 
	{ return OrderID; }
	public void setOrderID(int orderID) 
	{ OrderID = orderID; }
	public int getProductID() 
	{ return ProductID; }
	public void setProductID(int productID) 
	{ ProductID = productID; }
	public int getQuantity() 
	{ return Quantity; }
	public void setQuantity(int quantity) 
	{ Quantity = quantity; }

	//generate constructor class
	public OrderLine(int orderID, int productID, int quantity) 
	{	
		super();
		OrderID = orderID;
		ProductID = productID;
		Quantity = quantity;
	}
	
}
