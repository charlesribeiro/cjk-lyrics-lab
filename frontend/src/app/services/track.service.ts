import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Track, TrackAnalysis, AnkiExport } from '../models/track.model';
import { MOCK_TRACKS } from '../data/mock-tracks';

@Injectable({
  providedIn: 'root'
})
export class TrackService {
  private apiUrl = '/api/tracks';

  constructor() { }

  getTracks(): Observable<Track[]> {
    // For now, return mock data
    return of(MOCK_TRACKS);
  }

  getTrack(id: string): Observable<Track | undefined> {
    return of(MOCK_TRACKS.find(track => track.id === id));
  }

  createTrack(track: Omit<Track, 'id' | 'createdAt'>): Observable<Track> {
    const newTrack: Track = {
      ...track,
      id: Math.random().toString(36).substr(2, 9),
      createdAt: new Date()
    };
    return of(newTrack);
  }

  updateTrack(id: string, track: Partial<Track>): Observable<Track | undefined> {
    const existingTrack = MOCK_TRACKS.find(t => t.id === id);
    if (existingTrack) {
      return of({ ...existingTrack, ...track });
    }
    return of(undefined);
  }

  deleteTrack(id: string): Observable<boolean> {
    return of(true);
  }

  getTrackAnalysis(id: string): Observable<TrackAnalysis | undefined> {
    // Mock analysis data
    return of(undefined);
  }

  exportToAnki(id: string): Observable<AnkiExport> {
    const track = MOCK_TRACKS.find(t => t.id === id);
    const ankiExport: AnkiExport = {
      trackId: id,
      cards: []
    };
    return of(ankiExport);
  }

  downloadAnkiCsv(ankiExport: AnkiExport): void {
    const csv = this.convertToAnkiCsv(ankiExport);
    const blob = new Blob([csv], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `anki-export-${ankiExport.trackId}.csv`;
    a.click();
    window.URL.revokeObjectURL(url);
  }

  private convertToAnkiCsv(ankiExport: AnkiExport): string {
    let csv = '';
    ankiExport.cards.forEach(card => {
      const tags = card.tags.join(' ');
      const example = card.example || '';
      csv += `"${card.front}","${card.back}","${tags}","${example}"\n`;
    });
    return csv;
  }
}
