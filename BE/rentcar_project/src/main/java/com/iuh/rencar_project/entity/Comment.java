package com.iuh.rencar_project.entity;

import com.iuh.rencar_project.utils.enums.Status;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    private final int MAX_LEVEL = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(nullable = false)
    private String content;

    private int likes;

    private int dislike;

    private int level;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status status;

    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Comment parent;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Override
    public String toString() {
        return "Comment{" +
                "MAX_LEVEL=" + MAX_LEVEL +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateCreated=" + dateCreated +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", dislike=" + dislike +
                ", level=" + level +
                ", status=" + status +
                ", parent=" + parent +
                ", post=" + post +
                '}';
    }
}
