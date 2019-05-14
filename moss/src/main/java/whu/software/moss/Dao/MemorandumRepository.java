package whu.software.moss.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import whu.software.moss.Entity.Memorandum;

public interface MemorandumRepository extends JpaRepository<Memorandum,Integer> {
    Memorandum findById(int id);

}
