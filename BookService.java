package com.library.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	private final List<Book> bookList = new ArrayList<>();

	public Book addBook(Book book) {
		for (Book existing : bookList) {
			if (existing.getId().equals(book.getId())) {
				return null;
			}
		}
		bookList.add(book);
		return book;
	}

	public List<Book> getAllBooks() {
		return bookList;
	}

	public Book getBookById(Integer id) {
		for (Book book : bookList) {
			if (book.getId().equals(id)) {
				return book;
			}
		}
		return null;
	}

	public Book updateBook(Integer id, Book updatedBook) {
		for (Book book : bookList) {
			if (book.getId().equals(id)) {
				book.setTitle(updatedBook.getTitle());
				book.setAuthor(updatedBook.getAuthor());
				book.setCategory(updatedBook.getCategory());
				book.setAvailableCopies(updatedBook.getAvailableCopies());
				return book;
			}
		}
		return null;
	}

	public boolean deleteBook(Integer id) {
		return bookList.removeIf(book -> book.getId().equals(id));
	}
}
