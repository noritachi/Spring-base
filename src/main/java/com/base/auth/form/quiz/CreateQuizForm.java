package com.base.auth.form.quiz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class CreateQuizForm {

    @NotEmpty(message = "question cannot be null")
    @ApiModelProperty(required = true)
    private String question;

    @NotEmpty(message = "option1 cannot be null")
    @ApiModelProperty(required = true)
    private String option1;

    @NotEmpty(message = "option2 cannot be null")
    @ApiModelProperty(required = true)
    private String option2;

    @NotEmpty(message = "option3 cannot be null")
    @ApiModelProperty(required = true)
    private String option3;

    @NotEmpty(message = "option4 cannot be null")
    @ApiModelProperty(required = true)
    private String option4;

    @NotEmpty(message = "answer cannot be null")
    @ApiModelProperty(required = true)
    private String answer;


    @ApiModelProperty(name = "status", required = true)
    @NotNull(message = "status can not null")
    private Integer status;
}
