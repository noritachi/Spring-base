package com.base.auth.mapper;

import com.base.auth.dto.article.ArticleAdminDto;
import com.base.auth.form.article.CreateArticleForm;
import com.base.auth.form.article.UpdateArticleForm;
import com.base.auth.model.Article;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ArticleMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromEntityToArticleAdminDto")
    ArticleAdminDto fromEntityToArticleAdminDto(Article article);

    @IterableMapping(elementTargetType = ArticleAdminDto.class, qualifiedByName = "fromEntityToArticleAdminDto")
    List<ArticleAdminDto> fromEntityToArticleAdminDtoList(List<Article> article);



    @Mapping(source = "description", target = "description")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCreateArticleFormToEntity")
    Article fromCreateArticleFormToEntity(CreateArticleForm createArticleForm);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "content", target = "content")
    @BeanMapping(ignoreByDefault = true)
    @Named("updateCourseNotStartedFromForm")
    void updateArticleFromUpdateArticleForm(UpdateArticleForm updateArticleForm, @MappingTarget Article article);
}