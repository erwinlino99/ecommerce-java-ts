import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ApiService } from '../../../service/api.service';
import { WebUser } from '../../../shared/model-interface/WebUser';
import { Router } from '@angular/router';
import { LoginResponse } from '../../../shared/model-interface/LoginResponse';
@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './register-page.html',
  styleUrls: ['./register-page.scss'],
})
export class RegisterPage {

  webUsers: WebUser[] = [];

  constructor(private api: ApiService, private router: Router) {

  }

  registerForm = new FormGroup({
    name: new FormControl<string>(''),
    lastName: new FormControl<string>(''),
    email: new FormControl<string>(''),
    password: new FormControl<string>(''),
  });

  TryToRegister(): void {
    const formValue = this.registerForm.getRawValue();
    const newWebUser = {
      name: formValue.name ?? '',
      lastName: formValue.lastName ?? '',
      email: formValue.email ?? '',
      password: formValue.password ?? '',
    };
    const endpoint = 'auth/register';
    this.api.post<LoginResponse>(endpoint, newWebUser).subscribe({
      next: (resp) => {
        localStorage.setItem('token', resp.token);
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('Error registering:', err);
        if (err.status === 400) {
          alert('Email already registered');
        } else {
          alert('Unexpected error registering user');
        }
      },
    });
  }

  pingBackend(): void {
    this.api.get<string>('', { responseType: 'text' }).subscribe({
      next: (txt) => console.log('INFO FROM BACKEND :', txt),
      error: (err) => console.error('ERROR PING /:', err),
    });
  }
}
