import { Component, OnInit } from '@angular/core';
import { AuthService } from './../../auth/auth.service'

interface UserProfile {
  firstName: string;
  lastName: string;
  email: string;
  role: string;
}

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  userProfile: any;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    this.authService.getUserProfile().subscribe({
      next: (profile) => {
        this.userProfile = profile;
      },
      error: (error) => {
        console.error('Failed to load user profile', error);
      }
    });
  }
}
