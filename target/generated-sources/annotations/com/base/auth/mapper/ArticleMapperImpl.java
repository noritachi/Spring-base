package com.base.auth.mapper;

import com.base.auth.dto.article.ArticleAdminDto;
import com.base.auth.form.article.CreateArticleForm;
import com.base.auth.form.article.UpdateArticleForm;
import com.base.auth.model.Article;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-05T17:50:12+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.20 (Oracle Corporation)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public ArticleAdminDto fromEntityToArticleAdminDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleAdminDto articleAdminDto = new ArticleAdminDto();

        if ( article.getCreatedDate() != null ) {
            articleAdminDto.setCreatedDate( LocalDateTime.ofInstant( article.getCreatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( article.getModifiedDate() != null ) {
            articleAdminDto.setModifiedDate( LocalDateTime.ofInstant( article.getModifiedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        articleAdminDto.setDescription( article.getDescription() );
        articleAdminDto.setBanner( article.getBanner() );
        articleAdminDto.setId( article.getId() );
        articleAdminDto.setAvatar( article.getAvatar() );
        articleAdminDto.setTitle( article.getTitle() );
        articleAdminDto.setContent( article.getContent() );
        articleAdminDto.setStatus( article.getStatus() );

        return articleAdminDto;
    }

    @Override
    public List<ArticleAdminDto> fromEntityToArticleAdminDtoList(List<Article> article) {
        if ( article == null ) {
            return null;
        }

        List<ArticleAdminDto> list = new ArrayList<ArticleAdminDto>( article.size() );
        for ( Article article1 : article ) {
            list.add( fromEntityToArticleAdminDto( article1 ) );
        }

        return list;
    }

    @Override
    public Article fromCreateArticleFormToEntity(CreateArticleForm createArticleForm) {
        if ( createArticleForm == null ) {
            return null;
        }

        Article article = new Article();

        article.setDescription( createArticleForm.getDescription() );
        article.setBanner( createArticleForm.getBanner() );
        article.setAvatar( createArticleForm.getAvatar() );
        article.setTitle( createArticleForm.getTitle() );
        article.setContent( createArticleForm.getContent() );
        if ( createArticleForm.getStatus() != null ) {
            article.setStatus( createArticleForm.getStatus() );
        }

        return article;
    }

    @Override
    public void updateArticleFromUpdateArticleForm(UpdateArticleForm updateArticleForm, Article article) {
        if ( updateArticleForm == null ) {
            return;
        }

        if ( updateArticleForm.getDescription() != null ) {
            article.setDescription( updateArticleForm.getDescription() );
        }
        if ( updateArticleForm.getBanner() != null ) {
            article.setBanner( updateArticleForm.getBanner() );
        }
        if ( updateArticleForm.getAvatar() != null ) {
            article.setAvatar( updateArticleForm.getAvatar() );
        }
        if ( updateArticleForm.getTitle() != null ) {
            article.setTitle( updateArticleForm.getTitle() );
        }
        if ( updateArticleForm.getContent() != null ) {
            article.setContent( updateArticleForm.getContent() );
        }
        if ( updateArticleForm.getStatus() != null ) {
            article.setStatus( updateArticleForm.getStatus() );
        }
    }
}
