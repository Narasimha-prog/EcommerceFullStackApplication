export type ProductSizes = 'XS' | 'S' | 'M' | 'L' | 'XL' | 'XXL';
export const sizes: ProductSizes[] = ['XS', 'S', 'M', 'L', 'XL', 'XXL'];



export interface ProductCategory {

    publicId?: string;
    name?: string;
}

export interface ProductPicture {
    file: File;
    mineType: string;

}

export interface BaseProduct {
    brand: string;
    color: string;
    name: string;
    description: string;
    price: number;
    size: ProductSizes[];
    featured: boolean;
    nbInStock: number;
    category: ProductCategory;
    pictures: ProductPicture[];
}

export interface Product extends BaseProduct {
    publicId: string;
}