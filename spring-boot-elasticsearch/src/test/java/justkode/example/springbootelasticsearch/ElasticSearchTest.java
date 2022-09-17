package justkode.example.springbootelasticsearch;

import justkode.example.springbootelasticsearch.domain.group.repository.GroupDocumentRepository;
import justkode.example.springbootelasticsearch.domain.group.repository.GroupRepository;
import justkode.example.springbootelasticsearch.domain.user.User;
import justkode.example.springbootelasticsearch.domain.user.UserDocument;
import justkode.example.springbootelasticsearch.domain.user.repository.UserDocumentRepository;
import justkode.example.springbootelasticsearch.domain.user.repository.UserRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@WebAppConfiguration
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDocumentRepository userDocumentRepository;

    @Test
    void 유저_검색_테스트() {
        User user1 = User.builder().name("박민재").description("안녕 애들아").build();
        User user2 = User.builder().name("김민재").description("안녕안녕 애들아").build();
        User user3 = User.builder().name("최민재").description("안녕 애들아 안녕").build();
        User user4 = User.builder().name("박범구").description("안녕?").build();
        User user5 = User.builder().name("김범구").description("안녕").build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        userDocumentRepository.save(new UserDocument(user1));
        userDocumentRepository.save(new UserDocument(user2));
        userDocumentRepository.save(new UserDocument(user3));
        userDocumentRepository.save(new UserDocument(user4));
        userDocumentRepository.save(new UserDocument(user5));

        Page<User> users = userRepository.findByName("민재", Pageable.ofSize(10));
        System.out.println("민재를 mysql에서 검색 시 = " + users.getContent());

        Page<UserDocument> userDocuments = userDocumentRepository.findByName("민재", Pageable.ofSize(10));
        System.out.println("민재를 ES에서 검색 시 = " + userDocuments.getContent());

        Page<User> users2 = userRepository.findByName("박민재", Pageable.ofSize(10));
        System.out.println("박민재를 mysql에서 검색 시 = " + users2.getContent());

        Page<UserDocument> userDocuments2 = userDocumentRepository.findByName("박민재", Pageable.ofSize(10));
        System.out.println("박민재를 ES에서 검색 시 = " + userDocuments2.getContent());


        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("description", "안녕");
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery).withPageable(Pageable.ofSize(10)).build();
        SearchHits<UserDocument> search = elasticsearchOperations.search(searchQuery, UserDocument.class);
        System.out.println("박민재를 ES에서 유사도 검색 시 = " + search.stream().map(SearchHit::getContent).collect(Collectors.toList()));
        System.out.println("유사도 = " + search.stream().map(SearchHit::getScore).collect(Collectors.toList()));


    }
}
