package justkode.example.springbootelasticsearch.domain.user.repository;

import justkode.example.springbootelasticsearch.domain.user.UserDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, Long> {
    Page<UserDocument> findByName(String name, Pageable pageable);
}
