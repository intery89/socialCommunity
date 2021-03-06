package com.wwb.demo.dao;
import com.wwb.demo.domain.model.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class MemberDao extends BaseDao<Member> {
    public Member getMemberByUserName(String userName) {
        String Hql = "select m from Member m where m.userName=:userName and m.status = 0";
        Query query = entityManager.createQuery(Hql);
        query.setParameter("userName", userName);
        query.setMaxResults(1);
        List<Member> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    public boolean isUserNameExist(String userName) {
        String Hql = "select m.id from Member m where m.userName=:userName and m.status = 0";
        Query query = entityManager.createQuery(Hql);
        query.setParameter("userName", userName);
        query.setMaxResults(1);
        List<Long> result = query.getResultList();
        return result.isEmpty() ? false :true;
    }
    public boolean isEmailExist(String email) {
        String Hql = "select id from Member m where m.email=:email";
        Query query = entityManager.createQuery(Hql);
        query.setParameter("email", email);
        query.setMaxResults(1);
        List<Long> result = query.getResultList();
        return result.isEmpty() ? false :true;
    }
}
