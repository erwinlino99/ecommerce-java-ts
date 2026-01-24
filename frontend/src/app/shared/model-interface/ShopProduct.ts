export interface ShopProduct{
    id:number,
    name:string,
    shortDescription:string,
    description:string,
    brandName:string,
    BrandId:number,
    shopProductMeasurement:string,
    currentStock:number,
    price:number,
    deleted:string | null
}