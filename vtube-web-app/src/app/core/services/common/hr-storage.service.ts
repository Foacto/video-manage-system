import { Injectable } from '@angular/core';
import { CommonUtils } from './common-utils.service';
import { CryptoService } from './crypto.service';

@Injectable({
	providedIn: 'root',
})
class StorageData {
	userToken: string | undefined;
	navState: boolean | undefined;
	currenUrl: any | undefined;
}
export class HrStorage {
	private static instanceName = 'HrStorage';
	private static storage = localStorage;

	public static storedData(): any {
		const storedData = this.storage.getItem(this.instanceName);
		if (CommonUtils.isNullOrEmpty(storedData)) {
			return null;
		}
		return CryptoService.decr(storedData);
	}

	public static clear(): void {
		this.storage.removeItem(this.instanceName);
	}

	public static get(key: string): any {
		const storedData = this.storedData();
		if (CommonUtils.isNullOrEmpty(storedData)) {
			return null;
		}
		return storedData[key];
	}

	public static set(key: string, val: any): void {
		let storedData = this.storedData();
		if (CommonUtils.isNullOrEmpty(storedData)) {
			storedData = new StorageData();
		}
		storedData[key] = val;
		return this.storage.setItem(this.instanceName, CryptoService.encr(storedData));
	}

	public static getNavStage(): boolean {
		return this.get('navState') || false;
	}

	public static setNavStage(navState: boolean): void {
		this.set('navState', navState);
	}
}
