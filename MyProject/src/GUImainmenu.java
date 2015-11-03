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


public class GUImainmenu extends JFrame 
{
	 private JFrame mainFrame;
	 private JLabel headerLabel;
	 private JPanel controlPanel;
	 private String N = "\n";
	 private JLabel Label1;

	public GUImainmenu()
	{
		prepareGUI();
	}

	public static void main(String[] args) {
	 GUImainmenu sD = new GUImainmenu();
	 sD.showEvent();
}

	private void prepareGUI() 
	{
		 mainFrame = new JFrame("Warehouse Order Tracking System");
		 mainFrame.setSize(750, 750);
		 mainFrame.setLayout(new GridLayout(4, 1));
		 headerLabel = new JLabel("", JLabel.CENTER);
		 Label1 = new JLabel("", JLabel.CENTER);
		 
		 mainFrame.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent windowEvent){System.exit(0);}});
		 controlPanel = new JPanel();
		 controlPanel.setLayout(new GridLayout(1, 2));
		 mainFrame.add(headerLabel);
		 mainFrame.add(Label1);
		 mainFrame.add(controlPanel);
		 mainFrame.setVisible(true);
			 
	}

	private void showEvent() 
	{
		headerLabel.setText("Main Menu");
		Label1.setText("Please select from customer or stock orders");
	
		headerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton customerordersButton = new JButton("Customer Orders");
		JButton stockordersButton = new JButton("Stock Orders");
	
		customerordersButton.setActionCommand("Customer Orders");
		stockordersButton.setActionCommand("Stock Orders");
		
		customerordersButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		stockordersButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		customerordersButton.addActionListener(new BCL());
		stockordersButton.addActionListener(new BCL());
		
		controlPanel.add(customerordersButton);
		controlPanel.add(stockordersButton);
		
		mainFrame.setVisible(true);}

	private class BCL implements ActionListener 
	{
		 @Override
		 public void actionPerformed (ActionEvent ae) 
		 {
		  String command = ae.getActionCommand();
		  switch (command) 
		  {
		   case "Customer Orders":
			   mainFrame.setVisible(false);
			   GUIcustomerordermenu.main(null);
		   break;
		   case "Stock Orders": 
			   mainFrame.setVisible(false);
			   GUIstockordermenu.main(null);
		   break;
		  }
		 }
	}
}

	
	
	
	
	
	