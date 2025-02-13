package pairing.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pairing.member.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
