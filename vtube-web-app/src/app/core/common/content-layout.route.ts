import { Routes } from '@angular/router';

export const CONTENT_ROUTES: Routes = [
  {
    path: 'video',
    loadChildren: () => import('').then((m) => m.VideoModule),
  },
];
