package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthenticationRequest {
    private String username;
    private String password;
}