package com.onetool.wxplat.dao;

import com.onetool.wxplat.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zh
 * @date: 2023/4/16 17:22
 * @description: 歌曲数据库操作
 */
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByPlayId(String playId);
}
