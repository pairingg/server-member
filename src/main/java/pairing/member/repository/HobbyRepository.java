package pairing.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pairing.member.entity.Hobby;
import pairing.member.entity.Member;

import java.util.Optional;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Optional<Hobby> findByMember(Member byEmail);
}
