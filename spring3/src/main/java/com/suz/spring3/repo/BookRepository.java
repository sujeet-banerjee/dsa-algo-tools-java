package com.suz.spring3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suz.spring3.models.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
