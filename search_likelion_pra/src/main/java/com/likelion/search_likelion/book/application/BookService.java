package com.likelion.search_likelion.book.application;

import com.likelion.search_likelion.book.api.dto.request.BookSaveReqDto;
import com.likelion.search_likelion.book.api.dto.response.BookResDto;
import com.likelion.search_likelion.book.domain.*;
import com.likelion.search_likelion.book.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Long save(BookSaveReqDto req) {
        Book book = Book.builder()
                .title(req.title())
                .author(req.author())
                .genre(req.genre())
                .build();
        bookRepository.save(book);
        return book.getId();
    }

    public Page<BookResDto> search(String keyword, Pageable pageable) {
        return bookRepository.search(keyword, pageable).map(BookResDto::from);
    }

    public BookResDto findById(Long id) {
        Book b = bookRepository.findById(id).orElseThrow();
        return BookResDto.from(b);
    }
}
