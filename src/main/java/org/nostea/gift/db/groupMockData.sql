CREATE TABLE groups (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    groupName VARCHAR(255),
    description VARCHAR(1000),
    groupMode(50) NOT NULL,
    budgetLimit DECIMAL(8,2),
    owner BIGINT NOT NULL,
    deadline DATE,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP
)