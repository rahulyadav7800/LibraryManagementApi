package com.library.api;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		Book created = bookService.addBook(book);
		if (created == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Book with ID " + book.getId() + " already exists.");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Integer id) {
		Book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Book with ID " + id + " not found.");
		}
		return ResponseEntity.ok(book);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
		Book book = bookService.updateBook(id, updatedBook);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Book with ID " + id + " not found.");
		}
		return ResponseEntity.ok(book);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
		boolean deleted = bookService.deleteBook(id);
		if (!deleted) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Book with ID " + id + " not found.");
		}
		return ResponseEntity.ok("Book with ID " + id + " deleted successfully.");
	}
}
