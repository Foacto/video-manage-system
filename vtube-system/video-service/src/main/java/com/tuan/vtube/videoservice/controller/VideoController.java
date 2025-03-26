package com.tuan.vtube.videoservice.controller;

import com.tuan.vtube.videoservice.form.VideoForm;
import com.tuan.vtube.videoservice.bean.AppResponse;
import com.tuan.vtube.videoservice.bean.VideoBean;
import com.tuan.vtube.videoservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("Okkk");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppResponse> createProduct(@Valid @RequestBody VideoForm form) {
        return new ResponseEntity<>(videoService.createProduct(form), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VideoBean>> getAllProducts() {
        return ResponseEntity.ok(videoService.getAllProducts());
    }

    @GetMapping("/test/{page}")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoBean> getPage(@PathVariable("page") int page) {
        return videoService.getPage(page);
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoBean> getPage(@Param("min") BigDecimal min) {
        return videoService.getListMinProduct(min);
    }
}
