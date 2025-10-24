package com.likelion.search_likelion.book.api.dto.response;

import com.likelion.search_likelion.book.domain.Book;
import lombok.Builder;

@Builder
public record BookResDto(Long id, String title, String author, String genre) {
    public static BookResDto from(Book b) {
        return BookResDto.builder()
                .id(b.getId())
                .title(b.getTitle())
                .author(b.getAuthor())
                .genre(b.getGenre())
                .build();
    }
}
