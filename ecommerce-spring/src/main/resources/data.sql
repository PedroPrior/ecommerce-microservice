
INSERT INTO tb_users values(1, 'user1@gmail.com', '$2a$12$wZugNU1U40NXb5HuetkNgu9pcIardhIlgEtbtmfSHNLGcUms7MOW2', 'user1');
INSERT INTO tb_users values(2, 'user2@gmail.com', '$2a$12$tu86JF8626xF6kO8o.UhHOYRlYFcYtCl9ZXRFPgLKfhOni4s1VbeC', 'user2');
INSERT INTO tb_users values(3, 'user3@gmail.com', '$2a$12$9A4aNHH.DC7cUHQ8vXW9tOwr3PJ1un93A2mA.4ZTGv7dVSK63Qw.6', 'user3');


INSERT INTO tb_role values(1, 'ROLE_ADMIN');
INSERT INTO tb_role values(2, 'ROLE_USER');

INSERT INTO tb_users_roles values (1, 1);
INSERT INTO tb_users_roles values (1, 2);
INSERT INTO tb_users_roles values (2, 1);

INSERT INTO tb_client values (1, '012.123.456-02', 'Eduardo Henrique', '49981415791', 1);
INSERT INTO tb_client values (2, '012.321.456-02', 'Pedro Eduardo', '12981415792', 2);
INSERT INTO tb_client values (3, '012.444.456-02', 'Fernando Costa', '12981415710', 3);

INSERT INTO tb_address values(1, '89800-041', 'E', 153, 'Next to the hospital', 1);
INSERT INTO tb_address values(2, '89800-035', 'E', 135, 'Next to the Shopping Center', 2);
INSERT INTO tb_address values(3, '89801-011', 'E', 913, 'Next to the Supermarket', 3);


INSERT INTO tb_category values(1, 'lorem ipsum', 'Electronics');
INSERT INTO tb_category values(2, 'lorem ipsum', 'Books');
INSERT INTO tb_category values(3, 'lorem ipsum', 'Tools');
INSERT INTO tb_category values(4, 'lorem ipsum', 'Supplements');

INSERT INTO tb_product values (1, 'lorem ipsum', 'iPhone 13', 10000.00, 2, null, 1);
INSERT INTO tb_product values (2, 'lorem ipsum', 'Chainsaw Caterpillar', 2500, 1, null, 3);
INSERT INTO tb_product values (3, 'lorem ipsum', 'Harry Potter', 50.00, 1, null, 2);
INSERT INTO tb_product values(4, 'lorem ipsum', 'Whey Protein', 110.00, 1, '10/02/2024', 4);

INSERT INTO tb_order_status values(1, 'PENDING');
INSERT INTO tb_order_status values(2, 'SHIPPED');
INSERT INTO tb_order_status values(3, 'DELIVERED');
INSERT INTO tb_order_status values(4, 'PAID');

INSERT INTO tb_delivery_status values(1, 'CANCEL');
INSERT INTO tb_delivery_status values(2, 'PENDING');
INSERT INTO tb_delivery_status values(3, 'IN_TRANSIT');
INSERT INTO tb_delivery_status values(4, 'DELIVERED');

INSERT INTO tb_payment_status values(1, 'REFUSED');
INSERT INTO tb_payment_status values(2, 'PENDING');
INSERT INTO tb_payment_status values(3, 'PAID');

INSERT INTO tb_payment_type values(1, 'CREDIT_CARD_VISA');
INSERT INTO tb_payment_type values(2, 'CREDIT_CARD_MASTERCARD');
INSERT INTO tb_payment_type values(3, 'CREDIT_CARD_ANOTHER');
INSERT INTO tb_payment_type values(4, 'PIX');
INSERT INTO tb_payment_type values(5, 'BANK_SLIP');

select * from tb_order;
select * from tb_client;
select * from tb_payment;
select * from tb_delivery;
select * from tb_stock;
select * from tb_product;

INSERT INTO tb_stock values (1, 10, 1);
INSERT INTO tb_stock values (2, 10, 2);
INSERT INTO tb_stock values (3, 10, 3);
INSERT INTO tb_stock values (4, 10, 4);
