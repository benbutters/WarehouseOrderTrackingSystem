
public class Product 
{
	//define attributes
	private int ProductID;
	private String ProductName;
	private String Description;
	private String LocationInWarehouse;
	
	//generate getters and setters
	public int getProductID() 
	{ return ProductID; }
	public void setProduct(int productID) 
	{ ProductID = productID; }
	public String getProductName() 
	{ return ProductName; }
	public void setProductName(String productName) 
	{ ProductName = productName; }
	public String getDescription() 
	{ return Description; }
	public void setDescription(String description) 
	{ Description = description; }
	public String getLocationInWarehouse() 
	{ return LocationInWarehouse; }
	public void setLocationInWarehouse(String locationInWarehouse) 
	{ LocationInWarehouse = locationInWarehouse; }
	
	
	//generate constructor
	public Product(int productID, String productName, String description,String locationInWarehouse) 
	{
		super();
		ProductID = productID;
		ProductName = productName;
		Description = description;
		LocationInWarehouse = locationInWarehouse;
	}
}
