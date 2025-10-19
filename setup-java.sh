#!/bin/bash

# Setup script for CJK Lyrics Lab
# This script sets up the correct Java version for the project

echo "🚀 Setting up CJK Lyrics Lab development environment..."

# Set Java 17 as the default
export JAVA_HOME=/usr/local/Cellar/openjdk@17/17.0.16/libexec/openjdk.jdk/Contents/Home

echo "✅ Java version: $(java -version 2>&1 | head -1)"
echo "✅ JAVA_HOME: $JAVA_HOME"

echo ""
echo "🎯 You can now run:"
echo "  Backend: cd backend && mvn spring-boot:run"
echo "  Frontend: cd frontend && nx serve cjk-lyrics-lab --port 4201"
echo ""
echo "🌐 Access the application at:"
echo "  Frontend: http://localhost:4201"
echo "  Backend API: http://localhost:8080"
echo "  H2 Console: http://localhost:8080/h2-console"
echo ""
echo "🔑 Don't forget to set your OpenAI API key in the .env file!"
