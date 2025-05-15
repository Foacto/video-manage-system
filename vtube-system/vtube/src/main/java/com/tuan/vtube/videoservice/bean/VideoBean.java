package com.tuan.vtube.videoservice.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoBean {
    private String id;
    private String name;
    private String des;
    private BigDecimal price;
}
