import { Injectable } from '@angular/core';
import { HttpResolver } from '../common/http-resolver.service';
import Utils from '../../common/utils';

@Injectable({
  providedIn: 'root',
})
export class VideoService extends HttpResolver {
  public findAll() {
    return this.get('/video/all');
  }

  public findOne(id: number) {
    return this.get(`/video/${id}`);
  }

  public upload(form: any) {
    return this.post(
      '/video',
      Utils.convertJsontoFormData(form, null, undefined)
    );
  }
}
