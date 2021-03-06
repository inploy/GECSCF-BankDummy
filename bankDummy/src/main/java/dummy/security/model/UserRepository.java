package dummy.security.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT user FROM User user "
            + "INNER JOIN FETCH user.roles AS role "
            + "INNER JOIN FETCH role.privileges AS privilege "
            + "WHERE user.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT DISTINCT user FROM User user "
            + "INNER JOIN FETCH user.roles AS role "
            + "INNER JOIN FETCH role.privileges AS privilege "
            + "WHERE user.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u "
            + "INNER JOIN FETCH u.roles AS role "
            + "INNER JOIN FETCH role.privileges AS privilege "
            + "WHERE u.username LIKE %?1% "
            + "AND u.fullName LIKE %?2% "
            + "AND role.name LIKE %?3%")
    List<User> findByUserLike(String username, String fullName, String role);

    @Query("SELECT u FROM User u "
            + "INNER JOIN FETCH u.roles AS role "
            + "INNER JOIN FETCH role.privileges AS privilege "
            + "WHERE u.username LIKE %?1% "
            + "AND u.fullName LIKE %?2% "
            + "AND role.name LIKE %?3% "
            + "AND u.enabled = ?4")
    List<User> findByUserLike(String username, String fullName, String role, Boolean enabled);

}