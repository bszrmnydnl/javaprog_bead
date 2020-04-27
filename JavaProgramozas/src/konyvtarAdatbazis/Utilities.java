package konyvtarAdatbazis;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utilities {

	public static void showMessage(String message, int code) {
		if (code == 0) {
			JOptionPane.showMessageDialog(new JFrame(), message, "Hiba!", JOptionPane.ERROR_MESSAGE);
		} else if (code == 1) {
			JOptionPane.showMessageDialog(new JFrame(), message, "Siker!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static boolean openFile(String path) {
		boolean success = true;
		String fileExtension = path.substring(path.lastIndexOf(".") + 1);

		switch (fileExtension) {
		case "json":
			FileManager.openJson(path);
			break;
		case "csv":
			FileManager.openCsv(path);
			break;
		case "db":
			DatabaseManager.readFromSql(path);
			break;
		default:
			showMessage("Nem támogatott fájltípus!", 0);
			return false;
		}
		return success;
	}

	public static boolean saveFile(String path) {
		boolean success = true;
		String fileExtension = path.substring(path.lastIndexOf(".") + 1);

		switch (fileExtension) {
		case "json":
			break;
		case "csv":
			FileManager.saveCsv(path);
			break;
		case "db":
			Utilities.showMessage("SQL adatbázis esetén nincs szükség mentésre!", 0);
			break;
		default:
			return false;
		}
		return success;

	}

}
