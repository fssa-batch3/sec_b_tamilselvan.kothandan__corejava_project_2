create table if not exists doctors(
doctor_id int auto_increment primary key,
id int,
FOREIGN KEY (id) REFERENCES users (id),
qualifications varchar(30) not null,
experience int not null,
department varchar(30) not null,
is_active boolean default true,
created_at timestamp default current_timestamp,
modified_at timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);


create table if not exists appointments(
id int primary key auto_increment,
patient_id int,
foreign key (patient_id) references users(id),
doctor_id int,
foreign key (doctor_id) references doctors(doctor_id),
reason_for_consultation varchar(125) not null,
date_of_consultation date not null,
start_time time not null,
end_time time not null,
status enum ("Approved" , "Rejected" , "On_process" , "Consulted", "Cancelled") default "On_process"
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE KEY ,
    password VARCHAR(100) NOT NULL,
    is_active BOOLEAN DEFAULT (1),
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);