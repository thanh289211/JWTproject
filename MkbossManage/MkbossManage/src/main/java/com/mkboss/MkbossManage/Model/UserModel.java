package com.mkboss.MkbossManage.Model;

import com.mkboss.MkbossManage.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private Role role;
}
