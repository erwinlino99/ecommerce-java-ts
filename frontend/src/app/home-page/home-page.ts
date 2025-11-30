import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { SessionService } from '../service/session.service';
import { ApiService } from '../service/api.service';
import { WebUser } from '../model_interface/WebUser';
import { ShopIndex } from '../model_interface/ShopIndex';

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
  shopIndex : ShopIndex[]=[];

  constructor(private session: SessionService, private api: ApiService, private router: Router) {
  }

  ngOnInit(): void {
    this.userId = this.session.getUserId();
    if (!this.userId) {
      this.router.navigate(['/']);
      return;
    }
    this.fetchUser(this.userId);
    this.fetchShopIndex();
  }

  private fetchShopIndex(): void {
    this.api.get<ShopIndex[]>('shop-index').subscribe({   // 👈 ajusta string si tu ApiService usa otro path
      next: (items) => {
        this.shopIndex = items;
        console.log("INIDICES -->",this.shopIndex)
      },
      error: (err) => {
        console.error('Error loading shop_index:', err);
        this.errorMsg = 'No se pudo cargar el menú principal.';
      },
    });
  }
  private fetchUser(userId:number): void {
    this.loading = true;
    this.errorMsg = '';
    console.log("ID DE MI USUARIO->",userId)
    //AHORA NECESITAMOS HACER UN GET CON EL userId y sacar toda la informacion y asiganarala al interfaz de WebUser
    this.api.get<WebUser[]>(['web_user',userId]).subscribe({
      next: (list) => {
        console.log("-->",list)
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
