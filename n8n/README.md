# n8n Workflows for CJK Lyrics Lab

This directory contains n8n workflows for integrating Last.fm scrobbles with the CJK Lyrics Lab application, including automatic lyrics fetching and translation.

## Setup

1. Access n8n at http://localhost:5678
2. Login with credentials:
   - Username: admin (or value from N8N_USER env var)
   - Password: admin (or value from N8N_PASSWORD env var)

3. Import the enhanced workflow from `workflows/enhanced-lastfm-scrobbles.json`

## Enhanced Workflow: Last.fm Scrobbles with Lyrics & Translation

This comprehensive workflow:
1. Receives webhook calls from Last.fm (via IFTTT or similar service)
2. Checks if the track is in a CJK language (Japanese, Korean, Chinese)
3. Attempts to fetch lyrics from multiple sources (Genius, Musixmatch)
4. Creates a new track in the CJK Lyrics Lab backend
5. Automatically triggers OpenAI translation
6. Returns success/failure status

### Features

- **Language Detection**: Only processes CJK language tracks
- **Multiple Lyrics Sources**: Tries Genius API first, then Musixmatch as fallback
- **Automatic Translation**: Triggers OpenAI translation after track creation
- **Error Handling**: Graceful handling of missing lyrics or API failures
- **Response Feedback**: Returns detailed status information

### Configuration

#### Environment Variables

Set up the following environment variables in your n8n instance:

```bash
# Required for lyrics fetching
GENIUS_API_TOKEN=your_genius_api_token_here
MUSIXMATCH_API_KEY=your_musixmatch_api_key_here

# Required for translation (backend)
OPENAI_API_KEY=your_openai_api_key_here
```

#### API Keys Setup

1. **Genius API**: 
   - Sign up at https://genius.com/api-clients
   - Create a new API client
   - Copy the access token

2. **Musixmatch API**:
   - Sign up at https://developer.musixmatch.com/
   - Create a new application
   - Copy the API key

3. **OpenAI API**:
   - Sign up at https://platform.openai.com/
   - Create a new API key
   - Add to your backend environment variables

#### Last.fm Integration

Set up Last.fm integration using IFTTT or similar service:

1. **IFTTT Setup**:
   - Create an IFTTT applet
   - Trigger: Last.fm "New track scrobbled"
   - Action: Webhook
   - URL: `http://localhost:5678/webhook/scrobble`
   - Method: POST
   - Content Type: application/json

2. **Webhook Payload**:
```json
{
  "title": "Song Title",
  "artist": "Artist Name", 
  "album": "Album Name",
  "language": "ja",
  "difficulty": "N3",
  "tags": ["anime", "classic"]
}
```

### Language Codes

- `ja` - Japanese
- `ko` - Korean  
- `zh` - Chinese

### Workflow Steps

1. **Webhook Trigger**: Receives Last.fm scrobble data
2. **Language Check**: Validates if track is CJK language
3. **Lyrics Fetching**: Attempts to get lyrics from multiple APIs
4. **Track Creation**: Creates track in CJK Lyrics Lab database
5. **Auto Translation**: Triggers OpenAI translation
6. **Response**: Returns processing status

### API Endpoints Used

- `POST /api/tracks` - Create new track
- `POST /api/tracks/{id}/translate` - Trigger translation

## Legacy Workflow

The original simple workflow (`workflows/lastfm-scrobbles.json`) is still available for basic functionality without lyrics fetching.

## Troubleshooting

### Common Issues

1. **No Lyrics Found**:
   - Check API keys are correctly set
   - Verify track title/artist spelling
   - Some tracks may not have lyrics available

2. **Translation Not Working**:
   - Ensure OPENAI_API_KEY is set in backend environment
   - Check OpenAI API quota and billing
   - Verify network connectivity

3. **Webhook Not Triggering**:
   - Check IFTTT applet is active
   - Verify webhook URL is correct
   - Ensure n8n is running and accessible

### Logs

Check n8n execution logs for detailed error information:
1. Go to n8n interface
2. Click on "Executions" tab
3. View failed executions for error details

## Adding More Workflows

You can create additional workflows for:
- Scheduled vocabulary analysis
- Bulk Anki exports
- Track difficulty analysis
- User notification systems
- Integration with other music services
