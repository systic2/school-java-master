package com.school.java.repository;

import com.school.java.component.LoginUserAuditorAware;
import com.school.java.config.JpaConfig;
import com.school.java.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("AdminUser Repository 테스트")
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class AdminUserRepositoryTest {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    @DisplayName("AdminUser Create 테스트")
    public void create(){
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
        //adminUser.setCreatedAt(LocalDateTime.now()); // LoginUserAuditorAware 적용으로 자동 createdAt, createdBy 설정
        //adminUser.setCreatedBy("AdminServer"); // LoginUserAuditorAware 적용으로 자동 createdAt, createdBy 설정

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);
        // 새로운 유저 newAdminUser를 만들었고, 유저의 계정을 "change"로 변경
        newAdminUser.setAccount("change");
        adminUserRepository.save(newAdminUser);

    }
}
