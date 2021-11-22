package uz.setapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqUser {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String email;
    @NotBlank //probel ham bolishi keremas
    private String firstName;
    @NotBlank
    private String lastName;

    @NotBlank
    private String roles;
}
