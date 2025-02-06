package pairing.member.staticData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pairing.member.staticData.entity.HobbyList;

@Repository
public interface HobbyListRepository extends JpaRepository<HobbyList, Long> {
}
