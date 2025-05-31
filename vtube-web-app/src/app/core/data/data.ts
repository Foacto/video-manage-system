export class VideoMetadata {
  constructor(
    public id: number,
    public title: string,
    public description: string,
    public contentType: string,
    public previewUrl: string,
    public videoUrl: string
  ) {}
}
