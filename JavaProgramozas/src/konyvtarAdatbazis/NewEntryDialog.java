package konyvtarAdatbazis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NewEntryDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfId;
	private JTextField tfTitle;
	private JTextField tfAuthor;
	
	public boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    }
	    catch(Exception e) {
	        return false;
	    }
	}
	
	

	public NewEntryDialog(JFrame parent) throws ClassNotFoundException{
		super(parent, "Új adat felvétele", true);
		setResizable(false);
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 613, 228);
		contentPanel.add(panel);
		panel.setLayout(null);
			
		tfId = new JTextField();
		tfId.setBounds(172, 23, 175, 20);
		panel.add(tfId);
		tfId.setColumns(10);
						
		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");
				
		UtilDateModel model = new UtilDateModel();
		model.setDate(2020, 3, 27); //ekkor kezdtem csinálni
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.getJFormattedTextField().setLocation(165, 0);
		datePicker.setBounds(172, 142, 175, 27); 
		panel.add(datePicker);
						
		tfTitle = new JTextField();
		tfTitle.setBounds(172, 103, 175, 20);
		panel.add(tfTitle);
		tfTitle.setColumns(10);
						
		tfAuthor = new JTextField();
		tfAuthor.setBounds(172, 63, 175, 20);
		panel.add(tfAuthor);
		tfAuthor.setColumns(10);
				
		JComboBox<String> cbStatus = new JComboBox<String>();
		cbStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"szabad", "kikölcsönzött", "selejt"}));
		cbStatus.setBounds(172, 182, 175, 22);
		panel.add(cbStatus);
			
		JLabel lblNewLabel = new JLabel("Azonos\u00EDt\u00F3:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(30, 26, 119, 14);
		panel.add(lblNewLabel);
			
			
		JLabel lblNewLabel_1 = new JLabel("Szerz\u0151:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(30, 66, 119, 14);
		panel.add(lblNewLabel_1);
			
			
		JLabel lblNewLabel_2 = new JLabel("C\u00EDm:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(30, 106, 119, 14);
		panel.add(lblNewLabel_2);
			
		JLabel lblNewLabel_3 = new JLabel("Megjelen\u00E9s d\u00E1tuma:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(30, 146, 119, 14);
		panel.add(lblNewLabel_3);
			
		JLabel lblNewLabel_4 = new JLabel("St\u00E1tusz:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(30, 186, 119, 14);
		panel.add(lblNewLabel_4);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
			
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().isEmpty() || tfAuthor.getText().isEmpty() || tfTitle.getText().isEmpty() || datePicker.getModel().getValue() == null || cbStatus.getSelectedItem().equals("")) {
					Utilities.showMessage("Valamelyik adatmezõ nem került kitöltésre!", 0);
				}else if(!isInteger(tfId.getText())) {
					Utilities.showMessage("Az azonosító mezõ tartalma egész szám kell legyen!", 0);
				}else {
					Book konyv = new Book(Integer.parseInt(tfId.getText()), tfAuthor.getText(), tfTitle.getText(), (Date)datePicker.getModel().getValue(), cbStatus.getSelectedItem().toString());
					Program.getTableModel().add(konyv);
					
					if(Program.isDatabaseSelected()) {
						int azonosito = Integer.parseInt(tfId.getText());
						String szerzo = tfAuthor.getText();
						String cim = tfTitle.getText();
						long megjelenes_datuma = ((Date)datePicker.getModel().getValue()).getTime();
						String statusz = (String) cbStatus.getSelectedItem();
						DatabaseManager.addRow(azonosito, szerzo, cim, megjelenes_datuma, statusz);
					}
					dispose();
				}
			}
		});
			
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}