package pl.sda.pms.config;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@Table(name = "REVINFO")
@RevisionEntity(CustomRevisionEntityListener.class)
public class CustomRevisionEntity extends DefaultRevisionEntity {

  private static final long serialVersionUID = 1L;

  private String username;

  
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  @Override
  public String toString() {
    return "{" +
      " username='" + getUsername() + "'" +
      "revision date="+getRevisionDate()+
      "}";
  }

}