package com.boot.rest.api.book.war.dao;

import org.springframework.data.repository.CrudRepository;

import com.boot.rest.api.book.war.bean.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	
}
