import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Prueba } from './prueba/prueba';

export const routes: Routes = [
  { path: '', component: Home },       // BASIC LOGIN TO HGOME 
  { path: 'pruebas', component: Prueba },
  { path: '**', redirectTo: '' }

  
];
