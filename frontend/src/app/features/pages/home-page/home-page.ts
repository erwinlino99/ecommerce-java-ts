import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { Observable, of } from 'rxjs';
import { catchError, finalize, shareReplay } from 'rxjs/operators';

import { SessionService } from '../../../service/session.service';
import { ApiService } from '../../../service/api.service';
import { WebUser } from '../../../shared/model-interface/WebUser';
import { ShopIndex } from '../../../shared/model-interface/ShopIndex';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, RouterLink],
  templateUrl: './home-page.html',
  styleUrls: ['./home-page.scss'],
})
export class HomePage implements OnInit {
  userId: number | null = null;

  // ahora son Observables
  webUser$!: Observable<WebUser | null>;
  shopIndex$!: Observable<ShopIndex[]>;

  loading = false;
  errorMsg = '';

  constructor(
    private session: SessionService,
    private api: ApiService,
    private router: Router
  ) {}

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
    
    this.shopIndex$ = this.api.get<ShopIndex[]>('shop-index').pipe(
      catchError((err) => {
        console.error('Error loading shop_index:', err);
        this.errorMsg = 'No se pudo cargar el menú principal.';
        return of([] as ShopIndex[]);
      }),
   
      shareReplay(1)
    );
  }

  private fetchUser(userId: number): void {
    this.loading = true;
    this.errorMsg = '';

    this.webUser$ = this.api.get<WebUser>(['web-user', userId]).pipe(
      catchError((err) => {
        console.error('Error loading user:', err);
        this.errorMsg = 'No se pudo cargar la información del usuario.';
        return of(null);
      }),
      finalize(() => {
        this.loading = false;
      }),
      shareReplay(1)
    );
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
