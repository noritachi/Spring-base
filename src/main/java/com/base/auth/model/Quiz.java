package com.base.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "db_user_base_quiz")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Quiz extends Auditable<String> {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.base.auth.service.id.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    @Column(name = "question", columnDefinition = "LONGTEXT")
    private String question;

    @Column(name = "option1", columnDefinition = "LONGTEXT")
    private String option1;
    @Column(name = "option2", columnDefinition = "LONGTEXT")
    private String option2;
    @Column(name = "option3", columnDefinition = "LONGTEXT")
    private String option3;
    @Column(name = "option4", columnDefinition = "LONGTEXT")
    private String option4;

    private String answer;

}
