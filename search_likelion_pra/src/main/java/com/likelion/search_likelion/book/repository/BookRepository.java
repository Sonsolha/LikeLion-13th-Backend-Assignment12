package com.likelion.search_likelion.book.domain.repository;

import com.likelion.search_likelion.book.domain.Book;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        SELECT b FROM Book b
        WHERE (:keyword IS NULL OR :keyword = '' 
               OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) 
               OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')))
    """)
    Page<Book> search(@Param("keyword") String keyword, Pageable pageable);
}
