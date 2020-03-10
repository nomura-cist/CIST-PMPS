package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.Contact;
import com.example.demo.login.domain.repository.CreateNewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Contact> selectMany() throws DataAccessException {

        List<Map<String,Object>> getList = jdbc.queryForList("SELECT * FROM m_contact");
        List<Contact> contactList = new ArrayList<>();

        for (Map<String,Object> map: getList){

            Contact contact = new Contact();

            contact.setTitle((String)map.get("title"));
            contact.setText((String)map.get("text"));
            contact.setContributor((String)map.get("contribute"));
            contact.setCreateDate((Date) map.get("create_date"));
            contact.setRole((String)map.get("role"));
            System.out.println(contact);
            contactList.add(contact);

        }

        return contactList;
    }
}
