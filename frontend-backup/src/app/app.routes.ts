import { Routes } from '@angular/router';
import { TrackListComponent } from './components/track-list/track-list.component';
import { NewTrackComponent } from './components/new-track/new-track.component';
import { TrackDetailComponent } from './components/track-detail/track-detail.component';

export const routes: Routes = [
  { path: '', component: TrackListComponent },
  { path: 'new/track', component: NewTrackComponent },
  { path: 'tracks/:id', component: TrackDetailComponent },
  { path: '**', redirectTo: '' }
];
