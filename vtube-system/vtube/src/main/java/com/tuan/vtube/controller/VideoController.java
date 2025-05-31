package com.tuan.vtube.controller;

import com.tuan.vtube.common.AppResponse;
import com.tuan.vtube.common.Utils;
import com.tuan.vtube.common.VideoBean;
import com.tuan.vtube.form.VideoForm;
import com.tuan.vtube.model.UserData;
import com.tuan.vtube.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/vtube/api/v1/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VideoBean> getAll(HttpServletRequest request) {
        UserData userData = Utils.getUserData(request);
        System.out.println(request.getHeaders("vtube-auth").nextElement());
        VideoBean bean = new VideoBean();
        bean.setId("OKKKK");
        bean.setName("OKKKK");
        return ResponseEntity.ok(bean);
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
