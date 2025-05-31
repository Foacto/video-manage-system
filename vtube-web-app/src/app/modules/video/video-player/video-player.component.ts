import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { VideoMetadata } from '../../../core/data/data';
import { VideoService } from '../../../core/services/video/video.service';
import { ActivatedRoute } from '@angular/router';
import { LocalStorageService } from '../../../core/services/common/local-storage.service';
import { environment } from '../../../shared/environments/environment';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrl: './video-player.component.scss',
})
export class VideoPlayerComponent implements OnInit {
  serverAPI = environment.serverApi + '/api/v1';
  public videoMetadata: VideoMetadata = new VideoMetadata(
    0,
    '',
    '',
    '',
    '',
    ''
  );

  @ViewChild('videoPlayer') videoPlayerRef!: ElementRef;

  constructor(
    private videoService: VideoService,
    private route: ActivatedRoute,
    private localStorageService: LocalStorageService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params: any) => {
      if (params.id) {
        this.videoService.findOne(params.id).subscribe((res: any) => {
          this.videoMetadata = res.data;

          if (this.videoPlayerRef && this.videoPlayerRef) {
            const videoPlayer: HTMLVideoElement =
              this.videoPlayerRef.nativeElement;
            videoPlayer.load();

            let curTime = this.localStorageService.getItem(
              params.id + '_curTime'
            );
            if (curTime) {
              videoPlayer.currentTime = parseFloat(curTime);
            }

            videoPlayer.play();

            videoPlayer.ontimeupdate = () => {
              this.localStorageService.setItem(
                params.id + '_curTime',
                videoPlayer.currentTime.toString()
              );
            };
          }
        });
      }
    });
  }
}
