package com.base.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "db_user_base_video")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Video extends Auditable<String> {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.base.auth.service.id.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "banner")
    private String banner;
    @Column(name = "description", columnDefinition = "text")
    private String description;

}
