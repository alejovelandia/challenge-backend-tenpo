CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.transaction_history (
    id SERIAL PRIMARY KEY,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    endpoint VARCHAR(255) NOT NULL,
    parameters VARCHAR(255) NOT NULL,
    response VARCHAR(255) NOT NULL
);

INSERT INTO public.transaction_history (creation_date, endpoint, parameters, response) VALUES
  ('2025-06-01 09:33:55.155', '/math-operation/addition', '{"num1":"2","num2":"4"}', '{"result":16}'),
  ('2025-06-01 11:36:34.581', '/math-operation/addition', '{"num1":"4","num2":"6"}', '{"code":"404","message":"ERROR ::external api error and not value saved in cache"}');
