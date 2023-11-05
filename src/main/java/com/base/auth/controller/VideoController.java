package com.base.auth.controller;


import com.base.auth.dto.ApiMessageDto;
import com.base.auth.dto.ErrorCode;
import com.base.auth.dto.ResponseListDto;
import com.base.auth.dto.video.VideoAdminDto;
import com.base.auth.form.video.CreateVideoForm;
import com.base.auth.form.video.UpdateVideoForm;
import com.base.auth.mapper.VideoMapper;
import com.base.auth.model.Video;
import com.base.auth.model.criteria.VideoCriteria;
import com.base.auth.repository.VideoRepository;
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
@RequestMapping("/v1/video")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VideoController {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoMapper videoMapper;




    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('VIDEO_L')")
    public ApiMessageDto<ResponseListDto<List<VideoAdminDto>>> list(VideoCriteria videoCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<VideoAdminDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Video> listCourse = videoRepository.findAll(videoCriteria.getSpecification(), pageable);
        ResponseListDto<List<VideoAdminDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(videoMapper.fromEntityToVideoAdminDtoList(listCourse.getContent()));
        responseListObj.setTotalPages(listCourse.getTotalPages());
        responseListObj.setTotalElements(listCourse.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }


    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('VIDEO_V')")
    public ApiMessageDto<VideoAdminDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<VideoAdminDto> apiMessageDto = new ApiMessageDto<>();
        Video exsitingVideo = videoRepository.findById(id).orElse(null);
        if (exsitingVideo == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.VIDEO_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        apiMessageDto.setData(videoMapper.fromEntityToVideoAdminDto(exsitingVideo));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Get video success.");
        return apiMessageDto;
    }
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('VIDEO_C')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateVideoForm createVideoForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Video exsitingVideo = videoRepository.findByTitle(createVideoForm.getTitle());
        if (exsitingVideo != null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.VIDEO_ERROR_EXISTED);
            return apiMessageDto;
        }


        Video video = videoMapper.fromCreateVideoFormToEntity(createVideoForm);
        videoRepository.save(video);
        apiMessageDto.setMessage("Create video success");
        return apiMessageDto;

    }
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('VIDEO_U')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateVideoForm updateVideoForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Video exsitingVideo = videoRepository.findById(updateVideoForm.getId()).orElse(null);
        if (exsitingVideo == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.VIDEO_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        if(!exsitingVideo.getTitle().equals(updateVideoForm.getTitle())){
            Boolean exsitingVideoTitle = videoRepository.existsByTitle(updateVideoForm.getTitle());
            if (exsitingVideoTitle) {
                apiMessageDto.setResult(false);
                apiMessageDto.setMessage("Title đã tồn tại, không thể cập nhật");
                apiMessageDto.setCode(ErrorCode.VIDEO_ERROR_EXISTED);
                return apiMessageDto;
            }
        }

        videoMapper.updateVideoFromUpdateVideoForm(updateVideoForm,exsitingVideo);
        videoRepository.save(exsitingVideo);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Update video success");
        return apiMessageDto;
    }
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('VIDEO_D')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Video exsitingVideo = videoRepository.findById(id).orElse(null);
        if (exsitingVideo == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.VIDEO_ERROR_NOT_FOUND);
            return apiMessageDto;
        }
        videoRepository.delete(exsitingVideo);
        apiMessageDto.setMessage("Delete course success");
        return apiMessageDto;
    }
}
