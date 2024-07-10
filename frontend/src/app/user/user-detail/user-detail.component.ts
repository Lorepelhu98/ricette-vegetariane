import { Component, OnInit } from '@angular/core';
import { AuthService } from './../../auth/auth.service';
import { ActivatedRoute } from '@angular/router'


@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrl: './user-detail.component.scss'
})
export class UserDetailComponent implements OnInit {
  user: any;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    this.authService.getUserProfile().subscribe(
      (profile: any) => {
        this.user = profile;
      },
      (error: any) => {
        console.error('Failed to load user profile', error);
      }
    );
  }
}
