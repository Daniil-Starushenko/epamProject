use `testDatabase`;
CREATE TABLE `users` (
                         `id` INTEGER NOT NULL AUTO_INCREMENT,
                         `name` VARCHAR(20) NOT NULL,
                         `login` VARCHAR(255) NOT NULL UNIQUE,
                         `password` VARCHAR(32) NOT NULL,
                         `mail` VARCHAR(50) NOT NULL,
                         `reg_date` DATETIME NOT NULL,
    /*
	 * admin - администратор (Role.ADMINISTRATOR)
	 * user - пользователь (Role.USER)
	 * deliveryman - доставщик (Role.DELIVERY_MAN)
	 */
                         `role` VARCHAR(15) NOT NULL CHECK (`role` IN ('user', 'admin', 'deliveryman')),
                         PRIMARY KEY(`id`),
                         UNIQUE (id)
);

CREATE TABLE `product` (
                           `id` INTEGER NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(32) NOT NULL,
                           `png_path` VARCHAR(255) NOT NULL,
                           `definition` TEXT NOT NULL,
                           `price` DECIMAL(20,2) NOT NULL,
                           PRIMARY KEY(`id`),
                           UNIQUE (id)
);

CREATE TABLE `order` (
                         `id` INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
                         `user_id` INTEGER,
                         `address` VARCHAR(255) NOT NULL,
                         `date_of_ordering` DATETIME NOT NULL,
                         `phone_number` VARCHAR(20) NOT NULL,
                         `total_price` DECIMAL(20,2) NOT NULL,
                         `status` VARCHAR(20) NOT NULL CHECK (`status` IN('in_basket', 'in_transit', 'ready')),
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
                             ON UPDATE CASCADE
                             ON DELETE RESTRICT
);

CREATE TABLE `order_item` (
                              `id` INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
                              `order_id` INTEGER,
                              `product_id` INTEGER,
                              `quantity` INTEGER,
                              PRIMARY KEY (`id`),
                              FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
                              FOREIGN KEY (`order_id`) REFERENCES `order`(`id`)
                                  ON UPDATE CASCADE
                                  ON DELETE RESTRICT
);

CREATE TABLE `review` (
                          `id` INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
                          `user_id` INTEGER,
                          `text` TEXT NOT NULL,
                          `date` DATETIME NOT NULL,
                          PRIMARY KEY (`id`),
                          FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
                              ON UPDATE CASCADE
                              ON DELETE RESTRICT
);

CREATE TABLE `deliveryman` (
                               `id` INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
                               `user_id` INTEGER,
                               `phone_number` VARCHAR(20) NOT NULL,
                               PRIMARY KEY (`id`),
                               FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
                                   ON UPDATE CASCADE
                                   ON DELETE RESTRICT
);
CREATE TABLE `review_type` (
                               `review_id` INTEGER,
                               `deliveryman_id` INTEGER,
                               `order_id` INTEGER,
                               FOREIGN KEY (`deliveryman_id`) REFERENCES `deliveryman`(`id`),
                               FOREIGN KEY (`order_id`) REFERENCES `order`(`id`),
                               FOREIGN KEY (`review_id`) REFERENCES `review`(`id`)
                                   ON UPDATE CASCADE
                                   ON DELETE RESTRICT
);

CREATE TABLE `deliveryman_order` (
                                     `id` INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
                                     `deliveryman_id` INTEGER,
                                     `order_id` INTEGER,
                                     PRIMARY KEY (`id`),
                                     FOREIGN KEY (`deliveryman_id`) REFERENCES `deliveryman`(`id`),
                                     FOREIGN KEY (`order_id`) REFERENCES `order`(`id`)
                                         ON UPDATE CASCADE
                                         ON DELETE RESTRICT
)

