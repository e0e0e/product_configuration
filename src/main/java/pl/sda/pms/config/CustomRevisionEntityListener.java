package pl.sda.pms.config;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomRevisionEntityListener implements RevisionListener {

  @Override
  public void newRevision(Object revisionEntity) {
    CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
    // customRevisionEntity.setUsername(CurrentUser.INSTANCE.get());
    customRevisionEntity.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
  }

  
}