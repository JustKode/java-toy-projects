package justkode.example.springbootelasticsearch.domain.group;

import justkode.example.springbootelasticsearch.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users"})
@Table(name = "user_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "group")
    private Set<User> users;
}
