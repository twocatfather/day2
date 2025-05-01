package com.study.day2.library.repository;

import com.study.day2.library.model.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepository {

    // 로거
    // ConcurrentHashMap memberStore
    private final Map<Long, Member> memberStore = new ConcurrentHashMap<>();
    // emailIndex -> isbn 고유하다
    private final Map<String, Long> emailIndex = new ConcurrentHashMap<>();
    // ID 생성을위한 AtomicLong
    private final AtomicLong sequence = new AtomicLong(1);

    /**
     * 모든 회원 조회
     */

    /**
     * ID로 회원 조회
     */

    /**
     * Email 로 회원조회
     */
    public Optional<Member> findByEmail(String email) {
        Long id = emailIndex.get(email);

        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(memberStore.get(id));
    }

    /**
     * 키워드로 회원 검색 (리스트)
     */
    public List<Member> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String lowerKeyword = keyword.toLowerCase();
        return memberStore.values().stream()
                .filter(member ->
                        member.getName().toLowerCase().contains(lowerKeyword) ||
                                member.getEmail().toLowerCase().contains(lowerKeyword)
                )
                .toList();
    }

    /**
     * 회원저장(생성 / 수정)
     */

    /**
     * 회원 삭제
     */
}
