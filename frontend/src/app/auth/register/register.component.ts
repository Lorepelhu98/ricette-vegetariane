import { Component } from '@angular/core';
import { AuthService } from './../auth.service';
import { Router } from '@angular/router'


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerData = {
    firstName: '',
    lastName: '',
    email: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router) {}

  signUp() {
    this.authService.register(this.registerData).subscribe(
      (success: boolean) => {
        if (success) {
          this.router.navigate(['/auth/login']);
        } else {
          alert('Registrazione fallita');
        }
      },
      (error: any) => {
        console.error('Errore durante la registrazione:', error);
        alert('Errore durante la registrazione');
      }
    );
  }
}
