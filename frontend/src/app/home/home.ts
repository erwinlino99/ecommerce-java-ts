import { Component, OnInit } from '@angular/core';
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
import { environment } from '../environment/environment';

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
export class Home implements OnInit {
  emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  isPro = environment.production;
  constructor(private api: ApiService, private router: Router, private session: SessionService) {}

  webUserLoginForm = new FormGroup({
    email: new FormControl<string>('', [Validators.required, Validators.pattern(this.emailRegex)]),
    password: new FormControl<string>('', [Validators.required]),
  });

  ngOnInit(): void {
    //ESTA VARIABLE PUEDE SER TRUE OR FALSE, SI ES FALSE , TENEMSO QUE HACER REFERECIA EN EL LOGIN DE QUE ES LOCAL
    console.log('VER ESTO ->', this.isPro);
  }
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
        if (data.roleName && data.roleName !== 'ROLE_CLIENT') {
          this.session.setSuperAdminToken(data.token);
          this.router.navigate(['/admin/config']);
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
