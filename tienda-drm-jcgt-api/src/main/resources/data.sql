-- 1. INSERTAR MARCAS (IDs automáticos 1, 2, 3, 4)
INSERT INTO marcas (nombre, descripcion, imagen) VALUES
                                                     ('Pokémon TCG', 'Productora oficial de las cartas y mazos originales.', 'logo-pokemon.png'), -- ID 1
                                                     ('Ultra Pro', 'Fabricante de accesorios y protección.', 'logo-ultrapro.png'),              -- ID 2
                                                     ('Dragon Shield', 'Marca de fundas premium.', NULL),                         -- ID 3 (Marca sin productos - Punto 2)
                                                     ('Wizards of the Coast', 'Distribuidora clásica.', NULL);                                  -- ID 4

-- 2. INSERTAR CATEGORÍAS (IDs automáticos 1, 2, 3, 4, 5)
INSERT INTO categorias (nombre, descripcion, imagen) VALUES
                                                         ('Sobres', 'Ampliaciones de las últimas ediciones como Escarlata y Púrpura.', 'sobres.jpg'),      -- ID 1
                                                         ('Mazos', 'Mazos listos para jugar, ideales para entrenadores principiantes.', NULL),            -- ID 2
                                                         ('Accesorios', 'Protege tus cartas con las mejores fundas y cajas de almacenamiento.', 'accesorios.jpg'), -- ID 3
                                                         ('Cartas', 'Cartas raras, Full Art y Secretas vendidas por separado.', 'cartas.jpg'),            -- ID 4
                                                         ('Ediciones Limitadas', 'Productos exclusivos de colección.', NULL);                            -- ID 5 (Cat. sin productos - Punto 6)

-- 3. INSERTAR TUS 20 PRODUCTOS ORIGINALES CON COLUMNA STOCK
-- He añadido la columna 'stock' al final de la definición y de los valores.
INSERT INTO productos (codigo_ean, nombre, id_marca, descripcion, precio, descuento, imagen, stock) VALUES
-- SOBRES (Marca ID 1)
('820650855429', 'Sobre Escarlata y Púrpura-Evoluciones en Paldea', 1, 'Expande tu colección con el sobre de mejora de Evoluciones en Paldea...', 4.50, 0, '/imagenes/productos/sobre-paldea.png', 50),
('082065085055', 'Sobre Espada y Escudo-Origen Perdido', 1, 'Sumérgete en el Abismo con Origen Perdido...', 5.25, 10, '/imagenes/productos/sobre-origen.png', 25),
('082065085344', 'Sobre Escarlata y Púrpura-Fuerzas Temporales', 1, 'Las fuerzas del pasado y del futuro chocan...', 4.99, 0, '/imagenes/productos/sobre-fuerzas.png', 100),
('082065086006', 'Sobre Destinos de Paldea', 1, 'La luz brilla sobre los Pokémon Variocolor...', 5.95, 5, '/imagenes/productos/sobre-destinos.png', 0), -- Sin stock para pruebas de error
('082065085185', 'Sobre Astros Brillantes', 1, 'Descubre el poder de los Pokémon V-ASTRO...', 4.75, 0, '/imagenes/productos/sobre-astros.png', 40),
-- MAZOS (Marca ID 1)
('082065085526', 'Mazo de Combate Deluxe Meowscarada ex', 1, 'Mazo listo para jugar...', 19.90, 0, NULL, 15),
('082065085533', 'Mazo de Combate Deluxe Quaquaval ex', 1, 'Baraja preconstruida de nivel avanzado...', 19.90, 15, NULL, 10),
('082065085465', 'Mazo de Combate Lucario ex', 1, 'Un mazo de inicio perfecto...', 14.95, 0, NULL, 5),
('082065085472', 'Mazo de Combate Ampharos ex', 1, 'Mazo temático centrado en el tipo Rayo...', 14.95, 0, '/imagenes/productos/mazo-ampharos.png', 8),
('082065085283', 'Mazo de Inicio V de Mewtwo', 1, 'Domina los poderes psíquicos...', 15.50, 20, '/imagenes/productos/mazo-mewtwo.png', 3),
-- ACCESORIOS (Marca ID 2)
('400724625123', 'Moneda Coleccionista Lugia Plateada', 2, 'Moneda metálica oficial...', 3.00, 0, '/imagenes/productos/moneda-lugia.png', 200),
('400724625124', 'Moneda Coleccionista Rayquaza Esmeralda', 2, 'Moneda de alta calidad...', 3.00, 0, '/imagenes/productos/moneda-ray.png', 150),
('400724625125', 'Moneda Coleccionista Pikachu Clásica', 2, 'La moneda más icónica...', 2.50, 0, '/imagenes/productos/moneda-pika.png', 300),
('843530191234', 'Tapete Neopreno Kanto Starter', 2, 'Tapete de juego profesional...', 24.90, 0, '/imagenes/productos/tapete-kanto.png', 12),
('843530191235', 'Tapete Edición Limitada Arceus', 2, 'Tapete de juego con ilustración exclusiva...', 29.90, 10, '/imagenes/productos/tapete-arceus.png', 4),
-- CARTAS (Marca ID 4 para Oak, ID 1 para el resto)
('0000000000010', 'Carta Profesor Oak (Base Set)', 4, 'La carta de apoyo más mítica...', 85.00, 0, '/imagenes/productos/carta-oak.png', 1), -- Solo queda 1
('0000000000027', 'Carta Treecko (Rubí y Zafiro)', 1, 'Carta básica de tipo planta...', 1.20, 0, '/imagenes/productos/carta-treecko.png', 60),
('0000000000034', 'Carta Mudkip (Holofoil)', 1, 'Versión brillante de Mudkip...', 5.50, 0, '/imagenes/productos/carta-mudkip.png', 20),
('0000000000041', 'Carta Mewtwo GX', 1, 'Carta de alto valor estratégico...', 12.00, 5, NULL, 2),
('0000000000058', 'Carta Charizard VMAX', 1, 'Carta ultra rara de gran tamaño...', 45.00, 0, NULL, 0); -- Sin stock

-- 4. RELACIÓN PRODUCTOS-CATEGORÍAS (Tabla intermedia)
-- Sobres (Cat 1): ID del producto del 1 al 5
INSERT INTO productos_categorias (id_producto, id_categoria) VALUES (1, 1), (2, 1), (3, 1), (4, 1), (5, 1);
-- Mazos (Cat 2): ID 6 (Punto 7: Categoría con un solo producto)
INSERT INTO productos_categorias (id_producto, id_categoria) VALUES (6, 2);
-- Accesorios (Cat 3): ID del 11 al 15
INSERT INTO productos_categorias (id_producto, id_categoria) VALUES (11, 3), (12, 3), (13, 3), (14, 3), (15, 3);
-- Cartas (Cat 4): ID del 16 al 19 (El 18 tiene varias categorías - Punto 5)
INSERT INTO productos_categorias (id_producto, id_categoria) VALUES (16, 4), (17, 4), (18, 4), (19, 4);
INSERT INTO productos_categorias (id_producto, id_categoria) VALUES (18, 1); -- Mudkip en Cartas y Sobres (Punto 5)
-- El ID 20 (Charizard) NO tiene categoría (Punto 3: Producto sin categoría)

-- 1. CREACIÓN DE ROLES (Deben existir antes de poder asignarse)
INSERT INTO roles (id, descripcion) VALUES ('USER', 'Usuario normal');
INSERT INTO roles (id, descripcion) VALUES ('ADMIN', 'Administrador');

-- 2. INSERTAR USUARIOS
-- Usuario: admin | Password: Password
INSERT INTO usuarios (id, username, password, nombre, apellidos, email, activo, fecha_registro)
VALUES (1, 'admin', '$2a$12$bh8WT8.41jshB8Ks24wveeF.ZsNuK1JJCTdKVCe0jxQUA.G/Cwj3a', 'Admin', 'Sistema', 'admin@tienda.com', true, CURRENT_TIMESTAMP);

-- Usuario: user | Password: Password
INSERT INTO usuarios (id, username, password, nombre, apellidos, email, activo, fecha_registro)
VALUES (2, 'user', '$2a$12$bh8WT8.41jshB8Ks24wveeF.ZsNuK1JJCTdKVCe0jxQUA.G/Cwj3a', 'User', 'Normal', 'user@tienda.com', true, CURRENT_TIMESTAMP);

-- 3. ASIGNACIÓN DE ROLES (Tabla intermedia)
-- admin (ID 1) tiene los roles ADMIN y USER
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 'ADMIN');
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 'USER');

-- user (ID 2) solo tiene el rol USER
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 'USER');