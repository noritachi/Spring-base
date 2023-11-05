package com.base.auth.dto.video;

import com.base.auth.dto.ABasicAdminDto;
import com.base.auth.dto.category.CategoryDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class VideoAdminDto extends ABasicAdminDto {

    @ApiModelProperty(name = "title")
    private String title;

    @ApiModelProperty(name = "url")
    private String url;

    @ApiModelProperty(name = "avatar")
    private String avatar;

    @ApiModelProperty(name = "banner")
    private String banner;

    @ApiModelProperty(name = "description")
    private String description;

    @ApiModelProperty(name = "category")
    private CategoryDto category;

}
