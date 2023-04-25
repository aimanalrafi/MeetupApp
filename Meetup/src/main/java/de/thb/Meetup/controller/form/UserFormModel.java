package de.thb.Meetup.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFormModel {
    private String displayName;
    private String username;
    private String email;
    private String password;
}
