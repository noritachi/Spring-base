package com.base.auth.controller;


import com.base.auth.constant.UserBaseConstant;
import com.base.auth.dto.ApiMessageDto;
import com.base.auth.dto.ErrorCode;
import com.base.auth.dto.ResponseListDto;
import com.base.auth.dto.article.ArticleAdminDto;
import com.base.auth.exception.BadRequestException;
import com.base.auth.form.article.CreateArticleForm;
import com.base.auth.form.article.UpdateArticleForm;
import com.base.auth.mapper.ArticleMapper;
import com.base.auth.model.Article;
import com.base.auth.model.Category;
import com.base.auth.model.criteria.ArticleCriteria;
import com.base.auth.repository.ArticleRepository;
import com.base.auth.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/article")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleMapper articleMapper;




    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ARTICLE_L')")
    public ApiMessageDto<ResponseListDto<List<ArticleAdminDto>>> list(ArticleCriteria articleCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<ArticleAdminDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Article> listCourse = articleRepository.findAll(articleCriteria.getSpecification(), pageable);
        ResponseListDto<List<ArticleAdminDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(articleMapper.fromEntityToArticleAdminDtoList(listCourse.getContent()));
        responseListObj.setTotalPages(listCourse.getTotalPages());
        responseListObj.setTotalElements(listCourse.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }
//    @GetMapping(value = "/auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiMessageDto<ResponseListDto<List<ArticleAutoCompleteDto>>> autoComplete(ArticleCriteria articleCriteria) {
//        ApiMessageDto<ResponseListDto<List<NArticleAutoCompleteDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
//        articleCriteria.setStatus(UserBaseConstant.STATUS_ACTIVE);
//        Pageable pageable = PageRequest.of(0,10);
//        Page<Article> listCourse = articleRepository.findAll(articleCriteria.getSpecification(), pageable);
//        ResponseListDto<List<ArticleAutoCompleteDto>> responseListObj = new ResponseListDto<>();
//        responseListObj.setContent(articleMapper.fromEntityToArticleAutoCompleteDtoList(listCourse.getContent()));
//        responseListObj.setTotalPages(listCourse.getTotalPages());
//        responseListObj.setTotalElements(listCourse.getTotalElements());
//
//        responseListObjApiMessageDto.setData(responseListObj);
//        responseListObjApiMessageDto.setMessage("Get auto-complete list success");
//        return responseListObjApiMessageDto;
//    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ARTICLE_V')")
    public ApiMessageDto<ArticleAdminDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<ArticleAdminDto> apiMessageDto = new ApiMessageDto<>();
        Article exsitingArticle = articleRepository.findById(id).orElse(null);
        if (exsitingArticle == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.ARTICLE_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        apiMessageDto.setData(articleMapper.fromEntityToArticleAdminDto(exsitingArticle));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Get article success.");
        return apiMessageDto;
    }
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ARTICLE_C')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateArticleForm createArticleForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Article exsitingArticle = articleRepository.findByTitle(createArticleForm.getTitle());
        if (exsitingArticle != null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.ARTICLE_ERROR_EXISTED);
            return apiMessageDto;
        }


        Article article = articleMapper.fromCreateArticleFormToEntity(createArticleForm);
        articleRepository.save(article);
        apiMessageDto.setMessage("Create article success");
        return apiMessageDto;

    }
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ARTICLE_U')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateArticleForm updateArticleForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Article exsitingArticle = articleRepository.findById(updateArticleForm.getId()).orElse(null);
        if (exsitingArticle == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.ARTICLE_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        if(!exsitingArticle.getTitle().equals(updateArticleForm.getTitle())){
            Boolean exsitingArticleTitle = articleRepository.existsByTitle(updateArticleForm.getTitle());
            if (exsitingArticleTitle) {
                apiMessageDto.setResult(false);
                apiMessageDto.setMessage("Title đã tồn tại, không thể cập nhật");
                apiMessageDto.setCode(ErrorCode.ARTICLE_ERROR_EXISTED);
                return apiMessageDto;
            }
        }

        articleMapper.updateArticleFromUpdateArticleForm(updateArticleForm,exsitingArticle);
        articleRepository.save(exsitingArticle);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Update article success");
        return apiMessageDto;
    }
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ARTICLE_D')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Article exsitingArticle = articleRepository.findById(id).orElse(null);
        if (exsitingArticle == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.ARTICLE_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        articleRepository.delete(exsitingArticle);
        apiMessageDto.setMessage("Delete course success");
        return apiMessageDto;
    }
}
