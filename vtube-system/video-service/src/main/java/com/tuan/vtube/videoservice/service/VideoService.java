package com.tuan.vtube.videoservice.service;

import com.tuan.vtube.videoservice.form.VideoForm;
import com.tuan.vtube.videoservice.bean.AppResponse;
import com.tuan.vtube.videoservice.bean.VideoBean;
import com.tuan.vtube.videoservice.model.Video;
import com.tuan.vtube.videoservice.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VideoService {
    private final VideoRepository videoRepository;

    public AppResponse createProduct(VideoForm form) {
        Video video = Video.builder()
                .name(form.getName())
                .des(form.getDes())
                .price(form.getPrice())
                .build();

        // Save product to database
        videoRepository.save(video);
        log.info("Product ID:{} saved", video.getId());

        return AppResponse.builder()
                .message("Product create successful")
                .build();
    }

    public List<VideoBean> getAllProducts() {
        List<Video> Videos = videoRepository.findAll();

        //Mapping data to response
        return Videos.stream().map(this::mapToProductResponse).toList();
    }

    private VideoBean mapToProductResponse(Video VIdeo) {
        return VideoBean.builder()
                .id(VIdeo.getId())
                .name(VIdeo.getName())
                .des(VIdeo.getDes())
                .price(VIdeo.getPrice())
                .build();
    }

    public List<VideoBean> getPage(int page) {
        Page<Video> products = videoRepository.findAll(PageRequest.of(page, 3, Sort.by("price").descending()));
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public List<VideoBean> getListMinProduct(BigDecimal min) {
        List<Video> Videos = videoRepository.getAllProductWithMinPrice(min);
        return Videos.stream().map(this::mapToProductResponse).toList();
    }
}
