package com.onetool.spider.dao;

import com.onetool.spider.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zh
 * @date: 2023/3/29 9:24
 * @description:
 */

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {


    //根据创建人查询
    List<Song> findByQqAndDel(String qq, int del);

    List<Song> findByPlayId(String playId);

    @Modifying
    @Query("update Song set del = 1 where qq =?1")
    void upDateDelByQq(String qq);

}
