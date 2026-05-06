package es.iesclaradelrey.da2d1a.tiendadrmjcgt.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TokenRefreshRequest {
    private String refreshToken;
}