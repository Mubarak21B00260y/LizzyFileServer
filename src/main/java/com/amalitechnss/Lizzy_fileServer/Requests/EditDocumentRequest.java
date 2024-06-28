package com.amalitechnss.Lizzy_fileServer.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EditDocumentRequest {

    private String newTitle;
    private String newDescription;
}


