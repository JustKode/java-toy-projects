package justkode.example.springbootelasticsearch.domain.user.repository;

import justkode.example.springbootelasticsearch.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByName(String name, Pageable pageable);
}
