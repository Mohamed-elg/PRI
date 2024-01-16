import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { RegisterService } from '../services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private registerService: RegisterService
  ) {}

  ngOnInit(): void {
    const passwordMatchValidator: ValidatorFn = (
      control: AbstractControl
    ): ValidationErrors | null => {
      const password = control.get('password')?.value;
      const passwordRepeat = control.get('password_repeat')?.value;

      return password === passwordRepeat ? null : { passwordsNotMatch: true };
    };

    this.registerForm = this.fb.group(
      {
        identifiant: ['', Validators.required],
        password: ['', [Validators.required]],
        password_repeat: ['', [Validators.required]],
        selectedRole: ['USER', Validators.required],
      },
      { validators: passwordMatchValidator }
    );
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const finalData = {
        identifiant: this.registerForm.value.identifiant,
        password: this.registerForm.value.password,
        role: this.registerForm.get('selectedRole')?.value,
      };

      this.registerService
        .Register(localStorage.getItem('authToken'), finalData)
        .subscribe(
          (response) => {
            alert('Compte crée');
            this.router.navigate(['/add-mechanical-sheet']);
          },
          (error) => {
            console.log(localStorage.getItem('authToken'), finalData);
            console.error('Creation failed', error);
            alert(
              "Erreur de création, vérifier que l'utilisateur n'existe pas"
            );
          }
        );
    } else {
      alert('Formulaire invalide');
    }
  }
}
