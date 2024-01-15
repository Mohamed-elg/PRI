import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, tap } from 'rxjs';

interface LoginResponse {
  role: string;
  identifiant: string;
  message: string;
  token: string;
}


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly loginUrl = 'http://localhost:8081/api/account/auth';

  constructor(private http: HttpClient) { }

  login(credentials: {identidiant: string; pasword:string }): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.loginUrl, credentials).pipe(
      tap((response: LoginResponse) => {
        this.setAuth(response);
      }),
      catchError((error) => {
        console.error('Login failed', error);
        throw error;
      })
    )
  }

  private setAuth(response: LoginResponse): void {
    localStorage.setItem('authToken', response.token);
    localStorage.setItem('userRole', response.role);
  }

  logout(): void {
    localStorage.removeItem('authToken');
    localStorage.removeItem('userRole');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('authToken');
  }

}
