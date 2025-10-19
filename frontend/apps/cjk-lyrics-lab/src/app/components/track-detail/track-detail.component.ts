import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { Track, TrackAnalysis } from '../../models/track.model';
import { TrackService } from '../../services/track.service';

@Component({
    selector: 'app-track-detail',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './track-detail.component.html',
    styleUrl: './track-detail.component.css'
})
export class TrackDetailComponent implements OnInit {
  track?: Track;
  analysis?: TrackAnalysis;
  activeTab: 'lyrics' | 'vocabulary' | 'grammar' | 'export' = 'lyrics';
  isTranslating = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private trackService: TrackService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.trackService.getTrack(id).subscribe(track => {
        this.track = track;
      });
      this.trackService.getTrackAnalysis(id).subscribe(analysis => {
        this.analysis = analysis;
      });
    }
  }

  setActiveTab(tab: 'lyrics' | 'vocabulary' | 'grammar' | 'export'): void {
    this.activeTab = tab;
  }

  exportToAnki(): void {
    if (this.track) {
      this.trackService.exportToAnki(this.track.id).subscribe(ankiExport => {
        this.trackService.downloadAnkiCsv(ankiExport);
      });
    }
  }

  translateTrack(): void {
    if (!this.track || this.isTranslating) return;
    
    this.isTranslating = true;
    this.trackService.translateTrack(this.track.id).subscribe({
      next: (updatedTrack) => {
        this.track = updatedTrack;
        this.isTranslating = false;
      },
      error: (error) => {
        console.error('Translation failed:', error);
        this.isTranslating = false;
        // You could add a toast notification here
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/']);
  }
}
