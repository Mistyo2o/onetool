package com.onetool.wxplat.dao;

import com.onetool.wxplat.entity.WeChatUserQq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zh
 * @version 1.0
 * @date 2023/4/24 21:45
 */

@Repository
public interface WeChatUserQqRepository extends JpaRepository<WeChatUserQq, Long> {
}
