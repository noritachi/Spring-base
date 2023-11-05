package com.base.auth.model.criteria;


import com.base.auth.model.Video;
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
public class VideoCriteria implements Serializable {
    private Long id;
    private Integer status;
    private String title;
    private String url;
    private String avatar;
    private String banner;
    private String description;
    public Specification<Video> getSpecification() {
        return new Specification<Video>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                if(!StringUtils.isEmpty(getUrl())){
                    predicates.add(cb.like(cb.lower(root.get("content")), "%"+getUrl().toLowerCase()+"%"));
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
