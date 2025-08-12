package com.tuan.vtube.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "video_infor")
@Getter
@Setter
public class Video extends BaseModel {
    @Id
    @GeneratedValue(generator = "video_id_generator")
    @GenericGenerator(name = "video_id_generator", strategy = "com.tuan.vtube.generator.VideoIdGenerator")
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
