INSERT INTO public.roles (name)
VALUES ('ADMIN'),
       ('PREMIUM_USER'),
       ('GUEST')
ON CONFLICT (name) DO NOTHING;
