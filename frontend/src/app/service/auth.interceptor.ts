import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { SessionService } from './session.service';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
  const session = inject(SessionService);
  const token = session.getClientToken();

  if (!token) return next(req);

  return next(
    req.clone({
      setHeaders: { Authorization: `Bearer ${token}` },
    })
  );
};
