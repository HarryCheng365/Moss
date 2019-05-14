package whu.software.moss.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import whu.software.moss.Entity.Corpus;

public interface CorpusRepository extends JpaRepository<Corpus,Integer> {
    
}
