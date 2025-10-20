import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { SessionService } from '../service/session.service';
import { ApiService } from '../service/api.service';
import { WebUser } from '../model_interface/WebUser';
@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './home-page.html',
  styleUrls: ['./home-page.scss'],
})
export class HomePage implements OnInit {
  userId: number | null = null;
  user: WebUser | null = null;
  loading = false;
  errorMsg = '';
  constructor(
    private session: SessionService,
    private api: ApiService,
    private router: Router
  ) {}
  ngOnInit(): void {
    console.log('[HomePage] sessionStorage ->', sessionStorage);
    this.userId = this.session.getUserId();
    console.log('[HomePage] userId ->', this.userId);
    // 1) Recuperar el id desde la "sesión" (sessionStorage)
    this.userId = this.session.getUserId();
    // 2) Si no hay sesión, volver al home original app
    if (!this.userId) {
      this.router.navigate(['/']);
      return;
    }
    // 3) (Opcional) Cargar datos del usuario desde el backend
    // ESTO SE LA INVENTADO XDDDDD
    // this.fetchUser();
  }
  private fetchUser(): void {
    this.loading = true;
    this.errorMsg = '';
    // Como no tenemos endpoint /web_user/:id, traemos todos y filtramos en el front.
    this.api.get<WebUser[]>('web_user').subscribe({
      next: (list) => {
        this.user = list.find((u) => Number(u.id) === this.userId) ?? null;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading user list:', err);
        this.errorMsg = 'No se pudo cargar la información del usuario.';
        this.loading = false;
      },
    });
  }
  logout(): void {
    this.session.clear();
    this.router.navigate(['/register']);
  }
  // Ejemplo de acción protegida que requiere sesión
  goToMyProfile(): void {
    if (!this.userId) return;
    // Aquí no exponemos el id en URL; podrías usar un estado interno si tu ruta lo necesita
    this.router.navigate(['/profile']);
  }
}
