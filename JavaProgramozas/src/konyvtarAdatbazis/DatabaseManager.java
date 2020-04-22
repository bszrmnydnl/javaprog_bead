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
	 
    public static void createNewDatabase(String path) {
        try (Connection conn = connect(path)) {
            if (conn != null) {
            	String sql = "CREATE TABLE konyv('azonosító' INTEGER PRIMARY_KEY, 'szerzo' TEXT NOT NULL, 'cím' TEXT NOT NULL, 'megjelenés dátuma' INTEGER NOT NULL, 'státusz' TEXT NOT NULL);";
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
		try(Connection conn = connect(path);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next()) {
	 			Book konyv = new Book();
	            konyv.setId(rs.getInt("azonosító"));
				konyv.setAuthor(rs.getString("szerzo"));
				konyv.setTitle(rs.getString("cím"));
				konyv.setDateOfRelease(new Date(rs.getInt("megjelenés dátuma")));
				konyv.setStatus(rs.getString("státusz"));
				Program.getTableModel().add(konyv);
				
				
			}
			conn.close();
			return true;
		}catch(SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
			return false;
		}catch(MalformedURLException murle) {
			Utilities.showMessage("Hibás fájl elérési útvonal: " + murle.getMessage(), 0);
			return false;
		}catch(ClassNotFoundException cnfe){
			Utilities.showMessage("JDBC csomag nem található! " + cnfe.getMessage(), 0);
			return false;
		}
	}
	
	public static void updateRow(int id, int a, String b, String c, long d, String e) {
		String path = Program.getPath();
		String sql = "UPDATE konyv SET 'azonosító' = ?, 'szerzo' = ?, 'cím' = ?, 'megjelenés dátuma' = ?, 'státusz' = ? WHERE azonosító = ?";
		try(Connection conn = connect(path);
			PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, a);
			stmt.setString(2, b);
			stmt.setString(3, c);
			stmt.setLong(4, d);
			stmt.setString(5, e);
			stmt.setInt(6, id);
			
			stmt.executeUpdate();
			
			Utilities.showMessage("Változtatások eszközölve!", 1);
		}catch(SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		}catch(MalformedURLException murle) {
			Utilities.showMessage("Hibás fájl elérési útvonal: " + murle.getMessage(), 0);
		}catch(ClassNotFoundException cnfe){
			Utilities.showMessage("JDBC csomag nem található! " + cnfe.getMessage(), 0);
		}
	}

	public static void deleteRow(int id) {
		String path = Program.getPath();
		String sql = "DELETE FROM konyv WHERE azonosító = ?";
		try(Connection conn = connect(path);
			PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
			
			Utilities.showMessage("Változtatások eszközölve!", 1);
		}catch(SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		}catch(MalformedURLException murle) {
			Utilities.showMessage("Hibás fájl elérési útvonal: " + murle.getMessage(), 0);
		}catch(ClassNotFoundException cnfe){
			Utilities.showMessage("JDBC csomag nem található! " + cnfe.getMessage(), 0);
		}
	}
	
	public static void addRow(int a, String b, String c, long d, String e) {
		String path = Program.getPath();
		String sql = "INSERT INTO konyv(azonosító, szerzo, cím, 'megjelenés dátuma', státusz) VALUES(?, ?, ?, ?, ?)";
		try(Connection conn = connect(path);
			PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, a);
			stmt.setString(2, b);
			stmt.setString(3, c);
			stmt.setLong(4, d);
			stmt.setString(5, e);
			
			stmt.executeUpdate();
			
			Utilities.showMessage("Változtatások eszközölve!", 1);
		}catch(SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		}catch(MalformedURLException murle) {
			Utilities.showMessage("Hibás fájl elérési útvonal: " + murle.getMessage(), 0);
		}catch(ClassNotFoundException cnfe){
			Utilities.showMessage("JDBC csomag nem található! " + cnfe.getMessage(), 0);
		}
	}
    
}
