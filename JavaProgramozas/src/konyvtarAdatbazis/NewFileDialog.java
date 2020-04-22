package konyvtarAdatbazis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewFileDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JTextField tfDbName;
	private JTextField tfPath;

	public NewFileDialog(JFrame parent, String frameTitle) {
		super(parent, frameTitle, true);
		setResizable(false);
		setBounds(100, 100, 450, 183);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfDbName = new JTextField();
		tfDbName.setBounds(99, 26, 235, 20);
		contentPanel.add(tfDbName);
		tfDbName.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("\u00DAj f\u00E1jl neve:");
		lblNewLabel.setBounds(30, 29, 144, 14);
		contentPanel.add(lblNewLabel);
		
		
		tfPath = new JTextField();
		tfPath.setBounds(30, 58, 269, 20);
		contentPanel.add(tfPath);
		tfPath.setColumns(10);
		
		
		JButton btnBrowse = new JButton("Tall\u00F3z\u00E1s");
		btnBrowse.setBounds(315, 56, 90, 23);
		contentPanel.add(btnBrowse);
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(240, 240, 240));
		buttonPane.setBounds(0, 111, 434, 33);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
			
			
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		JComboBox<String> cbFileType = new JComboBox<String>();
		cbFileType.setModel(new DefaultComboBoxModel<String>(new String[] {".csv", ".json", ".db"}));
		cbFileType.setBounds(344, 25, 60, 22);
		contentPanel.add(cbFileType);
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser folder = new JFileChooser();
				folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = folder.showOpenDialog(NewFileDialog.this);
				if(returnVal == 0) {
					tfPath.setText(folder.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}

}
