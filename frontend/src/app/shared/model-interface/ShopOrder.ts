import { ShopOrderItem } from './ShopOrderItem';

export interface ShopOrder {
  id: number;
  totalAmount: number;
  items: ShopOrderItem[];
  shopOrderStatusName: string; 
  created: string; 
}
