import { Routes } from '@angular/router';

export const CONTENT_ROUTES: Routes = [
  {
    path: 'video',
    loadChildren: () =>
      import('../../modules/video/video.module').then((m) => m.VideoModule),
    data: {
      breadcrumb: {
        label: 'Video',
      },
    },
  },
];
