import { Component, EventEmitter, Output } from '@angular/core';
import { HrStorage } from '../../../../core/services/common/hr-storage.service';
import { Router } from '@angular/router';
import { KeycloakService } from '../../../../core/services/keycloak/keycloak.service';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrl: './header.component.css',
})
export class HeaderComponent {
	public isMinium: boolean;

	@Output()
	public navViewChange: EventEmitter<boolean> = new EventEmitter<boolean>();

	constructor(private router: Router, private keycloakService: KeycloakService) {
		this.isMinium = HrStorage.getNavStage();
		this.navViewChange.emit(this.isMinium);
	}

	toHomePage() {
		this.router.navigate(['/home']);
	}

	onMinium() {
		this.isMinium = !this.isMinium;
		this.navViewChange.emit(this.isMinium);
		HrStorage.setNavStage(this.isMinium);
	}

	onSearch() {}

	viewProfile() {
		this.keycloakService.accountManagement();
	}

	logout() {
		this.keycloakService.logout();
	}
}
