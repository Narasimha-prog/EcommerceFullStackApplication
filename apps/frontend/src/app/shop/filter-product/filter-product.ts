import { Component, effect, inject, input, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductFilter, RequestSort, SortableField, SortOrder } from '../../shared/model/request.model';
import { Form, FormBuilder, FormControl, FormRecord, Validators } from '@angular/forms';
import { FilterProductFormContent, ProductFilterForm, sizes } from '../../admin/model/product.model';

@Component({
  selector: 'app-filter-product',
  imports: [CommonModule],
  templateUrl: './filter-product.html',
  styleUrl: './filter-product.scss',
})
export class FilterProduct {

private  buildSizeFormController(): FormRecord<FormControl<boolean>> {
   const sizeFormControl = this.formBuilder.nonNullable.record<FormControl<boolean>>({});

    for(const size of sizes){
        sizeFormControl.addControl(size,new FormControl<boolean>(false,{nonNullable:true}));
    }
    return sizeFormControl;

  }

  sort=input<RequestSort>(
    {
      property:'createdDate',
      direction:'DESC'
    }
  );

  size=input<string>();


  productFilter=output<ProductFilter>();

  
  formBuilder=inject(FormBuilder);



formFilterProducts = this.formBuilder.nonNullable.group<FilterProductFormContent>({
  sort: this.formBuilder.nonNullable.group({
    property: new FormControl<SortableField>('createdDate', { nonNullable: true }),
    direction: new FormControl<SortOrder>('DESC', { nonNullable: true }),
  }),
  size: this.buildSizeFormController(),
});


  constructor() { 
    effect(()=>this.updateFormSortValue());
    effect(()=>this.updateFormSizeValue());
    this.formFilterProducts.valueChanges.subscribe( ()=> this.onFilterChange(this.formFilterProducts.getRawValue()))
  }
  private onFilterChange(filter: Partial<ProductFilterForm>): void {
    const filterProduct: ProductFilter = {
      size: '',
          sort: [{
      property: filter.sort?.property ?? 'createdDate',
      direction: filter.sort?.direction ?? 'DESC',
    }],
    };

    let sizes:[string,boolean][]=[];

    if(filter.size!==undefined){
       sizes=Object.entries(filter.size);
    }

    for(const [size,selected] of sizes){
      if(selected){
        if(filterProduct.size?.length===0){
          filterProduct.size=size;
        }else{
          filterProduct.size+=','+size;
        }
      }
    }

    this.productFilter.emit(filterProduct);

  }

  public getSizeFromGroup():FormRecord<FormControl<boolean>>{
    return this.formFilterProducts.get('size') as FormRecord<FormControl<boolean>>;
  }

  private updateFormSizeValue(){
    if(this.size()){
      const sizesSelected=this.size()?.split(',');
      const sizeFormControl=this.getSizeFromGroup();
      for(const size of sizesSelected!){
        if(sizeFormControl.contains(size)){
          sizeFormControl.get(size)?.setValue(true,{emitEvent:false});
        }
      }
    }
  }

  private updateFormSortValue(){
    if(this.sort()){
      const sortFormGroup=this.formFilterProducts.get('sort');
      sortFormGroup?.get('property')?.setValue(this.sort().property,{emitEvent:false});
      sortFormGroup?.get('direction')?.setValue(this.sort().direction,{emitEvent:false});
    }
  }
}


