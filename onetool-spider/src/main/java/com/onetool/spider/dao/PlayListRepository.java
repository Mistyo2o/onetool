package com.onetool.spider.dao;

import com.onetool.spider.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zh
 * @date: 2023/3/26 21:03
 * @description: 歌单dao
 */
@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long> {

    //根据创建人查询
    List<PlayList> findBycUserAndDel(String cUser, int del);

    //根据创建人作废数据
    @Modifying
    @Query("update PlayList set del = 1 where cUser =?1")
    void upDateDelBycUser(String cUser);
}
