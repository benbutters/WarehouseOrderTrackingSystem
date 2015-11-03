
import java.util.ArrayList;
import java.util.Scanner;

																			//this class is designed to act as a user interface for a warehouse order tracking system for NB Gardens

public class InterfaceWarehouse 
{
	public static void main(String[] args)									//main method asks user to decide between customer and stock orders
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
			CustomerOrder();												//case 1 is for if they want to access customer orders, and launches the CustomerOrder method
			break;
		case "2":
			StockOrder();													//case 2 is for if they want to access stock orders, and launches the StockOrder method
			break;
		default:
			System.out.println("Invalid response please try again");		//in the case of not inputting 1 or 2, prints invalid response and relaunches main method
			System.out.println("---------------------------------------------------------------------------------------");
			main(null);
			break;				
		}
		scanner.close();
	}
		
	public static void CustomerOrder()										//CustomerOrder method allows user to update the status of any customer order, check an order in or out, 
	{	
		
		System.out.println("--- CUSTOMER ORDERS ---");
		
		ArrayList<Order> orders = new ArrayList<Order>();					//set up 3 arraylists for customer orders, orderlines and products. Uses the DatabaseConnectionManager Class to get data from database
		orders = DatabaseConnectionManager.accessDBOrder();	
		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();


		int lengthorders = orders.size();									//defines lengths of arrays so they can be looped over
		int lengthorderlines = orderlines.size();
		int lengthproducts = products.size();

		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("List of all current customer orders: ");

		for (int i=0; i<lengthorders; i++)									//loops over orders array and prints out the full contents for user to see
		{
			System.out.println("OrderID:  " + orders.get(i).getOrderID() + "\t" + "Status:  " + orders.get(i).getStatus() + "\t" + "Employee:  " + orders.get(i).getEmployee() + "\t" + "Price:  " + orders.get(i).getPrice() + "\t" + "DatePlaced:  " + orders.get(i).getDatePlaced() + "\t" + "Checkedout:  " + orders.get(i).isCheckedOut());			
		}

		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Enter an OrderID number to select that order: ");
		Scanner scanner= new Scanner(System.in);							//set up scanner
		int x = scanner.nextInt();											//ask for scanner input to be the users desired OrderID that they wish to know more about
						
		for (int i=0; i<lengthorders; i++)									//loops over orders array
		{
			if (x == orders.get(i).getOrderID())							//where the scanner input matches the orders array OrderID
			{
				System.out.println("---------------------------------------------------------------------------------------");
				System.out.println("The details of this order are: ");
				for (int j=0; j<lengthorderlines; j++)						//loops over orderlines array
				{															//where the OrderID matches between the orders and orderlines arrays
					if (orders.get(i).getOrderID() == orderlines.get(j).getOrderID())		
					{														
						for (int k=0; k<lengthproducts; k++)				//loop over the products array
						{													//where the productID matches between the orderlines and products arrays
							if (orderlines.get(j).getProductID() == products.get(k).getProductID())
							{												//print the orderlines info and product info associated with the inputted OrderID
								System.out.println("OrderID: " + orders.get(i).getOrderID()  + "\t" + "Product ID:  " + orderlines.get(j).getProductID()  + "\t" + "Product:  " + products.get(k).getProductName()  + "\t" + "Quantity:  " + orderlines.get(j).getQuantity()  + "\t" + "Warehouse Location:  " + products.get(k).getLocationInWarehouse());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Would you like to check in or check out this order?");
								System.out.println("1 - Check in");
								System.out.println("2 - Check out");
								System.out.println("3 - Return to main menu");
								String y = scanner.next();					//next scanner input should be 1 or 2 based on whether the user would like to check in or check out the order

								if (y.equals("1"))
								{
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("You have selected:  1 - Check in");
									CheckInOrder(x);						//if 1 is selected then the CheckInOrder method is launched
															}
								if (y.equals("2"))
								{	
									if (orders.get(i).isCheckedOut() == true)	//check if the order is already checked out
									{
										System.out.println("Order " + orders.get(i).getOrderID() + " is already checked out");
										CustomerOrder();					//revert back to CustomerOrder method if already checked out 
									}
									else 
										System.out.println("---------------------------------------------------------------------------------------");
										System.out.println("You have selected:  2 - Check out");
										CheckOutOrder(x);					//if 2 is selected and not already checked out then the CheckOutOrder method is launched
								}
								if (y.equalsIgnoreCase("3"))
								{
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("You have selected:  3 - Return to main menu");
									main(null);								//if 3 is selected then the main method is launched and the user is returned to the main menu
								}
							}
						}
					}
				}
			}
		}
		scanner.close(); 													//close scanner
	}

	public static void CheckOutOrder(int x)									//the CheckOutOrder method gets the user (a warehouse employee) to input their EmployeeID and checks out their selected order, the argument used x is the same OrderID they have entered previously
	{
		System.out.println("--- CHECKING OUT AN ORDER ---");
		
		ArrayList<Order> orders = new ArrayList<Order>();					//sets up arrays for orders orderlines and products using the DatabaseConnectionManager Class
		orders = DatabaseConnectionManager.accessDBOrder();	
		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		ArrayList<employee> employees = new ArrayList<employee>();
		employees = DatabaseConnectionManager.accessDBEmployee();


		int lengthorders = orders.size();									//defines lengths of arrays again
		int lengthorderlines = orderlines.size();
		int lengthproducts = products.size();
		
		for (int i=0; i<lengthorders; i++)									//iterates over the arrays using the same conditions as before (where x=orders(OrderID)=orderlines(OrderID), and orderlines(ProductID)=products(ProductID)
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
								{											//ask the user to input their EmployeeID as string o
									Scanner scanner = new Scanner(System.in);
									System.out.println("Please enter your employee ID");
									System.out.println("1 - Ben");			
									System.out.println("2 - Frank");
									System.out.println("3 - Jim");
									System.out.println("4 - John");
									System.out.println("5 - Sergio");
									System.out.println("6 - Steve");
									String o = scanner.next();
									switch(o)								//the cases are identical apart from selecting a different Employeename, and they all check out the order, using the updatecheckedout and updateemployee methods from the DatabaseConnectionManager Class
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
										CheckOutOrder(x);					//if the user does not enter a valid EmployeeID they will be redirected to the start of the CheckOutOrder method
										break;
									}
									scanner.close();						//close scanner
								}
								
							}
						}
					}
				}
			}
		}
	}
	
	public static void CheckInOrder(int x)									//the CheckInOrder method gets the user to update the orders status after operating on an order and it will then be checked in
	{																		// uses x as an argument where x is the previously inputted OrderID they want to view
		System.out.println("--- CHECKING IN AN ORDER ---");
		
		Scanner scanner= new Scanner(System.in);							//setup scanner

		ArrayList<Order> orders = new ArrayList<Order>();					//define orders, orderlines and products arraylists using the methods in the DatabaseConnectionManager class
		orders = DatabaseConnectionManager.accessDBOrder();	
		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		int lengthorders = orders.size();									//define length of arrays
		int lengthorderlines = orderlines.size();
		int lengthproducts = products.size();


		for (int i=0; i<lengthorders; i++)									//iterate over all the the arrays using the same conditions as before (where x=orders(OrderID)=orderlines(OrderID), and orderlines(ProductID)=products(ProductID)
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
							{												//ask the user to select the new status of the order, with switch statement dependent on response
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
								case "1":									//updates status to placed, employee to noone and checkedout to checkedin
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Placed");
									DatabaseConnectionManager.updatestatus("Placed", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "2":									//updates status to picked, employee to noone and checkedout to checkedin
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Picked");
									DatabaseConnectionManager.updatestatus("Picked", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "3":									//updates status to packed, employee to noone and checkedout to checkedin
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Packed");
									DatabaseConnectionManager.updatestatus("Packed", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "4":									//updates status to awaiting courier, employee to noone and checkedout to checkedin
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to AwaitingCourier");
									DatabaseConnectionManager.updatestatus("AwaitingCourier", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "5":									//updates status to delivery, employee to noone and checkedout to checkedin
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Delivery");
									DatabaseConnectionManager.updatestatus("Delivery", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								case "6":									//updates status to completed, employee to noone and checkedout to checkedin
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Order status updated to Completed");
									DatabaseConnectionManager.updatestatus("Completed", orders.get(i).getOrderID());
									DatabaseConnectionManager.updatecheckedout(0, orders.get(i).getOrderID());
									DatabaseConnectionManager.updateemployee("N/A", orders.get(i).getOrderID());
									break;
								default:									//if the response does not correspond to one of the availible options, print invalid response
									System.out.println("---------------------------------------------------------------------------------------");
									System.out.println("Invalid response");
									CheckInOrder(x);
									break;
								}
								System.out.println("Thank you for updating the order management system");
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");			//after updating the system return to the main menu by using the main method
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

	public static void StockOrder()											//StockOrder method determines whether the user would like to update an order or start a new order and directs them to the relevant method
	{
		System.out.println("--- STOCK ORDERS ---");
		
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();	//define stockorders array using method from the DatabaseConnectionManager class
		stockorders = DatabaseConnectionManager.accessDBStockOrder();
		
		int lengthstockorder = stockorders.size();							//define length of stockorders array
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("The current stock order details are: ");
		
		for (int i=0; i<lengthstockorder; i++)								//iterate over stockorders and print the basic details of every current stock order
		{
			System.out.println("OrderID: " + stockorders.get(i).getOrderID() + "\t" + "  Status: " + stockorders.get(i).getStatus() + "\t" + "  Supplier: " + stockorders.get(i).getSupplier() + "\t" + "  Price: " + stockorders.get(i).getPrice() + "\t" + "  Date Placed: " + stockorders.get(i).getDatePlaced());
		}
																			
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Would you like to edit the status of a current order or place a new one?");
		System.out.println("1 - Edit current order");
		System.out.println("2 - Place new order");
		System.out.println("3 - Return to main menu");
		Scanner scanner = new Scanner(System.in);							//set up scanner
		String response = scanner.next();									//use input of scanner to determine switch statement
		
		switch (response)
		{
		case "1":
			System.out.println("---------------------------------------------------------------------------------------");
			editstockorderstatus();											//if 1 selected redirect to editstockorderstatus method to change the stock order's status
			break;
		case "2":
			System.out.println("---------------------------------------------------------------------------------------");
			createnewstockorder();											//if 2 selected redirect to createnewstockorder method to create a new stock order
			break;
		case "3":
			System.out.println("---------------------------------------------------------------------------------------");
			main(null);														//if 3 selected redirect back to main menu using main method
			break;
		default:
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("Invalid response please try again");
			StockOrder();													//if they dont enter 1,2 or 3 then restart the method StockOrder
			break;
		}
		scanner.close();													//close scanner
	}
	
	public static void editstockorderstatus()								//this method allows the user to change the order status of a given stock order
	{
		System.out.println("--- UPDATE STOCK ORDER PROGRESS ---");
		
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();	//defines 3 arrays, for stock orders, stock orderlines, and products
		stockorders = DatabaseConnectionManager.accessDBStockOrder();
		ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
		stockorderlines = DatabaseConnectionManager.accessDBStockOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		System.out.println("Please enter the stock order id that you wish to see further details of: ");
		Scanner scanner = new Scanner(System.in);							//set up scanner
		String id = scanner.next();											//get the user to input the orderID of the order they wish to view more info of
		
		int lengthstockorder = stockorders.size();							//defines the length of the arrays stockorders, stockorderlines, and products
		int lengthstockorderlines = stockorderlines.size();
		int lengthproducts = products.size();
		
		for (int i=0; i < lengthstockorder; i++)							//loop over stockorders
		{
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("The details of this order are: ");
			for (int j=0; j<lengthstockorderlines; j++)						//loop over stockorderlines
			{
				if (id.equals(stockorderlines.get(j).getOrderID()))			//where the stockorderline shares the OrderID with the input/ stockorder
				{
					for (int k=0; k<lengthproducts; k++)					//loop over the products array
					{
						if (stockorderlines.get(j).getProductID() == products.get(k).getProductID())
						{													//where productID is the same as on the stockorderline
							System.out.println("OrderID: " + stockorderlines.get(j).getOrderID()  + "\t" + "Product ID:  " + stockorderlines.get(j).getProductID()  + "\t" + "Product:  " + products.get(k).getProductName()  + "\t" + "Quantity:  " + stockorderlines.get(j).getQuantity()  + "\t" + "Warehouse Location:  " + products.get(k).getLocationInWarehouse());
							System.out.println("---------------------------------------------------------------------------------------");
																			//print out the details of the selected order
							System.out.println("Please enter the new status of the stock order");
							System.out.println("1 - Order Placed");
							System.out.println("2 - Awaiting Delivery");
							System.out.println("3 - Processing ");
							System.out.println("4 - Shelved");
							String response = scanner.next();				//scanner response is the new status of the stock order
							
							switch(response)								//switch statement dependent on response
							{
							case "1":
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("the stock order's status has been updated");
								DatabaseConnectionManager.updatestockstatus(response, stockorders.get(i).getOrderID());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");			//updates the order's status and starts the main method again
								main(null);
								break;
							case "2":
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("the stock order's status has been updated");
								DatabaseConnectionManager.updatestockstatus(response, stockorders.get(i).getOrderID());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");			//updates the order's status and starts the main method again
								main(null);
								break;
							case "3":
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("the stock order's status has been updated");
								DatabaseConnectionManager.updatestockstatus(response, stockorders.get(i).getOrderID());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");			//updates the order's status and starts the main method again
								main(null);
								break;
							case "4":
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("the stock order's status has been updated");
								DatabaseConnectionManager.updatestockstatus(response, stockorders.get(i).getOrderID());
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Next user");			//updates the order's status and starts the main method again
								main(null);
								break;
							default:
								System.out.println("---------------------------------------------------------------------------------------");
								System.out.println("Invalid response, please input again");
								editstockorderstatus();						//if response is not 1-4 then the editstockorderstatus method is run again
								break;
							}			
						}
					}
				}
			}
		}
		
		scanner.close();													//close scanner
	}
	
	public static void createnewstockorder()								//this method enables the user to create an order for new stock
	{
		System.out.println("--- PLACE NEW STOCK ORDER ---");
		
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();	//defines 3 arrays, for stock orders, stock orderlines, and products
		stockorders = DatabaseConnectionManager.accessDBStockOrder();
		ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
		stockorderlines = DatabaseConnectionManager.accessDBStockOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		int lengthstockorder = stockorders.size();							//defines the length of the arrays stockorders, stockorderlines, and products
		
		
		Scanner scanner = new Scanner(System.in);							//set up scanner
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Please enter the new OrderID: ");
		int OrderID = scanner.nextInt();									//ask the user to input an OrderID for the new order
		
		for(int i=0; i<lengthstockorder; i++)								//loop over existing stock order IDs to check new one is not already used
		{
			if (OrderID == stockorders.get(i).getOrderID())
			{
				System.out.println("This OrderID is already in use please try again");
				createnewstockorder();										//if already in use then the createnewstockorder method is restarted
			}
		}
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Please enter the supplier code for this order: ");
		System.out.println("Supplier 1 = Harry Styles/1D supplier");
		System.out.println("Supplier 2 = Justin Bieber supplier");
		System.out.println("Supplier 3 = Colm distribution gnome supplier");
		String Supplier = scanner.next();									//ask for the supplier's code
				
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Please enter the current date and time: ");
		System.out.println("yyyy-mm-dd");
		String DatePlaced = scanner.next();									//ask for the current date of the order
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Please enter how many different types of product you would like to order");
		int numberofproducts = scanner.nextInt();								//ask for the number of types of product they want to order
		
		int Price = 0;
		
		for(int i=0; i<numberofproducts; i++)
		{																	//loop until the number of types of products is reached
			System.out.println("Select which type of gnome you want to order");
			System.out.println("1 - Harry Styles gnome");
			System.out.println("2 - Justin Bieber gnome");
			System.out.println("3 - Colm Pool gnome");
			System.out.println("4 - Colm Pissed gnome");					//for each loop round, ask what type of product
			int x = scanner.nextInt();
			System.out.println("what is the desired quantity?");			//and how many
			int y = scanner.nextInt();
			switch (x)
			{
			case 1:
				Price = Price + (25 * y);									//calculate the price using a switch based on which product they are ordering
				break;
			case 2:
				Price = Price + (50 * y);
				break;
			case 3:
				Price = Price + (5 * y);
				break;
			case 4:
				Price = Price + (45 * y);
				break;
			}
																			//use method to create new orderlines in the orderlines table
			DatabaseConnectionManager.createnewstockorderline(OrderID,x,y);
		}
		
		DatabaseConnectionManager.createnewstockorder(OrderID, "Placed", Supplier, Price, DatePlaced);
																			//use method defined in DatabaseConnectionManager to update database
		scanner.close();													//close scanner
		
		System.out.println("Thank you for creating a new stock order");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("next user");									//print thank you and start main method again
		main(null);
	}
	
		
}



