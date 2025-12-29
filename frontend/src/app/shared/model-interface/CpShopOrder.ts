import { ShopOrderItem } from './ShopOrderItem';
export interface CpShopOrder {
  id: number;
  userName: string;
  userEmail: string;     
  totalAmount: number;
  items: ShopOrderItem[];
  shopOrderStatusName: string; 
  created: string;
  currentStock:number
  price:number
}
