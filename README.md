# CJK Lyrics Lab 🎵

Scrobble-to-Study: auto-fetch lyrics, tag JLPT/TOPIK, and export Anki—powered by Angular, Spring Boot, Tailwind, and n8n.

An AI-assisted lyrics learning application for studying Chinese (中文), Japanese (日本語), and Korean (한국어) through music. Automatically fetch lyrics from your Last.fm scrobbles, get AI-powered translations, analyze vocabulary by proficiency level (JLPT/TOPIK/HSK), and export to Anki for spaced repetition learning.

## Features

- 🎵 **Track Management**: Add and manage lyrics for CJK (Chinese, Japanese, Korean) songs
- 🔄 **Last.fm Integration**: Automatic scrobble tracking via n8n workflows
- 🤖 **AI Translation**: OpenAI-powered lyrics translation
- 📊 **Analysis Tabs**: Vocabulary, grammar, and difficulty analysis
- 📝 **Anki Export**: Export vocabulary and phrases as CSV for Anki import
- 🎨 **Modern UI**: Clean, responsive interface built with Angular 18 + Tailwind CSS
- 🐳 **Docker Support**: Easy deployment with Docker Compose

## Tech Stack

### Frontend
- **Angular 18**: Modern web framework with standalone components
- **Tailwind CSS**: Utility-first CSS framework
- **TypeScript**: Type-safe development

### Backend
- **Spring Boot 3**: Java-based REST API
- **H2 Database**: In-memory database for development
- **JPA/Hibernate**: ORM for data persistence
- **Lombok**: Reduce boilerplate code

### Infrastructure
- **Docker & Docker Compose**: Containerization
- **n8n**: Workflow automation for Last.fm integration
- **OpenAI API**: AI-powered translation

## Prerequisites

- **Node.js**: v20 or higher
- **Java**: 17 or higher
- **Maven**: 3.9 or higher
- **Docker & Docker Compose**: For containerized deployment

## Quick Start

### Using Docker Compose (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/charlesribeiro/cjk-lyrics-lab.git
   cd cjk-lyrics-lab
   ```

2. **Configure environment variables**
   ```bash
   cp .env.example .env
   # Edit .env and add your OpenAI API key
   ```

3. **Start all services**
   ```bash
   docker-compose up -d
   ```

4. **Access the application**
   - Frontend: http://localhost:4200
   - Backend API: http://localhost:8080
   - n8n Workflows: http://localhost:5678 (admin/admin)

### Development Setup

#### Frontend Development

```bash
cd frontend
npm install
npm start
```

The frontend will be available at http://localhost:4200

#### Backend Development

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend API will be available at http://localhost:8080

#### n8n Development

```bash
docker run -it --rm \
  --name n8n \
  -p 5678:5678 \
  -v ~/.n8n:/home/node/.n8n \
  n8nio/n8n
```

Access n8n at http://localhost:5678

## Project Structure

```
cjk-lyrics-lab/
├── frontend/                 # Angular 18 frontend
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/  # UI components
│   │   │   │   ├── new-track/       # Track creation form
│   │   │   │   ├── track-detail/    # Track view with analysis tabs
│   │   │   │   └── track-list/      # Track listing
│   │   │   ├── models/      # TypeScript interfaces
│   │   │   ├── services/    # API services
│   │   │   └── data/        # Mock data
│   │   └── styles.css       # Global styles with Tailwind
│   ├── Dockerfile
│   └── nginx.conf
│
├── backend/                  # Spring Boot 3 backend
│   ├── src/
│   │   └── main/
│   │       ├── java/com/cjk/lyrics/lab/
│   │       │   ├── controller/      # REST controllers
│   │       │   ├── service/         # Business logic
│   │       │   ├── repository/      # Data access
│   │       │   ├── model/           # JPA entities
│   │       │   └── dto/             # Data transfer objects
│   │       └── resources/
│   │           └── application.properties
│   ├── Dockerfile
│   └── pom.xml
│
├── n8n/                      # n8n workflow configurations
│   ├── workflows/
│   │   └── lastfm-scrobbles.json
│   └── README.md
│
├── docker/                   # Additional Docker configs
├── .github/
│   └── workflows/
│       └── ci.yml           # GitHub Actions CI/CD
├── docker-compose.yml
├── .env.example
└── README.md
```

## API Endpoints

### Tracks
- `GET /api/tracks` - Get all tracks
- `GET /api/tracks/{id}` - Get track by ID
- `POST /api/tracks` - Create new track
- `PUT /api/tracks/{id}` - Update track
- `DELETE /api/tracks/{id}` - Delete track
- `GET /api/tracks/{id}/anki-export` - Export track to Anki CSV

## Routes

### Frontend Routes
- `/` - Track list (home page)
- `/new/track` - Add new track (confirm lyrics)
- `/tracks/:id` - Track detail with analysis tabs (lyrics, vocabulary, grammar, export)

## Configuration

### OpenAI Integration

To enable AI translation, set your OpenAI API key:

```bash
export OPENAI_API_KEY=sk-your-key-here
```

Or add it to your `.env` file.

### Last.fm Scrobbles via n8n

1. Access n8n at http://localhost:5678
2. Import the workflow from `n8n/workflows/lastfm-scrobbles.json`
3. Configure IFTTT or similar service to send Last.fm scrobbles to:
   `http://localhost:5678/webhook/scrobble`

See [n8n/README.md](n8n/README.md) for detailed setup instructions.

## Building for Production

### Build Frontend
```bash
cd frontend
npm run build
```

### Build Backend
```bash
cd backend
mvn clean package
```

### Build Docker Images
```bash
docker-compose build
```

## Testing

### Frontend Tests
```bash
cd frontend
npm test
```

### Backend Tests
```bash
cd backend
mvn test
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.

## Support

For issues, questions, or contributions, please open an issue on GitHub.

## Roadmap

- [ ] Implement actual OpenAI translation API integration
- [ ] Add lyrics fetching from various lyrics APIs
- [ ] Implement vocabulary analysis with JLPT/TOPIK/HSK tagging
- [ ] Add grammar pattern detection
- [ ] Create study mode with spaced repetition
- [ ] Add user authentication and personalization
- [ ] Support for more languages
- [ ] Mobile app (React Native or Flutter)

---

Made with ❤️ for language learners who love music 🎵
