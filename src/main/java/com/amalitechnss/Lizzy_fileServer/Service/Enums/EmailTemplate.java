package com.amalitechnss.Lizzy_fileServer.Service.Enums;

import lombok.Getter;

@Getter


public enum EmailTemplate {

    Confirm_Account("Confirm_Account"),
    Reset_Password("Reset_Password"),
    ATTACHMENT("Attachment");





    private  String name;
    EmailTemplate(String name) {
        this.name= name;
    }
}
