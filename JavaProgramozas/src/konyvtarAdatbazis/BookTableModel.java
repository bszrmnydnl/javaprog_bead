	package konyvtarAdatbazis;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BookTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static List<Book> books;
	
	public BookTableModel() {
		books = new ArrayList<Book>();
	}
	
	public BookTableModel(List<Book> books) {
		BookTableModel.books = books;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}
	
	public List<Book> getBooks(){
		return books;
	}
	
	public static void emptyList() {
		books.clear();
	}
	

	public static void select(int rowIndex) {
		Book book = books.get(rowIndex);
		book.setSelected(true);
	}
	
	public static void deSelect(int rowIndex) {
		Book book = books.get(rowIndex);
		book.setSelected(false);
	}
	
	
	
	
	
	
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
			case 0: return Boolean.class;
			case 1: return Integer.class;
			case 2: return String.class;
			case 3: return String.class;
			case 4: return Date.class;
			case 5: return String.class;
		}
		return Object.class;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
			case 0: return "";
			case 1: return "ID";
			case 2: return "Szerzõ";
			case 3: return "Cím";
			case 4: return "Megjelenés dátuma";
			case 5: return "Státusz";
		}
		return null;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
	    switch (columnIndex) {
	    	case 0: return book.isSelected();
	    	case 1: return book.getId();
	        case 2: return book.getAuthor();
	        case 3: return book.getTitle();
	        case 4: return book.getDateOfRelease();
	        case 5: return book.getStatus();
	    }
	    return null;
	}
	
	public void setValueAt(String value, int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
	    switch (columnIndex) {
	    	case 0: 
	    		book.setSelected(Boolean.getBoolean(value));
	    		fireTableRowsUpdated(rowIndex, rowIndex);
	    		break;
	    	case 1: 
	    		book.setId(Integer.valueOf(value));
	    		fireTableRowsUpdated(rowIndex, rowIndex);
	    		break;
	        case 2: 
	        	book.setAuthor((String)value);
	        	fireTableRowsUpdated(rowIndex, rowIndex);
	        	break;
	        case 3: 
	        	book.setTitle((String)value);
	        	fireTableRowsUpdated(rowIndex, rowIndex);
	        	break;
	        case 4: 
	        	book.setDateOfRelease(new Date(Long.valueOf(value)));
	        	fireTableRowsUpdated(rowIndex, rowIndex);
	        	break;
	        case 5: 
	        	book.setStatus((String)value);
	        	fireTableRowsUpdated(rowIndex, rowIndex);
	        	break;
	    }
	}
	
	public boolean isCellEditable(int row, int col) {
		if (col == 0) {
			return true;
		}else {
			return false;
		}
	}
	 
	public void add(Book book) {
		books.add(book);
		int row = books.indexOf(book);
		fireTableRowsInserted(row,row);
	}
	
	public void remove(Book book) {
		if(books.contains(book)) {
			int row = books.indexOf(book);
			books.remove(row);
			fireTableRowsDeleted(row, row);
		}
	}
	
	public void remove(int id) {
		Book toRemove = null;
		for(Book book : books) {
			if(book.getId() == id) {
				toRemove=book;
			}
		}
		int row = books.indexOf(toRemove);
		books.remove(toRemove);
		fireTableRowsDeleted(row, row);
	}
	
}
