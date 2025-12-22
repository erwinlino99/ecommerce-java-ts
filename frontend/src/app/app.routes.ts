import { Routes } from '@angular/router';
import { Home } from './home/home';
import { RegisterPage } from './features/pages/register-page/register-page';
import { HomePage } from './features/pages/home-page/home-page';
import { ProductsPage } from './features/pages/products-page/products-page';
import { ShopProductDetail } from './features/pages/shop-product-detail/shop-product-detail';
import { AppShell } from './shared/components/app-shell/app-shell';
import { CartPage } from './features/pages/cart-page/cart-page';
export const routes: Routes = [
  { path: '', component: Home },
  { path: 'register', component: RegisterPage },
  {
    path: '',
    component: AppShell,
    children: [
      { path: 'home', component: HomePage },
      { path: 'products', component: ProductsPage },
      { path: 'cart', component: CartPage },
      { path: 'products/detail/:shopProductId', component: ShopProductDetail },
    ],
  },
];
