import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConfigService {
  private configUrl = '/config';

  constructor(private http: HttpClient) {}

  getConfig(): Observable<any> {
    return this.http.get(this.configUrl);
  }

  getApiUrl(): string | null {
    return localStorage.getItem('URL_API');
  }
}
