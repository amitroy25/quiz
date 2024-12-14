
# first configure H2 database then follow these steps
# Quiz API

This project is a Spring Boot-based quiz application that allows users to:
1. Start a new quiz session.
2. Retrieve a random multiple-choice question.
3. Submit an answer for the question.
4. Retrieve statistics of the quiz session.

## Assumptions
- The application uses H2 in-memory database for simplicity.
- A default set of questions is seeded when the application starts.
- A single user (`userId = 1`) is assumed for simplicity.
- Each quiz session is independent of the others.
- Correct answers are case-insensitive.

## API Endpoints
### 1. Start New Quiz Session
**POST** `/api/quiz/start?userId={userId}`  
Starts a new quiz session for the given `userId`.

### 2. Get Random Question
**GET** `/api/quiz/question`  
Fetches a random question from the database.

### 3. Submit Answer
**POST** `/api/quiz/submit?sessionId={sessionId}&questionId={questionId}&userAnswer={userAnswer}`  
Submits the user's answer for a specific question in the session.

### 4. Get Quiz Session Statistics
**GET** `/api/quiz/stats?sessionId={sessionId}`  
Retrieves the session's total questions answered, and counts of correct and incorrect answers.

## Setup Instructions
1. Clone the repository and navigate to the project directory.
2. Run the application using:
   ```bash
   ./mvnw spring-boot:run
