export interface Track {
  id: string;
  title: string;
  artist: string;
  album?: string;
  originalLyrics: string;
  translatedLyrics?: string;
  language: 'ja' | 'ko' | 'zh';
  difficulty?: string;
  tags?: string[];
  createdAt: Date;
  lastStudied?: Date;
}

export interface LyricsLine {
  original: string;
  translation?: string;
  annotations?: Annotation[];
}

export interface Annotation {
  word: string;
  reading?: string;
  translation: string;
  jlptLevel?: string;
  topikLevel?: string;
  hskLevel?: string;
}

export interface TrackAnalysis {
  trackId: string;
  vocabulary: VocabularyItem[];
  grammar: GrammarPoint[];
  difficulty: DifficultyAnalysis;
}

export interface VocabularyItem {
  word: string;
  reading?: string;
  translation: string;
  level?: string;
  frequency: number;
}

export interface GrammarPoint {
  pattern: string;
  explanation: string;
  examples: string[];
}

export interface DifficultyAnalysis {
  overallLevel: string;
  vocabularyLevel: string;
  grammarLevel: string;
  estimatedStudyTime: number;
}

export interface AnkiExport {
  trackId: string;
  cards: AnkiCard[];
}

export interface AnkiCard {
  front: string;
  back: string;
  tags: string[];
  example?: string;
}
