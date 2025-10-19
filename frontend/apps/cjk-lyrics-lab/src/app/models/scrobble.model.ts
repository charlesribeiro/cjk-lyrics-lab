export interface Scrobble {
  id: string;
  artist: string;
  track: string;
  album?: string;
  timestamp: Date;
  processed: boolean;
}

export interface LastFmTrack {
  artist: {
    '#text': string;
    mbid?: string;
  };
  name: string;
  album?: {
    '#text': string;
    mbid?: string;
  };
  date?: {
    uts: string;
    '#text': string;
  };
}
