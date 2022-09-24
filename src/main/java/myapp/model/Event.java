package myapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import myapp.model.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
public class Event extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType = EventType.CREATED;

    @ManyToOne(
            targetEntity = User.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            table = "events",
            name = "user_id",
            nullable = false
    )
    @ToString.Exclude
    @JsonBackReference
    private User user;
    @OneToOne(
            targetEntity = File.class,
            cascade ={ CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY,
            optional = false,
            orphanRemoval = true
    )
    @JoinColumn(
            table = "events",
            name = "file_id",
            nullable = false
    )
    @ToString.Exclude
    @JsonManagedReference
    private File file;
}
