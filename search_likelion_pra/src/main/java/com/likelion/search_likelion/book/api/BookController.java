package com.likelion.search_likelion.book.api;

import com.likelion.search_likelion.book.api.dto.request.BookSaveReqDto;
import com.likelion.search_likelion.book.api.dto.response.BookResDto;
import com.likelion.search_likelion.book.application.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody BookSaveReqDto req) {
        Long id = bookService.save(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<BookResDto>> list(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(bookService.search(keyword, pageable));
    }
}
