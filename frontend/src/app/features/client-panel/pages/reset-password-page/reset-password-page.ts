import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

// IMPORTACIONES DE MATERIAL NECESARIAS
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ApiService } from '../../../../service/api.service';
import { PopupService } from '../../../../service/pop.up.data.service';
@Component({
  selector: 'app-reset-password-page',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './reset-password-page.html',
  styleUrl: './reset-password-page.scss',
})
export class ResetPasswordPage {
  private router = inject(Router);
  private popup = inject(PopupService);
  private api = inject(ApiService);
  resetForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    newPassword: new FormControl('', [Validators.required, Validators.minLength(1)]),
  });

  onResetPassword() {
    if (this.resetForm.valid) {
      const endpoint = 'auth/reset-password';
      const payload = {
        email: this.resetForm.value.email,
        newPassword: this.resetForm.value.newPassword,
      };
      this.api.post(endpoint, payload).subscribe({
        next: (data) => {
          this.popup.success('CONTRASEÑA RESTRABLECIDA');
          setTimeout(() => {
            this.router.navigate(['/']);
          }, 1000);
        },
      });
    }
  }
  goToLogin() {
    this.router.navigate(['/']);
  }
}
