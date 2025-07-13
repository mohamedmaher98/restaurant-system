import {Category} from './Category';
import {BaseEntity} from './BaseEntity';

export class Product extends BaseEntity {
  description: string;
  price: number;
  category: Category;


  constructor(product: Partial<Product>) {
    super(product);
    if (product) {
      this.description = product.description;
      this.price = product.price;
      this.category = product.category;
    }
  }
}
