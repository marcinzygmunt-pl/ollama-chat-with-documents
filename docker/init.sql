CREATE EXTENSION IF NOT EXISTS vector WITH SCHEMA public;
COMMENT ON EXTENSION vector IS 'vector data type and ivfflat and hnsw access methods';

CREATE TABLE public.embeddings (
    embedding_id integer NOT NULL,
    embedding public.vector,
    text text,
    metadata text,
    created_at timestamp with time zone DEFAULT now()
);

ALTER TABLE public.embeddings OWNER TO testuser;

CREATE SEQUENCE public.embeddings_embedding_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.embeddings_embedding_id_seq OWNER TO testuser;
ALTER SEQUENCE public.embeddings_embedding_id_seq OWNED BY public.embeddings.embedding_id;

ALTER TABLE ONLY public.embeddings ALTER COLUMN embedding_id SET DEFAULT nextval('public.embeddings_embedding_id_seq'::regclass);

ALTER TABLE ONLY public.embeddings
    ADD CONSTRAINT embeddings_pkey PRIMARY KEY (embedding_id);
