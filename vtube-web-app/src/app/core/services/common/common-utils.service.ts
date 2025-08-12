import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CommonUtils {
  public static isNullOrEmpty(value: any): boolean {
    return !value || (value + '').trim() === '';
  }
}
