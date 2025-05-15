import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../shared/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class HttpResolver {
  public serverUrl: string = environment.serverApi;

  constructor(public http: HttpClient) {}

  get(api: string) {
    return this.http.get(this.serverUrl + api);
  }

  post(api: string, body: any) {
    return this.http.post(this.serverUrl + api, body);
  }
}
