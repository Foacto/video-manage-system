import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { NavComponent } from './nav/nav.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
	declarations: [HeaderComponent, NavComponent, FooterComponent],
	imports: [CommonModule, RouterModule, NgbModule, FontAwesomeModule],
	exports: [HeaderComponent, NavComponent, FooterComponent],
})
export class LayoutModule {}
