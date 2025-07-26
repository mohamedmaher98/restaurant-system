import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: UntypedFormGroup;
  submitted = false;
  errorMessage = '';
  isLoggingIn = false;

  constructor(private authService: AuthService, private formBuilder: UntypedFormBuilder, private router: Router) {
    this.loginForm = this.formBuilder.group({
      usernameOrEmail: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }
    this.isLoggingIn = true;
    this.errorMessage = '';

    this.authService.login(this.loginForm.value.usernameOrEmail, this.loginForm.value.password).subscribe({
      next: () => {
        this.router.navigate(['/products']);
      },
      error: (error) => {
        this.errorMessage = error;
        this.isLoggingIn = false;
      },
      complete: () => {
        this.isLoggingIn = false;
      }
    });

    this.submitted = false;
    this.loginForm.reset();
  }

  ngOnInit(): void {
    this.authService.autoLogin();
  }

}
