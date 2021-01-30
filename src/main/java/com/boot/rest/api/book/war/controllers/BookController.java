package com.boot.rest.api.book.war.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.rest.api.book.war.bean.Book;
import com.boot.rest.api.book.war.services.BookService;

@RestController /* @RestController = @Controller + @ResponseBody */
public class BookController {

	@Autowired
	private BookService bookService;

	/*
	 * @GetMapping("/books") = @RequestMapping(value = "/books", method =
	 * RequestMethod.GET)
	 */
	/* Get all books handler */
//	@GetMapping("/books")
//	public List<Book> getBooks() {
//		/* Response : Spring can automatic convert book object to JSON using jackson */
//		return this.bookService.getAllBooks();
//	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		/* ResponseEntity : Handing HttpStatus Code */
		/* Response : Spring can automatic convert book object to JSON using jackson */

		List<Book> listOfBook = this.bookService.getAllBooks();
		/* If book list is empty than it return not found with 404 status code */
		if (listOfBook.size() == 0) {
			/* build() is created object of ResponseEntity with status */
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		/* For Success 200 OK : Return List of Book */
		return ResponseEntity.of(Optional.of(listOfBook));
	}

//	/* Get single book handler */
//	@GetMapping("/books/{id}")
//	public Book getBookById(@PathVariable("id") int id) {
//		/* Response : Spring can automatic convert book object to JSON using jackson */
//		return this.bookService.getBookByID(id);
//	}

	/* Get single book handler with HttpStatus Code */
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
		Book book = this.bookService.getBookByID(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

//	/* Add new book handler */
//	@PostMapping("/books")
//	public Book addNewBook(@RequestBody Book book) {
//		Book b = this.bookService.addBook(book);
//		System.out.println(book);
//		return b;
//	}

	/* Add new book handler with HttpStatus Code */
	@PostMapping("/books")
	public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.bookService.addBook(book);
			System.out.println(book);
//			return ResponseEntity.of(Optional.of(b));
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	/* Delete book handler */
//	@DeleteMapping("/books/{bookId}")
//	public void deleteBookById(@PathVariable("bookId") int bookId) {
//		this.bookService.deleteBookById(bookId);
//	}

	/* Delete book handler with HttpStatus Code */
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("bookId") int bookId) {
		try {
			this.bookService.deleteBookById(bookId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/* Update book handler */
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBookById(@RequestBody Book updatedBook, @PathVariable("bookId") int bookId) {
		try {
			this.bookService.updateBookById(updatedBook, bookId);
			return ResponseEntity.ok().body(updatedBook);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
