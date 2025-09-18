# Imagen base de JDK 17 ligera
FROM eclipse-temurin:17-jdk-alpine

# Crear usuario sin privilegios
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Crear directorio de trabajo dentro del contenedor
WORKDIR /requests

# Copiar el jar generado por Gradle
COPY build/libs/*.jar requests.jar

# Puerto del servicio
EXPOSE 8082

# Opciones JVM 
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

# Permisos y usuario
RUN chown appuser:appgroup requests.jar
USER appuser

# Ejecutar el microservicio
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar requests.jar"]