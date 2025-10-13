import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../environment/environment';

type Path = string | number;

export interface RequestOptions {
  params?: Record<string, any> | HttpParams;      // objeto plano o HttpParams
  headers?: Record<string, string> | HttpHeaders; // objeto plano o HttpHeaders
  withCredentials?: boolean;
  responseType?: 'json' | 'blob' | 'text';
}

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = (environment.apiBase ?? '').replace(/\/+$/, '');

  constructor(private http: HttpClient) {}

  // ---------- MÉTODOS PÚBLICOS ----------
  get<T>(endpoint: string | Path[], options?: RequestOptions): Observable<T> {
    const { url, opts } = this.prepare(endpoint, undefined, options);
    // console.log('calling -->',url);
    return this.http.get<T>(url, opts).pipe(catchError(this.handleError));
  }
  getRootUrl():String{
    return this.baseUrl;
  }

  post<T>(endpoint: string | Path[], body?: any, options?: RequestOptions): Observable<T> {
    const { url, opts } = this.prepare(endpoint, body, options);
    return this.http.post<T>(url, body, opts).pipe(catchError(this.handleError));
  }

  put<T>(endpoint: string | Path[], body?: any, options?: RequestOptions): Observable<T> {
    const { url, opts } = this.prepare(endpoint, body, options);
    return this.http.put<T>(url, body, opts).pipe(catchError(this.handleError));
  }

  patch<T>(endpoint: string | Path[], body?: any, options?: RequestOptions): Observable<T> {
    const { url, opts } = this.prepare(endpoint, body, options);
    return this.http.patch<T>(url, body, opts).pipe(catchError(this.handleError));
  }

  // DELETE soportando body opcional (usa request() para permitir body)
  delete<T>(endpoint: string | Path[], body?: any, options?: RequestOptions): Observable<T> {
    const { url, opts } = this.prepare(endpoint, body, options);
    return this.http.request<T>('DELETE', url, { ...opts, body }).pipe(catchError(this.handleError));
  }

  // ---------- HELPERS PRIVADOS ----------
  private prepare(endpoint: string | Path[], _body?: any, options?: RequestOptions) {
    const url = this.buildUrl(endpoint);
    const opts = this.buildOptions(options);
    return { url, opts };
  }

  private buildUrl(endpoint: string | Path[]): string {
    const path = Array.isArray(endpoint)
      ? endpoint
          .filter(v => v !== null && v !== undefined && `${v}`.trim().length > 0)
          .map(seg => encodeURIComponent(String(seg)))
          .join('/')
      : endpoint.replace(/^\/+/, '');
    return `${this.baseUrl}/${path}`;
  }

  private buildOptions(options?: RequestOptions) {
    const params = this.toHttpParams(options?.params);
    const headers = this.toHttpHeaders(options?.headers);
    const responseType = (options?.responseType ?? 'json') as 'json';
    const withCredentials = options?.withCredentials ?? false;
    return { params, headers, responseType, withCredentials };
  }

  private toHttpParams(source?: Record<string, any> | HttpParams): HttpParams | undefined {
    if (!source) return undefined;
    if (source instanceof HttpParams) return source;

    let params = new HttpParams();
    Object.entries(source).forEach(([key, value]) => {
      if (value === null || value === undefined || value === '') return;

      if (Array.isArray(value)) {
        value.forEach(v => {
          if (v !== null && v !== undefined && v !== '') {
            params = params.append(key, String(v));
          }
        });
      } else if (typeof value === 'object') {
        params = params.set(key, JSON.stringify(value));
      } else {
        params = params.set(key, String(value));
      }
    });
    return params;
  }

  private toHttpHeaders(source?: Record<string, string> | HttpHeaders): HttpHeaders | undefined {
    if (!source) return undefined;
    if (source instanceof HttpHeaders) return source;

    let headers = new HttpHeaders();
    Object.entries(source).forEach(([k, v]) => { headers = headers.set(k, v); });
    return headers;
  }

  private handleError(err: any) {
    console.error('[ApiService] HTTP error:', err);
    return throwError(() => err);
  }
}
