import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from '@env';
import { ApiEndpoints } from '../ApiEndpoints';

interface UserData {
  username: string;
  email: string;
}

interface RegisterResponse {
  message: string;
  success: boolean;
}

interface LoginResponse {
  token: string;
  username: string;
  email: string;
  message: string;
  success: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly API_URL = environment.apiUrl;
  private readonly TOKEN_KEY = 'jwt_token';
  private readonly USER_KEY = 'current_user';

  private currentUserSubject: BehaviorSubject<UserData | null>;
  public currentUser$: Observable<UserData | null>;

  constructor(
    private http: HttpClient,
    private router: Router,
    private jwtHelper: JwtHelperService
  ) {
    const user = this.getStoredUser();
    this.currentUserSubject = new BehaviorSubject<UserData | null>(user);
    this.currentUser$ = this.currentUserSubject.asObservable();
  }

  register(email: string, name: string, username: string, password: string, phone: string): Observable<boolean> {
    return this.http.post<RegisterResponse>(
      `${this.API_URL}${ApiEndpoints.REGISTER}`,
      { email, name, username, password, phone }
    ).pipe(
      map(response => {
        if (!response.success) {
          throw new Error(response.message || 'Registration failed');
        }
        return true;
      }),
      catchError((error: HttpErrorResponse) => {
        const errorMessage = error.error?.message
          || error.message
          || 'Registration failed due to an unknown error';
        console.error('Registration error:', errorMessage);
        return throwError(errorMessage);
      })
    );
  }

  login(usernameOrEmail, password): Observable<boolean> {
    return this.http.post<LoginResponse>(this.API_URL + ApiEndpoints.LOGIN, {
      usernameOrEmail, password
    }).pipe(
      tap(response => this.handleAuthentication(response)),
      map(response => response.success),
      catchError(error => {
        console.error('Login error:', error);
        return throwError(() => error.error?.message || 'Login failed');
      })
    );
  }

  private handleAuthentication(response: LoginResponse): void {
    if (response.success && response.token) {
      localStorage.setItem(this.TOKEN_KEY, response.token);
      const userData = { username: response.username, email: response.email };
      localStorage.setItem(this.USER_KEY, JSON.stringify(userData));
      this.currentUserSubject.next(userData);
    }
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY);
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  private getStoredUser(): UserData | null {
    const userJson = localStorage.getItem(this.USER_KEY);
    return userJson ? JSON.parse(userJson) : null;
  }

  public get currentUserValue(): UserData | null {
    return this.currentUserSubject.value;
  }

  get token(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  get isAuthenticated(): boolean {
    const token = this.token;
    return token ? !this.jwtHelper.isTokenExpired(token) : false;
  }

  get username(): string | null {
    return this.currentUserValue?.username || null;
  }

  get email(): string | null {
    return this.currentUserValue?.email || null;
  }

  autoLogin(): void {
    if (this.isAuthenticated && !this.currentUserValue) {
      const user = this.getStoredUser();
      if (user) {
        this.currentUserSubject.next(user);
      }
    }
  }
}
