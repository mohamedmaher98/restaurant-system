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
  currentPageNumber = 1;
  productsNumberPerPage = 10;
  totalProductsNumber = 60;

  constructor(private prodService: ProductService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(value => this.fetchProducts());
  }


  private fetchProducts() {
    const paramMap = this.activatedRoute.snapshot.paramMap;
    if (paramMap.has('id')) {
      this.getProductsByCategoryId(paramMap.get('id'));
    } else if (paramMap.has('key')) {
      this.searchByProductNama(paramMap.get('key'));
    } else {
      this.getAllProducts(this.currentPageNumber, this.productsNumberPerPage);
    }
  }

  getAllProducts(pageNo, pageSize) {
    const products = this.prodService.getAllProduct(pageNo, pageSize);
    products.subscribe(r => this.products = r);
  }

  getProductsByCategoryId(categoryId: string) {
    const products = this.prodService.getProductsByCategoryId(categoryId);
    products.subscribe(p => this.products = p);
  }

  searchByProductNama(key: string) {
    const products = this.prodService.searchByProductNama(key);
    products.subscribe(p => this.products = p);
  }

  doPagination() {
    this.getAllProducts(this.currentPageNumber, this.productsNumberPerPage);
  }
}
