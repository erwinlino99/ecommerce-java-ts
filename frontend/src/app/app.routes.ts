import { Routes } from '@angular/router';
import { Home } from './home/home';
import { RegisterPage } from './features/pages/register-page/register-page';
import { HomePage } from './features/pages/home-page/home-page';
import { ProductsPage } from './features/pages/products-page/products-page';
import { ShopProductDetail } from './features/pages/shop-product-detail/shop-product-detail';
import { AppShell } from './shared/components/app-shell/app-shell';
import { CartPage } from './features/pages/cart-page/cart-page';
import { ShopOrderPage } from './features/pages/shop-order-page/shop-order-page';


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
      { path: 'orders', component: ShopOrderPage },
      { path: 'products/detail/:shopProductId', component: ShopProductDetail },
    ],
  },
];
