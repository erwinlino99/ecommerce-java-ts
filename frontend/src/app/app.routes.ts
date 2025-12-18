import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Prueba } from './features/pages/prueba/prueba';
import { RegisterPage } from './features/pages/register-page/register-page';
import { HomePage } from './features/pages/home-page/home-page';
import { ProductsPage } from './features/pages/products-page/products-page';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'pruebas', component: Prueba },
  { path: 'register', component: RegisterPage },
  { path: 'home', component: HomePage },
  { path: 'products', component: ProductsPage },
];
