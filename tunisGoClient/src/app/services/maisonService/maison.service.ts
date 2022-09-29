import { Injectable } from '@angular/core';
import { HttpClient , HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_HOME = 'http://localhost:8011/gestionmaison/api/';

@Injectable({
  providedIn: 'root'
})
export class MaisonService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(API_HOME+'all');
  }
  getAllPublished(): Observable<any> {
    return this.http.get(API_HOME+'getAllPublished');
  }
  getAllNotPublished(): Observable<any> {
    return this.http.get(API_HOME+'getAllNotPublished');
  }
  getHouse(id): Observable<any> {
    return this.http.get(`${API_HOME}maison/${id}`);
  }
  getHousePerUser(id): Observable<any> {
    return this.http.get(`${API_HOME}allHomeForUser/${id}`);
  }
  addHouse(data): Observable<any> {
    return this.http.post(API_HOME+'add', data);
  
  }
//ajouter images
addImg(maisonId,file): Observable<HttpEvent<any>> {
  const formData: FormData = new FormData();

  formData.append('file', file);

  const req = new HttpRequest('POST', `${API_HOME}uploadmaison/${maisonId}`, formData, {
    reportProgress: true,
    responseType: 'json'
  });

  return this.http.request(req);
}

 //get maison by token
 getHouseByToken(addHouseToken): Observable<any> {
  return this.http.get(`${API_HOME}getByToken/${addHouseToken}`);
}
//update house
updateHouse(idHouse,data): Observable<any> {
  return this.http.put(`${API_HOME}edit/${idHouse}`, data);

}
//approuver maison
approuverHouse(idHouse): Observable<any> {
  return this.http.put(`${API_HOME}approuver/${idHouse}`,{});

}

//delete house
deleteHouse(id): Observable<any> {
  return this.http.delete(`${API_HOME}delete/${id}`);
}
}
