import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VideoRoutingModule } from './video-routing.module';
import { VideoGalleryComponent } from './video-gallery/video-gallery.component';
import { VideoPlayerComponent } from './video-player/video-player.component';
import { VideoUploadComponent } from './video-upload/video-upload.component';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [
    VideoGalleryComponent,
    VideoUploadComponent,
    VideoPlayerComponent,
  ],
  imports: [CommonModule, SharedModule, VideoRoutingModule],
})
export class VideoModule {}
