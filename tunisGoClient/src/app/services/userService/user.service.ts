import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8011/gestionuser/service/';
const API_PWD = 'http://localhost:8011/gestionuser/resetmdp/';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(API_URL+'users/list');
  }

  getUser(id): Observable<any> {
    return this.http.get(`${API_URL}users/${id}`);
  }

  addUser(data): Observable<any> {
    return this.http.post(API_URL+'users/add', data);
  
  }

  updateUser(id, data): Observable<any> {
    return this.http.put(`${API_URL}users/${id}`, data);
  }

  deleteUser(id): Observable<any> {
    return this.http.delete(`${API_URL}users/${id}`);
  }

  sendMailResetMdp(data): Observable<any> {
    return this.http.post(`${API_PWD}forgot_password`,data);
  }

  resetMdp(data): Observable<any> {
    return this.http.post(`${API_PWD}reset_password`,data);
  }
}