import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private serviceUrl = environment.backendUrl;

  constructor(private http: HttpClient) { }

  public login(username: string, password: string): Observable<void> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Basic ' + btoa(username + ':' + password)
      })
    };
    return this.http.get<void>(this.serviceUrl + '/login', httpOptions);
  }
}
