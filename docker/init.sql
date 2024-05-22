CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS embeddings (
  embedding_id SERIAL PRIMARY KEY,
  embedding vector,
  text text,
  metadata text,
  created_at timestamptz DEFAULT now()
);
