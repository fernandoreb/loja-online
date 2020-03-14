import { KeycloakConfig } from 'keycloak-angular';
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

let keycloakConfig: KeycloakConfig = {
  url: 'http://localhost:8180/auth',
  realm: 'loja_online',
  clientId: 'lojaOnline',
  "credentials": {
    "secret": "d47a2939-0fff-4603-94fb-30c4f13eb507"
  }
};

export const environment = {
  production: true,
  keycloakConfig,
  apiProductUrl: 'http://localhost:8081/sales/v1',
  apiProductCommentsUrl: 'http://localhost:3000/api',
  apiBPMUrl: 'http://localhost:8080/kie-server/services/rest/server/'
};
