package justkode.example.springbootelasticsearch.domain.user;

import justkode.example.springbootelasticsearch.domain.group.Group;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"group"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
