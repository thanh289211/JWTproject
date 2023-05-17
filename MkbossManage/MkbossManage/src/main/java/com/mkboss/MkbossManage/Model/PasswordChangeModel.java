package com.mkboss.MkbossManage.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeModel {
    private String email;
    private String oldPassword;
    private String newPassword;
}
