package com.boot.rest.api.book.war.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.rest.api.book.war.bean.Book;
import com.boot.rest.api.book.war.dao.BookRepository;

/* We can also use @Service instead of @Component*/
@Component // Scan : spring can create object where we use @Autowired.
public class BookService {
	/* Service Layer interact with DAO(Database Layer) */
	@Autowired
	private BookRepository bookRepository;

//	/* Test Service using Static Data */
//	private static List<Book> bookList = new ArrayList<>();
//
//	static {
//		bookList.add(new Book(12, "Java Complete Reference", "Mr. XYZ"));
//		bookList.add(new Book(36, "Head First to Java", "Mr. ABC"));
//		bookList.add(new Book(50, "Think in Java", "Mr. PQR"));
//	}
//
	/* Get all books */
	public List<Book> getAllBooks() {
		List<Book> getBookList = (List<Book>) this.bookRepository.findAll();
		return getBookList;
	}

	/* Get single book by id */
	public Book getBookByID(int id) {
		/* Get Book based on id using Stream API */
//		Book book = null;
//		try {
//			book = bookList.stream().filter(e -> e.getId() == id).findFirst().get();
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}

		Book book = null;
		try {
			Optional<Book> getBookById = this.bookRepository.findById(id);
			book = getBookById.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	/* Add a new book : add data in JSON */
	public Book addBook(Book book) {
//		if (bookList != null)
//			bookList.add(book);

		Book getAddedNewBook = this.bookRepository.save(book);
		return getAddedNewBook;
	}

	/* Delete single book by id */
	public void deleteBookById(int id) {
//		if (bookList != null && bookList.size() > 0) {
//			List<Book> newBookList = new ArrayList<>();
//			for (Book getBook : bookList) {
//				if (getBook.getId() != id) {
//					newBookList.add(getBook);
//				}
//			}
//			bookList.clear();
//			bookList.addAll(newBookList);
//		}

		this.bookRepository.deleteById(id);
	}

	/* Update book : add data in JSON */
	public void updateBookById(Book updatedBook, int id) {
//		if (bookList != null && bookList.size() > 0) {
//			List<Book> newBookList = new ArrayList<>();
//			for (Book getBook : bookList) {
//				if (getBook.getId() == id) {
//					Book book = new Book();
//					book.setId(updatedBook.getId());
//					book.setTitle(updatedBook.getTitle());
//					book.setAuthor(updatedBook.getAuthor());
//					newBookList.add(book);
//				} else {
//					newBookList.add(getBook);
//				}
//			}
//			bookList.clear();
//			bookList.addAll(newBookList);
//		}

		updatedBook.setId(id);
		this.bookRepository.save(updatedBook);

	}
}
