#!/bin/bash
# Script para arrancar CodeLingo forzando Java 17 (necesario para el plugin)
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
echo "🕹️ Arrancando CodeLingo con Java 17..."
./gradlew runIde
