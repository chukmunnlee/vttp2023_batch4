package vttp.batch4.csf.day38.upload.respositories;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batch4.csf.day38.upload.models.ImageData;

@Repository
public class ImageRepository {

   public static final String SQL_INSERT_PICTURE = """
      insert into pictures(pic_id, content, mime)
      values (?, ?, ?)
   """;

   public static final String SQL_GET_PICTURE_BY_ID = """
      select * from pictures where pic_id = ?
   """;

   @Autowired
   private JdbcTemplate template;

   public void save(String imageId, InputStream is, String contentType) {
      template.update(SQL_INSERT_PICTURE, imageId, is, contentType);
   }

   public Optional<ImageData> getPicture(String picId) {
      return template.query(SQL_GET_PICTURE_BY_ID, 
         (ResultSet rs) -> {
            if (!rs.next())
               return Optional.empty();
            ImageData data = new ImageData(picId 
                  , rs.getBytes("content")
                  , rs.getString("mime"));
            return Optional.of(data);
         },
         picId
      );
   }
   
}
