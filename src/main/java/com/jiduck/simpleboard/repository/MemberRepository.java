package com.jiduck.simpleboard.repository;

import com.jiduck.simpleboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    Long countByUsername(String username);

    Long countByEmail(String email);

    Long countByNickname(String nickname);
}
