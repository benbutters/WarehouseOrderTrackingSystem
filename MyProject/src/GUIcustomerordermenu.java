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




public class GUIcustomerordermenu extends JFrame
{
	 private JFrame mainFrame;
	 private JLabel headerLabel;
	 private JPanel controlPanel;
	 private JPanel orderPanel;
	 private JList orderlist;
	 private JPanel BPanel;
	 private JTextArea textarea;
	 private String[] details;
	 private String nextline = "\n";
	 private JScrollPane scrollpane;
	 private JList statusoptions;
	 private JList employee;
	 private JLabel instruction;

	public GUIcustomerordermenu()
	{
		prepareGUI();
	}

	public static void main(String[] args) {
	 GUIcustomerordermenu sD = new GUIcustomerordermenu();
	 sD.showEvent();
}

	private void prepareGUI() 
	{
		
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = DatabaseConnectionManager.accessDBOrder();
		int lengthorders = orders.size();
		String[] listData = new String[lengthorders];
		
		for(int i=0; i<lengthorders; i++)
		{
			listData[i] = ("Order ID: "+orders.get(i).getOrderID())+"  Status: "+(orders.get(i).getStatus())+"  Employee: "+(orders.get(i).getEmployee())+"  Price: "+(orders.get(i).getPrice())+"  Date Placed: "+(orders.get(i).getDatePlaced())+"  Checked Out: "+(orders.get(i).isCheckedOut());
		}
		
						
		mainFrame = new JFrame("Warehouse Order Tracking System");
		mainFrame.setSize(2000,2000);
		mainFrame.setLayout(new GridLayout(5, 1));
		headerLabel = new JLabel("", JLabel.CENTER);
		orderlist = new JList(listData);
		
		String[] options = {"Placed","Picked","Packed","AwaitingCourier","Delivery","Completed"};
		statusoptions = new JList(options);
		
		String[] employees = {"Ben","Frank","Jim","John","Sergio","Steve"};
		employee = new JList(employees);
		
		String instructions = "Please select an order to view the details. To check out an order select your name and click Check Out. To check in an order select the new status of the order and click Check In";
		 
		instruction = new JLabel("",JLabel.CENTER);
		instruction.setSize(350, 100);
		instruction.setText(instructions);
		
		mainFrame.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent windowEvent){System.exit(0);}});
		orderPanel = new JPanel();
		orderPanel.setLayout(new GridLayout());
		BPanel = new JPanel();
		BPanel.setLayout(new GridLayout());
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 2));
		mainFrame.add(headerLabel);
		mainFrame.add(instruction);
		mainFrame.add(orderPanel);
		mainFrame.add(BPanel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		
	}

	private void showEvent() 
	{
		
		scrollpane = new JScrollPane(orderlist);
		
		textarea = new JTextArea(20, 60);
			
		ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
		orderlines = DatabaseConnectionManager.accessDBOrderLine();
		int lengthorderlines = orderlines.size();
		details = new String[lengthorderlines+1];
		
		headerLabel.setText("Customer Orders Menu");
		
		headerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JButton mainButton = new JButton("Main Menu");
		JButton stockorderButton = new JButton("Stock Orders");
		JButton checkoutButton = new JButton("Check Out");
		JButton checkinButton = new JButton("Check In");
		
		mainButton.setActionCommand("Main Menu");
		stockorderButton.setActionCommand("Stock Orders");
		checkoutButton.setActionCommand("Check Out");
		checkinButton.setActionCommand("Check In");
		
		mainButton.addActionListener(new BCL());
		stockorderButton.addActionListener(new BCL());
		checkoutButton.addActionListener(new BCL());
		checkinButton.addActionListener(new BCL());
				
		orderPanel.add(scrollpane);
		orderPanel.add(employee);
		orderPanel.add(checkoutButton);
		BPanel.add(textarea);
		BPanel.add(statusoptions);
		BPanel.add(checkinButton);
		controlPanel.add(mainButton);
		controlPanel.add(stockorderButton);
		
		textarea.setEditable(false);
		
		orderlist.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent arg0)
			{
				ArrayList<Order> orders = new ArrayList<Order>();
				orders = DatabaseConnectionManager.accessDBOrder();
				ArrayList<OrderLine> orderlines = new ArrayList<OrderLine>();
				orderlines = DatabaseConnectionManager.accessDBOrderLine();
				int lengthorders = orders.size();				
				int lengthorderlines = orderlines.size();
				
				if(!arg0.getValueIsAdjusting())
				{
					for(int i=0; i<lengthorders; i++)
					{
						if((orderlist.getSelectedIndex()+1) == orders.get(i).getOrderID())
						{
							textarea.setText(null);
							details[0] = ("OrderID:  "+orders.get(i).getOrderID()+"  Status: "+orders.get(i).getStatus()+"  Employee: "+orders.get(i).getEmployee()+"  Price: "+orders.get(i).getPrice()+ nextline +"DatePlaced: "+orders.get(i).getDatePlaced()+"  Checkedout: "+orders.get(i).isCheckedOut());		
							textarea.append(details[0]);
							for(int j=0; j<lengthorderlines; j++)
							{
								if(orders.get(i).getOrderID() == orderlines.get(j).getOrderID())
								{				
									switch(String.valueOf(orderlines.get(j).getProductID()))
									{
									case "1":
										details[j+1] = (nextline + orderlines.get(j).getQuantity()+" of Harry Styles gnomes"+nextline);
										textarea.append(details[j+1]);
										break;
									case "2":
										details[j+1] = (nextline + orderlines.get(j).getQuantity()+" of Justin Bieber gnomes"+nextline);
										textarea.append(details[j+1]);
										break;
									case "3":
										details[j+1] = (nextline + orderlines.get(j).getQuantity()+" of Colm Pool Edition gnomes"+nextline);
										textarea.append(details[j+1]);
										break;
									case "4":
										details[j+1] = (nextline + orderlines.get(j).getQuantity()+" of Colm Pissed Edition gnomes"+nextline);
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
		   case "Stock Orders":
			   mainFrame.setVisible(false);
			   GUIstockordermenu.main(null);
			   break;
		   case "Check Out":
			   DatabaseConnectionManager.updateemployee(String.valueOf(employee.getSelectedValue()), (orderlist.getSelectedIndex()+1));
			   DatabaseConnectionManager.updatecheckedout(1, (orderlist.getSelectedIndex()+1));
			   mainFrame.setVisible(false);
			   GUIcustomerordermenu.main(null);
			   break;
		   case "Check In":
			   if((String.valueOf(statusoptions.getSelectedValue()).equals("Picked"))
					   {
				   
					   }
			   DatabaseConnectionManager.updateemployee("N/A", (orderlist.getSelectedIndex()+1));
			   DatabaseConnectionManager.updatecheckedout(0, (orderlist.getSelectedIndex()+1));
			   DatabaseConnectionManager.updatestatus(String.valueOf(statusoptions.getSelectedValue()),(orderlist.getSelectedIndex()+1));
			   mainFrame.setVisible(false);
			   GUIcustomerordermenu.main(null);
			   break;
		  }
		 }
	}
}


