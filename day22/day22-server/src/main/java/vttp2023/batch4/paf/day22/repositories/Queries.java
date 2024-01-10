package vttp2023.batch4.paf.day22.repositories;

public class Queries {

   public static final String SQL_UPDATE_TASK_COMPLETED_BY_ID = """
      update tasks 
         set completed = ?
         where task_id = ?
   """;

   public static final String SQL_SELECT_TASK_AS_SUMMARY = """
      select task_id, title, completed from tasks
   """;

   public static final String SQL_INSERT_BFF = """
      insert into bff(email, name, dob, phone, comments)
      values (?, ?, ?, ?, ?)
   """;

   public static final String SQL_COUNT_EMAIL = """
      select count(*) as email_count
         from bff
         where email = ?
   """;

   public static final String SQL_SELECT_BFF_BY_EMAIL = """
      select * from bff where email = ?
   """;
   
}
