import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { KeycloakService } from '../services/keycloak/keycloak.service';

export const AuthGuard: CanActivateFn = () => {
  const keycloakService = inject(KeycloakService);
  const router = inject(Router);
  if (keycloakService.keycloak?.isTokenExpired()) {
    keycloakService.logout();
    router.navigate(['login']);
    return false;
  }
  return true;
};
