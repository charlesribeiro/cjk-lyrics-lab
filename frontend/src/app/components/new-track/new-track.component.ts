import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Track } from '../../models/track.model';
import { TrackService } from '../../services/track.service';

@Component({
  selector: 'app-new-track',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './new-track.component.html',
  styleUrl: './new-track.component.css'
})
export class NewTrackComponent {
  track = {
    title: '',
    artist: '',
    album: '',
    originalLyrics: '',
    language: 'ja' as 'ja' | 'ko' | 'zh',
    tags: [] as string[]
  };

  constructor(
    private trackService: TrackService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.trackService.createTrack(this.track).subscribe(
      (newTrack: Track) => {
        this.router.navigate(['/tracks', newTrack.id]);
      }
    );
  }

  onCancel(): void {
    this.router.navigate(['/']);
  }
}
