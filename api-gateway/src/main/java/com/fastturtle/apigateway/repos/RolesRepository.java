package com.fastturtle.apigateway.repos;

import com.fastturtle.apigateway.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
