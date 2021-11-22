package uz.setapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.setapp.entity.enums.RoleName;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private Long id;
    private String phone;
    private String email;

    @Column(nullable = false)
    private String fullName;
    private String marketName;
    private List<RoleName> roles;
}
