package whu.software.moss.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import whu.software.moss.Entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
    UserInfo findByUsername(String username);
}
