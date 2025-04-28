package com.study.day2.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class LoanRepository {
    // 로거

    // 동시성 이슈를 고려한 ConcurrentHashMap 구성한 loanStore
    // Id 생성을 위한 AtomicLong 사용

    /**
     *  모든 대출 조회
     */

    /**
     * ID 로 특정 조회 (Optional)
     */


    /**
     *  회원 ID 로 대출 목록 조회
     */

    /**
     *  도서 Id 로 대출 목록 조회
     */

    /**
     *  반납 여부로 대출 목록 필터링
     */

    /**
     *  연체된 대출 목록 조회
     */

    /**
     *  대출 저장(생성/수정)
     */

    /**
     * 대출 삭제
     */

    /**
     * 도서 ID 로 대출 삭제
     */

    /**
     * 회원 ID로 대출 삭제
     */
}
