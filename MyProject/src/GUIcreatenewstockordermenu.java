import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class GUIcreatenewstockordermenu extends JFrame
{
	 private JFrame mainFrame;
	 private JLabel headerLabel;
	 private JPanel controlPanel1;
	 private JPanel controlPanel2;
	 private JPanel controlPanel3;
	 private JPanel controlPanel4;
	 private JPanel controlPanel5;
	 private JPanel controlPanel6;
	 private JTextField orderID;
	 private JTextField quantity1;
	 private JTextField quantity2;
	 private JTextField quantity3;
	 private JTextField quantity4;
	 private JList products1;
	 private JList products2;
	 private JList products3;
	 private JList products4;
	 private JTextField OrderID;
	 private Date date;
	 private JLabel instruction;
	 private JPanel instructionPanel;
	 
	 

	public GUIcreatenewstockordermenu()
	{
		prepareGUI();
	}

	public static void main(String[] args) 
	{
	 GUIcreatenewstockordermenu sD = new GUIcreatenewstockordermenu();
	 sD.showEvent();
	}

	private void prepareGUI() 
	{
		 mainFrame = new JFrame("Warehouse Order Tracking System");
		 mainFrame.setSize(2000,2000);
		 mainFrame.setLayout(new GridLayout(9, 1));
		 headerLabel = new JLabel("", JLabel.CENTER);
		 
		String instructions = "Please select a new order ID number, you will not be able to use a ID number aready in use. Select all the products and quantity that you want, click Create Order Lines and Create New Order to update the order database.";
			 
		instruction = new JLabel("",JLabel.CENTER);
		instruction.setSize(350, 100);
		instruction.setText(instructions);
		 
		 
		 mainFrame.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent windowEvent){System.exit(0);}});
		 instructionPanel = new JPanel();
		 instructionPanel.setLayout(new GridLayout());
		 controlPanel1 = new JPanel();
		 controlPanel1.setLayout(new GridLayout());
		 controlPanel2 = new JPanel();
		 controlPanel2.setLayout(new GridLayout());
		 controlPanel3 = new JPanel();
		 controlPanel3.setLayout(new GridLayout());
		 controlPanel4 = new JPanel();
		 controlPanel4.setLayout(new GridLayout());
		 controlPanel5 = new JPanel();
		 controlPanel5.setLayout(new GridLayout());
		 controlPanel6 = new JPanel();
		 controlPanel6.setLayout(new GridLayout());
		 mainFrame.add(headerLabel);
		 mainFrame.add(instructionPanel);
		 mainFrame.add(controlPanel1);
		 mainFrame.add(controlPanel2);
		 mainFrame.add(controlPanel3);
		 mainFrame.add(controlPanel4);
		 mainFrame.add(controlPanel5);
		 mainFrame.add(controlPanel6);
		 mainFrame.setVisible(true);
	}

	private void showEvent() 
	{
		
		date = new Date();
		
		OrderID = new JTextField();
		OrderID.setText("Enter new order ID number here");
		
		quantity1 = new JTextField();
		quantity1.setText("0");
		quantity2 = new JTextField();
		quantity2.setText("0");
		quantity3 = new JTextField();
		quantity3.setText("0");
		quantity4 = new JTextField();
		quantity4.setText("0");
		
		String[] product = {"N/A","Harry Styles","Justin Bieber","Colm Pool","Colm Pissed"};
		products1 = new JList(product);
		products2 = new JList(product);
		products3 = new JList(product);
		products4 = new JList(product);
		
		
		headerLabel.setText("Create New Order");
		JButton mainButton = new JButton("Main Menu");
		JButton stockorderButton = new JButton("Stock Orders");
		JButton createorderlineButton1 = new JButton("Create Order Lines");
		JButton createorderButton = new JButton("Create New Order");
		
		mainButton.setActionCommand("Main Menu");
		stockorderButton.setActionCommand("Stock Orders");
		createorderlineButton1.setActionCommand("Create Order Lines");
		createorderButton.setActionCommand("Create New Order");
		
		mainButton.addActionListener(new BCL());
		stockorderButton.addActionListener(new BCL());
		createorderlineButton1.addActionListener(new BCL());

		createorderButton.addActionListener(new BCL());
				
		instructionPanel.add(instruction);
		controlPanel1.add(OrderID);
		controlPanel2.add(products1);
		controlPanel2.add(quantity1);
		controlPanel1.add(createorderlineButton1);
		controlPanel3.add(products2);
		controlPanel3.add(quantity2);
		controlPanel4.add(products3);
		controlPanel4.add(quantity3);
		controlPanel5.add(products4);
		controlPanel5.add(quantity4);
		controlPanel6.add(mainButton);
		controlPanel6.add(stockorderButton);
		controlPanel6.add(createorderButton);
		
		mainFrame.setVisible(true);}

	private class BCL implements ActionListener 
	{

		
		@Override
		 public void actionPerformed (ActionEvent ae) 
		 {
			ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();
		    stockorders = DatabaseConnectionManager.accessDBStockOrder();
			ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
			stockorderlines = DatabaseConnectionManager.accessDBStockOrderLine();
			int lengthstockorders = stockorders.size();				
			int lengthstockorderlines = stockorderlines.size();
			int productid = 0;
			String command = ae.getActionCommand();
			
			switch (command) 
			{
		 
			case "Main Menu": 
				mainFrame.setVisible(false);
				GUImainmenu.main(null);
				break;
			case "Stock Orders":
				mainFrame.setVisible(false);
				GUIstockordermenu.main(null);
				break;
			case "Create Order Lines":			   			   
  		   		for(int i=0; i<lengthstockorders; i++)								//loop over existing stock order IDs to check new one is not already used
  		   		{
  		   			if (Integer.parseInt(OrderID.getText()) == stockorders.get(i).getOrderID())
  		   			{
  		   				OrderID.setText("This OrderID is already in use please try again");
  		   			}
  		   			else
  		   			{
  		   				switch(products1.getSelectedIndex())
  		   				{
  		   				case 0:
							break;
						case 1:
							productid = 1;
							DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
							break;
						case 2:
							productid = 2;
							DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
							break;
						case 3:
							productid = 3;
							DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
							break;
						case 4:
							productid = 4;
							DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
							break;
  		   				}
  		   				
  		   				switch(products2.getSelectedIndex())
  		   				{
  		   				case 0:
  		   					break;
  		   				case 1:
  		   					productid = 1;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 2:
  		   					productid = 2;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 3:
  		   					productid = 3;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 4:
  		   					productid = 4;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				}
  		   				
  		   				switch(products3.getSelectedIndex())
  		   				{
  		   				case 0:
  		   					break;
  		   				case 1:	
  		   					productid = 1;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 2:
  		   					productid = 2;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 3:
  		   					productid = 3;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 4:
  		   					productid = 4;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				}
  		   				
  		   				switch(products4.getSelectedIndex())
  		   				{
  		   				case 0:
  		   					break;
  		   				case 1:
  		   					productid = 1;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 2:
  		   					productid = 2;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 3:
  		   					productid = 3;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				case 4:
  		   					productid = 4;
  		   					DatabaseConnectionManager.createnewstockorderline(Integer.parseInt(OrderID.getText()), productid, Integer.parseInt(quantity1.getText()));
  		   					break;
  		   				}					
  		   			}
  		   		}
  		   		break;
			case "Create New Order":
			   String Supplier = new String();
			   int Price = 0;
			   String[] product = {"Harry Styles","Justin Bieber","Colm Pool","Colm Pissed"};
			   
			  	   
			   switch (products1.getSelectedIndex())
			   {
			   case 0:
				   Supplier = Supplier;
				   break;
			   case 1:
				   Supplier = ("1- 1D Supplier");
				   Price = Price + (Integer.parseInt(quantity1.getText())* 25);
				   break;
			   case 2:
				   Supplier = ("2- Bieber Supplier");
				   Price = Price + (Integer.parseInt(quantity1.getText())* 40);
				   break;
			   case 3:
				   Supplier = ("3- Colm Supplier");
				   Price = Price + (Integer.parseInt(quantity1.getText())* 5);
				   break;
			   case 4:
				   Supplier = ("3- Colm Supplier");
				   Price = Price + (Integer.parseInt(quantity1.getText())* 60);
				   break;
			   default:
				   break;
			   }
			   			  
			   switch (products2.getSelectedIndex())
			   {
			   case 0:
				   Supplier = Supplier;
				   break;
			   case 1:
				   Supplier = (Supplier+" & 1- 1D Supplier");
				   Price = Price + (Integer.parseInt(quantity2.getText())* 25);
				   break;
			   case 2:
				   Supplier = (Supplier+" & 2- Bieber Supplier");
				   Price = Price + (Integer.parseInt(quantity2.getText())* 40);
				   break;
			   case 3:
				   Supplier = (Supplier+" & 3- Colm Supplier");
				   Price = Price + (Integer.parseInt(quantity2.getText())* 5);
				   break;
			   case 4:
				   Supplier = (Supplier+" & 3- Colm Supplier");
				   Price = Price + (Integer.parseInt(quantity2.getText())* 60);
				   break;
			   default:
				   break;
			   }
			   			   
			   switch (products3.getSelectedIndex())
			   {
			   case 0:
				   Supplier = Supplier;
				   break;
			   case 1:
				   Supplier = (Supplier+" & 1- 1D Supplier");
				   Price = Price + (Integer.parseInt(quantity3.getText())* 25);
				   break;
			   case 2:
				   Supplier = (Supplier+" & 2- Bieber Supplier");
				   Price = Price + (Integer.parseInt(quantity3.getText())* 40);
				   break;
			   case 3:
				   Supplier = (Supplier+" & 3- Colm Supplier");
				   Price = Price + (Integer.parseInt(quantity3.getText())* 5);
				   break;
			   case 4:
				   Supplier = (Supplier+" & 3- Colm Supplier");
				   Price = Price + (Integer.parseInt(quantity3.getText())* 60);
				   break;
			   default:
				   break;
			   }
			   
			   switch (products4.getSelectedIndex())
			   {
			   case 0:
				   Supplier = Supplier;
				   break;
			   case 1:
				   Supplier = (Supplier+" & 1- 1D Supplier");
				   Price = Price + (Integer.parseInt(quantity4.getText())* 25);
				   break;
			   case 2:
				   Supplier = (Supplier+" & 2- Bieber Supplier");
				   Price = Price + (Integer.parseInt(quantity4.getText())* 40);
				   break;
			   case 3:
				   Supplier = (Supplier+" & 3- Colm Supplier");
				   Price = Price + (Integer.parseInt(quantity4.getText())* 5);
				   break;
			   case 4:
				   Supplier = (Supplier+" & 3- Colm Supplier");
				   Price = Price + (Integer.parseInt(quantity4.getText())* 60);
				   break;
			   default:
				   break;
			   }
			   
			   String Dates = (String.valueOf(date));
			   String[] DatesPlaced = Dates.split(" ");
			   String month = null;
			   			   
			   switch(DatesPlaced[1])
			   {
			   case "Jan":
				   month = "01";
				   break;
			   case "Feb":
				   month = "02";
				   break;
			   case "Mar":
				   month = "03";
				   break;
			   case "Apr":
				   month = "04";
				   break;
			   case "May":
				   month = "05";
				   break;
			   case "Jun":
				   month = "06";
				   break;
			   case "Jul":
				   month = "07";
			   case "Aug":
				   month = "08";
				   break;
			   case "Sep":
				   month = "09";
				   break;
			   case "Oct":
				   month = "10";
				   break;
			   case "Nov":
				   month = "11";
				   break;
			   case "Dec":
				   month = "12";
				   break;
			   }
			   String dateplaced = (DatesPlaced[5]+"-"+month+"-"+DatesPlaced[2]);
	   
			   DatabaseConnectionManager.createnewstockorder(Integer.parseInt(OrderID.getText()), "Placed", Supplier, Price, dateplaced);
			   mainFrame.setVisible(false);
			   GUIcreatenewstockordermenu.main(null);
			   break;
			}
		 }
	}
}
	  




