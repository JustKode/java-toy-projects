package justkode.example.springbootelasticsearch.domain.group;

import justkode.example.springbootelasticsearch.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "group")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    public GroupDocument(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
    }
}
