<h1 align="center" id="title">Chat with your documents using Ollama</h1>

<p id="description">Sample AI Assistant to chat with your documents. Documents could be stored in inMemory or pgVector repository.</p>



<h2>🧐 Features</h2>

Here're some of the project's best features:

*   Ollama
*   Rest API
*   Memory Repository
*   PgVector Repository

<h2>🛠️ Installation Steps:</h2>

<p>1. Start Ollama Server</p>
<p>Default language model used in project is llama3. You can change it in docker-compose command section in ollama-server service *</p>

```
docker-compose up -d ollama-server
```

<p>2. Start PgVector Database</p>

```
docker-compose up -d db
```

<p>3. Run application form your favorite IDE</p>
<p>* dont forget to update language model in application.yml if you change it in step 1</p>


<h2>💻 Built with</h2>

Technologies used in the project:

*   Ollama server
*   Docker
*   SpringBoot 3
*   LangChain4j
*   PostgreSql
*   PgVector
*   Java

<h2>📗 How to use it ? </h2>

check [Swagger](http://localhost:8088/swagger-ui/index.html#/) - `http://host:port/swagger-ui/index.html#/`

or you can use curl commands - some exmaples below

### Send file to application repository 
```
curl --location 'http://localhost:8088/api/upload' \
--form 'file=@"/src/main/resources/Forging.txt"'
```

### Ask AI about document content
```
curl --location 'http://localhost:8088/api/chat' \
--header 'Content-Type: application/json' \
--data '{
    "request":"What is Forged Metal Used for?"
}'
```