ALTER TABLE public.refresh_token
DROP
CONSTRAINT fkjtx87i0jvq2svedphegvdwcuy,
    ADD CONSTRAINT fkjtx87i0jvq2svedphegvdwcuy
         FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE
CASCADE;

ALTER TABLE public.users_roles
DROP
CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa,
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa
         FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE
CASCADE;

ALTER TABLE public.users_roles
DROP
CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy,
    ADD CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy
         FOREIGN KEY (role_id) REFERENCES public.roles(id);

CREATE INDEX idx_refresh_token_user ON public.refresh_token (user_id);
