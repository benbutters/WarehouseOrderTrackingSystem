
import java.util.ArrayList;
import java.util.Scanner;

																			//this class is designed to act as a user interface for a warehouse order tracking system for NB Gardens

public class InterfaceWarehouse 
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("--- MAIN MENU ---");
		System.out.println("Customer Orders or Stock Orders?:");
		System.out.println("1 - Customer Orders");
		System.out.println("2 - Stock Orders");
		String p = scanner.next();
		
		switch (p)
		{
		case "1":
			CustomerOrder();
			break;
		case "2":
			StockOrder();
			break;
		default:
			System.out.println("Invalid response please try again");
			System.out.println("---------------------------------------------------------------------------------------");
			main(null);
			break;				
		}
		scanner.close();
	}
		
	public static void CustomerOrder()
	{	
		
		System.out.println("--- CUSTOMER ORDERS ---");
		
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = DatabaseConnectionManager.accessDBOrder();	

		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();

		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();


		int lengthorders = orders.size();
		int lengthorderlines = orderlines.size();
		int lengthproducts = products.size();

		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("List of all current customer orders: ");

		for (int i=0; i<lengthorders; i++)
		{
			System.out.println("OrderID:  " + orders.get(i).getOrderID() + "\t" + "Status:  " + orders.get(i).getStatus() + "\t" + "Employee:  " + orders.get(i).getEmployee() + "\t" + "Price:  " + orders.get(i).getPrice() + "\t" + "DatePlaced:  " + orders.get(i).getDatePlaced() + "\t" + "Checkedout:  " + orders.get(i).isCheckedOut());			
		}

		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Enter an OrderID number to select that order: ");
		Scanner scanner= new Scanner(System.in);
		int x = scanner.nextInt();
						
		for (int i=0; i<lengthorders; i++)
		{
			if (x == orders.get(i).getOrderID())
			{
				System.out.println("---------------------------------------------------------------------------------------");
				System.out.println("The details of this order are: ");
				for (int j=0; j<lengthorderlines; j++)
				{
					if (orders.get(i).getOrderID() == orderlines.get(j).getOrderID())
					{
						for (int k=0; k<lengthproducts; k++)
						{
							if (orderlines.get(j).getProductID() == products.get(k).getProductID())
							{
								System.out.println("OrderID: " + orders.get(i).getOrderID()  + "\t" + "Product ID:  " + orderlines.get(j).getProductID()  + "\t" + "Product:  " + products.get(k).getProductName()  + "\t" + "Quantity:  " + orderlines.get(j).getQuantity()  + "\t" + "Warehouse Location:  " + products.get(k).getLocationInWarehouse());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Would you like to check in or check out this order?");
								System.out.println("1 - Check in");
								System.out.println("2 - Check out");
								System.out.println("3 - Return to main menu");
								String y = scanner.next();

								if (y.equals("1"))
								{
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("You have selected:  1 - Check in");
									CheckInOrder(x);
															}
								if (y.equals("2"))
								{
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("You have selected:  2 - Check out");
									CheckOutOrder(x);
								}
								if (y.equalsIgnoreCase("3"))
								{
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("You have selected:  3 - Return to main menu");
									main(null);
								}
							}
						}
					}
				}
			}
		}
		scanner.close(); 
	}

	public static void CheckOutOrder(int x)
	{
		System.out.println("--- CHECKING OUT AN ORDER ---");
		
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = DatabaseConnectionManager.accessDBOrder();	

		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();

		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		ArrayList<employee> employees = new ArrayList<employee>();
		employees = DatabaseConnectionManager.accessDBEmployee();


		int lengthorders = orders.size();
		int lengthorderlines = orderlines.size();
		int lengthproducts = products.size();
		
		for (int i=0; i<lengthorders; i++)
		{
			if (x == orders.get(i).getOrderID())
			{
				for (int j=0; j<lengthorderlines; j++)
				{
					if (x == orderlines.get(j).getOrderID())
					{
						for (int k=0; k<lengthproducts; k++)
						{
							if (orderlines.get(j).getProductID() == products.get(k).getProductID())
							{
		
								if (orders.get(i).isCheckedOut() == false)
								{
									Scanner scanner = new Scanner(System.in);
									System.out.println("Please enter your employee ID");
									System.out.println("1 - Ben");
									System.out.println("2 - Frank");
									System.out.println("3 - Jim");
									System.out.println("4 - John");
									System.out.println("5 - Sergio");
									System.out.println("6 - Steve");
									String o = scanner.next();
									switch(o)
									{
									case "1":
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("You have now checked out order:" + orders.get(i).getOrderID());
										DatabaseConnectionManager.updatecheckedout(1, orders.get(i).getOrderID());
										DatabaseConnectionManager.updateemployee(employees.get(0).getEmployeename(), orders.get(i).getOrderID());
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("Next user");
										main(null);
										break;
									case "2":
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("You have now checked out order:" + orders.get(i).getOrderID());
										DatabaseConnectionManager.updatecheckedout(1, orders.get(i).getOrderID());
										DatabaseConnectionManager.updateemployee(employees.get(1).getEmployeename(), orders.get(i).getOrderID());
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("Next user");
										main(null);
										break;
									case "3":
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("You have now checked out order:" + orders.get(i).getOrderID());
										DatabaseConnectionManager.updatecheckedout(1, orders.get(i).getOrderID());
										DatabaseConnectionManager.updateemployee(employees.get(2).getEmployeename(), orders.get(i).getOrderID());
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("Next user");
										main(null);
										break;
									case "4":
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("You have now checked out order:" + orders.get(i).getOrderID());
										DatabaseConnectionManager.updatecheckedout(1, orders.get(i).getOrderID());
										DatabaseConnectionManager.updateemployee(employees.get(3).getEmployeename(), orders.get(i).getOrderID());
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("Next user");
										main(null);
										break;
									case "5":
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("You have now checked out order:" + orders.get(i).getOrderID());
										DatabaseConnectionManager.updatecheckedout(1, orders.get(i).getOrderID());
										DatabaseConnectionManager.updateemployee(employees.get(5).getEmployeename(), orders.get(i).getOrderID());
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("Next user");
										main(null);
										break;
									case "6":
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("You have now checked out order:" + orders.get(i).getOrderID());
										DatabaseConnectionManager.updatecheckedout(1, orders.get(i).getOrderID());
										DatabaseConnectionManager.updateemployee(employees.get(6).getEmployeename(), orders.get(i).getOrderID());
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("Next user");
										main(null);
										break;
									default:
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("You have entered an invalid employee id");
										CheckOutOrder(x);
										break;
									}
									scanner.close();
								}
								if (orders.get(i).isCheckedOut() == true)
								{
									System.out.println("Order " + orders.get(i).getOrderID() + " is already checked out");
									CustomerOrder();
								}
								else 
									CheckOutOrder(x);
							}
						}
					}
				}
			}
		}
	}
	
	public static void CheckInOrder(int x)
	{
		System.out.println("--- CHECKING IN AN ORDER ---");
		
		Scanner scanner= new Scanner(System.in);
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = DatabaseConnectionManager.accessDBOrder();	

		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();

		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		int lengthorders = orders.size();
		int lengthorderlines = orderlines.size();
		int lengthproducts = products.size();


		for (int i=0; i<lengthorders; i++)
		{
			if (x == orders.get(i).getOrderID())
			{
				for (int j=0; j<lengthorderlines; j++)
				{
					if (x == orderlines.get(j).getOrderID())
					{
						for (int k=0; k<lengthproducts; k++)
						{
							if (orderlines.get(j).getProductID() == products.get(k).getProductID())
							{
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Please update the order status to the relevant option: ");								 
								System.out.println("1 - Placed");												
								System.out.println("2 - Picked");
								System.out.println("3 - Packed");
								System.out.println("4 - AwaitingCourier");
								System.out.println("5 - Delivery");
								System.out.println("6 - Completed");		
								String response = scanner.next();

								switch(response)
								{
								case "1":
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Placed");
									DatabaseConnectionManager.updatestatus("Placed", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "2":
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Picked");
									DatabaseConnectionManager.updatestatus("Picked", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "3":
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Packed");
									DatabaseConnectionManager.updatestatus("Packed", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "4":
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to AwaitingCourier");
									DatabaseConnectionManager.updatestatus("AwaitingCourier", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "5":
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Delivery");
									DatabaseConnectionManager.updatestatus("Delivery", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "6":
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Completed");
									DatabaseConnectionManager.updatestatus("Completed", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								default:
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Invalid response");
									CheckInOrder(x);
									break;
								}
								System.out.println("Thank you for updating the order management system");
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");
								System.out.println("---------------------------------------------------------------------------------------");
								main(null);
							}
						}
					}
				}
			}
		}
		scanner.close();
	}

	public static void StockOrder()
	{
		System.out.println("--- STOCK ORDERS ---");
		
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();
		stockorders = DatabaseConnectionManager.accessDBStockOrder();
		
		int lengthstockorder = stockorders.size();
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("The current stock order details are: ");
		
		for (int i=0; i<lengthstockorder; i++)
		{
			System.out.println("OrderID: " + stockorders.get(i).getOrderID() + "\t" + "  Status: " + stockorders.get(i).getStatus() + "\t" + "  Supplier: " + stockorders.get(i).getSupplier() + "\t" + "  Price: " + stockorders.get(i).getPrice() + "\t" + "  Date Placed: " + stockorders.get(i).getDatePlaced());
		}
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Would you like to edit the status of a current order or place a new one?");
		System.out.println("1 - Edit current order");
		System.out.println("2 - Place new order");
		System.out.println("3 - Return to main menu");
		Scanner scanner = new Scanner(System.in);
		String response = scanner.next();
		
		switch (response)
		{
		case "1":
			System.out.println("---------------------------------------------------------------------------------------");
			editstockorderstatus();
			break;
		case "2":
			System.out.println("---------------------------------------------------------------------------------------");
			createnewstockorder();
			break;
		case "3":
			System.out.println("---------------------------------------------------------------------------------------");
			main(null);
			break;
		default:
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("Invalid response please try again");
			StockOrder();
			break;
		}
		scanner.close();
	}
	
	public static void editstockorderstatus()
	{
		System.out.println("--- UPDATE STOCK ORDER PROGRESS ---");
		
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();
		stockorders = DatabaseConnectionManager.accessDBStockOrder();
		
		ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
		stockorderlines = DatabaseConnectionManager.accessDBStockOrderLine();
		
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		System.out.println("Please enter the stock order id that you wish to see further details of: ");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.next();
		
		int lengthstockorder = stockorders.size();
		int lengthstockorderlines = stockorderlines.size();
		int lengthproducts = products.size();
		
		for (int i=0; i < lengthstockorder; i++)
		{
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("The details of this order are: ");
			for (int j=0; j<lengthstockorderlines; j++)
			{
				if (id.equals(stockorderlines.get(j).getOrderID()))
				{
					for (int k=0; k<lengthproducts; k++)
					{
						if (stockorderlines.get(j).getProductID() == products.get(k).getProductID())
						{
							System.out.println("OrderID: " + stockorderlines.get(j).getOrderID()  + "\t" + "Product ID:  " + stockorderlines.get(j).getProductID()  + "\t" + "Product:  " + products.get(k).getProductName()  + "\t" + "Quantity:  " + stockorderlines.get(j).getQuantity()  + "\t" + "Warehouse Location:  " + products.get(k).getLocationInWarehouse());
							System.out.println("---------------------------------------------------------------------------------------");
							
							System.out.println("Please enter the new status of the stock order");
							System.out.println("1 - Order Placed");
							System.out.println("2 - Awaiting Delivery");
							System.out.println("3 - Processing ");
							System.out.println("4 - Shelved");
							String response = scanner.next();
							
							switch(response)
							{
							case "1":
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("the stock order's status has been updated");
								DatabaseConnectionManager.updatestockstatus(response, stockorders.get(i).getOrderID());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");
								main(null);
								break;
							case "2":
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("the stock order's status has been updated");
								DatabaseConnectionManager.updatestockstatus(response, stockorders.get(i).getOrderID());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");
								main(null);
								break;
							case "3":
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("the stock order's status has been updated");
								DatabaseConnectionManager.updatestockstatus(response, stockorders.get(i).getOrderID());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");
								main(null);
								break;
							case "4":
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("the stock order's status has been updated");
								DatabaseConnectionManager.updatestockstatus(response, stockorders.get(i).getOrderID());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");
								main(null);
								break;
							default:
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Invalid response, please input again");
								editstockorderstatus();
								break;
							}			
						}
					}
				}
			}
		}
		
		scanner.close();
	}
	
	public static void createnewstockorder()
	{
		System.out.println("--- PLACE NEW STOCK ORDER ---");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Please enter the new OrderID: ");
		int OrderID = scanner.nextInt();
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Please enter the supplier for this order: ");
		String Supplier = scanner.next();
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Please enter the total cost of this order: ");
		int Price = scanner.nextInt();
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Please enter the current date and time: ");
		System.out.println("yyyy-mm-dd hh:mm:ss");
		String DatePlaced = scanner.next();
		
		
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String x = dateFormat.format(date);*/
			
		DatabaseConnectionManager.createnewstockorder(OrderID, "Placed", Supplier, Price, DatePlaced);
		scanner.close();
		
		System.out.println("Thank you for creating a new stock order");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("next user");
		main(null);
	}
	
		
}



