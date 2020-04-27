package konyvtarAdatbazis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FileManager {

	public static void openCsv(String path) {
		try {
			BufferedReader buffreader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
			String line = buffreader.readLine();
			line = buffreader.readLine();
			while (line != null) {
				Book konyv = new Book();
				String[] tokens = line.split(";");
				konyv.setId(Integer.parseInt(tokens[0]));
				konyv.setAuthor(tokens[1]);
				konyv.setTitle(tokens[2]);
				konyv.setDateOfRelease(new Date(Long.parseLong(tokens[3])));
				konyv.setStatus(tokens[4]);
				Program.getTableModel().add(konyv);
				line = buffreader.readLine();
			}
			buffreader.close();
		} catch (IOException e) {
			Utilities.showMessage("IOException: " + e.getMessage(), 0);
		}
	}

	public static void openJson(String path) {
		JSONParser jsonParser = new JSONParser();
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "utf-8")) {
			Object obj = jsonParser.parse(reader);
			JSONArray bookList = (JSONArray) obj;
			for (Object book : bookList) {
				Book konyv = new Book();
				JSONObject bookJson = (JSONObject) book;
				JSONObject bookObject = (JSONObject) bookJson.get("könyv");

				konyv.setId(((Long) bookObject.get("Azonosító")).intValue());
				konyv.setAuthor((String) bookObject.get("Szerzõ"));
				konyv.setTitle((String) bookObject.get("Cím"));
				konyv.setDateOfRelease(new Date(((Long) bookObject.get("Megjelenés dátuma")).longValue()));
				konyv.setStatus((String) bookObject.get("Státusz"));
				Program.getTableModel().add(konyv);
			}

		} catch (FileNotFoundException e) {
			Utilities.showMessage("Fájl nem található! " + e.getMessage(), 0);
		} catch (IOException e) {
			Utilities.showMessage("IOException: " + e.getMessage(), 0);
		} catch (ParseException e) {
			Utilities.showMessage("JSON ParseException: Sérült fájl!", 0);
		}
	}

	public static void saveCsv(String path) {
		List<Book> konyvek = Program.getTableModel().getBooks();
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))) {
			writer.write("Azonosító;Szerzõ;Cím;Megjelenés dátuma;Státusz\n");
			for (Book konyv : konyvek) {
				// System.out.println("asd");
				String line = konyv.getId() + ";" + konyv.getAuthor() + ";" + konyv.getTitle() + ";"
						+ konyv.getDateOfRelease().getTime() + ";" + konyv.getStatus() + "\n";
				writer.write(line);
			}
			writer.close();
		} catch (UnsupportedEncodingException e) {
			Utilities.showMessage("Nem támogatott kódolás! " + e.getMessage(), 0);
		} catch (FileNotFoundException e) {
			Utilities.showMessage("Fájl nem található!" + e.getMessage(), 0);
		} catch (IOException e) {
			Utilities.showMessage("IOException: " + e.getMessage(), 0);
		}
	}

	@SuppressWarnings("unchecked")
	public static void saveJson(String path) {
		List<Book> konyvek = Program.getTableModel().getBooks();
		JSONArray konyvekList = new JSONArray();

		for (Book konyv : konyvek) {
			JSONObject konyvekObj = new JSONObject();
			JSONObject konyvObj = new JSONObject();
			konyvObj.put("Azonosító", konyv.getId());
			konyvObj.put("Szerzõ", konyv.getAuthor());
			konyvObj.put("Cím", konyv.getTitle());
			konyvObj.put("Megjelenés dátuma", konyv.getDateOfRelease().getTime());
			konyvObj.put("Státusz", konyv.getStatus());
			konyvekObj.put("könyv", konyvObj);
			konyvekList.add(konyvekObj);
		}

		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))) {
			writer.write(konyvekList.toJSONString());
			writer.flush();
		} catch (IOException e) {
			Utilities.showMessage("IOException: " + e.getMessage(), 0);
		}
	}

}
