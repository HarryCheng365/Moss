package whu.software.moss.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import whu.software.moss.Entity.Account;

public interface AccountRepository  extends JpaRepository<Account,Integer> {
}
