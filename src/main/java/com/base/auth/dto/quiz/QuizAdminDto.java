package com.base.auth.dto.quiz;

import com.base.auth.dto.ABasicAdminDto;
import com.base.auth.dto.category.CategoryDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class QuizAdminDto extends ABasicAdminDto {


    @ApiModelProperty(name = "question")
    private String question;

    @ApiModelProperty(name = "option1")
    private String option1;

    @ApiModelProperty(name = "option2")
    private String option2;

    @ApiModelProperty(name = "option3")
    private String option3;

    @ApiModelProperty(name = "option4")
    private String option4;

    @ApiModelProperty(name = "answer")
    private String answer;

}
