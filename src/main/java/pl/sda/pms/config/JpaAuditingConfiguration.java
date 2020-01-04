package pl.sda.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider",auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {

        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
      return () -> Optional.of(OffsetDateTime.now());
    }
}