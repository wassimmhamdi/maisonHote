import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

const API_CONV = 'http://localhost:8011/gestionactivite/api/Convention/';

@Injectable({
  providedIn: 'root'
})
export class ConventionService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(API_CONV+'all');
  }

  getConv(id): Observable<any> {
    return this.http.get(`${API_CONV}getById/${id}`);
  }

  addConv(id, data): Observable<any> {
    return this.http.post(`${API_CONV}getById/${id}`, data);
  
  }
}
