import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
	selector: 'app-content-layout',
	templateUrl: './content-layout.component.html',
	styleUrl: './content-layout.component.css',
})
export class ContentLayoutComponent {
	public home: MenuItem;
	public items: MenuItem[];
	public isMinium = false;

	constructor(private router: Router, private activatedRoute: ActivatedRoute) {
		this.home = { icon: 'pi pi-home', url: '/home' };
		this.items = [];

		this.router.events.subscribe((event) => {
			if (event instanceof NavigationEnd) {
				this.items = [];
				this.builtBreadcrumb(activatedRoute);
			}
		});
	}

	private builtBreadcrumb(currentAR: ActivatedRoute): void {
		if (currentAR.snapshot.data['breadcrumb']) {
			const lastLink = this.items.length !== 0 ? this.items[this.items.length - 1].url : '';
			let currentLink = '';
			if (currentAR.routeConfig?.path?.startsWith(':')) {
				currentLink = currentAR.snapshot.params[currentAR.routeConfig?.path];
			} else {
				currentLink = currentAR.routeConfig?.path || '';
			}

			this.items.push({
				label: currentAR.snapshot.data['breadcrumb'].label,
				url: `${lastLink}/${currentLink}`,
			});
		}

		if (currentAR.firstChild != undefined) {
			this.builtBreadcrumb(currentAR.firstChild);
		}
	}

	public navViewChange(isMinium: boolean): void {
		this.isMinium = isMinium;
	}
}
