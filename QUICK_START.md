# Quick Start Guide

## 🚀 Running the Application

### Option 1: Docker Compose (Recommended)

```bash
# Clone the repository
git clone https://github.com/charlesribeiro/cjk-lyrics-lab.git
cd cjk-lyrics-lab

# Copy environment variables
cp .env.example .env

# Start all services
docker-compose up -d

# View logs
docker-compose logs -f
```

**Access the application:**
- Frontend: http://localhost:4200
- Backend API: http://localhost:8080
- n8n: http://localhost:5678 (admin/admin)
- H2 Console: http://localhost:8080/h2-console

### Option 2: Local Development

#### Frontend Only

```bash
cd frontend
npm install
npm start
```

Visit http://localhost:4200

#### Backend Only

```bash
cd backend
mvn spring-boot:run
```

API available at http://localhost:8080

#### Both Services

```bash
# Terminal 1 - Backend
cd backend
mvn spring-boot:run

# Terminal 2 - Frontend
cd frontend
npm start
```

## 🎯 Using the Application

### 1. View Existing Tracks
- Open http://localhost:4200
- See 3 sample tracks (Japanese, Korean, Chinese)
- Click any track to view details

### 2. Add New Track
- Click "+ Add New Track" button
- Fill in track information:
  - Title
  - Artist
  - Album (optional)
  - Language (Japanese/Korean/Chinese)
  - Original Lyrics
- Click "Confirm & Analyze"

### 3. View Track Analysis
- Click on a track from the list
- Navigate through tabs:
  - **Lyrics**: View original and translated lyrics
  - **Vocabulary**: (Coming soon) Word analysis with JLPT/TOPIK/HSK levels
  - **Grammar**: (Coming soon) Grammar pattern analysis
  - **Export to Anki**: Download CSV for Anki import

### 4. Export to Anki
- Go to a track detail page
- Click "Export to Anki" tab
- Click "Download Anki CSV"
- Import the CSV file into Anki

## 🔧 Configuration

### OpenAI API (Optional)

To enable AI translation:

```bash
# In .env file
OPENAI_API_KEY=sk-your-api-key-here
```

### n8n Workflows

1. Access n8n at http://localhost:5678
2. Login with admin/admin
3. Import workflow from `n8n/workflows/lastfm-scrobbles.json`
4. Configure Last.fm integration via IFTTT or similar service

## 🧪 Testing

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

### Build for Production
```bash
# Frontend
cd frontend
npm run build

# Backend
cd backend
mvn clean package
```

## 📚 Sample Data

The application includes 3 mock tracks:

1. **残酷な天使のテーゼ** (Cruel Angel's Thesis)
   - Language: Japanese
   - Level: N3
   - Artist: 高橋洋子

2. **강남스타일** (Gangnam Style)
   - Language: Korean
   - Level: TOPIK-2
   - Artist: PSY

3. **月亮代表我的心** (The Moon Represents My Heart)
   - Language: Chinese
   - Level: HSK-3
   - Artist: 鄧麗君

## 🛠️ Troubleshooting

### Port Already in Use
```bash
# Stop existing services
docker-compose down

# Or change ports in docker-compose.yml
```

### Frontend Not Building
```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
```

### Backend Not Starting
```bash
cd backend
mvn clean install
```

### Database Issues
H2 is in-memory, so data resets on restart. For persistence, configure a different database in `application.properties`.

## 📖 Additional Resources

- [Full README](README.md)
- [n8n Setup Guide](n8n/README.md)
- [API Documentation](README.md#api-endpoints)

## 🤝 Support

For issues or questions:
- Open an issue on GitHub
- Check the main README for detailed documentation
