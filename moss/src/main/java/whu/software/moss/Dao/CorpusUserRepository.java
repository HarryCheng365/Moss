package whu.software.moss.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import whu.software.moss.Entity.CorpusUser;


public interface CorpusUserRepository extends JpaRepository<CorpusUser,String> {

    CorpusUser findByCorpusId(String id);
}
