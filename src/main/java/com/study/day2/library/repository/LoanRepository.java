package com.study.day2.library.repository;

import com.study.day2.library.model.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LoanRepository {
    // 로거
    private static final Logger logger = LoggerFactory.getLogger(LoanRepository.class);
    // 동시성 이슈를 고려한 ConcurrentHashMap 구성한 loanStore
    private final Map<Long, Loan> loanStore = new ConcurrentHashMap<>();
    // Id 생성을 위한 AtomicLong 사용
    private final AtomicLong sequence = new AtomicLong(1);

    /**
     *  모든 대출 조회
     */
    public List<Loan> findAll() {
        logger.debug("Finding all loans");
        return new ArrayList<>(loanStore.values());
    }

    /**
     * ID 로 특정 조회 (Optional)
     */
    public Optional<Loan> findById(Long id) {
        return Optional.ofNullable(loanStore.get(id));
    }


    /**
     *  회원 ID 로 대출 목록 조회
     */
    public List<Loan> findByMemberId(Long memberId) {
        return loanStore.values().stream()
                .filter(loan -> loan.getMember() != null &&
                        loan.getMember().getId().equals(memberId))
                .toList();
    }

    /**
     *  도서 Id 로 대출 목록 조회
     */


    /**
     *  반납 여부로 대출 목록 필터링
     */
    public List<Loan> findByReturned(boolean returned) {
        return loanStore.values().stream()
                .filter(loan -> loan.isReturned() == returned)
                .toList();
    }

    /**
     *  연체된 대출 목록 조회
     */
    public List<Loan> findOverdueLoans() {
        LocalDateTime now = LocalDateTime.now();

        return loanStore.values().stream()
                // loan.getDueDate() 2025.05.01 19:20, 2025.05.01 19:25
                .filter(loan -> !loan.isReturned() && loan.getDueDate().isBefore(now))
                .toList();
    }


    /**
     *  대출 저장(생성/수정)
     */
    public Loan save(Loan loan) {
        if (loan.getId() == null) {
            Long id = sequence.getAndIncrement();
            loan.setId(id);

        } else {
            // 기존 대출 수정

        }

        loanStore.put(loan.getId(), loan);
        return loan;
    }

    /**
     * 대출 삭제
     */
    public boolean delete(Long id) {
        return loanStore.remove(id) != null;
    }

    /**
     * 도서 ID 로 대출 삭제
     */
    public void deleteByBookId(Long bookId) {
        List<Long> loanIds = loanStore.values().stream()
                .filter(loan -> loan.getBook() != null &&
                        loan.getBook().getId().equals(bookId))
                .map(Loan::getId)
                .toList();

        loanIds.forEach(this::delete);
    }

    /**
     * 회원 ID로 대출 삭제
     */
}
