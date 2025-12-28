import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../service/api.service';
import { SessionService } from '../service/session.service';
import { Router } from '@angular/router';

type LoginResponse = {
  roleName: string;
  token: string;
  webUserId: number;
};

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
})
export class Home {
  emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

  constructor(private api: ApiService, private router: Router, private session: SessionService) {}

  webUserLoginForm = new FormGroup({
    email: new FormControl<string>('', [Validators.required, Validators.pattern(this.emailRegex)]),
    password: new FormControl<string>('', [Validators.required]),
  });

  goToHomeApp() {
    const endpoint = 'auth/login';
    const email = this.webUserLoginForm.get('email')?.value ?? '';
    const password = this.webUserLoginForm.get('password')?.value ?? '';

    if (!this.emailRegex.test(email) || password.length === 0) {
      return;
    }

    const body = { email, password };

    this.api.post<LoginResponse>(endpoint, body).subscribe({
      next: (data) => {
        console.log('VER ESTO ->', data);
        if (data.roleName == 'SUPER_ADMIN') {
          this.session.setSuperAdminToken(data.token);
          this.router.navigate(['/admin']);
        } else {
          this.session.setWebUserToken(data.token);
          this.router.navigate(['/home']);
        }
      },
      error: (err) => {
        console.error('Error en login:', err);
      },
    });
  }

  goToRegister() {
    this.router.navigate(['/register']);
  }
}
