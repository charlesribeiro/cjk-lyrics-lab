import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Track, TrackAnalysis, AnkiExport } from '../models/track.model';
import { MOCK_TRACKS } from '../data/mock-tracks';

@Injectable({
  providedIn: 'root'
})
export class TrackService {
  private apiUrl = 'http://localhost:8080/api/tracks';

  constructor(private http: HttpClient) { }

  getTracks(): Observable<Track[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      map(tracks => tracks.map(track => this.convertFromBackend(track)))
    );
  }

  getTrack(id: string): Observable<Track | undefined> {
    return this.http.get<any>(`${this.apiUrl}/${id}`).pipe(
      map(track => track ? this.convertFromBackend(track) : undefined)
    );
  }

  createTrack(track: Omit<Track, 'id' | 'createdAt'>): Observable<Track> {
    const backendTrack = this.convertToBackend(track);
    return this.http.post<any>(this.apiUrl, backendTrack).pipe(
      map(track => this.convertFromBackend(track))
    );
  }

  updateTrack(id: string, track: Partial<Track>): Observable<Track | undefined> {
    const backendTrack = this.convertToBackend(track);
    return this.http.put<any>(`${this.apiUrl}/${id}`, backendTrack).pipe(
      map(track => track ? this.convertFromBackend(track) : undefined)
    );
  }

  deleteTrack(id: string): Observable<boolean> {
    return this.http.delete(`${this.apiUrl}/${id}`).pipe(
      map(() => true)
    );
  }

  translateTrack(id: string): Observable<Track> {
    return this.http.post<any>(`${this.apiUrl}/${id}/translate`, {}).pipe(
      map(track => this.convertFromBackend(track))
    );
  }

  getTrackAnalysis(id: string): Observable<TrackAnalysis | undefined> {
    // Mock analysis data
    return of(undefined);
  }

  exportToAnki(id: string): Observable<AnkiExport> {
    return this.http.get<string>(`${this.apiUrl}/${id}/anki-export`).pipe(
      map(csv => {
        // Parse CSV and convert to AnkiExport format
        const ankiExport: AnkiExport = {
          trackId: id,
          cards: []
        };
        return ankiExport;
      })
    );
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

  private convertFromBackend(backendTrack: any): Track {
    return {
      ...backendTrack,
      language: backendTrack.language?.toLowerCase() as 'ja' | 'ko' | 'zh',
      createdAt: new Date(backendTrack.createdAt),
      lastStudied: backendTrack.lastStudied ? new Date(backendTrack.lastStudied) : undefined
    };
  }

  private convertToBackend(frontendTrack: any): any {
    return {
      ...frontendTrack,
      language: frontendTrack.language?.toUpperCase()
    };
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
