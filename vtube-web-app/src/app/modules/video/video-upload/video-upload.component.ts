import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { VideoService } from '../../../core/services/video/video.service';

@Component({
  selector: 'app-video-upload',
  templateUrl: './video-upload.component.html',
  styleUrl: './video-upload.component.scss',
})
export class VideoUploadComponent {
  isError: boolean = false;
  form: FormGroup;

  constructor(
    private videoService: VideoService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.form = this.fb.group({
      title: [null, Validators.required],
      description: [null],
      file: [null, Validators.required],
    });
  }

  submit() {
    if (this.form.invalid) {
      this.isError = true;
      return;
    }

    console.log(this.form.value);
    console.log();
    this.videoService.upload(this.form.value).subscribe(
      (res: any) => {
        if (res.data) {
          this.isError = false;
          this.router.navigate(['/video/player/' + res.data.id]);
        } else {
          this.isError = true;
        }
      },
      (err) => {
        this.isError = true;
        console.error(err);
      }
    );
  }

  changeFile(event: any) {
    this.form.get('file')!.setValue(event.target.files[0]);
  }
}
