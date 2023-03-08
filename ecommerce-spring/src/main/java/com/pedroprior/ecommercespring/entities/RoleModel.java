package com.pedroprior.ecommercespring.entities;

import com.pedroprior.ecommercespring.entities.enums.RoleName;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Table(name = "tb_role")
@Data
public class RoleModel implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;


    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }

    public String returnRoleName() {
        return roleName.toString();
    }


}
