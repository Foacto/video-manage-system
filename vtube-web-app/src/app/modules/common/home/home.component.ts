import { Component } from '@angular/core';
import { VideoService } from '../../../core/services/video/video.service';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrl: './home.component.css',
})
export class HomeComponent {
	constructor(private videoService: VideoService) {
		this.videoService.findAll().subscribe(
			(res: any) => {
				// Handle the response data here
				console.log('Videos fetched successfully:', res.data);
			},
			(error) => {
				// Handle the error here
				console.error('Error fetching videos:', error);
			}
		);
	}
}
