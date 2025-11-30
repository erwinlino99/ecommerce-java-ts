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
  webUser: WebUser | null = null;
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
    this.api.get<ShopIndex[]>('shop_index').subscribe({
      next: (items) => {
        this.shopIndex = items;
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
    this.api.get<WebUser>(['web_user',userId]).subscribe({
      next: (webUserFound) => {
        this.webUser=webUserFound;
        console.log("WEBUSER:",this.webUser)
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
  goToMyProfile(): void {
    if (!this.userId) return;
    this.router.navigate(['/profile']);
  }
}
