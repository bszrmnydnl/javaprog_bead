package konyvtarAdatbazis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class EditEntryDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfId;
	private JTextField tfTitle;
	private JTextField tfAuthor;

	public EditEntryDialog(JFrame parent, int row) throws ClassNotFoundException{
		super(parent, "Új adat beszúrása", true);
				
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
		model.setDate(1990, 8, 24);
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
		
		tfId.setText(String.valueOf(Program.getTableModel().getValueAt(row, 1)));
		tfAuthor.setText((String) Program.getTableModel().getValueAt(row, 2));
		tfTitle.setText((String) Program.getTableModel().getValueAt(row, 3));
		model.setValue((Date) Program.getTableModel().getValueAt(row, 4));
		cbStatus.setSelectedItem(Program.getTableModel().getValueAt(row, 5));
		int id = Integer.parseInt(tfId.getText());
	
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Program.getTableModel().setValueAt(tfId.getText(), row, 1);
				Program.getTableModel().setValueAt(tfAuthor.getText(), row, 2);
				Program.getTableModel().setValueAt(tfTitle.getText(), row, 3);
				Program.getTableModel().setValueAt(model.getValue(), row, 4);
				Program.getTableModel().setValueAt(cbStatus.getSelectedItem(), row, 5);
				
				//Program.getTableModel().remove(row);
				//Program.getTableModel().add(new Book(Integer.parseInt(tfId.getText()), tfAuthor.getText(), tfTitle.getText(), model.getValue(), (String) cbStatus.getSelectedItem()));
				
				if(Program.isDatabaseSelected()) {
					int azonosito = (int) Program.getTableModel().getValueAt(row, 1);
					String szerzo = (String) Program.getTableModel().getValueAt(row, 2);
					String cim = (String) Program.getTableModel().getValueAt(row, 3);
					long megjelenes_datuma = ((Date)Program.getTableModel().getValueAt(row, 4)).getTime();
					String statusz = (String) Program.getTableModel().getValueAt(row, 5);
					//String query = "UPDATE 'konyv' SET 'azonosító'='" + azonosito + "' 'szerzo'='" + szerzo + "' 'cím'='" + cim + "' 'megjelenés dátuma'='" + megjelenes_datuma + "' 'státusz'='" + statusz + "';";
					//String query = "UPDATE konyv SET 'azonosító' = ?, 'szerzo' = ?, 'cím' = ?, 'megjelenés dátuma' = ?, 'státusz' = ?";
					DatabaseManager.updateRow(id, azonosito, szerzo, cim, megjelenes_datuma, statusz);
				}else {

				}
				dispose();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}

}
