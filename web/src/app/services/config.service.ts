import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConfigService {
  private configUrl = '/config';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getConfig().subscribe(
      (data) => {
        localStorage.setItem('URL_API', data.URL_API);
      },
      (error) => {
        console.error('Error fetching configuration:', error);
        localStorage.setItem('URL_API', 'http://localhost:8081/api');
      }
    );
  }

  getConfig(): Observable<any> {
    return this.http.get(this.configUrl);
  }

  getApiUrl(): string | null {
    return localStorage.getItem('URL_API');
  }
}
