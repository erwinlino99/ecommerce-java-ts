import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class SessionService {
  private userIdKey = 'sessionUserId';
  private readonly TOKEN_KEY = 'auth_token';
  private readonly USER_ID_KEY = 'user_id';

  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  setUserId(id: number): void {
    sessionStorage.setItem(this.userIdKey, String(id));
  }

  getUserId(): number | null {
    const v = sessionStorage.getItem(this.userIdKey);
    return v ? Number(v) : null;
  }

  clear(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_ID_KEY);
    sessionStorage.removeItem(this.userIdKey);
  }

  hasSession(): boolean {
    return !!sessionStorage.getItem(this.userIdKey);
  }
}
