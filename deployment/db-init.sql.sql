-- Tabla: loan_type
CREATE TABLE loan_type (
    id_tip INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name_loan VARCHAR(100) NOT NULL,
    mini_amount DECIMAL(15,2) NOT NULL,
    max_amount DECIMAL(15,2) NOT NULL,
    rate_interest DECIMAL(5,2) NOT NULL,
    auto_validation BOOLEAN NOT NULL
);

-- Tabla: state_application (estados de aplicación)
CREATE TABLE state_application (
    id_state INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name_state VARCHAR(100) NOT NULL,
    description_st VARCHAR(255)
);

-- Tabla: application (solicitudes de préstamo)
CREATE TABLE application (
    id_application INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    num_document_user VARCHAR(50) NOT NULL,
    amount_app DECIMAL(15,2) NOT NULL,
    term_app INT NOT NULL,
    email_app VARCHAR(150) NOT NULL,
    id_state INT NOT NULL,
    id_tip INT NOT NULL,
    CONSTRAINT fk_state FOREIGN KEY (id_state) REFERENCES state_application (id_state),
    CONSTRAINT fk_loantype FOREIGN KEY (id_tip) REFERENCES loan_type (id_tip)
);