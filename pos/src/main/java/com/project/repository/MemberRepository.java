package com.project.repository;

import static com.project.repository.JDBCUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MemberRepository {
    private static final  MemberRepository instance=new MemberRepository();
    public MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository(){};

}
