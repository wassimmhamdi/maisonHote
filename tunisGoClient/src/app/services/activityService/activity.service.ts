import { Injectable } from '@angular/core';
import { HttpClient , HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';


const API_ACT = 'http://localhost:8011/gestionactivite/api/activity/';
const API_IMG = 'http://localhost:8011/gestionactivite/api/FileActivity/';
@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) { }

   getAll(): Observable<any> {
    return this.http.get(API_ACT+'all');
  }
  getAllNotPublished(): Observable<any> {
    return this.http.get(API_ACT+'allNotPublished');
  }
  getAllPublished(): Observable<any> {
    return this.http.get(API_ACT+'allPublished');
  }
  getAct(id): Observable<any> {
    return this.http.get(`${API_ACT}getById/${id}`);
  }

  //ajouter activité
  addAct(data): Observable<any> {
    return this.http.post(API_ACT+'add', data);
  
  }
//ajouter images
addImg(activityId,file): Observable<HttpEvent<any>> {
  const formData: FormData = new FormData();

  formData.append('file', file);

  const req = new HttpRequest('POST', `${API_IMG}add/${activityId}`, formData, {
    reportProgress: true,
    responseType: 'json'
  });

  return this.http.request(req);
}

  updateAct(id, data): Observable<any> {
    return this.http.put(`${API_ACT}edit/${id}`, data);
  }

  deleteAct(id): Observable<any> {
    return this.http.delete(`${API_ACT}delete/${id}`);
  }

  //get activity by token
  getActByToken(addActToken): Observable<any> {
    return this.http.get(`${API_ACT}getByToken/${addActToken}`);
  }

  //get activity per user
  getActPerUser(idClient){
    return this.http.get(`${API_ACT}allActivityForUser/${idClient}`);
  }
  getActById(id){
    return this.http.get(`${API_ACT}getById/${id}`);
  }

  //get images d'une activitée specifique
  getImgPerAct(idAct){
    return this.http.get(`${API_IMG}files/${idAct}`);
  }

  approuverActivity(idAct): Observable<any> {
    return this.http.put(`${API_ACT}approuver/${idAct}`,{});
  
  }
}
