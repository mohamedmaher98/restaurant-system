import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Chef} from '../model/Chef';
import {ApiProvider} from './ApiProvider';
import {ApiEndpoints} from './ApiEndpoints';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChefService {

  constructor(private http: HttpClient) {
  }

  getAllChefs(): Observable<Chef[]> {
    return this.http.get<Chef[]>(ApiProvider.getFullUrl(ApiEndpoints.CHEFS));
  }
}
