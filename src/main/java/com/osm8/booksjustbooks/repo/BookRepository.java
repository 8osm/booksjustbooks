package com.osm8.booksjustbooks.repo;

import com.osm8.booksjustbooks.models.BookModel;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookModel, Long> {
}
