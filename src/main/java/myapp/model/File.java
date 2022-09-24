package myapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@ToString
public class File extends BaseEntity {

    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_path")
    private String filePath;
    @OneToOne(
            targetEntity = Event.class,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY,
            mappedBy = "file",
            optional = false,
            orphanRemoval = true
    )
    @ToString.Exclude
    @JsonBackReference
    private Event event;
}
