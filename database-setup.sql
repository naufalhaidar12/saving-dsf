-- 1. DROP DATABASE (Hapus database lama)
DROP DATABASE IF EXISTS saving_db;

-- 2. BUAT DATABASE BARU
CREATE DATABASE saving_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 3. GUNAKAN DATABASE
USE saving_db;

-- 4. BUAT TABLE CUSTOMER
CREATE TABLE customer (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          government_id VARCHAR(255) NOT NULL UNIQUE,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          phone VARCHAR(50) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. BUAT TABLE SAVING (dengan tambahan bank_name dan bank_logo)
CREATE TABLE saving (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE,
                        account_number VARCHAR(50) NOT NULL UNIQUE,
                        balance DOUBLE NOT NULL DEFAULT 0.0,
                        bank_name VARCHAR(100) NOT NULL,
                        bank_logo VARCHAR(255),
                        customer_id BIGINT NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6. BUAT TABLE TRANSACTION HISTORY
CREATE TABLE transaction_history (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     transaction_type VARCHAR(50) NOT NULL,
                                     amount DOUBLE NOT NULL,
                                     sender_account_number VARCHAR(50) NOT NULL,
                                     sender_name VARCHAR(255) NOT NULL,
                                     sender_bank VARCHAR(100) NOT NULL,
                                     receiver_account_number VARCHAR(50) NOT NULL,
                                     receiver_name VARCHAR(255) NOT NULL,
                                     receiver_bank VARCHAR(100) NOT NULL,
                                     description VARCHAR(500),
                                     status VARCHAR(50) NOT NULL,
                                     transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     INDEX idx_sender_account (sender_account_number),
                                     INDEX idx_receiver_account (receiver_account_number),
                                     INDEX idx_transaction_date (transaction_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 7. BUAT INDEX
CREATE INDEX idx_saving_customer_id ON saving(customer_id);

-- 8. INSERT SAMPLE DATA CUSTOMER
INSERT INTO customer (name, government_id, email, phone) VALUES
                                                             ('John Doe', '3201234567890001', 'john.doe@email.com', '081234567890'),
                                                             ('Jane Smith', '3201234567890002', 'jane.smith@email.com', '081234567891'),
                                                             ('Bob Johnson', '3201234567890003', 'bob.johnson@email.com', '081234567892'),
                                                             ('Alice Wonder', '3201234567890004', 'alice.wonder@email.com', '081234567893');

-- 9. INSERT SAMPLE DATA SAVING (dengan bank_name dan bank_logo)
INSERT INTO saving (name, account_number, balance, bank_name, bank_logo, customer_id) VALUES
                                                                                          ('John Emergency Fund', '1234567890', 5000000.00, 'BCA', '🏦', 1),
                                                                                          ('John Vacation Fund', '1234567891', 3000000.00, 'Mandiri', '🏦', 1),
                                                                                          ('Jane Education Fund', '1234567892', 10000000.00, 'BNI', '🏦', 2),
                                                                                          ('Bob Retirement Fund', '1234567893', 50000000.00, 'BRI', '🏦', 3),
                                                                                          ('Alice Investment', '1234567894', 15000000.00, 'CIMB Niaga', '🏦', 4);

-- 10. INSERT SAMPLE TRANSACTION HISTORY
INSERT INTO transaction_history (transaction_type, amount, sender_account_number, sender_name, sender_bank,
                                 receiver_account_number, receiver_name, receiver_bank, description, status) VALUES
                                                                                                                 ('TRANSFER', 500000.00, '1234567890', 'John Doe', 'BCA', '1234567892', 'Jane Smith', 'BNI', 'Transfer untuk pendidikan', 'SUCCESS'),
                                                                                                                 ('TRANSFER', 1000000.00, '1234567892', 'Jane Smith', 'BNI', '1234567893', 'Bob Johnson', 'BRI', 'Pembayaran hutang', 'SUCCESS'),
                                                                                                                 ('TRANSFER', 250000.00, '1234567891', 'John Doe', 'Mandiri', '1234567894', 'Alice Wonder', 'CIMB Niaga', 'Gift', 'SUCCESS');