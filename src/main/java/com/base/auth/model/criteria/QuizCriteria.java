package com.base.auth.model.criteria;


import com.base.auth.model.Quiz;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuizCriteria implements Serializable {
    private Long id;
    private String question;
    public Specification<Quiz> getSpecification() {
        return new Specification<Quiz>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(!StringUtils.isEmpty(getQuestion())){
                    predicates.add(cb.like(cb.lower(root.get("quiz")), "%"+getQuestion().toLowerCase()+"%"));
                }


                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
