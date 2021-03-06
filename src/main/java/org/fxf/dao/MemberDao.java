package org.fxf.dao;

import java.util.List;

import org.fxf.bean.Member;

public interface MemberDao {

	/**
     * 根据查询条件查询会员列表
     * @param member 查询条件
     * @return 会员列表
     */
    List<Member> select(Member member);
}
