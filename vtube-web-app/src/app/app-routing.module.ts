import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/auth/login/login.component';
import { AuthGuard } from './core/guards/auth.guard';
import { CONTENT_ROUTES } from './core/common/content-layout.route';
import { ContentLayoutComponent } from './modules/common/content-layout/content-layout.component';
import { HomeComponent } from './modules/common/home/home.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard],
    runGuardsAndResolvers: 'always',
    data: {
      breadcrumb: {
        label: 'Home',
      },
    },
  },
  {
    path: '',
    component: ContentLayoutComponent,
    children: CONTENT_ROUTES,
    canActivate: [AuthGuard],
    runGuardsAndResolvers: 'always',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
