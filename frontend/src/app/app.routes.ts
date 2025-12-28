import { Routes } from '@angular/router';
import { Home } from './home/home';
import { RegisterPage } from './features/client-panel/pages/register-page/register-page';
import { HomePage } from './features/client-panel/pages/home-page/home-page';
import { ProductsPage } from './features/client-panel/pages/products-page/products-page';
import { ShopProductDetail } from './features/client-panel/details/shop-product-detail/shop-product-detail';
import { ClientAppShell } from './shared/components/cliente-app-shell/app-shell';
import { CartPage } from './features/client-panel/pages/cart-page/cart-page';
import { ShopOrderPage } from './features/client-panel/pages/shop-order-page/shop-order-page';
import { ControlPanelAppShell } from './shared/components/control-panel-app-shell/control-panel-app-shell';
import { ConfigurationPage } from './features/control-panel/pages/configuration-page/configuration-page';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'register', component: RegisterPage },
  {
    // --- RUTAS DE CLIENTE ---
    path: '',
    component: ClientAppShell,
    children: [
      { path: 'home', component: HomePage },
      { path: 'products', component: ProductsPage },
      { path: 'cart', component: CartPage },
      { path: 'orders', component: ShopOrderPage },
      { path: 'products/detail/:shopProductId', component: ShopProductDetail },
    ],
  },
  // --- RUTAS DE ADMINISTRACIÓN ---
  {
    path: 'admin', // Prefijo para el panel
    component: ControlPanelAppShell,
    children: [
      { path: 'config', component: ConfigurationPage },
      { path: '', redirectTo: 'config', pathMatch: 'full' },
    ],
  },
];
