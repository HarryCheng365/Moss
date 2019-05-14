package whu.software.moss.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import whu.software.moss.Entity.ClockIn;

public interface ClockInRepository extends JpaRepository<ClockIn,Integer> {
}
