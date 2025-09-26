import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Prueba } from './prueba/prueba';
import { RegisterPage } from './register-page/register-page';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'pruebas', component: Prueba },
  { path: 'register', component: RegisterPage }
];
