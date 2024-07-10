import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  getUsers() {
    throw new Error('Method not implemented.');
  }
  private apiUrl = 'http://localhost:3000/api';
  private isLoggedInStatus = false;

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  login(username: string, password: string): Observable<boolean> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, { username, password })
      .pipe(
        map(response => {
          if (response.token) {
            localStorage.setItem('authToken', response.token);
            this.isLoggedInStatus = true;
            return true;
          }
          return false;
        }),
        catchError(error => {
          console.error('Login failed', error);
          return of(false);
        })
      );
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('authToken');
    }
    this.isLoggedInStatus = false;
  }

  isLoggedIn(): boolean {
    return this.isLoggedInStatus || (isPlatformBrowser(this.platformId) && !!localStorage.getItem('authToken'));
  }

  getUserProfile(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user/profile`)
      .pipe(
        catchError(error => {
          console.error('Failed to fetch user profile', error);
          throw error;
        })
      );
  }

  register(registerData: { firstName: string; lastName: string; email: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, registerData)
      .pipe(
        catchError(error => {
          console.error('Registration failed', error);
          return of(error);
        })
      );
  }
}
