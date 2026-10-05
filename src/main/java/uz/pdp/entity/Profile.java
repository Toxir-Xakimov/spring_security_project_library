package uz.pdp.entity;


import lombok.*;
import uz.pdp.enums.Role;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Profile {

    private Integer id;

    private String fullname;
    private String username;
    private String password;

    private Role role;

}
