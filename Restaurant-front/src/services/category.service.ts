import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Category} from '../model/category';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  url = 'http://localhost:4040/api/categories';

  constructor(private http: HttpClient) {
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url);
  }

  getCategoryById(id: number): Observable<any> {
    return this.http.get<any>(`${this.url}000/${id}`);
  }
}

