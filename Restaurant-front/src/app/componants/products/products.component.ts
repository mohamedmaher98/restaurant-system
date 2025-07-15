import {Component, OnInit} from '@angular/core';
import {Product} from '../../../model/Product';
import {ProductService} from '../../../services/ProductService';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Product[];

  constructor(private prodService: ProductService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(value => this.fetchProducts());
  }


  private fetchProducts() {
    const paramMap = this.activatedRoute.snapshot.paramMap;
    const isExist = paramMap.has('id');
    if (isExist) {
      const id = paramMap.get('id');
      console.log(`id:${id}`);
      this.getProductsByCategoryId(id);
    } else {
      this.getAllProducts();
    }
  }

  getAllProducts() {
    const products = this.prodService.getAllProduct();
    products.subscribe(r => this.products = r);
  }

  getProductsByCategoryId(categoryId: string) {
    const products = this.prodService.getProductsByCategoryId(categoryId);
    products.subscribe(p => this.products = p);
  }
}
