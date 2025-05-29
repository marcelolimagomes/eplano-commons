package br.com.eplano.commons.dto;

import java.beans.Transient;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDTO implements EntityDTO {
    private Long id;
    private String email;
    private String password;
    private String createdBy;
    private ProfileDTO profile;
    private Set<RoleDTO> roles;
    private boolean enabled;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean accountActivated = false;

    // Necessário para padronizar o nome do método iniciando com "is"
    public Boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    // Necessário para padronizar o nome do método iniciando com "is"
    public Boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    // Necessário para padronizar o nome do método iniciando com "is"
    public Boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public Boolean isAccountActivated() {
        return accountActivated;
    }

    @Transient
    public boolean isAdmin() {
        return roles.stream().anyMatch(role -> role.getName().equals("ADMIN"));
    }
}