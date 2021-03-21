import { KeycloakConfig } from 'keycloak-angular';
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

let keycloakConfig: KeycloakConfig = {
  url: 'http://192.168.0.15:8180/auth',
  realm: 'loja_online',
  clientId: 'lojaOnline',
  "credentials": {
    "secret": "17dea393-abdc-4286-a819-c10eef1145c0"
  }
};

export const environment = {
  production: false,
  keycloakConfig,
  apiProductUrl: 'http://192.168.0.15:8081/sales/v1',
  apiProductCommentsUrl: 'http://192.168.0.15:3000/api',
  apiBPMUrl: 'http://192.168.0.15:8082/kie-server/services/rest/server/'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
