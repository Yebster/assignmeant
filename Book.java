
public class Book {
	private int id;
	private String name;
	private Author author;
	private int qty;
	private String category;
	private int pages;
	private String[] chapters;
	private String publisher;
	private double price;
	
	public Book() {
		
	}

	public Book(int id, String name, Author author, int qty, String category, int pages, String[] chapters,
			String publisher, double price) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.qty = qty;
		this.category = category;
		this.pages = pages;
		this.chapters = chapters;
		this.publisher = publisher;
		this.price = price;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String[] getChapters() {
		return chapters;
	}

	public void setChapters(String[] chapters) {
		this.chapters = chapters;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void bookDetails() {
		System.out.println(getName() + " [ID: " + getId()+"]");
		if(author == null) {
			System.out.println("Author: N/A");
		} else {
			System.out.println("Author: " + getAuthor().getName());
		}
		System.out.println("Quantity: " + getQty());
		System.out.println("Category: " + getCategory());
		System.out.println("Pages: " + getPages());
		System.out.println("Chapters:");
		for(int i=0;i<chapters.length;i++) {
			if(getChapters()[i] == null) {
				System.out.println("Chapter " + (i+1) + ": N/A");
			} else {
			System.out.println("\tChapter " + (i+1) + ": " + getChapters()[i]);
			}
		}
		System.out.println("Publisher: " + getPublisher());
		System.out.println("Price: $" + getPrice());
		System.out.println("-------------------------");
	}
	
}