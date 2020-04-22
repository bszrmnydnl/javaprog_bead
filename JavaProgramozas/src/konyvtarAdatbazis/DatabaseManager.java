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
            	String sql = "CREATE TABLE konyv('azonos�t�' INTEGER PRIMARY_KEY, 'szerzo' TEXT NOT NULL, 'c�m' TEXT NOT NULL, 'megjelen�s d�tuma' INTEGER NOT NULL, 'st�tusz' TEXT NOT NULL);";
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
	            konyv.setId(rs.getInt("azonos�t�"));
				konyv.setAuthor(rs.getString("szerzo"));
				konyv.setTitle(rs.getString("c�m"));
				konyv.setDateOfRelease(new Date(rs.getInt("megjelen�s d�tuma")));
				konyv.setStatus(rs.getString("st�tusz"));
				Program.getTableModel().add(konyv);
				
				
			}
			conn.close();
			return true;
		}catch(SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
			return false;
		}catch(MalformedURLException murle) {
			Utilities.showMessage("Hib�s f�jl el�r�si �tvonal: " + murle.getMessage(), 0);
			return false;
		}catch(ClassNotFoundException cnfe){
			Utilities.showMessage("JDBC csomag nem tal�lhat�! " + cnfe.getMessage(), 0);
			return false;
		}
	}
	
	public static void updateRow(int id, int a, String b, String c, long d, String e) {
		String path = Program.getPath();
		String sql = "UPDATE konyv SET 'azonos�t�' = ?, 'szerzo' = ?, 'c�m' = ?, 'megjelen�s d�tuma' = ?, 'st�tusz' = ? WHERE azonos�t� = ?";
		try(Connection conn = connect(path);
			PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, a);
			stmt.setString(2, b);
			stmt.setString(3, c);
			stmt.setLong(4, d);
			stmt.setString(5, e);
			stmt.setInt(6, id);
			
			stmt.executeUpdate();
			
			Utilities.showMessage("V�ltoztat�sok eszk�z�lve!", 1);
		}catch(SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		}catch(MalformedURLException murle) {
			Utilities.showMessage("Hib�s f�jl el�r�si �tvonal: " + murle.getMessage(), 0);
		}catch(ClassNotFoundException cnfe){
			Utilities.showMessage("JDBC csomag nem tal�lhat�! " + cnfe.getMessage(), 0);
		}
	}

	public static void deleteRow(int id) {
		String path = Program.getPath();
		String sql = "DELETE FROM konyv WHERE azonos�t� = ?";
		try(Connection conn = connect(path);
			PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
			
			Utilities.showMessage("V�ltoztat�sok eszk�z�lve!", 1);
		}catch(SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		}catch(MalformedURLException murle) {
			Utilities.showMessage("Hib�s f�jl el�r�si �tvonal: " + murle.getMessage(), 0);
		}catch(ClassNotFoundException cnfe){
			Utilities.showMessage("JDBC csomag nem tal�lhat�! " + cnfe.getMessage(), 0);
		}
	}
	
	public static void addRow(int a, String b, String c, long d, String e) {
		String path = Program.getPath();
		String sql = "INSERT INTO konyv(azonos�t�, szerzo, c�m, 'megjelen�s d�tuma', st�tusz) VALUES(?, ?, ?, ?, ?)";
		try(Connection conn = connect(path);
			PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, a);
			stmt.setString(2, b);
			stmt.setString(3, c);
			stmt.setLong(4, d);
			stmt.setString(5, e);
			
			stmt.executeUpdate();
			
			Utilities.showMessage("V�ltoztat�sok eszk�z�lve!", 1);
		}catch(SQLException sqle) {
			Utilities.showMessage("SQL hiba: " + sqle.getMessage(), 0);
		}catch(MalformedURLException murle) {
			Utilities.showMessage("Hib�s f�jl el�r�si �tvonal: " + murle.getMessage(), 0);
		}catch(ClassNotFoundException cnfe){
			Utilities.showMessage("JDBC csomag nem tal�lhat�! " + cnfe.getMessage(), 0);
		}
	}
    
}
