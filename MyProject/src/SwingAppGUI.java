import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SwingAppGUI extends JFrame 
{
	private JFrame mainmenu;
	private JLabel headerLabel;
	private JPanel controlPanel;
	private JTextArea textarea;
		
	public SwingAppGUI(){mainmenu();}

	public static void main(String[] args) 
	{
		SwingAppGUI sD = new SwingAppGUI();
	}
	
	private void Getcustomerinfo(int b)
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = DatabaseConnectionManager.accessDBOrder();	
		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		ArrayList<String> moreList = new ArrayList<String>();
		
		int lengthorders = orders.size();
		int lengthorderlines = orderlines.size();
		int lengthproducts = products.size();
		
		
		for(int i=0; i<lengthorders; i++)
		{
			if(i == b)
			{
				for(int j=0; j<lengthorderlines; j++)
				{
					if(orderlines.get(j).getOrderID() == orders.get(i).getOrderID())
					{
						for(int k=0; k<lengthproducts; k++)
						{
							if(orderlines.get(j).getProductID() == products.get(k).getProductID())
							{ 
								moreList.add("OrderID: " + orders.get(i).getOrderID()  + "\t" + "Product ID:  " + orderlines.get(j).getProductID()  + "\t" + "Product:  " + products.get(k).getProductName()  + "\t" + "Quantity:  " + orderlines.get(j).getQuantity()  + "\t" + "Warehouse Location:  " + products.get(k).getLocationInWarehouse());
							}
						}
					}
				}
			}
		}
		int length = moreList.size();
		textarea.setText(null);
		for (int i=0; i<length; i++)
		{
			System.out.println(moreList.get(i));
			textarea.append(moreList.get(i) + "\n");
		}
	}

	private void Getstockinfo(int b)
	{
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();
		stockorders = DatabaseConnectionManager.accessDBStockOrder();	
		ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
		stockorderlines = DatabaseConnectionManager.accessDBStockOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		ArrayList<String> moreList = new ArrayList<String>();
		
		int lengthstockorders = stockorders.size();
		int lengthstockorderlines = stockorderlines.size();
		int lengthproducts = products.size();
		
		
		for(int i=0; i<lengthstockorders; i++)
		{
			if(i == b)
			{
				for(int j=0; j<lengthstockorderlines; j++)
				{
					if(stockorderlines.get(j).getOrderID() == stockorders.get(i).getOrderID())
					{
						for(int k=0; k<lengthproducts; k++)
						{
							if(stockorderlines.get(j).getProductID() == products.get(k).getProductID())
							{ 
								moreList.add("OrderID: " + stockorders.get(i).getOrderID()  + "\t" + "Product ID:  " + stockorderlines.get(j).getProductID()  + "\t" + "Product:  " + products.get(k).getProductName()  + "\t" + "Quantity:  " + stockorderlines.get(j).getQuantity()  + "\t");
							}
						}
					}
				}
			}
		}
		int length = moreList.size();
		textarea.setText(null);
		for (int i=0; i<length; i++)
		{
			System.out.println(moreList.get(i));
			textarea.append(moreList.get(i) + "\n");
		}
	}

//--------------------------------------------------------------------------------------------------
	private void mainmenu() 
	{
		mainmenu = new JFrame("Order Management System");
		mainmenu.setSize(1000, 1000);
		mainmenu.setLayout(new GridLayout(3, 1));
		headerLabel = new JLabel("", JLabel.CENTER);
				
		mainmenu.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent windowEvent)     
			{ System.exit(0); }
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 2));
		mainmenu.add(headerLabel);
		mainmenu.add(controlPanel);
		mainmenu.setVisible(true);

		
		headerLabel.setText("Main Menu");
		headerLabel.setText("Please select Customer or Stock orders");
		headerLabel.setFont(new Font ("Times New Roman", Font.PLAIN, 30));
		JButton CustomerOrdersButton = new JButton("Customer Orders");
		JButton StockOrdersButton = new JButton("Stock Orders");
		CustomerOrdersButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		StockOrdersButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		CustomerOrdersButton.setActionCommand("CustomerOrders");
		StockOrdersButton.setActionCommand("StockOrders");
		CustomerOrdersButton.addActionListener(new BCL());
		StockOrdersButton.addActionListener(new BCL());
		controlPanel.add(CustomerOrdersButton);
		controlPanel.add(StockOrdersButton);
		mainmenu.setVisible(true);

	}
//--------------------------------------------------------------------------------------------------
	private class BCL implements ActionListener 
	{
		 @Override
		 public void actionPerformed (ActionEvent ae) 
		 {
			 String command = ae.getActionCommand();
			 switch (command) 
			 {
		  		case "CustomerOrders": 
		  			CustomerOrders();
		  			break;
		  			
		  		case "StockOrders": 
		  			StockOrders();
		  			break;
		  			
		  		case "Go Back":
		  			mainmenu();
		  			break;
			 }
		 }
	}
//--------------------------------------------------------------------------------------------------
	
	public void CustomerOrders()
	{
		mainmenu.setVisible(false);
		
		JFrame customermenu = new JFrame();
		JLabel customerLabel = new JLabel();
		JPanel customercontrolPanel = new JPanel();
		customermenu = new JFrame("Customer Orders");
		
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = DatabaseConnectionManager.accessDBOrder();	
		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		int lengthorders = orders.size();
		int lengthorderlines = orderlines.size();
		int lengthproducts = products.size();
		
		customermenu.setSize(1000, 1000);
		customermenu.setLayout(new GridLayout(3, 3));
		customerLabel = new JLabel("", JLabel.CENTER);
		customermenu.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent windowEvent)     
			{ System.exit(0); }
		});
		customercontrolPanel = new JPanel();
		customercontrolPanel.setLayout(new GridLayout(3, 2));
		customermenu.add(customerLabel);
		customermenu.add(customercontrolPanel);
		
		customerLabel.setText("Customer Orders");
		customerLabel.setFont(new Font ("Times New Roman", Font.PLAIN, 30));

		String[] customerinfo= new String[lengthorders];
		
		for (int i=0; i<lengthorders; i++)
		{
			customerinfo[i] = ("OrderID: " + orders.get(i).getOrderID()  + "    Status: " + orders.get(i).getStatus()  + "    Employee: " + orders.get(i).getEmployee() + "    Price: " + orders.get(i).getPrice() + "    DatePlaced: " + orders.get(i).getDatePlaced() + "    Checkedout: " + orders.get(i).isCheckedOut());			
		}
		JList customerinfolist = new JList(customerinfo);
		customercontrolPanel.add(customerinfolist);
		
		
		textarea = new JTextArea(5, 20);
		customercontrolPanel.add(textarea);
		textarea.setEditable(false);
		
		customerinfolist.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent arg0)
			{
				if (!arg0.getValueIsAdjusting())
				{
					Getcustomerinfo(customerinfolist.getSelectedIndex());
				}
			}
		});
		
		JButton goback = new JButton("Go Back");
		goback.addActionListener(new BCL());
		goback.setActionCommand("Go Back");
		customercontrolPanel.add(goback);
		
		
	
		customermenu.setVisible(true);
	}	

	public void StockOrders()
	{
		mainmenu.setVisible(false);
		JFrame stockmenu = new JFrame();
		JLabel stockLabel = new JLabel();
		JPanel stockcontrolPanel = new JPanel();
		stockmenu = new JFrame("Customer Orders");
		
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();
		stockorders = DatabaseConnectionManager.accessDBStockOrder();	
		ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
		stockorderlines = DatabaseConnectionManager.accessDBStockOrderLine();
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		
		int lengthstockorders = stockorders.size();
		int lengthstockorderlines = stockorderlines.size();
		int lengthproducts = products.size();
		
		stockmenu.setSize(1000, 1000);
		stockmenu.setLayout(new GridLayout(3, 1));
		stockLabel = new JLabel("", JLabel.CENTER);
		stockmenu.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent windowEvent)     
			{ System.exit(0); }
		});
		stockcontrolPanel = new JPanel();
		stockcontrolPanel.setLayout(new GridLayout(3, 2));
		stockmenu.add(stockLabel);
		stockmenu.add(stockcontrolPanel);
		
		stockLabel.setText("Stock Orders");
		stockLabel.setFont(new Font ("Times New Roman", Font.PLAIN, 30));

		String[] stockinfo= new String[lengthstockorders];
		
		for (int i=0; i<lengthstockorders; i++)
		{
			stockinfo[i] = ("OrderID: " + stockorders.get(i).getOrderID()  + "    Status: " + stockorders.get(i).getStatus()  + "    Supplier: " + stockorders.get(i).getSupplier() + "    Price: " + stockorders.get(i).getPrice() + "    DatePlaced: " + stockorders.get(i).getDatePlaced());			
		}
		JList stockinfolist = new JList(stockinfo);
		stockcontrolPanel.add(stockinfolist);
		
		textarea = new JTextArea(5, 20);
		stockcontrolPanel.add(textarea);
		textarea.setEditable(false);
		
		stockinfolist.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent arg0)
			{
				if (!arg0.getValueIsAdjusting())
				{
					Getcustomerinfo(stockinfolist.getSelectedIndex());
				}
			}
		});
		
		
		
		JButton goback = new JButton("Go Back");
		goback.addActionListener(new BCL());
		goback.setActionCommand("Go Back");
		stockcontrolPanel.add(goback);
		
		stockmenu.setVisible(true);
	}

}






