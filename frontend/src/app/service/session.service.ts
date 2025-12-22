import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({ providedIn: 'root' })
export class SessionService {
  private readonly TOKEN_KEY = 'auth_token';
  private readonly USER_ID_KEY = 'user_id';

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  private isBrowser(): boolean {
    return isPlatformBrowser(this.platformId);
  }

  setToken(token: string): void {
    if (!this.isBrowser()) return;
    sessionStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    if (!this.isBrowser()) return null;
    return sessionStorage.getItem(this.TOKEN_KEY);
  }

  setUserId(id: number): void {
    if (!this.isBrowser()) return;
    sessionStorage.setItem(this.USER_ID_KEY, String(id));
  }

  getUserId(): number | null {
    if (!this.isBrowser()) return null;
    const v = sessionStorage.getItem(this.USER_ID_KEY);
    return v ? Number(v) : null;
  }

  clear(): void {
    if (!this.isBrowser()) return;
    sessionStorage.removeItem(this.TOKEN_KEY);
    sessionStorage.removeItem(this.USER_ID_KEY);
  }

  hasSession(): boolean {
    return !!this.getToken();
  }
}
