package com.amalitechnss.Lizzy_fileServer.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResetPasswordRequest {
    @NotEmpty
    @NotBlank
    private    String oldPassword;
    @NotEmpty
    @NotBlank
    private   String newPassword  ;

//    public ResetPasswordRequest(String oldPassword, String newPassword) {
//        OldPassword = oldPassword;
//        NewPassword = newPassword;
//    }
}
