package com.tuan.vtube.videoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "video_infor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(generator = "video_id_generator")
    @GenericGenerator(name = "video_id_generator", strategy = "com.tuan.vtube.videoservice.generator.VideoIdGenerator")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "description")
    private String description;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "video_length")
    private Long videoLength;
}
