package com.onetool.wxplat.dao;

import com.onetool.wxplat.entity.WeChatUserQq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author zh
 * @version 1.0
 * @date 2023/4/24 21:45
 */

@Repository
public interface WeChatUserQqRepository extends JpaRepository<WeChatUserQq, Long> {

    List<WeChatUserQq> findByOpenId(String openId);

    WeChatUserQq findByQq(String qq);

    @Transactional
    @Modifying
    @Query("update WeChatUserQq set def = 0 where openId = ?1")
    int updateDefByOpenId(String openId);

    @Transactional
    @Modifying
    @Query("update WeChatUserQq set def = 1 where id = ?1")
    int updateDefById(Long id);
}
