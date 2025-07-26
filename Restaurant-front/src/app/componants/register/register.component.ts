import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: UntypedFormGroup;
  submitted = false;
  errorMessage = '';
  successMessage = '';
  isRegistering = false;

  constructor(private authService: AuthService, private router: Router, private formBuilder: UntypedFormBuilder) {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      fullName: ['', [Validators.required]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      phone: ['', [Validators.required, Validators.minLength(11)]]
    }, {
      validator: this.mustMatch('password', 'confirmPassword')
    });
  }

  mustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: UntypedFormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors.mustMatch) {
        return;
      }

      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }
    this.isRegistering = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.authService.register(this.registerForm.value.email,
      this.registerForm.value.fullName,
      this.registerForm.value.username,
      this.registerForm.value.password,
      this.registerForm.value.phone).subscribe({
      next: () => {
        this.successMessage = 'Registration successful! Please login.';
        this.isRegistering = false;
        this.submitted = false;
        this.registerForm.reset();
      },
      error: (error) => {
        this.errorMessage = error;
        this.isRegistering = false;
        this.submitted = false;
      }
    });
  }

  ngOnInit(): void {
  }

}
