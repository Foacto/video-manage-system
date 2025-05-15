import { Component } from '@angular/core';
import { VideoService } from '../../../core/services/video/video.service';
import { VideoMetadata } from '../../../core/data/data';
import { environment } from '../../../shared/environments/environment';

@Component({
  selector: 'app-video-gallery',
  templateUrl: './video-gallery.component.html',
  styleUrl: './video-gallery.component.scss',
})
export class VideoGalleryComponent {
  serverAPI = environment.serverApi;
  previews: VideoMetadata[] = [];
  isError: boolean = false;

  constructor(private videoService: VideoService) {
    this.videoService.findAll().subscribe(
      (res: any) => {
        this.previews = res.data;
      },
      (error) => {
        this.isError = true;
      }
    );
  }
}
