import { ShopCartItem } from './ShopCartItem';

export interface ShopCart {
  shopCartId: number;
  webUserId: number;
  totalAmount: number;
  totalItems: number;
  items: ShopCartItem[];
}
