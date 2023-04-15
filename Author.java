import java.util.ArrayList;

public class Author {
	private int id;
	private String name;
	private ArrayList<Book> books;
	private int numBooksPublished;
	
	public Author() {
		
	}
	
	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Author(int id, String name, ArrayList<Book> books) {
		this.id = id;
		this.name = name;
		this.books = books;
	}
	
	public Author(int id, String name, ArrayList<Book> books, int numBooksPublished) {
		this.id = id;
		this.name = name;
		this.books = books;
		this.numBooksPublished = numBooksPublished;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public int getNumBooksPublished() {
		numBooksPublished = books.size();
		return numBooksPublished;
	}

	public void removeBook(int id) {
		int counter = 0;
		for(Book i: books) {
			if(i.getId() == id) {
				System.out.println("Removing Book " + i.getName() + " From The Author " + i.getAuthor().getName());
				break;
			}
			counter++;
		}
		books.remove(counter);
	}
	
	public void viewBooks() {
		System.out.println("This Author Has A Total Published Books Of: " + getNumBooksPublished());
		System.out.println("Book(s) Written By " + getName());
		for(Book i: books) {
			System.out.println("[ID: " + i.getId() + "] " + i.getName());
		}
	}
}