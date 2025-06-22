import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url = 'http://localhost:4040/api/products';

  constructor(private http: HttpClient) {
  }

  getAllProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(this.url);
  }

}
