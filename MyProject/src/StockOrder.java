
public class StockOrder 
{

	private int OrderID;
	private String Status;
	private String Supplier;
	private int Price;
	private String DatePlaced;
	
	
	
	public int getOrderID() 
	{ return OrderID; }
	public void setOrderID(int orderID) 
	{ OrderID = orderID; }
	public String getStatus() 
	{ return Status; }
	public void setStatus(String status) 
	{ Status = status; }
	public  String getSupplier() 
	{ return Supplier; }
	public void setSupplier(String supplier) 
	{ Supplier = supplier; }
	public int getPrice() 
	{ return Price; }
	public void setPrice(int price) 
	{ Price = price; }
	public String getDatePlaced() 
	{ return DatePlaced; }
	public void setDatePlaced(String datePlaced) 
	{ DatePlaced = datePlaced; }
	
	public StockOrder(int orderID, String status, String supplier, int price, String datePlaced) 
	{
		super();
		OrderID = orderID;
		Status = status;
		Supplier = supplier;
		Price = price;
		DatePlaced = datePlaced;
	}
	
	
	
	
}
