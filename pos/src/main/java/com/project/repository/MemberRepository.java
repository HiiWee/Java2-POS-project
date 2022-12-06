package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.domain.Member;
import com.project.domain.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private static final  MemberRepository instance=new MemberRepository();
    public static MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository(){};

    public List<Member> fineAll(){
        List<Member> members = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id,auth,password from member");

            while (resultSet.next()) {

            }
            closeAll(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
    }

}
