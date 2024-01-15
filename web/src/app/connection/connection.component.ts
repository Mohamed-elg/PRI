import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../services/auth.service';

interface LoginResponse {
  role: string;
  identifiant: string;
  message: string;
  token: string;
}

@Component({
  selector: 'connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.css']
})
export class ConnectionComponent implements OnInit {
  loginForm!: FormGroup;

  login_url = 'http://localhost:8081/api/account/auth';

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
      this.loginForm = this.fb.group({
        identifiant:   ['', Validators.required],
        password: ['', Validators.required]
      });
  }

  onSubmit() {
    if(this.loginForm.valid) {
      const formData = this.loginForm.value;

      this.authService.login(formData).subscribe(
        (response) => {
          this.router.navigate(['/add-mechanical-sheet']);
        },
        (error) => {
          console.error('Login failed', error);
        }
      )
    }
  }

}
