import { Injectable } from '@angular/core';
import { HttpResolver } from '../common/http-resolver.service';
import Utils from '../../common/utils';
import { environment } from '../../../shared/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class VideoService extends HttpResolver {
  override serverUrl = environment.serverApi + '/vtube/api/v1/video';
  public findAll() {
    return this.get('/all');
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
