package vttp2023.batch4.paf.day22.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch4.paf.day22.models.Contact;

@Repository
public class BffRepository {

   @Autowired
   private JdbcTemplate template;

   public boolean contactExists(String email) {
      SqlRowSet rs = template.queryForRowSet(Queries.SQL_COUNT_EMAIL, email);
      if (!rs.next())
         return false;
      int emailCount = rs.getInt("email_count");
      return emailCount > 0;
   }

   public boolean insertContact(Contact c) {
      return template.update(Queries.SQL_INSERT_BFF, 
         c.getEmail(), c.getName(), c.getDob(), c.getPhone(), c.getComments()
      ) >= 1;
   }
   
}
