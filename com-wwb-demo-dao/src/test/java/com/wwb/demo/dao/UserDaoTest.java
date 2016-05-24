package com.wwb.demo.dao;

import com.wwb.demo.domain.model.Area;
import com.wwb.demo.domain.model.Member;
import com.wwb.demo.domain.model.MemberCertificate;
import com.wwb.demo.domain.model.Receiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedArray;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaoai on 2016/2/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config-dao-test.xml"})
@TransactionConfiguration(defaultRollback = false)
public class UserDaoTest {
    @Autowired
    AreaDao areaDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    MemberCertificateDao memberCertificateDao;
    @Autowired
    ReceiverDao receiverDao;

    @Test
    @Transactional
    public void createMember() {
        Member member= new Member();
        member.setUserName("sao");
        member.setPassword("sao");
        memberDao.create(member);
    }


    @Test
    @Transactional
    public void updateMember() {
        Member member =memberDao.get(1L);
        Area area = areaDao.get(2l);
        member.setArea(area);
        memberDao.update(member);
    }
    @Test
    @Transactional
    public void createMemberCertificate() {
        Member member =memberDao.get(1L);
        MemberCertificate memberCertificate = new MemberCertificate();
        memberCertificate.setRealName("小扁扁");
        memberCertificate.setRegisterMember(member);
        memberCertificateDao.create(memberCertificate);
    }

    @Test
    @Transactional
    public void createReceivers() {
        Member member =memberDao.get(1L);
        Receiver receiver = new Receiver();
        Area area = areaDao.get(2l);
        receiver.setArea(area);
        receiver.setReceiverName("funckBina1");
        receiver.setMember(member);
        receiverDao.create(receiver);

        Receiver receiver1 = new Receiver();
        receiver1.setArea(area);
        receiver1.setReceiverName("funckBina2");
        receiver1.setMember(member);
        receiverDao.create(receiver1);
    }

    @Test
    @Transactional
    public void getMemberByUserName() {
        Member member = memberDao.getMemberByUserName("sao");
        System.out.println(member.getPassword());
    }

    @Test
    public void testMobile(){

    }



}
