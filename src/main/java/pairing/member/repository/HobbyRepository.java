package pairing.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pairing.member.entity.Hobby;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
