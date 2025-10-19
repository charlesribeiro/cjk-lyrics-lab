# n8n Workflow for Last.fm Scrobbles

This directory contains n8n workflows for integrating Last.fm scrobbles with the CJK Lyrics Lab application.

## Setup

1. Access n8n at http://localhost:5678
2. Login with credentials:
   - Username: admin (or value from N8N_USER env var)
   - Password: admin (or value from N8N_PASSWORD env var)

3. Import the workflow from `workflows/lastfm-scrobbles.json`

## Workflow: Last.fm Scrobbles

This workflow:
1. Receives webhook calls from Last.fm (via IFTTT or similar service)
2. Processes the scrobble data
3. Creates a new track in the CJK Lyrics Lab backend

### Configuration

Set up Last.fm integration:
1. Use a service like IFTTT to monitor Last.fm scrobbles
2. Configure the webhook URL: `http://localhost:5678/webhook/scrobble`
3. Send POST requests with the following JSON structure:

```json
{
  "title": "Song Title",
  "artist": "Artist Name",
  "album": "Album Name",
  "language": "ja",
  "originalLyrics": "Lyrics here..."
}
```

## Adding More Workflows

You can create additional workflows for:
- Automatic lyrics fetching from lyrics APIs
- Translation using OpenAI API
- Vocabulary extraction and JLPT/TOPIK/HSK tagging
- Scheduled exports to Anki
