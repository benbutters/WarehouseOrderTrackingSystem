
public class StockOrderLine
{

	private int OrderID;
	private int ProductID;
	private int Quantity;
	
	
	
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
	
	public StockOrderLine(int orderID, int productID, int quantity2) 
	{
		super();
		OrderID = orderID;
		ProductID = productID;
		Quantity = quantity2;
	}
	
	
	
}
