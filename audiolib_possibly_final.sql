PGDMP  :    0            
    |            AudioLib     16.6 (Ubuntu 16.6-1.pgdg22.04+1)    16.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    98325    AudioLib    DATABASE     r   CREATE DATABASE "AudioLib" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C.UTF-8';
    DROP DATABASE "AudioLib";
                postgres    false            |           1247    98363    carriers    TYPE     N   CREATE TYPE public.carriers AS ENUM (
    'digital',
    'cd',
    'vinyl'
);
    DROP TYPE public.carriers;
       public          postgres    false                       1255    163875    carrier_rented()    FUNCTION     d  CREATE FUNCTION public.carrier_rented() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
	UPDATE audio_carriers
	SET amt_available = amt_available - 1
	WHERE id = NEW.audio_carrier_id AND carrier <> 'digital';
	INSERT INTO history
		VALUES(NEW.user_id,
		(SELECT cav.audio_id 
		FROM carrier_audio_view cav
		WHERE cav.id = NEW.audio_carrier_id), 1)
	ON CONFLICT(user_id, audio_id)
		DO UPDATE SET
		amt_listened = EXCLUDED.amt_listened + 1;
	UPDATE users
		SET balance = balance - (SELECT ac.cost
		FROM audio_carriers ac
		WHERE ac.id = NEW.audio_carrier_id)
		WHERE id = NEW.user_id;
	RETURN NULL;
END;$$;
 '   DROP FUNCTION public.carrier_rented();
       public          postgres    false            �            1255    163946    get_rents_by_date(date)    FUNCTION     4  CREATE FUNCTION public.get_rents_by_date(_date date) RETURNS TABLE(audio_carrier_id bigint, quantity integer, date date)
    LANGUAGE sql
    AS $$
SELECT t.audio_carrier_id, COUNT(*) as quantity, _date
FROM transact t
WHERE _date between t.date_rented and t.date_end_of_rent
GROUP BY t.audio_carrier_id
$$;
 4   DROP FUNCTION public.get_rents_by_date(_date date);
       public          postgres    false            �            1259    122905    aud_car_inventory    TABLE     �   CREATE TABLE public.aud_car_inventory (
    id bigint NOT NULL,
    carrier_id bigint NOT NULL,
    quantity integer NOT NULL,
    date date NOT NULL
);
 %   DROP TABLE public.aud_car_inventory;
       public         heap    postgres    false            �            1259    98370    audio    TABLE     �   CREATE TABLE public.audio (
    id bigint NOT NULL,
    name character varying(64) NOT NULL,
    year integer NOT NULL,
    duration interval NOT NULL,
    date_added date DEFAULT CURRENT_DATE NOT NULL
);
    DROP TABLE public.audio;
       public         heap    postgres    false            �            1259    98378    audio_carriers    TABLE     �   CREATE TABLE public.audio_carriers (
    id bigint NOT NULL,
    audio_id bigint NOT NULL,
    carrier public.carriers NOT NULL,
    amt_available integer,
    cost money DEFAULT 0.0 NOT NULL
);
 "   DROP TABLE public.audio_carriers;
       public         heap    postgres    false    892            �            1259    155676    carrier_audio_view    VIEW     �   CREATE VIEW public.carrier_audio_view AS
 SELECT ac.id,
    a.id AS audio_id,
    a.name,
    ac.carrier,
    ac.amt_available
   FROM (public.audio_carriers ac
     RIGHT JOIN public.audio a ON ((ac.audio_id = a.id)))
  ORDER BY ac.id;
 %   DROP VIEW public.carrier_audio_view;
       public          postgres    false    227    224    224    227    227    227    892            �            1259    163902    aci_name    VIEW     �   CREATE VIEW public.aci_name AS
 SELECT id,
    carrier_id,
    ( SELECT cav.name
           FROM public.carrier_audio_view cav
          WHERE (cav.id = aci.carrier_id)) AS name
   FROM public.aud_car_inventory aci;
    DROP VIEW public.aci_name;
       public          postgres    false    246    246    240    240            �            1259    98345    artists    TABLE     b   CREATE TABLE public.artists (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);
    DROP TABLE public.artists;
       public         heap    postgres    false            �            1259    98344    artists_id_seq    SEQUENCE     �   CREATE SEQUENCE public.artists_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.artists_id_seq;
       public          postgres    false    220            �           0    0    artists_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.artists_id_seq OWNED BY public.artists.id;
          public          postgres    false    219            �            1259    122904    aud_car_inventory_id_seq    SEQUENCE     �   CREATE SEQUENCE public.aud_car_inventory_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.aud_car_inventory_id_seq;
       public          postgres    false    240            �           0    0    aud_car_inventory_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.aud_car_inventory_id_seq OWNED BY public.aud_car_inventory.id;
          public          postgres    false    239            �            1259    122917    aud_car_sales    TABLE     �   CREATE TABLE public.aud_car_sales (
    id bigint NOT NULL,
    carrier_id bigint NOT NULL,
    quantity integer NOT NULL,
    date date NOT NULL
);
 !   DROP TABLE public.aud_car_sales;
       public         heap    postgres    false            �            1259    122916    aud_car_sales_id_seq    SEQUENCE     }   CREATE SEQUENCE public.aud_car_sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.aud_car_sales_id_seq;
       public          postgres    false    242            �           0    0    aud_car_sales_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.aud_car_sales_id_seq OWNED BY public.aud_car_sales.id;
          public          postgres    false    241            �            1259    98377    audio_carriers_audio_id_seq    SEQUENCE     �   CREATE SEQUENCE public.audio_carriers_audio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.audio_carriers_audio_id_seq;
       public          postgres    false    227            �           0    0    audio_carriers_audio_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.audio_carriers_audio_id_seq OWNED BY public.audio_carriers.audio_id;
          public          postgres    false    226            �            1259    98376    audio_carriers_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.audio_carriers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.audio_carriers_id_seq;
       public          postgres    false    227            �           0    0    audio_carriers_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.audio_carriers_id_seq OWNED BY public.audio_carriers.id;
          public          postgres    false    225            �            1259    98369    audio_id_seq    SEQUENCE     u   CREATE SEQUENCE public.audio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.audio_id_seq;
       public          postgres    false    224            �           0    0    audio_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.audio_id_seq OWNED BY public.audio.id;
          public          postgres    false    223            �            1259    98354    book_authors    TABLE     g   CREATE TABLE public.book_authors (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);
     DROP TABLE public.book_authors;
       public         heap    postgres    false            �            1259    114739    books    TABLE     �   CREATE TABLE public.books (
    id bigint NOT NULL,
    author integer NOT NULL,
    publisher character varying(128) NOT NULL,
    genre integer
);
    DROP TABLE public.books;
       public         heap    postgres    false            �            1259    98336    genre_books    TABLE     f   CREATE TABLE public.genre_books (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);
    DROP TABLE public.genre_books;
       public         heap    postgres    false            �            1259    114836    history    TABLE     �   CREATE TABLE public.history (
    user_id character varying(24) NOT NULL,
    audio_id bigint NOT NULL,
    amt_listened integer DEFAULT 0 NOT NULL
);
    DROP TABLE public.history;
       public         heap    postgres    false            �            1259    147712 	   book_view    VIEW     �  CREATE VIEW public.book_view AS
 SELECT a.id,
    a.name,
    ( SELECT au.name
           FROM public.book_authors au
          WHERE (au.id = b.author)) AS author,
    a.duration,
    a.year,
    ( SELECT g.name
           FROM public.genre_books g
          WHERE (g.id = b.genre)) AS genre,
    b.publisher,
    COALESCE(( SELECT sum(h.amt_listened) AS sum
           FROM public.history h
          WHERE (h.audio_id = a.id)), (0)::bigint) AS listens
   FROM (public.audio a
     JOIN public.books b ON ((a.id = b.id)))
  ORDER BY COALESCE(( SELECT sum(h.amt_listened) AS sum
           FROM public.history h
          WHERE (h.audio_id = a.id)), (0)::bigint) DESC;
    DROP VIEW public.book_view;
       public          postgres    false    224    218    218    222    222    236    236    231    231    224    224    231    231    224            �            1259    98327    genre_music    TABLE     f   CREATE TABLE public.genre_music (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);
    DROP TABLE public.genre_music;
       public         heap    postgres    false            �            1259    114719    songs    TABLE     �   CREATE TABLE public.songs (
    id bigint NOT NULL,
    artist integer NOT NULL,
    album character varying(64),
    genre integer
);
    DROP TABLE public.songs;
       public         heap    postgres    false            �            1259    155680 	   song_view    VIEW     �  CREATE VIEW public.song_view AS
 SELECT a.id,
    a.name,
    ( SELECT ar.name
           FROM public.artists ar
          WHERE (ar.id = s.artist)) AS artist,
    s.album,
    a.duration,
    a.year,
    ( SELECT g.name
           FROM public.genre_music g
          WHERE (g.id = s.genre)) AS genre,
    COALESCE(( SELECT sum(h.amt_listened) AS sum
           FROM public.history h
          WHERE (h.audio_id = a.id)), (0)::bigint) AS listens
   FROM (public.audio a
     JOIN public.songs s ON ((a.id = s.id)))
  ORDER BY COALESCE(( SELECT sum(h.amt_listened) AS sum
           FROM public.history h
          WHERE (h.audio_id = a.id)), (0)::bigint) DESC;
    DROP VIEW public.song_view;
       public          postgres    false    220    216    230    224    224    216    230    230    230    224    236    224    220    236            �            1259    163910    audio_name_creators    VIEW     �  CREATE VIEW public.audio_name_creators AS
 SELECT id,
    name,
        CASE
            WHEN (EXISTS ( SELECT 1
               FROM public.songs s
              WHERE (s.id = a.id))) THEN ( SELECT ss.artist
               FROM public.song_view ss
              WHERE (ss.id = a.id))
            ELSE ( SELECT b.author
               FROM public.book_view b
              WHERE (b.id = a.id))
        END AS creator
   FROM public.audio a;
 &   DROP VIEW public.audio_name_creators;
       public          postgres    false    224    247    247    245    245    230    224            �            1259    114853    transact    TABLE     �   CREATE TABLE public.transact (
    id bigint NOT NULL,
    user_id character varying(24) NOT NULL,
    audio_carrier_id bigint NOT NULL,
    date_rented date NOT NULL,
    date_end_of_rent date NOT NULL
);
    DROP TABLE public.transact;
       public         heap    postgres    false            �            1259    163914    audio_rent_stats    VIEW     f  CREATE VIEW public.audio_rent_stats AS
 SELECT avg((t.date_end_of_rent - t.date_rented)) AS avgrent,
    count(*) AS rents,
    cav.audio_id
   FROM (public.transact t
     JOIN public.carrier_audio_view cav ON ((t.audio_carrier_id = cav.id)))
  WHERE (t.audio_carrier_id IN ( SELECT ac.id
           FROM public.audio_carriers ac))
  GROUP BY cav.audio_id;
 #   DROP VIEW public.audio_rent_stats;
       public          postgres    false    246    227    238    238    238    246            �            1259    163935    audio_rent_stats_verb    VIEW       CREATE VIEW public.audio_rent_stats_verb AS
 SELECT anc.id,
    anc.name,
    anc.creator,
    (ars.avgrent)::double precision AS avgrent,
    ars.rents
   FROM (public.audio_rent_stats ars
     JOIN public.audio_name_creators anc ON ((ars.audio_id = anc.id)));
 (   DROP VIEW public.audio_rent_stats_verb;
       public          postgres    false    251    251    251    252    252    252            �            1259    98353    book_authors_id_seq    SEQUENCE     �   CREATE SEQUENCE public.book_authors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.book_authors_id_seq;
       public          postgres    false    222            �           0    0    book_authors_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.book_authors_id_seq OWNED BY public.book_authors.id;
          public          postgres    false    221            �            1259    114820    feedback    TABLE     �   CREATE TABLE public.feedback (
    id bigint NOT NULL,
    user_id character varying(24) NOT NULL,
    audio_id bigint NOT NULL,
    mark smallint NOT NULL,
    text character varying(256)
);
    DROP TABLE public.feedback;
       public         heap    postgres    false            �            1259    114780    feedback_id_seq    SEQUENCE     x   CREATE SEQUENCE public.feedback_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.feedback_id_seq;
       public          postgres    false    235            �           0    0    feedback_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.feedback_id_seq OWNED BY public.feedback.id;
          public          postgres    false    233            �            1259    98335    genre_books_id_seq    SEQUENCE     �   CREATE SEQUENCE public.genre_books_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.genre_books_id_seq;
       public          postgres    false    218            �           0    0    genre_books_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.genre_books_id_seq OWNED BY public.genre_books.id;
          public          postgres    false    217            �            1259    98326    genre_id_seq    SEQUENCE     �   CREATE SEQUENCE public.genre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.genre_id_seq;
       public          postgres    false    216            �           0    0    genre_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.genre_id_seq OWNED BY public.genre_music.id;
          public          postgres    false    215            �            1259    139289    genre_stats    TABLE     �   CREATE TABLE public.genre_stats (
    id bigint NOT NULL,
    is_book boolean NOT NULL,
    active_rents bigint DEFAULT 0 NOT NULL,
    date date NOT NULL,
    genre_id integer NOT NULL
);
    DROP TABLE public.genre_stats;
       public         heap    postgres    false            �            1259    139288    genre_stats_id_seq    SEQUENCE     {   CREATE SEQUENCE public.genre_stats_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.genre_stats_id_seq;
       public          postgres    false    244            �           0    0    genre_stats_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.genre_stats_id_seq OWNED BY public.genre_stats.id;
          public          postgres    false    243            �            1259    114713    params    TABLE     �   CREATE TABLE public.params (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    value character varying(256)
);
    DROP TABLE public.params;
       public         heap    postgres    false            �            1259    114712    params_id_seq    SEQUENCE     �   CREATE SEQUENCE public.params_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.params_id_seq;
       public          postgres    false    229            �           0    0    params_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.params_id_seq OWNED BY public.params.id;
          public          postgres    false    228            �            1259    163892    popular_books    VIEW     C  CREATE VIEW public.popular_books AS
 SELECT bv.id,
    bv.name,
    bv.author,
    hg.listened
   FROM (( SELECT sum(h.amt_listened) AS listened,
            h.audio_id
           FROM public.history h
          GROUP BY h.audio_id) hg
     JOIN public.book_view bv ON ((bv.id = hg.audio_id)))
  ORDER BY hg.listened DESC;
     DROP VIEW public.popular_books;
       public          postgres    false    245    245    236    236    245            �            1259    163897    popular_songs    VIEW     C  CREATE VIEW public.popular_songs AS
 SELECT sv.id,
    sv.name,
    sv.artist,
    hg.listened
   FROM (( SELECT sum(h.amt_listened) AS listened,
            h.audio_id
           FROM public.history h
          GROUP BY h.audio_id) hg
     JOIN public.song_view sv ON ((sv.id = hg.audio_id)))
  ORDER BY hg.listened DESC;
     DROP VIEW public.popular_songs;
       public          postgres    false    236    236    247    247    247            �            1259    114852    transact_id_seq    SEQUENCE     x   CREATE SEQUENCE public.transact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.transact_id_seq;
       public          postgres    false    238            �           0    0    transact_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.transact_id_seq OWNED BY public.transact.id;
          public          postgres    false    237            �            1259    114812    users    TABLE       CREATE TABLE public.users (
    id character varying(24) NOT NULL,
    email character varying(64) NOT NULL,
    name character varying(64) NOT NULL,
    blocked boolean DEFAULT false NOT NULL,
    balance money DEFAULT 0.0 NOT NULL,
    pwd character varying(64) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    114764    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    234            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    232            �           2604    147481 
   artists id    DEFAULT     h   ALTER TABLE ONLY public.artists ALTER COLUMN id SET DEFAULT nextval('public.artists_id_seq'::regclass);
 9   ALTER TABLE public.artists ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            �           2604    147482    aud_car_inventory id    DEFAULT     |   ALTER TABLE ONLY public.aud_car_inventory ALTER COLUMN id SET DEFAULT nextval('public.aud_car_inventory_id_seq'::regclass);
 C   ALTER TABLE public.aud_car_inventory ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    239    240    240            �           2604    147483    aud_car_sales id    DEFAULT     t   ALTER TABLE ONLY public.aud_car_sales ALTER COLUMN id SET DEFAULT nextval('public.aud_car_sales_id_seq'::regclass);
 ?   ALTER TABLE public.aud_car_sales ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    241    242    242            �           2604    147484    audio id    DEFAULT     d   ALTER TABLE ONLY public.audio ALTER COLUMN id SET DEFAULT nextval('public.audio_id_seq'::regclass);
 7   ALTER TABLE public.audio ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    147485    audio_carriers id    DEFAULT     v   ALTER TABLE ONLY public.audio_carriers ALTER COLUMN id SET DEFAULT nextval('public.audio_carriers_id_seq'::regclass);
 @   ALTER TABLE public.audio_carriers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    227    227            �           2604    147486    book_authors id    DEFAULT     r   ALTER TABLE ONLY public.book_authors ALTER COLUMN id SET DEFAULT nextval('public.book_authors_id_seq'::regclass);
 >   ALTER TABLE public.book_authors ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    147487    feedback id    DEFAULT     j   ALTER TABLE ONLY public.feedback ALTER COLUMN id SET DEFAULT nextval('public.feedback_id_seq'::regclass);
 :   ALTER TABLE public.feedback ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    233    235            �           2604    147488    genre_books id    DEFAULT     p   ALTER TABLE ONLY public.genre_books ALTER COLUMN id SET DEFAULT nextval('public.genre_books_id_seq'::regclass);
 =   ALTER TABLE public.genre_books ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    147489    genre_music id    DEFAULT     j   ALTER TABLE ONLY public.genre_music ALTER COLUMN id SET DEFAULT nextval('public.genre_id_seq'::regclass);
 =   ALTER TABLE public.genre_music ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    147490    genre_stats id    DEFAULT     p   ALTER TABLE ONLY public.genre_stats ALTER COLUMN id SET DEFAULT nextval('public.genre_stats_id_seq'::regclass);
 =   ALTER TABLE public.genre_stats ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    243    244    244            �           2604    147491 	   params id    DEFAULT     f   ALTER TABLE ONLY public.params ALTER COLUMN id SET DEFAULT nextval('public.params_id_seq'::regclass);
 8   ALTER TABLE public.params ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    229    229            �           2604    147492    transact id    DEFAULT     j   ALTER TABLE ONLY public.transact ALTER COLUMN id SET DEFAULT nextval('public.transact_id_seq'::regclass);
 :   ALTER TABLE public.transact ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    238    237    238            �          0    98345    artists 
   TABLE DATA           +   COPY public.artists (id, name) FROM stdin;
    public          postgres    false    220   �       �          0    122905    aud_car_inventory 
   TABLE DATA           K   COPY public.aud_car_inventory (id, carrier_id, quantity, date) FROM stdin;
    public          postgres    false    240   `�       �          0    122917    aud_car_sales 
   TABLE DATA           G   COPY public.aud_car_sales (id, carrier_id, quantity, date) FROM stdin;
    public          postgres    false    242   ʮ       �          0    98370    audio 
   TABLE DATA           E   COPY public.audio (id, name, year, duration, date_added) FROM stdin;
    public          postgres    false    224   �       �          0    98378    audio_carriers 
   TABLE DATA           T   COPY public.audio_carriers (id, audio_id, carrier, amt_available, cost) FROM stdin;
    public          postgres    false    227   N�       �          0    98354    book_authors 
   TABLE DATA           0   COPY public.book_authors (id, name) FROM stdin;
    public          postgres    false    222   +�       �          0    114739    books 
   TABLE DATA           =   COPY public.books (id, author, publisher, genre) FROM stdin;
    public          postgres    false    231   u�       �          0    114820    feedback 
   TABLE DATA           E   COPY public.feedback (id, user_id, audio_id, mark, text) FROM stdin;
    public          postgres    false    235   ޱ       �          0    98336    genre_books 
   TABLE DATA           /   COPY public.genre_books (id, name) FROM stdin;
    public          postgres    false    218   �       �          0    98327    genre_music 
   TABLE DATA           /   COPY public.genre_music (id, name) FROM stdin;
    public          postgres    false    216   :�       �          0    139289    genre_stats 
   TABLE DATA           P   COPY public.genre_stats (id, is_book, active_rents, date, genre_id) FROM stdin;
    public          postgres    false    244   ��       �          0    114836    history 
   TABLE DATA           B   COPY public.history (user_id, audio_id, amt_listened) FROM stdin;
    public          postgres    false    236   ˴       �          0    114713    params 
   TABLE DATA           1   COPY public.params (id, name, value) FROM stdin;
    public          postgres    false    229   N�       �          0    114719    songs 
   TABLE DATA           9   COPY public.songs (id, artist, album, genre) FROM stdin;
    public          postgres    false    230   k�       �          0    114853    transact 
   TABLE DATA           `   COPY public.transact (id, user_id, audio_carrier_id, date_rented, date_end_of_rent) FROM stdin;
    public          postgres    false    238   �       �          0    114812    users 
   TABLE DATA           G   COPY public.users (id, email, name, blocked, balance, pwd) FROM stdin;
    public          postgres    false    234   ¶       �           0    0    artists_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.artists_id_seq', 4, true);
          public          postgres    false    219            �           0    0    aud_car_inventory_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.aud_car_inventory_id_seq', 15, true);
          public          postgres    false    239            �           0    0    aud_car_sales_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.aud_car_sales_id_seq', 8, true);
          public          postgres    false    241            �           0    0    audio_carriers_audio_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.audio_carriers_audio_id_seq', 1, false);
          public          postgres    false    226            �           0    0    audio_carriers_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.audio_carriers_id_seq', 24, true);
          public          postgres    false    225            �           0    0    audio_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.audio_id_seq', 14, true);
          public          postgres    false    223            �           0    0    book_authors_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.book_authors_id_seq', 4, true);
          public          postgres    false    221            �           0    0    feedback_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.feedback_id_seq', 6, true);
          public          postgres    false    233                        0    0    genre_books_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.genre_books_id_seq', 4, true);
          public          postgres    false    217                       0    0    genre_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.genre_id_seq', 7, true);
          public          postgres    false    215                       0    0    genre_stats_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.genre_stats_id_seq', 1, false);
          public          postgres    false    243                       0    0    params_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.params_id_seq', 1, false);
          public          postgres    false    228                       0    0    transact_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.transact_id_seq', 22, true);
          public          postgres    false    237                       0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
          public          postgres    false    232            �           2606    98352    artists artists_name_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_name_key UNIQUE (name);
 B   ALTER TABLE ONLY public.artists DROP CONSTRAINT artists_name_key;
       public            postgres    false    220            �           2606    98350    artists artists_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.artists DROP CONSTRAINT artists_pkey;
       public            postgres    false    220                       2606    122910 (   aud_car_inventory aud_car_inventory_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.aud_car_inventory
    ADD CONSTRAINT aud_car_inventory_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.aud_car_inventory DROP CONSTRAINT aud_car_inventory_pkey;
       public            postgres    false    240                       2606    122922     aud_car_sales aud_car_sales_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.aud_car_sales
    ADD CONSTRAINT aud_car_sales_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.aud_car_sales DROP CONSTRAINT aud_car_sales_pkey;
       public            postgres    false    242                       2606    98384 "   audio_carriers audio_carriers_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.audio_carriers
    ADD CONSTRAINT audio_carriers_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.audio_carriers DROP CONSTRAINT audio_carriers_pkey;
       public            postgres    false    227                       2606    98375    audio audio_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.audio
    ADD CONSTRAINT audio_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.audio DROP CONSTRAINT audio_pkey;
       public            postgres    false    224            �           2606    98361 "   book_authors book_authors_name_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.book_authors
    ADD CONSTRAINT book_authors_name_key UNIQUE (name);
 L   ALTER TABLE ONLY public.book_authors DROP CONSTRAINT book_authors_name_key;
       public            postgres    false    222                       2606    98359    book_authors book_authors_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.book_authors
    ADD CONSTRAINT book_authors_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.book_authors DROP CONSTRAINT book_authors_pkey;
       public            postgres    false    222                       2606    114743    books books_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pkey;
       public            postgres    false    231                       2606    114825    feedback feedback_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.feedback DROP CONSTRAINT feedback_pkey;
       public            postgres    false    235            �           2606    98343     genre_books genre_books_name_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.genre_books
    ADD CONSTRAINT genre_books_name_key UNIQUE (name);
 J   ALTER TABLE ONLY public.genre_books DROP CONSTRAINT genre_books_name_key;
       public            postgres    false    218            �           2606    98341    genre_books genre_books_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.genre_books
    ADD CONSTRAINT genre_books_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.genre_books DROP CONSTRAINT genre_books_pkey;
       public            postgres    false    218            �           2606    98334     genre_music genre_music_name_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.genre_music
    ADD CONSTRAINT genre_music_name_key UNIQUE (name);
 J   ALTER TABLE ONLY public.genre_music DROP CONSTRAINT genre_music_name_key;
       public            postgres    false    216            �           2606    98332    genre_music genre_music_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.genre_music
    ADD CONSTRAINT genre_music_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.genre_music DROP CONSTRAINT genre_music_pkey;
       public            postgres    false    216                       2606    139295    genre_stats genre_stats_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.genre_stats
    ADD CONSTRAINT genre_stats_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.genre_stats DROP CONSTRAINT genre_stats_pkey;
       public            postgres    false    244                       2606    114841    history history_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_pkey PRIMARY KEY (user_id, audio_id);
 >   ALTER TABLE ONLY public.history DROP CONSTRAINT history_pkey;
       public            postgres    false    236    236                       2606    114718    params params_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.params
    ADD CONSTRAINT params_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.params DROP CONSTRAINT params_pkey;
       public            postgres    false    229            	           2606    114723    songs songs_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.songs
    ADD CONSTRAINT songs_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.songs DROP CONSTRAINT songs_pkey;
       public            postgres    false    230                       2606    114858    transact transact_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.transact
    ADD CONSTRAINT transact_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.transact DROP CONSTRAINT transact_pkey;
       public            postgres    false    238                       2606    114819    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    234                       2606    114817    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    234            -           2620    163876    transact transactinsert    TRIGGER     u   CREATE TRIGGER transactinsert AFTER INSERT ON public.transact FOR EACH ROW EXECUTE FUNCTION public.carrier_rented();
 0   DROP TRIGGER transactinsert ON public.transact;
       public          postgres    false    238    259            )           2606    122911 3   aud_car_inventory aud_car_inventory_carrier_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.aud_car_inventory
    ADD CONSTRAINT aud_car_inventory_carrier_id_fkey FOREIGN KEY (carrier_id) REFERENCES public.audio_carriers(id);
 ]   ALTER TABLE ONLY public.aud_car_inventory DROP CONSTRAINT aud_car_inventory_carrier_id_fkey;
       public          postgres    false    240    3333    227            *           2606    122923 +   aud_car_sales aud_car_sales_carrier_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.aud_car_sales
    ADD CONSTRAINT aud_car_sales_carrier_id_fkey FOREIGN KEY (carrier_id) REFERENCES public.audio_carriers(id);
 U   ALTER TABLE ONLY public.aud_car_sales DROP CONSTRAINT aud_car_sales_carrier_id_fkey;
       public          postgres    false    242    227    3333                       2606    114759 +   audio_carriers audio_carriers_audio_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.audio_carriers
    ADD CONSTRAINT audio_carriers_audio_id_fkey FOREIGN KEY (audio_id) REFERENCES public.audio(id) NOT VALID;
 U   ALTER TABLE ONLY public.audio_carriers DROP CONSTRAINT audio_carriers_audio_id_fkey;
       public          postgres    false    227    224    3331                        2606    114749    books books_author_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_author_fkey FOREIGN KEY (author) REFERENCES public.book_authors(id);
 A   ALTER TABLE ONLY public.books DROP CONSTRAINT books_author_fkey;
       public          postgres    false    222    231    3329            !           2606    114754    books books_genre_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_genre_fkey FOREIGN KEY (genre) REFERENCES public.genre_books(id);
 @   ALTER TABLE ONLY public.books DROP CONSTRAINT books_genre_fkey;
       public          postgres    false    218    231    3321            "           2606    114744    books books_id_fkey    FK CONSTRAINT     m   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_id_fkey FOREIGN KEY (id) REFERENCES public.audio(id);
 =   ALTER TABLE ONLY public.books DROP CONSTRAINT books_id_fkey;
       public          postgres    false    3331    231    224            #           2606    114826    feedback feedback_audio_id_fkey    FK CONSTRAINT        ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_audio_id_fkey FOREIGN KEY (audio_id) REFERENCES public.audio(id);
 I   ALTER TABLE ONLY public.feedback DROP CONSTRAINT feedback_audio_id_fkey;
       public          postgres    false    3331    235    224            $           2606    114831    feedback feedback_user_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 H   ALTER TABLE ONLY public.feedback DROP CONSTRAINT feedback_user_id_fkey;
       public          postgres    false    234    3343    235            +           2606    139296 %   genre_stats genre_stats_genre_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.genre_stats
    ADD CONSTRAINT genre_stats_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genre_music(id) NOT VALID;
 O   ALTER TABLE ONLY public.genre_stats DROP CONSTRAINT genre_stats_genre_id_fkey;
       public          postgres    false    3317    216    244            ,           2606    139301 &   genre_stats genre_stats_genre_id_fkey1    FK CONSTRAINT     �   ALTER TABLE ONLY public.genre_stats
    ADD CONSTRAINT genre_stats_genre_id_fkey1 FOREIGN KEY (genre_id) REFERENCES public.genre_books(id) NOT VALID;
 P   ALTER TABLE ONLY public.genre_stats DROP CONSTRAINT genre_stats_genre_id_fkey1;
       public          postgres    false    244    3321    218            %           2606    114842    history history_audio_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_audio_id_fkey FOREIGN KEY (audio_id) REFERENCES public.audio(id);
 G   ALTER TABLE ONLY public.history DROP CONSTRAINT history_audio_id_fkey;
       public          postgres    false    3331    236    224            &           2606    114847    history history_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) NOT VALID;
 F   ALTER TABLE ONLY public.history DROP CONSTRAINT history_user_id_fkey;
       public          postgres    false    234    236    3343                       2606    114729    songs songs_artist_fkey    FK CONSTRAINT     w   ALTER TABLE ONLY public.songs
    ADD CONSTRAINT songs_artist_fkey FOREIGN KEY (artist) REFERENCES public.artists(id);
 A   ALTER TABLE ONLY public.songs DROP CONSTRAINT songs_artist_fkey;
       public          postgres    false    220    3325    230                       2606    114734    songs songs_genre_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.songs
    ADD CONSTRAINT songs_genre_fkey FOREIGN KEY (genre) REFERENCES public.genre_music(id);
 @   ALTER TABLE ONLY public.songs DROP CONSTRAINT songs_genre_fkey;
       public          postgres    false    3317    216    230                       2606    114724    songs songs_id_fkey    FK CONSTRAINT     m   ALTER TABLE ONLY public.songs
    ADD CONSTRAINT songs_id_fkey FOREIGN KEY (id) REFERENCES public.audio(id);
 =   ALTER TABLE ONLY public.songs DROP CONSTRAINT songs_id_fkey;
       public          postgres    false    3331    224    230            '           2606    114864 '   transact transact_audio_carrier_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.transact
    ADD CONSTRAINT transact_audio_carrier_id_fkey FOREIGN KEY (audio_carrier_id) REFERENCES public.audio_carriers(id);
 Q   ALTER TABLE ONLY public.transact DROP CONSTRAINT transact_audio_carrier_id_fkey;
       public          postgres    false    227    3333    238            (           2606    114859    transact transact_user_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.transact
    ADD CONSTRAINT transact_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 H   ALTER TABLE ONLY public.transact DROP CONSTRAINT transact_user_id_fkey;
       public          postgres    false    234    3343    238            �   4   x�3�������SH,��2��M-I���LN�2�t,�H�P)������� 0?�      �   Z   x�]���@B��K2��~zI�u�ܢGހ���05�ȋ[#eA����6Fu��1*8{�7��i������|-އ0���=����'�      �   )   x�3�4�4�4202�54�54�2�44F��4G����� ��      �   ;  x�m��N�0�瓧�����8�V��Z	hE+��H�-\%.U��0�����_*X7}E;BףƵ(����~�_�����u@�Z�c
c���!3R��ˮ	�^Ѻ��_�x�%P�����p��^g�1�H	D(�'�M�jU�J�����h�͚k� ,��>�7G�?��	&|Z�=yB�m]��^\�k�\Q:�	l�TU8���c]�ʥ��q�S��$�B1:l\N�rmZg�)v����-��OO��Px6��k�?�>��,� �����ą��a���:�Ǝ��{�E��
��      �   �   x�e�1
�0�����$ۉ}�� Ki�J�R��+�I�����K2#� ��2�����}B�Ec��X߷'櫪a��	��a��d�IFLf����լ,�x.S��1�d��m!��I_�Ge&�F��8;��0�Fn�ø�^���l/���~	VDu?�x�D�kM����e;�6U*�~�I?�r��ӏ*�<s���I�m}      �   :   x�3���S��S�/���K�2�.I-�H�S�qM8�2��3�2���sҸb���� �{n      �   Y   x�3�4�t����-N*-��4�@0�4�����KW(J-.�4�
��&夦$V�Ɯ&���E%P5�\&X�0M�b���� &%�      �   �  x�]�Mn�0���)&{�@�E�q����ߢ�fD�$��r�r��5z���C)�む����M���e���-�t|�8�:d@ۓ#f���Y9�h{b`�y#�Al�|���t0Rd�Nm�ِs�0�6�z-@���N�А�PY��j�4{l�o��m�ݭ�.ެ��^;'/��P&�Mr0X�A�dh��?�i����+{�&E>�7ņ��K��%Y�>D�H�f]M�ڛU8ˢ�L�*v맏O����}q��m�àKӝ�����|�j��#�v��.+�C&��$�F�miq����(�N�G�Z��/��Yөp�&&}?�	��d����8�$*�!�5W�riFVp᭦E=�0\� �[@�f�i���j^����s�z�����\w8�lf������9�+�S�[{�I!O�1��|��h6Ʃ��\|ɬv������G���}Rw��0�Pa��1�����p΁��l�.г&H��+.˲���.o      �   C   x�3�tK�+I,��2���/*�/�2���OI-��,.Qp�L.����2�����M���]��+�b���� ��*      �   d   x�3��+U�M-I��2��HM,���L8C2��3�\SNO_}ל�䒢���d.3N�ܤ�Լ���䌼|.s΀�ļ|}���Ԣ<�����d��=... �� E      �      x������ � �      �   s   x�]��
@@D��~��^�<"E�E��;쪵�6�iN㎵Y���,Њ̸ݰh	
�dl{6*���5D�m�)(PC�^�(s\�c�j��+���S[�J�����teDt�1>      �      x������ � �      �   �   x��M
�0@���)����,���B7n�f��4�4E���Г8.�ǫ��q��t}pL��Μ�z���޻���PJ���i]���1Q�=���й`�K��T��{3[j��q���4-������@������@�7��?x++�      �   �   x���M
�0���]"y��?�V
�����?�VcR�Pw�c�2 ��}�D�U�)�Xhh�cw��,����ج2ƒ�{3�{=�ZE�Hu\@�
Q��!C��ԌK�+A�M��h>��%S�L���ɱ: �Q��,z�`�ȺO�SU<����b�      �   �   x���w�r���O�J,r(�.��K-ἰ����.�ɽv]�ǙƩ�b�gj�Y\�X������������Y�������8���.l�8��.��.l����V�NSSS �30�j����p�����,.vH�M���K���0�ⴋ��/�ګbhb�ga�������6�����!S�4����;.6^�p����@ڌ�AV$���p��qqq �b.     