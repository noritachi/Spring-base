package com.base.auth.model.criteria;


import com.base.auth.model.Article;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleCriteria implements Serializable {
    private Long id;
    private Integer status;
    private String title;
    private String content;
    private String avatar;
    private String banner;
    private String description;
    public Specification<Article> getSpecification() {
        return new Specification<Article>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getStatus() != null){
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                if(!StringUtils.isEmpty(getTitle())){
                    predicates.add(cb.like(cb.lower(root.get("title")), "%"+getTitle().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getContent())){
                    predicates.add(cb.like(cb.lower(root.get("content")), "%"+getContent().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getAvatar())){
                    predicates.add(cb.like(cb.lower(root.get("avatar")), "%"+getAvatar().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getBanner())){
                    predicates.add(cb.like(cb.lower(root.get("banner")), "%"+getBanner().toLowerCase()+"%"));
                }
                if(!StringUtils.isEmpty(getDescription())){
                    predicates.add(cb.like(cb.lower(root.get("description")), "%"+getDescription().toLowerCase()+"%"));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
