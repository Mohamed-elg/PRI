import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.css']
})
export class ConnectionComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  ngOnInit(): void {
      this.loginForm = this.fb.group({
        inputId:   ['', Validators.required],
        inputPassword: ['', Validators.required]
      });
  }

  onSubmit() {
    if(this.loginForm.valid) {
      const formData = this.loginForm.value;

      console.log(formData);

      /*
      this.http.post('[api_endpoint]', formData).subscribe(
        (response) => {
          console.log('Login successful',response);
        },
        (error) => {
          console.error('Login failed', error);
        }
      );
      */
    }
  }

}
