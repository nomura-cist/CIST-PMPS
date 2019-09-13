package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.Contact;
import com.example.demo.login.domain.repository.CreateNewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreateNewJdbcImpl implements CreateNewDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public int insertOne(Contact contact) throws DataAccessException {
        int rowNumber2 = jdbc.update("insert into m_contact(title, text, contribute, create_date, role) values (?,?,?,?,?)"
                ,contact.getTitle()
                ,contact.getText()
                ,contact.getContributor()
                ,contact.getCreateDate()
                ,contact.getRole());

        return rowNumber2;
    }

    @Override
    public int deleteOne(String title) throws DataAccessException {
        int rowNumber = jdbc.update("delete from m_contact where title=?",title);

        return rowNumber;
    }
}
