package com.base.auth.mapper;


import com.base.auth.dto.quiz.QuizAdminDto;
import com.base.auth.form.quiz.CreateQuizForm;
import com.base.auth.form.quiz.UpdateQuizForm;
import com.base.auth.model.Quiz;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface QuizMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "option1", target = "option1")
    @Mapping(source = "option2", target = "option2")
    @Mapping(source = "option3", target = "option3")
    @Mapping(source = "option4", target = "option4")
    @Mapping(source = "answer", target = "answer")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromEntityToQuizAdminDto")
    QuizAdminDto fromEntityToQuizAdminDto(Quiz quiz);

    @IterableMapping(elementTargetType = QuizAdminDto.class, qualifiedByName = "fromEntityToQuizAdminDto")
    List<QuizAdminDto> fromEntityToQuizAdminDtoList(List<Quiz> quiz);



    @Mapping(source = "question", target = "question")
    @Mapping(source = "option1", target = "option1")
    @Mapping(source = "option2", target = "option2")
    @Mapping(source = "option3", target = "option3")
    @Mapping(source = "option4", target = "option4")
    @Mapping(source = "answer", target = "answer")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCreateQuizFormToEntity")
    Quiz fromCreateQuizFormToEntity(CreateQuizForm createQuizForm);

    @Mapping(source = "question", target = "question")
    @Mapping(source = "option1", target = "option1")
    @Mapping(source = "option2", target = "option2")
    @Mapping(source = "option3", target = "option3")
    @Mapping(source = "option4", target = "option4")
    @Mapping(source = "answer", target = "answer")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("updateCourseNotStartedFromForm")
    void updateQuizFromUpdateQuizForm(UpdateQuizForm updateQuizForm, @MappingTarget Quiz quiz);
}