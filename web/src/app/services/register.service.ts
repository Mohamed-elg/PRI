import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  // private readonly RegisterUrl = 'http://localhost:8081/api/account/create';
  private readonly RegisterUrl = `${localStorage.getItem(
    'URL_API'
  )}/account/create`;

  constructor(private http: HttpClient) {}

  Register(bearerToken: string | null, payload: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${bearerToken}`,
    });

    const body = {
      identifiant: payload.identifiant,
      password: payload.password,
      role: payload.role,
    };

    return this.http.post<any>(this.RegisterUrl, body, { headers: headers });
  }
}
