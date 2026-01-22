import { ShopOrderItem } from './ShopOrderItem';
import { PokemonGift } from './PokemonGift';
export interface CpShopOrder {
  id: number;
  userName: string;
  userEmail: string;
  totalAmount: number;
  items: ShopOrderItem[];
  shopOrderStatusName: string;
  created: string;
  currentStock: number;
  price: number;
  pokeGift?: PokemonGift[];
}
