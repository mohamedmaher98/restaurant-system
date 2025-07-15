import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Product} from '../model/Product';
import {ApiEndpoints} from './ApiEndpoints';
import {HttpClient} from '@angular/common/http';
import {ApiProvider} from './ApiProvider';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  getAllProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(ApiProvider.getFullUrl(ApiEndpoints.PRODUCTS));
  }

  getProductsByCategoryId(categoryId: string): Observable<Product[]> {
    return this.http.get<Product[]>(ApiProvider.getFullUrl(ApiEndpoints.FIND_PRODUCTS_BY_CATEGORY_ID + `/${categoryId}`));
  }

}
