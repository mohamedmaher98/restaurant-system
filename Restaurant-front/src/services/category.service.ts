import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Category} from '../model/Category';
import {HttpClient} from '@angular/common/http';
import {ApiEndpoints} from './ApiEndpoints';
import {ApiProvider} from './ApiProvider';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) {
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(ApiProvider.getFullUrl(ApiEndpoints.CATEGORIES));
  }

  getCategoryById(id: number): Observable<any> {
    return this.http.get<any>(ApiProvider.getFullUrl(ApiEndpoints.CATEGORIES + `${id}`));
  }
}

