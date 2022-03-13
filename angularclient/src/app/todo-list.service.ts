import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Todo} from "./todo";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TodoListService {

  private serviceUrl: string;

  constructor(private http: HttpClient) {
    this.serviceUrl = 'http://localhost:8080/api/todo';
  }

  createAuthorizationHeader(headers: HttpHeaders) {
    headers.append('Authorization', 'Basic ' +
    btoa('username:password'));

  }

  public findAll(): Observable<Todo[]> {
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Basic ' + btoa('h2ving@mail.com:tricky'))
    return this.http.get<Todo[]>(this.serviceUrl, {
      headers: headers
    });
  }
}
