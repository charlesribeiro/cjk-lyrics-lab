import { Track } from '../models/track.model';

export const MOCK_TRACKS: Track[] = [
  {
    id: '1',
    title: '残酷な天使のテーゼ',
    artist: '高橋洋子',
    album: '新世紀エヴァンゲリオン',
    originalLyrics: `残酷な天使のように
少年よ神話になれ

蒼い風がいま
胸のドアを叩いても
私だけをただ見つめて
微笑んでるあなた`,
    translatedLyrics: `Like a cruel angel
Young boy, become a legend

Even if the blue wind
Knocks on the door of your heart
You just keep staring at me
Smiling`,
    language: 'ja',
    difficulty: 'N3',
    tags: ['anime', 'JLPT-N3', 'classic'],
    createdAt: new Date('2024-01-15'),
    lastStudied: new Date('2024-01-20')
  },
  {
    id: '2',
    title: '강남스타일',
    artist: 'PSY',
    album: 'PSY 6甲 Part 1',
    originalLyrics: `오빤 강남스타일
강남스타일

낮에는 따사로운 인간적인 여자
커피 한잔의 여유를 아는 품격 있는 여자`,
    translatedLyrics: `Oppa is Gangnam style
Gangnam style

A girl who is warm and humane during the day
A classy girl who knows how to enjoy a cup of coffee`,
    language: 'ko',
    difficulty: 'TOPIK-2',
    tags: ['kpop', 'TOPIK-2', 'viral'],
    createdAt: new Date('2024-02-01'),
    lastStudied: new Date('2024-02-10')
  },
  {
    id: '3',
    title: '月亮代表我的心',
    artist: '鄧麗君',
    album: '島國之情歌第六集',
    originalLyrics: `你問我愛你有多深
我愛你有幾分
我的情也真
我的愛也真
月亮代表我的心`,
    translatedLyrics: `You ask me how deep my love for you is
How much I love you
My feelings are true
My love is true
The moon represents my heart`,
    language: 'zh',
    difficulty: 'HSK-3',
    tags: ['classic', 'HSK-3', 'mandarin'],
    createdAt: new Date('2024-02-15'),
    lastStudied: new Date('2024-02-20')
  }
];
