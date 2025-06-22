import {Component, OnInit} from '@angular/core';
import {Product} from '../../../model/product';
import {ProductService} from '../../../services/ProductService';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Product[];

  constructor(private prodService: ProductService) {
  }

  ngOnInit(): void {
    this.getAllProducts();
  }


  getAllProducts() {
    const products = this.prodService.getAllProduct();
    products.subscribe(r => this.products = r);
  }
}
