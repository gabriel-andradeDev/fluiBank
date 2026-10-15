INSERT INTO users (
    name,
    email,
    password,
    cpf,
    phone,
    active,
    role,
    created_at,
    updated_at
)
VALUES (
           'Gabriel',
           'admin@fluibank.com',
           '$2a$10$1Qjxf.DFDtub3Gtus3g3Kus142D7T5zsS0wkWvHWE5Qh9NT/dkFTa',
           '00000000000',
           NULL,
           TRUE,
           'ADMIN',
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP
       )
    ON CONFLICT (email) DO NOTHING;