import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
	private static Scanner input = new Scanner(System.in);
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static int centralBookID = 101;
	private static int centralAuthorID = 1001;
	
	private static ArrayList<Book> Books = new ArrayList<Book>();
	private static ArrayList<Author> Authors = new ArrayList<Author>();
	
	public static void main(String args[]) {
		try {
			menu();
		} catch(Exception e) {
			System.out.println("System Error, Rebooting...");
			menu();
		}
	}
	
	private static void menu() {
		System.out.println("*** Welcome to The Book Inventory System ***");
		System.out.println("1) Author Menu");
		System.out.println("2) Book Menu");
		System.out.println("3) Shut System Down");
		
		String choice = input.next();
		switch(choice) {
		case "1": {
			try {
				authorMenu();
			} catch (IOException e) {
				System.out.println("Error, Please try again");
				menu();
			}
			break;
		}
		case "2": {
			bookMenu();
			break;
		}
		case "3": {
			System.out.println("System shutting down...");
			System.exit(0);
			break;
		}
		case "r": {
			System.out.println("Populating the system with data...");
			System.out.println("Successfully populated the system");
			populate();
			break;
		}
		default: {
			System.out.println("Invalid option, try again!");
			menu();
		}
		}
		menu();
	}
	
	private static void populate() {
		/*
		 * Method for populating hard coded data, purposely for testing.
		 */
		String[] chapters = new String[5];
		Author a1 = new Author(centralAuthorID, "J.K. Rowling");
		centralAuthorID++;
		Author a2 = new Author(centralAuthorID, "Tom Clancy");
		centralAuthorID++;
		
		Book b1 = new Book(centralBookID, "Harry Potter 1", a1, 100, "Fiction", 567, chapters, "Harper Collins", 15);
		centralBookID++;
		Book b2 = new Book(centralBookID, "Harry Potter 2", a1, 100, "Fiction", 657, chapters, "Harper Collins", 15);
		centralBookID++;
		Book b3 = new Book(centralBookID, "Sum Of All Fears", a2, 200, "Fiction", 957, chapters, "Penguin", 12);
		centralBookID++;
		Book b4 = new Book(centralBookID, "Patriot Game", a2, 150, "Fiction", 1052, chapters, "Penguin", 10);
		centralBookID++;
		
		ArrayList<Book> a1Book = new ArrayList<Book>();
		a1Book.add(b1);
		a1Book.add(b2);
		a1.setBooks(a1Book);
		
		ArrayList<Book> a2Book = new ArrayList<Book>();
		a2Book.add(b3);
		a2Book.add(b4);
		a2.setBooks(a2Book);
		
		Authors.add(a1);
		Authors.add(a2);
		
		Books.add(b1);
		Books.add(b2);
		Books.add(b3);
		Books.add(b4);
		menu();
	}
	
	
	private static void authorMenu() throws IOException {
		System.out.println("1) Create Author");
		System.out.println("2) Edit Author");
		System.out.println("3) Add Book To Author");
		System.out.println("4) Remove Book From Author");
		System.out.println("5) View All Authors");
		System.out.println("6) View All Books Associated With Author");
		System.out.println("7) Return to Main Menu");
		String choice = input.next();
		switch(choice) {
		case "1": {
			createAuthor();
			break;
		}
		case "2": {
			editAuthor();
			break;
		}
		case "3": {
			addBookToAuthor();
			authorMenu();
			break;
		}
		case "4": {
			removeBookFromAuthor();
			break;
		}
		case "5": {
			viewAuthors();
			break;
		}
		case "6": {
			viewAuthorsBook();
		}
		case "7": {
			System.out.println("Returning to main menu...");
			menu();
			break;
		}
		default: {
			System.out.println("Invalid option, try again!");
			authorMenu();
		}
		}
		authorMenu();
	}
	
	private static void createAuthor() throws IOException {
		/*
		 * Method for creating an author object
		 */
		System.out.println("Enter The Author's Name");
		String name = reader.readLine();
		Author author = new Author(centralAuthorID, name);
		centralAuthorID++;
		Authors.add(author);
		System.out.println("Author Has Been Successfully Created");
	}

	private static void editAuthor() throws IOException {
		/*
		 * Method for editing author's details
		 */
		Author author = selectAuthor();
		System.out.println("Editing Author: " + author.getName());
		System.out.println("1) Change Author's Name");
		System.out.println("2) Delete Author");
		System.out.println("3) Return To Author Menu");
		String choice = input.next();
		switch(choice) {
		case "1": {
			System.out.println("Enter The Author's New Name");
			for(Author i: Authors) {
				if(author.getId() == i.getId()) {
					i.setName(reader.readLine());
					System.out.println("The Author's Name Has Been Successfully Changed To: " + i.getName());
					authorMenu();
				}
			}
			break;
		}
		case "2": {
			int j = 0;
			for(Author i: Authors) {
				if(author.getId() == i.getId()) {
					System.out.println("Author " + i.getName() + " Has Been Successfully Removed");
					for(Book k: Books) {
						if(k.getAuthor().getId() == i.getId()) {
							k.setAuthor(null);
						}
					}
					break;
				}
				j++;
			}
			/*
			 * Remove object after the for loop to prevent errors
			 */
			Authors.remove(j);
			authorMenu();
			break;
		}
		case "3": {
			authorMenu();
			break;
		}
		default: {
			System.out.println("Invalid option, Please try again!");
			editAuthor();
			break;
		}
		}
		authorMenu();
	}
	
	
	private static void addBookToAuthor() {
		/*
		 * Method to add a book to the author's object book arraylist
	 	 */
		int id = selectAuthor().getId();
		for(Author i: Authors) {
			if(i.getId() == id) {
				System.out.println("Enter Book's ID");
				viewBookTitles();
				int bookID = input.nextInt();
				for(Book j: Books) {
					if(j.getId() == bookID) {
						for(Book k: i.getBooks()) {
							if(k.getId() == bookID) {
								System.out.println( i.getName() + " Is Already The Author Of " + k.getName());
								return;
							}
						}
						System.out.println(j.getName() + " Has Been Successfully Added To The Author: " + i.getName());
						i.getBooks().add(j);
					}
				}
			}
		}
	}
	
	
	private static void removeBookFromAuthor() throws IOException {
		/*
		 * Method for removing a book from the author's object book arraylist
		 */
		viewAuthorsBook();
		System.out.println("Enter The Book's ID To Remove From This Author");
		int id = input.nextInt();
		for(Author i: Authors) {
			for(Book b: i.getBooks()) {
				if(b.getId() == id) {
					i.removeBook(id);
					for(Book j: Books) {
						j.setAuthor(null);
					}
					return;
				}
			}
		}
		System.out.println("The Entered Book ID Does Not Match, Please Try Again");
		removeBookFromAuthor();
	}
	

	private static void viewAuthors() {
		/*
		 * Method for printing author's ID and Name
		 */
		System.out.println("*** List Of All Authors ***");
		for(Author i: Authors) {
			System.out.println("[ID: " + i.getId() + "] " + i.getName());
		}
	}

	private static void viewAuthorsBook() {
		/*
		 * Method for viewing author's book arraylist
		 */
		viewAuthors();
		System.out.println("Enter The Author's ID To View Their Books");
		int id = input.nextInt();
		for(Author i: Authors) {
			if(i.getId() == id) {
				i.viewBooks();
			}
		}
	}

	private static void bookMenu() {
		System.out.println("1) Create Book");
		System.out.println("2) Edit Book");
		System.out.println("3) Remove Book");
		System.out.println("4) View All Books");
		System.out.println("5) Return to Main Menu");
		String choice = input.next();
		switch(choice) {
		case "1": {
			try {
				createBook();
			} catch (IOException e) {
				System.out.println("Error, Please try again");
			}
			break;
		}
		case "2": {
			try {
				editBook();
			} catch (IOException e) {
				System.out.println("Error, Please try again");
			}
			break;
		}
		case "3": {
			removeBook();
			break;
		}
		case "4": {
			System.out.println("*** List Of All Books ***");
			System.out.println("-------------------------");
			for(Book i: Books) {
				i.bookDetails();
			}
			break;
		}
		case "5": {
			System.out.println("Returning to main menu...");
			menu();
			break;
		}
		default: {
			System.out.println("Invalid option, try again!");
			menu();
		}
		}
		bookMenu();
	}
	
	private static void createBook() throws IOException {
		/*
		 * Method for creating a book object
		 */
		Book book = new Book();
		book.setId(centralBookID);
		centralBookID++;
		System.out.println("Enter The Book's Name");
		String name = reader.readLine();
		System.out.println("Select The Book's Author");
		System.out.println("1) New Author");
		System.out.println("2) Existing Author");
		String choice = input.next();
		switch(choice) {
		case "1": {
			System.out.println("New Author :: Enter The Author's Name");
			String authorName = input.next();
			Author author = new Author(centralAuthorID, authorName);
			book.setAuthor(author);
			book.getAuthor().getBooks().add(book);
			System.out.println("Author Successfully Set To: " + author.getName());
			break;
		}
		case "2": {
			System.out.println("Existing Author :: Enter The Author's ID");
			book.setAuthor(selectAuthor());
			book.getAuthor().getBooks().add(book);
			break;
		}
		default:
			System.out.println("Error, Please try again!");
			createBook();
			break;
		}
		System.out.println("Enter The Book's Quantity");
		int qty = input.nextInt();
		System.out.println("Enter The Book's Category");
		String category = input.next();
		System.out.println("Enter The Book's Page Amount");
		int pages = input.nextInt();
		System.out.println("Enter The The Amount Of Chapters");
		int numChapters = input.nextInt();
		String[] chapters = new String[numChapters];
//		for(int i=0;i<chapters.length;i++) {
//			System.out.println("Insert the the name of the Chapter " + (i+1));
//			chapters[i] = reader.readLine();
//		}
		
		int j = 0;
		while(j<chapters.length) {
			System.out.println("Insert the the name of the Chapter " + (j+1));
			chapters[j] = reader.readLine();
			j++;
		}
		System.out.println("Enter The Book's Publisher");
		String publisher = input.next();
		System.out.println("Enter The Book's Price");
		double price = input.nextDouble();
		System.out.println("The Book Has Been Successfully Created");
		/*
		 * Value assigned later in case of inputmismatch errors
		 */
		book.setName(name);
		book.setQty(qty);
		book.setCategory(category);
		book.setPages(pages);
		book.setChapters(chapters);
		book.setPublisher(publisher);
		book.setPrice(price);
		book.bookDetails();
		Books.add(book);
	}
		
	
	private static void editBook() throws IOException {
		/*
		 * Method for editing book details
		 */
		viewBookTitles();
		System.out.println("Enter The Book's ID To Edit");
		int id = input.nextInt();
		for(Book i: Books) {
			if(i.getId() == id) {
				System.out.println("Now Editing: " + i.getName());
				System.out.println("1) Change Book's Title");
				System.out.println("2) Change Book's Genre");
				System.out.println("3) Change Book's Price");
				System.out.println("4) Change Book's Publisher");
				System.out.println("5) Change Book's Quantity");
				System.out.println("6) Return To Book Menu");
				String choice = input.next();
				switch(choice) {
				case "1": {
					System.out.println("Enter A New Book Title To Replace");
					i.setName(reader.readLine());
					System.out.println("The Book's Name Has Been Updated To: " + i.getName());
					break;
				}
				case "2": {
					System.out.println("Enter A New Book Genre To Replace");
					i.setCategory(reader.readLine());
					System.out.println("The Book's Genre Has Been Updated To: " + i.getCategory());
					break;
				}
				case "3": {
					System.out.println("Enter A New Book Price To Replace");
					i.setPrice(input.nextDouble());
					System.out.println("The Book's Price Has Been Updated To: " + i.getPrice());
					break;
				}
				case "4": {
					System.out.println("Enter A New Book Publisher To Replace");
					i.setPublisher(reader.readLine());
					System.out.println("The Book's Publisher Has Been Updated To: " + i.getPublisher());
					break;
				}
				case "5": {
					System.out.println("Enter A New Book Quantity To Replace");
					i.setQty(input.nextInt());
					System.out.println("The Book's Quantity Has Been Updated To: " + i.getQty());
					break;
				}
				case "6": {
					System.out.println("Returning To Book Menu...");
					bookMenu();
					break;
				}
				default: {
					System.out.println("Invalid option, Please try again!");
					editBook();
					break;
				}
			}
		}
		updateBook();
	}
}
	
	
	private static void removeBook() {
		/*
		 * Method for removing book from both the global arraylist and also the author's object book arraylist
		 */
		viewBookTitles();
		System.out.println("Select A Book To Delete");
		System.out.println("Enter The Book's ID");
		int id = input.nextInt();
		int position = 0;
		for(Book i: Books) {
			if(i.getId() == id) {
				i.getAuthor().removeBook(id);
				System.out.println("The Book \"" + i.getName() + "\" Has Been Successfully Removed From The System");
				break;
			}
			position++;
		}
		Books.remove(position);
	}
	
	private static void viewBookTitles() {
		/*
		 * Method for viewing book titles
		 */
		System.out.println("*** List Of All Book Titles ***");
		System.out.println("-------------------------");
		for(Book i: Books) {
			System.out.println("[ID: " + i.getId() + "] " + i.getName() + " :: " + i.getAuthor().getNumBooksPublished() + " Book(s) Published");
		}
	}
	
	private static Author selectAuthor() {
		/*
		 * Method for selecting an author object
		 */
		viewAuthors();
		System.out.println("Select An Author");
		int id = input.nextInt();
		for(Author i: Authors) {
			if(i.getId() == id) {
				return i;
			}
		}
		System.out.println("Error, Author Not Found Please Try Again!");
		selectAuthor();
		return null;
	}
	private static void updateBook() {
		/*
		 * Method for updating book details and matching global book arraylist with the author's object book arraylist
		 */
		for(Author a: Authors) {
			for(Book b: Books) {
				ArrayList<Book> book = new ArrayList<Book>();
				for(Book ab: a.getBooks()) {
					if(b.getAuthor() == a) {
						book.add(ab);
						a.setBooks(book);
						System.out.println("The List Of Books Has Been Updated!");
					}
				}
			}
		}
	}
}