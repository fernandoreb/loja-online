
import { KeycloakConfig } from 'keycloak-angular';

let keycloakConfig: KeycloakConfig = {
  url: 'http://localhost:8180/auth',
  realm: 'loja_online',
  clientId: 'lojaOnline',
  "credentials": {
    "secret": "d47a2939-0fff-4603-94fb-30c4f13eb507"
  }
};

export const config = {
  apiUrl: 'http://localhost:3000',
  keycloak: keycloakConfig,
};
