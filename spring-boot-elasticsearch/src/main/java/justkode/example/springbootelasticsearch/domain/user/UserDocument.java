package justkode.example.springbootelasticsearch.domain.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    public UserDocument(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.description = user.getDescription();
    }
}
