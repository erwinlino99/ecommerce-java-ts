import { ShopProductLite } from './ShopProductLite';

export interface ShopOrderItem {
  product: ShopProductLite; 
  quantity: number;
  unitPrice: number;
  subtotal: number;
}