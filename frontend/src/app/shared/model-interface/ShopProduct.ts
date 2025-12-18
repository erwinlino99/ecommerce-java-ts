export interface ShopProduct{
    id:number,
    name:string,
    shortDescription:string,
    description:string,
    shopProductBrand:string,
    shopProductMeasurement:string,
    currentStock:number,
    price:number,
    deleted:string | null
}