package com.base.auth.controller;


import com.base.auth.dto.ApiMessageDto;
import com.base.auth.dto.ErrorCode;
import com.base.auth.dto.ResponseListDto;
import com.base.auth.dto.quiz.QuizAdminDto;
import com.base.auth.form.quiz.CreateQuizForm;
import com.base.auth.form.quiz.UpdateQuizForm;
import com.base.auth.mapper.QuizMapper;
import com.base.auth.model.Quiz;
import com.base.auth.model.criteria.QuizCriteria;
import com.base.auth.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/quiz")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuizMapper quizMapper;




    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('QUIZ_L')")
    public ApiMessageDto<ResponseListDto<List<QuizAdminDto>>> list(QuizCriteria quizCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<QuizAdminDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Quiz> listCourse = quizRepository.findAll(quizCriteria.getSpecification(), pageable);
        ResponseListDto<List<QuizAdminDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(quizMapper.fromEntityToQuizAdminDtoList(listCourse.getContent()));
        responseListObj.setTotalPages(listCourse.getTotalPages());
        responseListObj.setTotalElements(listCourse.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }


    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('QUIZ_V')")
    public ApiMessageDto<QuizAdminDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<QuizAdminDto> apiMessageDto = new ApiMessageDto<>();
        Quiz exsitingQuiz = quizRepository.findById(id).orElse(null);
        if (exsitingQuiz == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.QUIZ_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        apiMessageDto.setData(quizMapper.fromEntityToQuizAdminDto(exsitingQuiz));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Get quiz success.");
        return apiMessageDto;
    }
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('QUIZ_C')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateQuizForm createQuizForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Quiz quiz = quizMapper.fromCreateQuizFormToEntity(createQuizForm);
        quizRepository.save(quiz);
        apiMessageDto.setMessage("Create quiz success");
        return apiMessageDto;

    }
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('QUIZ_U')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateQuizForm updateQuizForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Quiz exsitingQuiz = quizRepository.findById(updateQuizForm.getId()).orElse(null);
        if (exsitingQuiz == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.QUIZ_ERROR_NOT_FOUND);
            return apiMessageDto;
        }

        quizMapper.updateQuizFromUpdateQuizForm(updateQuizForm,exsitingQuiz);
        quizRepository.save(exsitingQuiz);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Update quiz success");
        return apiMessageDto;
    }
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('QUIZ_D')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Quiz exsitingQuiz = quizRepository.findById(id).orElse(null);
        if (exsitingQuiz == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.QUIZ_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        quizRepository.delete(exsitingQuiz);
        apiMessageDto.setMessage("Delete course success");
        return apiMessageDto;
    }
}
