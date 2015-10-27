
public class employee 
{
	//define attributes
	private String employeename;
	private int employeeid;
	
	//generate getters and setters
	public String getEmployeename() 
	{ return employeename; }
	public void setEmployeename(String employeename) 
	{ this.employeename = employeename; }
	public int getEmployeeid() 
	{ return employeeid; }
	public void setEmployeeid(int employeeid) 
	{ this.employeeid = employeeid; }
	
	
	//generate constructor class
	public employee(String employeename, int employeeid) 
	{
		super();
		this.employeename = employeename;
		this.employeeid = employeeid;
	}
	
	
}
