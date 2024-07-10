import { Component } from '@angular/core';
import { AuthService } from './../../auth/auth.service';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  isMenuCollapsed = true;
  isUserLoggedIn = false;

  constructor(private authService: AuthService) {
    this.isUserLoggedIn = this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.isUserLoggedIn = false;
  }
}
