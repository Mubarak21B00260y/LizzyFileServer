package com.amalitechnss.Lizzy_fileServer.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EditDocumentRequest {
@NotEmpty
@NotBlank
    private   String newTitle;
@NotEmpty
@NotBlank
    private    String  newDescription;

//    public EditDocumentRequest(String newTitle, String newDescription) {
//        NewTitle = newTitle;
//        NewDescription = newDescription;
//    }
}
