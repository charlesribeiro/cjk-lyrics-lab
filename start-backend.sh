#!/bin/bash

# Start CJK Lyrics Lab Backend with OpenAI API Key
echo "🚀 Starting CJK Lyrics Lab Backend..."

# Set Java environment
export JAVA_HOME=/usr/local/Cellar/openjdk@17/17.0.16/libexec/openjdk.jdk/Contents/Home

# Set OpenAI API Key from .env file
export OPENAI_API_KEY=$(grep "OPENAI_API_KEY" .env | cut -d '=' -f2)

echo "✅ Java version: $(java -version 2>&1 | head -1)"
echo "✅ OpenAI API Key: ${OPENAI_API_KEY:0:20}..."

# Start the backend
cd backend
mvn spring-boot:run
