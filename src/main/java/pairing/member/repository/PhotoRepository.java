package pairing.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pairing.member.entity.Member;
import pairing.member.entity.Photo;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findByMember(Member byEmail);
}
