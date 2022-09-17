package justkode.example.springbootelasticsearch.domain.group.repository;

import justkode.example.springbootelasticsearch.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDocumentRepository extends JpaRepository<Group, Long> {
}
