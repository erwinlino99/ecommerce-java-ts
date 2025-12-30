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
import { CpProductsPage } from './features/control-panel/pages/cp-products-page/cp-products-page';
import { CpOrdersPage } from './features/control-panel/pages/cp-orders-page/cp-orders-page';
import { CpUsersPage } from './features/control-panel/pages/cp-web-users-page/cp-web-users-page';
import { CpImportsPage } from './features/control-panel/pages/cp-imports-page/cp-imports-page';
import { CpOrderDetailPage } from './features/control-panel/details/cp-order-detail-page/cp-order-detail-page';
import { CpProductDetailPage } from './features/control-panel/details/cp-product-detail-page/cp-product-detail-page';
import { CpWebUserDetailPage } from './features/control-panel/details/cp-web-user-detail-page/cp-web-user-detail-page';
import path from 'path';
import { Component } from '@angular/core';

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
      {
        path: 'orders',
        children: [
          { path: '', component: CpOrdersPage },
          { path: 'order-detail/:orderIdDetail', component: CpOrderDetailPage },
        ],
      },
      {
        path: 'products',
        children: [
          { path: '', component: CpProductsPage },
          { path: 'product-detail/:productIdDetail', component: CpProductDetailPage },
        ],
      },
      {
        path: 'users',
        children: [
          { path: '', component: CpUsersPage },
          { path: 'web-detail/:webUserIdDetail', component: CpWebUserDetailPage },
        ],
      },
    ],
  },
];
