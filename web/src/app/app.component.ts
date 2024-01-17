import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';
import { ConfigService } from './services/config.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private router: Router,
    private configService: ConfigService
  ) {}

  // Solution provisoire pour ne pas avoir à manuellement modifier l'url de l'api dans le docker
  ngOnInit(): void {
    this.configService.getConfig().subscribe(
      (data) => {
        localStorage.setItem('URL_API', data.URL_API);
      },
      (error) => {
        console.error('Error fetching configuration:', error);
        localStorage.setItem('URL_API', 'http://localhost:8081/api');
      }
    );
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  logout() {
    this.authService.logout();
  }
}
