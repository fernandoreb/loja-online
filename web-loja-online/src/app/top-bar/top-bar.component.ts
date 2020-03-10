import { Component, OnInit } from '@angular/core';
import { KeycloakProfile } from 'keycloak-js';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {

  userDetails: KeycloakProfile;
  userRoles: string[];
  showName: boolean;
  constructor(private keycloakService: KeycloakService) {
    this.showName = false;
}

  async ngOnInit() {

    if (await this.keycloakService.isLoggedIn()) {
      this.userDetails = await this.keycloakService.loadUserProfile();
      console.log(this.userDetails);
      this.userRoles = await this.keycloakService.getUserRoles();
      console.log(this.userRoles);
      this.showName = true;
    }
  }

  async doLogout() {
    await this.keycloakService.logout();
    this.showName = false;
  }

}
