package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.domain.exam.data.Exam;
import ua.domain.user.User;

import java.util.List;

/**
 * Created by Adevi on 8/9/2014.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query ("SELECT u FROM User u WHERE u.login = :login")
    User find(@Param("login")String login);
}
