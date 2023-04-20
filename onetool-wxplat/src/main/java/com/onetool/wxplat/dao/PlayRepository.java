package com.onetool.wxplat.dao;

import com.onetool.wxplat.entity.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zh
 * @date: 2023/4/14 9:41
 * @description: 歌单数据库操作
 */
@Repository
public interface PlayRepository extends JpaRepository<Play, Long> {

    List<Play> findBycUserAndDel(String cUser, int del);
}
