package com.tuan.vtube.videoservice.repository;

import com.tuan.vtube.videoservice.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, String> {
    @Query(value = "SELECT * FROM tblproduct p WHERE p.price >= :min", nativeQuery = true)
    public List<Video> getAllProductWithMinPrice(@Param("min") BigDecimal min);
}
