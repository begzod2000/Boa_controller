package uz.setapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqSignUp {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Boolean active;

    @NotBlank
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "998[0-9]{2}[0-9]{7}", message = "Not validated")
    private String phone;

    @NotBlank
    private String roles;
}
