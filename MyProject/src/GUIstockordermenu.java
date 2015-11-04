import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class GUIstockordermenu extends JFrame
{
	 private JFrame mainFrame;
	 private JLabel headerLabel;
	 private JPanel controlPanel;
	 private JPanel orderPanel;
	 private JList orderlist;
	 private JPanel BPanel;
	 private JTextArea textarea;
	 private JTextArea productinfo;
	 private String[] details;
	 private String nextline = "\n";
	 private JScrollPane scrollpane;
	 private JList statusoptions;
	 private JTextArea instruction;
	 private JPanel instructionPanel;

	public GUIstockordermenu()
	{
		prepareGUI();
	}

	public static void main(String[] args) {
	 GUIstockordermenu sD = new GUIstockordermenu();
	 sD.showEvent();
}

	private void prepareGUI() 
	{
		
		ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();
		stockorders = DatabaseConnectionManager.accessDBStockOrder();
		int lengthstockorders = stockorders.size();
		String[] listData = new String[lengthstockorders];
		
		for(int i=0; i<lengthstockorders; i++)
		{
			listData[i] = ("Order ID: "+stockorders.get(i).getOrderID())+"  Status: "+(stockorders.get(i).getStatus())+"  Supplier: "+(stockorders.get(i).getSupplier())+"  Price: "+(stockorders.get(i).getPrice())+"  Date Placed: "+(stockorders.get(i).getDatePlaced());
		}
		
						
		mainFrame = new JFrame("Warehouse Order Tracking System");
		mainFrame.setSize(2000,2000);
		mainFrame.setLayout(new GridLayout(5, 1));
		headerLabel = new JLabel("", JLabel.CENTER);
		orderlist = new JList(listData);

		String instructions = "Please select an order to view the details."+"\n"+"To update the status of a order please select the new status and select apply."+"\n"+"To create a new order, click the create new order button.";
		 
		instruction = new JTextArea(20, 60);
		instruction.setSize(350, 100);
		instruction.setText(instructions);
		instruction.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		String[] options = {"Placed","AwaitingDelivery","Processing","Shelved"};
		statusoptions = new JList(options);
		statusoptions.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		 
		mainFrame.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent windowEvent){System.exit(0);}});
		instructionPanel = new JPanel();
		instructionPanel.setLayout(new GridLayout());
		orderPanel = new JPanel();
		orderPanel.setLayout(new GridLayout());
		BPanel = new JPanel();
		BPanel.setLayout(new GridLayout());
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 2));
		mainFrame.add(headerLabel);
		mainFrame.add(instructionPanel);
		mainFrame.add(orderPanel);
		mainFrame.add(BPanel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		
	}

	private void showEvent() 
	{
		
		scrollpane = new JScrollPane(orderlist);
		orderlist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		textarea = new JTextArea(20, 60);
		
		productinfo = new JTextArea(20, 60);
		productinfo.setEditable(false);
		productinfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		ArrayList<Product> products = new ArrayList<Product>();
		products = DatabaseConnectionManager.accessDBProduct();
		int lengthproducts = products.size();
		
		for(int i=0; i<lengthproducts; i++)
		{
			String product = ("ProductID: "+products.get(i).getProductID()+" "+products.get(i).getProductName()+"\t"+" Location in warehouse: "+products.get(i).getLocationInWarehouse()+"\t"+" Stock level: "+products.get(i).getStockLevel()+"\n");
			productinfo.append(product);
		}
		
			
		ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
		stockorderlines = DatabaseConnectionManager.accessDBStockOrderLine();
		int lengthstockorderlines = stockorderlines.size();
		details = new String[lengthstockorderlines+1];
		
		headerLabel.setText("Stock Orders Menu");
		
		headerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JButton mainButton = new JButton("Main Menu");
		JButton customerorderButton = new JButton("Customer Orders");
		JButton apply = new JButton("Apply");
		JButton newstockorderbutton = new JButton("Create New Order");
		
		mainButton.setActionCommand("Main Menu");
		customerorderButton.setActionCommand("Customer Orders");
		apply.setActionCommand("Apply");
		newstockorderbutton.setActionCommand("Create New Order");
		
		mainButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		customerorderButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		apply.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		newstockorderbutton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		mainButton.addActionListener(new BCL());
		customerorderButton.addActionListener(new BCL());
		apply.addActionListener(new BCL());
		newstockorderbutton.addActionListener(new BCL());
		
		instructionPanel.add(instruction);
		instructionPanel.add(productinfo);
		orderPanel.add(scrollpane);
		orderPanel.add(textarea);
		BPanel.add(statusoptions);
		BPanel.add(apply);
		controlPanel.add(mainButton);
		controlPanel.add(customerorderButton);
		controlPanel.add(newstockorderbutton);
		
		textarea.setEditable(false);
		
		orderlist.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent arg0)
			{
				ArrayList<StockOrder> stockorders = new ArrayList<StockOrder>();
				stockorders = DatabaseConnectionManager.accessDBStockOrder();
				ArrayList<StockOrderLine> stockorderlines = new ArrayList<StockOrderLine>();
				stockorderlines = DatabaseConnectionManager.accessDBStockOrderLine();
				int lengthstockorders = stockorders.size();				
				int lengthstockorderlines = stockorderlines.size();
				
				if(!arg0.getValueIsAdjusting())
				{
					for(int i=0; i<lengthstockorders; i++)
					{
						if((orderlist.getSelectedIndex()+1) == stockorders.get(i).getOrderID())
						{
							textarea.setText(null);
							details[0] = ("OrderID:  "+stockorders.get(i).getOrderID()+"  Status: "+stockorders.get(i).getStatus()+"  Supplier: "+(stockorders.get(i).getSupplier())+"  Price: "+stockorders.get(i).getPrice()+ nextline +"DatePlaced: "+stockorders.get(i).getDatePlaced());		
							textarea.append(details[0]);
							for(int j=0; j<lengthstockorderlines; j++)
							{
								if(stockorders.get(i).getOrderID() == stockorderlines.get(j).getOrderID())
								{				
									switch(String.valueOf(stockorderlines.get(j).getProductID()))
									{
									case "1":
										details[j+1] = (nextline + stockorderlines.get(j).getQuantity()+" of Harry Styles gnomes"+nextline);
										textarea.append(details[j+1]);
										break;
									case "2":
										details[j+1] = (nextline + stockorderlines.get(j).getQuantity()+" of Justin Bieber gnomes"+nextline);
										textarea.append(details[j+1]);
										break;
									case "3":
										details[j+1] = (nextline + stockorderlines.get(j).getQuantity()+" of Colm Pool Edition gnomes"+nextline);
										textarea.append(details[j+1]);
										break;
									case "4":
										details[j+1] = (nextline + stockorderlines.get(j).getQuantity()+" of Colm Pissed Edition gnomes"+nextline);
										textarea.append(details[j+1]);
										break;
									}
								}
							}
						}
					}
				}
			}
		});
		
		mainFrame.setVisible(true);}

	private class BCL implements ActionListener 
	{
		 @Override
		 public void actionPerformed (ActionEvent ae) 
		 {
		  String command = ae.getActionCommand();
		  switch (command) 
		  {
		   case "Main Menu": 
			   mainFrame.setVisible(false);
			   GUImainmenu.main(null);
		   break;
		   case "Customer Orders":
			   mainFrame.setVisible(false);
			   GUIcustomerordermenu.main(null);
			   break;
		   case "Apply":
			   DatabaseConnectionManager.updatestockstatus(String.valueOf(statusoptions.getSelectedValue()),(orderlist.getSelectedIndex()+1));
			   mainFrame.setVisible(false);
			   GUIstockordermenu.main(null);
			   break;
		   case "Create New Order":
			   mainFrame.setVisible(false);
			   GUIcreatenewstockordermenu.main(null);
			   break;
		  }
		 }
	}
}


