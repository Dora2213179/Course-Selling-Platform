package com.tnduck.newinstitute.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ductn
 * @project The new institute
 * @created 31/01/2024 - 10:33 PM
 */
@Entity
@Table(name = "tags")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagCourse extends AbstractBaseEntity{
    @Column(name = "name", nullable = false, length = 500)
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "course_tags",
            joinColumns = @JoinColumn(
                    name = "tag_id",
                    foreignKey = @ForeignKey(
                            name = "fk_course_tags_tag_id",
                            foreignKeyDefinition = "FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE"
                    ),
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    foreignKey = @ForeignKey(
                            name = "fk_course_tags_course_id",
                            foreignKeyDefinition = "FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE"
                    ),
                    nullable = false
            ),
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = {"course_id", "tag_id"},
                            name = "uk_course_tags_course_id_tag_id"
                    )
            }
    )
    @Builder.Default
    private Set<Course> courses = new HashSet<>();
}
