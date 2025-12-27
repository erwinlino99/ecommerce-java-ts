import { ShopOrderItem } from './ShopOrderItem';
import { PokemonGift } from './PokemonGift';
export interface ShopOrder {
  id: number;
  totalAmount: number;
  items: ShopOrderItem[];
  shopOrderStatusName: string; 
  created: string;
  pokeGift?: PokemonGift[];
}
