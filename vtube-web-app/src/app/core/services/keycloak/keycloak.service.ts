import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';
import { UserProfile } from '../../common/user-profile';
import { environment } from '../../../shared/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  private _keycloak: Keycloak | undefined;
  private _profile: UserProfile | undefined;

  get keycloak(): Keycloak {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: environment.keycloakUrl,
        realm: environment.keycloakRealm,
        clientId: 'vtube-client',
      });
    }
    return this._keycloak;
  }

  get profile(): UserProfile | undefined {
    return this._profile;
  }

  constructor() {}

  async init() {
    const authenticated = await this.keycloak?.init({
      onLoad: 'login-required',
    });

    if (authenticated) {
      this._profile = (await this.keycloak?.loadUserProfile()) as UserProfile;
      this._profile.token = this.keycloak.token as string;
      this._profile.roles = this.keycloak.tokenParsed?.realm_access
        ?.roles as string[];
    }
  }

  login(): Promise<void> {
    return this.keycloak?.login();
  }

  logout(): Promise<void> {
    return this.keycloak?.logout();
  }

  accountManagement(): Promise<void> {
    return this.keycloak?.accountManagement();
  }
}
