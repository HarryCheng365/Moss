package whu.software.moss.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import whu.software.moss.Entity.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder,Integer> {
}
