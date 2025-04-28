package com.study.day2.library.service;

import com.study.day2.library.dto.BookRequest;
import com.study.day2.library.model.Book;
import com.study.day2.library.repository.BookRepository;
import com.study.day2.library.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    /**
     * 새로운 도서 생성
     */
    public Book createBook(BookRequest request) {

        // ISBN 중복체크
        bookRepository.findByIsbn(request.getIsbn()).ifPresent(book -> {
            throw new IllegalArgumentException("Book with ISBN " + request.getIsbn() + " already exists");
        });

        Book book = request.toEntity();
        return bookRepository.save(book);
    }

    /**
     * 기존 도서 수정
     */


}
