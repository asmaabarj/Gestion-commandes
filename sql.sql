PGDMP  
                	    |           gestion_commandes    16.4    16.4 (               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                        1262    27347    gestion_commandes    DATABASE     �   CREATE DATABASE gestion_commandes WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_France.1252';
 !   DROP DATABASE gestion_commandes;
                postgres    false            _           1247    27404    role    TYPE     ?   CREATE TYPE public.role AS ENUM (
    'Client',
    'Admin'
);
    DROP TYPE public.role;
       public          postgres    false            b           1247    27410    statut    TYPE     x   CREATE TYPE public.statut AS ENUM (
    'ENATTENTE',
    'ENTRAITEMENT',
    'EXPIDIEE',
    'LIVREE',
    'ANNULEE'
);
    DROP TYPE public.statut;
       public          postgres    false            �            1259    27348    admin    TABLE     �   CREATE TABLE public.admin (
    id bigint NOT NULL,
    email character varying(255),
    motdepasse character varying(255),
    nom character varying(255),
    prenom character varying(255),
    role character varying(255),
    niveauacces integer
);
    DROP TABLE public.admin;
       public         heap    postgres    false            �            1259    27355    client    TABLE     ;  CREATE TABLE public.client (
    id bigint NOT NULL,
    email character varying(255),
    motdepasse character varying(255),
    nom character varying(255),
    prenom character varying(255),
    role character varying(255),
    adresselivraison character varying(255),
    moyenpaiement character varying(255)
);
    DROP TABLE public.client;
       public         heap    postgres    false            �            1259    27363    commande    TABLE     �   CREATE TABLE public.commande (
    id bigint NOT NULL,
    date_commande date NOT NULL,
    statut character varying(255) NOT NULL,
    client_id bigint NOT NULL
);
    DROP TABLE public.commande;
       public         heap    postgres    false            �            1259    27362    commande_id_seq    SEQUENCE     x   CREATE SEQUENCE public.commande_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.commande_id_seq;
       public          postgres    false    218            !           0    0    commande_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.commande_id_seq OWNED BY public.commande.id;
          public          postgres    false    217            �            1259    27369    commande_produit    TABLE     j   CREATE TABLE public.commande_produit (
    commande_id bigint NOT NULL,
    produit_id bigint NOT NULL
);
 $   DROP TABLE public.commande_produit;
       public         heap    postgres    false            �            1259    27387    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    27375    produit    TABLE     �   CREATE TABLE public.produit (
    id bigint NOT NULL,
    description character varying(255),
    nom character varying(255),
    prix double precision,
    stock integer
);
    DROP TABLE public.produit;
       public         heap    postgres    false            �            1259    27374    produit_id_seq    SEQUENCE     w   CREATE SEQUENCE public.produit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.produit_id_seq;
       public          postgres    false    221            "           0    0    produit_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.produit_id_seq OWNED BY public.produit.id;
          public          postgres    false    220            �            1259    27421    utilisateur    TABLE     �   CREATE TABLE public.utilisateur (
    id integer NOT NULL,
    email character varying(255),
    motdepasse character varying(255),
    nom character varying(255),
    prenom character varying(255),
    role character varying(255)
);
    DROP TABLE public.utilisateur;
       public         heap    postgres    false            l           2604    27366    commande id    DEFAULT     j   ALTER TABLE ONLY public.commande ALTER COLUMN id SET DEFAULT nextval('public.commande_id_seq'::regclass);
 :   ALTER TABLE public.commande ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            m           2604    27378 
   produit id    DEFAULT     h   ALTER TABLE ONLY public.produit ALTER COLUMN id SET DEFAULT nextval('public.produit_id_seq'::regclass);
 9   ALTER TABLE public.produit ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    221    221                      0    27348    admin 
   TABLE DATA           V   COPY public.admin (id, email, motdepasse, nom, prenom, role, niveauacces) FROM stdin;
    public          postgres    false    215   �-                 0    27355    client 
   TABLE DATA           k   COPY public.client (id, email, motdepasse, nom, prenom, role, adresselivraison, moyenpaiement) FROM stdin;
    public          postgres    false    216   Y/                 0    27363    commande 
   TABLE DATA           H   COPY public.commande (id, date_commande, statut, client_id) FROM stdin;
    public          postgres    false    218   )0                 0    27369    commande_produit 
   TABLE DATA           C   COPY public.commande_produit (commande_id, produit_id) FROM stdin;
    public          postgres    false    219   F0                 0    27375    produit 
   TABLE DATA           D   COPY public.produit (id, description, nom, prix, stock) FROM stdin;
    public          postgres    false    221   c0                 0    27421    utilisateur 
   TABLE DATA           O   COPY public.utilisateur (id, email, motdepasse, nom, prenom, role) FROM stdin;
    public          postgres    false    223   S1       #           0    0    commande_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.commande_id_seq', 1, false);
          public          postgres    false    217            $           0    0    hibernate_sequence    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hibernate_sequence', 221, true);
          public          postgres    false    222            %           0    0    produit_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.produit_id_seq', 14, true);
          public          postgres    false    220            o           2606    27354    admin admin_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.admin DROP CONSTRAINT admin_pkey;
       public            postgres    false    215            s           2606    27361    client client_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.client DROP CONSTRAINT client_pkey;
       public            postgres    false    216            w           2606    27368    commande commande_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.commande
    ADD CONSTRAINT commande_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.commande DROP CONSTRAINT commande_pkey;
       public            postgres    false    218            y           2606    27373 &   commande_produit commande_produit_pkey 
   CONSTRAINT     y   ALTER TABLE ONLY public.commande_produit
    ADD CONSTRAINT commande_produit_pkey PRIMARY KEY (commande_id, produit_id);
 P   ALTER TABLE ONLY public.commande_produit DROP CONSTRAINT commande_produit_pkey;
       public            postgres    false    219    219            {           2606    27382    produit produit_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.produit DROP CONSTRAINT produit_pkey;
       public            postgres    false    221            }           2606    27429 (   utilisateur uk_35ysk0sh9ruwixrld3nc0weut 
   CONSTRAINT     d   ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT uk_35ysk0sh9ruwixrld3nc0weut UNIQUE (email);
 R   ALTER TABLE ONLY public.utilisateur DROP CONSTRAINT uk_35ysk0sh9ruwixrld3nc0weut;
       public            postgres    false    223            u           2606    27386 #   client uk_f07ymtqaif0tbcawyb71l3one 
   CONSTRAINT     _   ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_f07ymtqaif0tbcawyb71l3one UNIQUE (email);
 M   ALTER TABLE ONLY public.client DROP CONSTRAINT uk_f07ymtqaif0tbcawyb71l3one;
       public            postgres    false    216            q           2606    27384 "   admin uk_jl20d0ecx48g7qwy1dxe2akre 
   CONSTRAINT     ^   ALTER TABLE ONLY public.admin
    ADD CONSTRAINT uk_jl20d0ecx48g7qwy1dxe2akre UNIQUE (email);
 L   ALTER TABLE ONLY public.admin DROP CONSTRAINT uk_jl20d0ecx48g7qwy1dxe2akre;
       public            postgres    false    215                       2606    27427    utilisateur utilisateur_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.utilisateur DROP CONSTRAINT utilisateur_pkey;
       public            postgres    false    223            �           2606    27388 $   commande fk5rrlskbu107son4anaelg5r57    FK CONSTRAINT     �   ALTER TABLE ONLY public.commande
    ADD CONSTRAINT fk5rrlskbu107son4anaelg5r57 FOREIGN KEY (client_id) REFERENCES public.client(id);
 N   ALTER TABLE ONLY public.commande DROP CONSTRAINT fk5rrlskbu107son4anaelg5r57;
       public          postgres    false    216    4723    218            �           2606    27393 ,   commande_produit fkhp4pp75emrkv8kfqq1hn2rk7s    FK CONSTRAINT     �   ALTER TABLE ONLY public.commande_produit
    ADD CONSTRAINT fkhp4pp75emrkv8kfqq1hn2rk7s FOREIGN KEY (produit_id) REFERENCES public.produit(id);
 V   ALTER TABLE ONLY public.commande_produit DROP CONSTRAINT fkhp4pp75emrkv8kfqq1hn2rk7s;
       public          postgres    false    219    221    4731            �           2606    27398 ,   commande_produit fks9k1rm4xmlmu7ulyhpw4q0svl    FK CONSTRAINT     �   ALTER TABLE ONLY public.commande_produit
    ADD CONSTRAINT fks9k1rm4xmlmu7ulyhpw4q0svl FOREIGN KEY (commande_id) REFERENCES public.commande(id);
 V   ALTER TABLE ONLY public.commande_produit DROP CONSTRAINT fks9k1rm4xmlmu7ulyhpw4q0svl;
       public          postgres    false    219    218    4727               �  x����r� @��+�`��|B.=�"@�ɬ���6ۯ/�l'����zOH��mA��ޞ��\p��)ԅ9EF'��b�Q� &�Ѫ��rI)ޠ� �KἚ����Gf�"^�z`/qɅ��e���}.��6��59����څ��	�jR&���(&N�݀�g&�xb[�ff�i�+�/̉��Wn8��K�0�J�V�@@(���eyHd�K�'Ta҉�E�DP �s^X�7ȡ�����P���q,u�۱���^��X��~CK�=��Y��l�K}�ʷz>�w������~�/}�i����Eű�u�J��]���.t�6�z��k��2��'�����F�Í؇(�D���}����j΅-=��&�J`���<��1Ji?��_���a~H��         �   x���Aj�0е}
�`H��wJoэ,)���ہaN�Br����@g���&�ضr�m5��4.#�`a�y�<{�ه��	�a �n�C�#��D,�uE4_I%7SqQCX����P��~���填��n�������$��Q��	I�9<U����m�픊ϸw�]+�äX]������Yk� ���            x������ � �            x������ � �         �   x���=N1��>EN����Hl��Ж4�1���Iv&�bo�9�H�("M��s�l��<).�RI]*�+�h�i᷺� �Ҙ�:8��H��¹p�U���yIC�N�0ZK��S9�I��<�����_��w�iֈ�`�Z۬k��}׺f�Xw]��b=x�wmh6��-���m��eOi���j�Ñ�ɜ�8�kk.��P^�����B؁���"� ����            x������ � �     