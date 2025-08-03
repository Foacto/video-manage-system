import { Injectable } from '@angular/core';
import * as CryptoJs from 'crypto-js';

@Injectable({
	providedIn: 'root',
})
export class CryptoService {
	public static encrKey = 'NFnRy23311XOCtmGQ74J6aDc8nS9nYEVS7EfC9VAWuydAQsNYcx43b1nmnkVYOmU';

	public static encr(data: any): any {
		try {
			return CryptoJs.AES.encrypt(JSON.stringify(data), this.encrKey).toString();
		} catch (error) {
			console.error('Encryption failed:', error);
			return null;
		}
	}

	public static decr(data: any): any {
		try {
			const bytes = CryptoJs.AES.decrypt(data, this.encrKey);
			if (bytes) {
				return JSON.parse(bytes.toString(CryptoJs.enc.Utf8));
			}
		} catch (error) {
			console.error('Decryption failed:', error);
			return null;
		}
	}
}
