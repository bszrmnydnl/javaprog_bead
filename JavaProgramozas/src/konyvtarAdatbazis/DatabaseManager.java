package konyvtarAdatbazis;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class DatabaseManager {

	public static void jdbcJar() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
	}

	public static Connection connect(String path) throws MalformedURLException, ClassNotFoundException, SQLException {
		Connection conn = null;
		File db = new File(path);
		String url = db.toURI().toURL().toString().replace("file:/", "").replace("%20", " ");
		jdbcJar();
		conn = DriverManager.getConnection("jdbc:sqlite:" + url);
		return conn;
	}

	public void createNewDatabase(String path) {
		try (Connection conn = connect(path)) {
			if (conn != null) {
				String sql = "CREATE TABLE konyv('id' INTEGER PRIMARY_KEY, 'author' TEXT NOT NULL, 'title' TEXT NOT NULL, 'release_date' INTEGER NOT NULL, 'status' TEXT NOT NULL);";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.execute();
			}

		} catch (SQLException e) {
			Utilities.showMessage(e.getMessage(), 0);
		} catch (MalformedURLException e) {
			Utilities.showMessage(e.getMessage(), 0);
		} catch (ClassNotFoundException e) {
			Utilities.showMessage(e.getMessage(), 0);
		}
	}

	public static boolean readFromSql(String path) {
		String query = "select * from konyv";
		try (Connection conn = connect(path);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Book konyv = new Book();
				konyv.setId(rs.getInt("id"));
				konyv.setAuthor(rs.getString("author"));
				konyv.setTitle(rs.getString("title"));
				konyv.setDateOfRelease(new Date(rs.getInt("release_date")));
				konyv.setStatus(rs.getString("status"));
				Program.getTableModel().add(konyv);

			}
			conn.close();
			return true;
		} catch (SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
			return false;
		} catch (MalformedURLException murle) {
			Utilities.showMessage("Hib�s f�jl el�r�si �tvonal: " + murle.getMessage(), 0);
			return false;
		} catch (ClassNotFoundException cnfe) {
			Utilities.showMessage("JDBC csomag nem tal�lhat�! " + cnfe.getMessage(), 0);
			return false;
		}
	}

	public static void updateRow(int ida, int idb, String author, String title, long releaseDate, String status) {
		String path = Program.getPath();
		String sql = "UPDATE konyv SET 'id' = ?, 'author' = ?, 'title' = ?, 'release_date' = ?, 'status' = ? WHERE id = ?";
		try (Connection conn = connect(path); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idb);
			stmt.setString(2, author);
			stmt.setString(3, title);
			stmt.setLong(4, releaseDate);
			stmt.setString(5, status);
			stmt.setInt(6, ida);

			stmt.executeUpdate();

			Utilities.showMessage("V�ltoztat�sok eszk�z�lve!", 1);
		} catch (SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		} catch (MalformedURLException murle) {
			Utilities.showMessage("Hib�s f�jl el�r�si �tvonal: " + murle.getMessage(), 0);
		} catch (ClassNotFoundException cnfe) {
			Utilities.showMessage("JDBC csomag nem tal�lhat�! " + cnfe.getMessage(), 0);
		}
	}

	public static void deleteRow(int id) {
		String path = Program.getPath();
		String sql = "DELETE FROM konyv WHERE id = ?";
		try (Connection conn = connect(path); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

			Utilities.showMessage("V�ltoztat�sok eszk�z�lve!", 1);
		} catch (SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		} catch (MalformedURLException murle) {
			Utilities.showMessage("Hib�s f�jl el�r�si �tvonal: " + murle.getMessage(), 0);
		} catch (ClassNotFoundException cnfe) {
			Utilities.showMessage("JDBC csomag nem tal�lhat�! " + cnfe.getMessage(), 0);
		}
	}

	public static void addRow(int a, String b, String c, long d, String e) {
		String path = Program.getPath();
		String sql = "INSERT INTO konyv(id, author, title, release_date, status) VALUES(?, ?, ?, ?, ?)";
		try (Connection conn = connect(path); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, a);
			stmt.setString(2, b);
			stmt.setString(3, c);
			stmt.setLong(4, d);
			stmt.setString(5, e);

			stmt.executeUpdate();

			Utilities.showMessage("V�ltoztat�sok eszk�z�lve!", 1);
		} catch (SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		} catch (MalformedURLException murle) {
			Utilities.showMessage("Hib�s f�jl el�r�si �tvonal: " + murle.getMessage(), 0);
		} catch (ClassNotFoundException cnfe) {
			Utilities.showMessage("JDBC csomag nem tal�lhat�! " + cnfe.getMessage(), 0);
		}
	}

}