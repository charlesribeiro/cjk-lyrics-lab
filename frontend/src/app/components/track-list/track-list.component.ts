import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Track } from '../../models/track.model';
import { TrackService } from '../../services/track.service';

@Component({
  selector: 'app-track-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './track-list.component.html',
  styleUrl: './track-list.component.css'
})
export class TrackListComponent implements OnInit {
  tracks: Track[] = [];

  constructor(
    private trackService: TrackService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.trackService.getTracks().subscribe(tracks => {
      this.tracks = tracks;
    });
  }

  viewTrack(id: string): void {
    this.router.navigate(['/tracks', id]);
  }

  addNewTrack(): void {
    this.router.navigate(['/new/track']);
  }
}
