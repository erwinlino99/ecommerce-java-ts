import { ShopOrderItem } from './ShopOrderItem';

export interface ShopOrder{
    id:number,
    totalAmount: number,
    items:ShopOrderItem[],
    created:String

}