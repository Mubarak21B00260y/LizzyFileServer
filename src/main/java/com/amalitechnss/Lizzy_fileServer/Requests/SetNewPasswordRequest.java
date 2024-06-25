package com.amalitechnss.Lizzy_fileServer.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetNewPasswordRequest {
    private  String newPassword;
    private  String token;
}
