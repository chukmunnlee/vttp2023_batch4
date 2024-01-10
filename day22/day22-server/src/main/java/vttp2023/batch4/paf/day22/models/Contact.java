package vttp2023.batch4.paf.day22.models;

import java.sql.Timestamp;
import java.sql.Date;

public class Contact {

   private String email;
   private String name;
   private Date dob;
   private String phone;
   private String comments;
   private Timestamp lastUpdate;

   public String getEmail() { return email; }
   public void setEmail(String email) { this.email = email; }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public Date getDob() { return dob; }
   public void setDob(Date dob) { this.dob = dob; }

   public String getPhone() { return phone; }
   public void setPhone(String phone) { this.phone = phone; }

   public String getComments() { return comments; }
   public void setComments(String comments) { this.comments = comments; }

   public Timestamp getLastUpdate() { return lastUpdate; }
   public void setLastUpdate(Timestamp lastUpdate) { this.lastUpdate = lastUpdate; }
}
