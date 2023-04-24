package com.onetool.wxplat.dao;

import com.onetool.wxplat.entity.WeChatUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zh
 * @version 1.0
 * @date 2023/4/22 21:07
 */
@Repository
public interface WeChatUserRepository extends JpaRepository<WeChatUserInfo, Long> {

    WeChatUserInfo findByOpenId(String openId);
}
