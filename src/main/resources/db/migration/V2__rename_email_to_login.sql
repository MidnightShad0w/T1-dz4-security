ALTER TABLE public.users
    RENAME COLUMN email TO login;

ALTER TABLE public.users
    DROP CONSTRAINT users_email_key;

ALTER TABLE public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);
