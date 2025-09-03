### Import and Build Project

```bash
# Clone the repository
git clone <repository-url>
cd fintech-api

# Import Gradle project (refreshes dependencies)
./gradlew build --refresh-dependencies

# Clean and build
./gradlew clean build

# Run the application (local environment with dummy data)
./gradlew bootRun

# Run with specific environment
./gradlew bootRun --args='--spring.profiles.active=dev'
./gradlew bootRun --args='--spring.profiles.active=prod'
```

### WAR File Generation

This project is configured to generate WAR files for deployment to WebLogic Server:

```bash
# Generate WAR file for deployment
./gradlew clean build

# Generate WAR with dependency refresh
./gradlew clean build --refresh-dependencies
```

**Generated WAR Location**: `build/libs/fintech-api-0.0.1-SNAPSHOT.war`

### WebLogic Deployment

Deploy the generated WAR file to WebLogic Server with environment-specific configuration:

```bash
# Deploy WAR to WebLogic with specific profile
# Set system property in WebLogic startup script or deployment descriptor:

# For Development environment
-Dspring.profiles.active=dev

# For SIT environment
-Dspring.profiles.active=sit

# For UAT environment  
-Dspring.profiles.active=uat

# For Production environment
-Dspring.profiles.active=prod
```

*


