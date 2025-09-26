import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './register-page.html',
  styleUrls: ['./register-page.scss'],
})
export class RegisterPage {

  constructor(private api: ApiService) {}

  registerForm = new FormGroup({
    firstName: new FormControl<string>(''),
    lastName:  new FormControl<string>(''),
    email:     new FormControl<string>(''),
    password:  new FormControl<string>(''),
  });

  TryToRegister(): void {
    const newWebUser = this.registerForm.getRawValue();
    console.log('DATOS DEL FORMULARIO:', newWebUser);

    // 👉 Llamada de prueba al backend (GET /) que devuelve TEXTO
    this.api.get<string>('', { responseType: 'text' }).subscribe({
      next: (txt) => console.log('BACKEND DICE:', txt),
      error: (err) => console.error('ERROR PING /:', err)
    });
  }

  // (Opcional) botón separado en la vista:
  pingBackend(): void {
    this.api.get<string>('', { responseType: 'text' }).subscribe({
      next: (txt) => console.log('FROM BACKEND', txt),
      error: (err) => console.error('ERROR PING /:', err)
    });
  }
}
