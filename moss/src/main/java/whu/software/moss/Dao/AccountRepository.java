package whu.software.moss.Dao;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import sun.jvm.hotspot.debugger.Page;
import whu.software.moss.Entity.Account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;

public interface AccountRepository  extends JpaRepository<Account,String> {

    Account findByUsername(String username);

}
