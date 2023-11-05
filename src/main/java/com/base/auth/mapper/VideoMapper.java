package com.base.auth.mapper;

import com.base.auth.dto.video.VideoAdminDto;
import com.base.auth.form.video.CreateVideoForm;
import com.base.auth.form.video.UpdateVideoForm;
import com.base.auth.model.Video;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface VideoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "url", target = "url")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromEntityToVideoAdminDto")
    VideoAdminDto fromEntityToVideoAdminDto(Video video);

    @IterableMapping(elementTargetType = VideoAdminDto.class, qualifiedByName = "fromEntityToVideoAdminDto")
    List<VideoAdminDto> fromEntityToVideoAdminDtoList(List<Video> video);



    @Mapping(source = "description", target = "description")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCreateVideoFormToEntity")
    Video fromCreateVideoFormToEntity(CreateVideoForm createVideoForm);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "url", target = "url")
    @BeanMapping(ignoreByDefault = true)
    @Named("updateCourseNotStartedFromForm")
    void updateVideoFromUpdateVideoForm(UpdateVideoForm updateVideoForm, @MappingTarget Video video);
}