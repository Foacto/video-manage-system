import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VideoGalleryComponent } from './video-gallery/video-gallery.component';
import { VideoUploadComponent } from './video-upload/video-upload.component';
import { VideoPlayerComponent } from './video-player/video-player.component';

const routes: Routes = [
  {
    path: 'gallery',
    component: VideoGalleryComponent,
    data: {
      breadcrumb: {
        label: 'Gallery',
      },
    },
  },
  {
    path: 'upload',
    component: VideoUploadComponent,
    data: {
      breadcrumb: {
        label: 'Upload',
      },
    },
  },
  {
    path: 'player',
    component: VideoPlayerComponent,
    data: {
      breadcrumb: {
        label: 'Player',
      },
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VideoRoutingModule {}
