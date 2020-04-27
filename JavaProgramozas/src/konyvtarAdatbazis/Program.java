package konyvtarAdatbazis;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class Program extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	static JTable table;
	private static BookTableModel tableModel = new BookTableModel();
	private static boolean databaseSelected = false;
	private static String path = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static BookTableModel getTableModel() {
		return tableModel;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		Program.path = path;
	}

	public static boolean isDatabaseSelected() {
		return databaseSelected;
	}

	public static void setDatabaseSelected(boolean databaseSelected) {
		Program.databaseSelected = databaseSelected;
	}

	public Program() {
		setResizable(false);
		setTitle("K\u00F6nyvt\u00E1r adatb\u00E1zis");
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1080, 638);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 1064, 599);
		contentPanel.add(panel);
		panel.setLayout(null);

		JPanel upper_menu = new JPanel();
		upper_menu.setBounds(0, 0, 1064, 29);
		panel.add(upper_menu);
		upper_menu.setLayout(null);

		JButton btnNew = new JButton("\u00DAj");
		btnNew.setBounds(10, 3, 50, 23);
		upper_menu.add(btnNew);

		JButton btnOpen = new JButton("Megnyit\u00E1s...");
		btnOpen.setBounds(65, 3, 110, 23);
		upper_menu.add(btnOpen);

		JButton btnSave = new JButton("Ment\u00E9s");
		btnSave.setBounds(180, 3, 80, 23);
		upper_menu.add(btnSave);

		JButton btnSaveAs = new JButton("Ment\u00E9s m\u00E1sk\u00E9nt...");
		btnSaveAs.setBounds(265, 3, 140, 23);
		upper_menu.add(btnSaveAs);

		JButton btnDatabase = new JButton("Adatb\u00E1zis kapcsolat");
		btnDatabase.setBounds(927, 3, 127, 23);
		upper_menu.add(btnDatabase);

		JPanel preview_panel = new JPanel();
		preview_panel.setBackground(Color.LIGHT_GRAY);
		preview_panel.setBounds(0, 29, 864, 570);
		panel.add(preview_panel);
		preview_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 844, 548);
		preview_panel.add(scrollPane);

		JPanel rightside_menu = new JPanel();
		rightside_menu.setBackground(new Color(204, 204, 204));
		rightside_menu.setBounds(864, 29, 200, 570);
		panel.add(rightside_menu);
		rightside_menu.setLayout(null);

		JButton btnNewEntry = new JButton("\u00DAj adat felv\u00E9tele");
		btnNewEntry.setBounds(10, 11, 180, 23);
		rightside_menu.add(btnNewEntry);

		JButton btnDeleteEntry = new JButton("Kijel\u00F6lt adat(ok) t\u00F6rl\u00E9se");
		btnDeleteEntry.setBounds(10, 45, 180, 23);
		rightside_menu.add(btnDeleteEntry);

		JButton btnEditEntry = new JButton("Kijel\u00F6lt adat m\u00F3dos\u00EDt\u00E1sa");
		btnEditEntry.setBounds(10, 79, 180, 23);
		rightside_menu.add(btnEditEntry);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 113, 200, 2);
		rightside_menu.add(separator);

		JComboBox<String> searchBy = new JComboBox<String>();
		searchBy.setModel(new DefaultComboBoxModel<String>(
				new String[] { "azonosítóban", "szerzõben", "címben", "megjelenés dátumában", "státuszban" }));
		searchBy.setSelectedIndex(2);
		searchBy.setBounds(10, 148, 180, 22);
		rightside_menu.add(searchBy);

		JLabel keresesLabel = new JLabel("Keres\u00E9s");
		keresesLabel.setBounds(75, 123, 50, 14);
		rightside_menu.add(keresesLabel);

		table = new JTable(tableModel);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);

		JTextField searchField = new JTextField();
		searchField.setBounds(10, 178, 180, 22);
		rightside_menu.add(searchField);
		searchField.setColumns(10);

		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewFileDialog fileFolder = new NewFileDialog(Program.this, "új fájl létrehozása");
				fileFolder.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				fileFolder.setLocationRelativeTo(preview_panel);
				fileFolder.setVisible(true);
			}
		});

		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!path.isEmpty()) {
					tableModel.emptyList();
				}
				JFileChooser fileToOpen = new JFileChooser();
				fileToOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fileToOpen.showOpenDialog(Program.this);
				if (returnVal == 0) {
					path = fileToOpen.getSelectedFile().getAbsolutePath();
					Utilities.openFile(path);
					if (path.contains(".db"))
						Program.setDatabaseSelected(true);
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utilities.saveFile(path);
			}
		});

		btnSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (path.isEmpty()) {
					for (ActionListener listener : btnSaveAs.getActionListeners()) {
						listener.actionPerformed(null);
					}
				} else {
					if (Utilities.saveFile(path)) {
						Utilities.showMessage("Sikeres mentés!", 1);
					}
				}
			}
		});

		btnDatabase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!path.isEmpty()) {
					tableModel.emptyList();
				}
				JFileChooser fileToOpen = new JFileChooser();
				fileToOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fileToOpen.showOpenDialog(Program.this);
				if (returnVal == 0) {
					path = fileToOpen.getSelectedFile().getAbsolutePath();
					if (path.contains(".db")) {
						Utilities.openFile(path);
						Program.setDatabaseSelected(true);
					} else {
						Utilities.showMessage("A kiválasztott fájl nem SQL adatbázis fájl!", 0);
					}
				}
			}
		});

		btnNewEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewEntryDialog newDialog;
				try {
					newDialog = new NewEntryDialog(Program.this, tableModel);
					newDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					newDialog.setLocationRelativeTo(preview_panel);
					newDialog.setVisible(true);
				} catch (ClassNotFoundException e1) {
					Utilities.showMessage("JDatePicker csomag nem található! " + e1.getMessage(), 0);
				}
			}
		});

		btnDeleteEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if ((Boolean) tableModel.getValueAt(i, 0)) {
						if (tableModel.getValueAt(i, 1).getClass().getName().equals("java.lang.String")) {
							id = Integer.parseInt((String) tableModel.getValueAt(i, 1));
						} else {
							id = ((Integer) tableModel.getValueAt(i, 1));
						}
						tableModel.remove(id);
					}
				}
				if (Program.isDatabaseSelected()) {
					DatabaseManager.deleteRow(id);
				}
			}
		});

		btnEditEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ticked = 0;
				int row = 0;
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if ((Boolean) tableModel.getValueAt(i, 0)) {
						ticked++;
						row = i;
					}
				}
				if (ticked == 1) {
					try {
						EditEntryDialog editDialog = new EditEntryDialog(Program.this, row, tableModel);
						editDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						editDialog.setLocationRelativeTo(preview_panel);
						editDialog.setVisible(true);
					} catch (ClassNotFoundException e1) {
						Utilities.showMessage("JDatePicker csomag nem található! " + e1.getMessage(), 0);
					}
				} else if (ticked == 0) {
					Utilities.showMessage("Nincs kijelölve sor módosításra!", 0);
				} else {
					Utilities.showMessage("Több sort nem lehet egyszerre módosítani!", 0);
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());

				if (col == 0) {
					boolean val = (boolean) tableModel.getValueAt(row, col);
					if (!val) {
						tableModel.select(row);
					} else {
						tableModel.deSelect(row);
					}
				}
			}
		});

	}
}
