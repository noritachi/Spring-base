package com.base.auth.mapper;

import com.base.auth.dto.video.VideoAdminDto;
import com.base.auth.form.video.CreateVideoForm;
import com.base.auth.form.video.UpdateVideoForm;
import com.base.auth.model.Video;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-05T19:43:13+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.20 (Oracle Corporation)"
)
@Component
public class VideoMapperImpl implements VideoMapper {

    @Override
    public VideoAdminDto fromEntityToVideoAdminDto(Video video) {
        if ( video == null ) {
            return null;
        }

        VideoAdminDto videoAdminDto = new VideoAdminDto();

        if ( video.getCreatedDate() != null ) {
            videoAdminDto.setCreatedDate( LocalDateTime.ofInstant( video.getCreatedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( video.getModifiedDate() != null ) {
            videoAdminDto.setModifiedDate( LocalDateTime.ofInstant( video.getModifiedDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        videoAdminDto.setDescription( video.getDescription() );
        videoAdminDto.setBanner( video.getBanner() );
        videoAdminDto.setId( video.getId() );
        videoAdminDto.setAvatar( video.getAvatar() );
        videoAdminDto.setTitle( video.getTitle() );
        videoAdminDto.setUrl( video.getUrl() );
        videoAdminDto.setStatus( video.getStatus() );

        return videoAdminDto;
    }

    @Override
    public List<VideoAdminDto> fromEntityToVideoAdminDtoList(List<Video> video) {
        if ( video == null ) {
            return null;
        }

        List<VideoAdminDto> list = new ArrayList<VideoAdminDto>( video.size() );
        for ( Video video1 : video ) {
            list.add( fromEntityToVideoAdminDto( video1 ) );
        }

        return list;
    }

    @Override
    public Video fromCreateVideoFormToEntity(CreateVideoForm createVideoForm) {
        if ( createVideoForm == null ) {
            return null;
        }

        Video video = new Video();

        video.setDescription( createVideoForm.getDescription() );
        video.setBanner( createVideoForm.getBanner() );
        video.setAvatar( createVideoForm.getAvatar() );
        video.setTitle( createVideoForm.getTitle() );
        video.setUrl( createVideoForm.getUrl() );
        if ( createVideoForm.getStatus() != null ) {
            video.setStatus( createVideoForm.getStatus() );
        }

        return video;
    }

    @Override
    public void updateVideoFromUpdateVideoForm(UpdateVideoForm updateVideoForm, Video video) {
        if ( updateVideoForm == null ) {
            return;
        }

        if ( updateVideoForm.getDescription() != null ) {
            video.setDescription( updateVideoForm.getDescription() );
        }
        if ( updateVideoForm.getBanner() != null ) {
            video.setBanner( updateVideoForm.getBanner() );
        }
        if ( updateVideoForm.getAvatar() != null ) {
            video.setAvatar( updateVideoForm.getAvatar() );
        }
        if ( updateVideoForm.getTitle() != null ) {
            video.setTitle( updateVideoForm.getTitle() );
        }
        if ( updateVideoForm.getUrl() != null ) {
            video.setUrl( updateVideoForm.getUrl() );
        }
        if ( updateVideoForm.getStatus() != null ) {
            video.setStatus( updateVideoForm.getStatus() );
        }
    }
}
