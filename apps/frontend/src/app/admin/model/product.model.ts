import {   FormControl, FormGroup, FormRecord } from "@angular/forms";
import { SortableField, SortOrder } from "../../shared/model/request.model";

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
    size: ProductSizes;
    featured: boolean;
    nbInStock: number;
    category: ProductCategory;
    pictures: ProductPicture[];
}

export interface Product extends BaseProduct {
    publicId: string;
}

export type CreateCategoryFormContent={
    name:FormControl<string>;
}

export type CreateProductFormContent = {
     brand: FormControl<string>;
    color: FormControl<string>;
    name: FormControl<string>;
    description: FormControl<string>;
    price: FormControl<number>;
    size: FormControl<ProductSizes>;
    featured: FormControl<boolean>;
    stock: FormControl<number>;
    category: FormControl<string>;
    pictures: FormControl<ProductPicture[]>;
}

export type FilterProductFormContent = {
  sort: FormGroup<{
    property: FormControl<SortableField>;
    direction: FormControl<SortOrder>;
  }>;
  size: FormRecord<FormControl<boolean>>;
};


export interface ProductFilterForm{
    size?:{
        [size:string]:boolean;

    }|undefined;
    sort:{property:SortableField;direction:SortOrder}
}
