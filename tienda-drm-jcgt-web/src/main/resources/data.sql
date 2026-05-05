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

-- 3. INSERTAR TUS 20 PRODUCTOS ORIGINALES
-- He mapeado 'Pokémon TCG' al ID 1 y los accesorios al ID 2 (Ultra Pro)
INSERT INTO productos (codigo_ean, nombre, id_marca, descripcion, precio, descuento, imagen) VALUES
-- SOBRES (Marca ID 1)
('820650855429', 'Sobre Escarlata y Púrpura-Evoluciones en Paldea', 1, 'Expande tu colección con el sobre de mejora de Evoluciones en Paldea. Esta expansión introduce nuevos Pokémon ex y mecánicas que cambian el juego competitivo actual, permitiendo a los entrenadores descubrir el poder oculto de los iniciales de la región de Paldea en sus formas más poderosas y brillantes.', 4.50, 0, '/imagenes/productos/sobre-paldea.png'),
('082065085055', 'Sobre Espada y Escudo-Origen Perdido', 1, 'Sumérgete en el Abismo con Origen Perdido. Este sobre contiene 10 cartas adicionales diseñadas para potenciar mazos basados en la Zona Perdida. Es una de las colecciones más buscadas por coleccionistas debido a sus cartas con arte alternativo de Giratina V y otras leyendas de la región de Galar.', 5.25, 10, '/imagenes/productos/sobre-origen.png'),
('082065085344', 'Sobre Escarlata y Púrpura-Fuerzas Temporales', 1, 'Las fuerzas del pasado y del futuro chocan en esta increíble expansión. Los Entrenadores pueden encontrar Pokémon Paradoja como Ondulagua y Ferroverdor en sus versiones ex. Cada sobre incluye un código para el juego de cartas coleccionables online, asegurando diversión tanto física como digitalmente.', 4.99, 0, '/imagenes/productos/sobre-fuerzas.png'),
('082065086006', 'Sobre Destinos de Paldea', 1, 'La luz brilla sobre los Pokémon Variocolor en esta edición especial de Destinos de Paldea. Los sobres de esta expansión son conocidos por tener una alta probabilidad de contener cartas Shiny (variocolor), incluyendo al codiciado Charizard ex Shiny, convirtiéndose en el favorito de los abridores de sobres.', 5.95, 5, '/imagenes/productos/sobre-destinos.png'),
('082065085185', 'Sobre Astros Brillantes', 1, 'Descubre el poder de los Pokémon V-ASTRO con Astros Brillantes. Esta colección marcó un antes y un después en el juego competitivo gracias a sus habilidades especiales de un solo uso por partida. Incluye una galería de entrenadores con artes espectaculares que narran historias entre humanos y Pokémon.', 4.75, 0, '/imagenes/productos/sobre-astros.png'),
-- MAZOS (Marca ID 1)
('082065085526', 'Mazo de Combate Deluxe Meowscarada ex', 1, 'Mazo listo para jugar que incluye una baraja completa de 60 cartas centrada en Meowscarada ex. Es ideal para jugadores que quieren saltar directamente a la acción competitiva con una estrategia sólida basada en el control del daño en el banco del rival mediante habilidades de tipo planta muy precisas.', 19.90, 0, NULL),
('082065085533', 'Mazo de Combate Deluxe Quaquaval ex', 1, 'Baraja preconstruida de nivel avanzado. Quaquaval ex permite acelerar energías de agua de forma constante, permitiendo ataques potentes turno tras turno. Incluye caja para cartas, contadores de daño y una guía de estrategia para aprender a dominar los combos de energía necesarios para la victoria.', 19.90, 15, NULL),
('082065085465', 'Mazo de Combate Lucario ex', 1, 'Un mazo de inicio perfecto para aprender las mecánicas básicas de los Pokémon ex. Lucario lidera esta baraja de tipo lucha con ataques contundentes que escalan según el daño recibido. Es una opción excelente para niños y nuevos jugadores que buscan una baraja resistente y fácil de manejar en sus primeros torneos.', 14.95, 0, NULL),
('082065085472', 'Mazo de Combate Ampharos ex', 1, 'Mazo temático centrado en el tipo Rayo. Ampharos ex brilla por su capacidad de paralizar al rival mientras inflige grandes cantidades de daño. El kit viene con un tapete de papel para un jugador y monedas exclusivas de la colección, facilitando el montaje de partidas rápidas en cualquier lugar.', 14.95, 0, '/imagenes/productos/mazo-ampharos.png'),
('082065085283', 'Mazo de Inicio V de Mewtwo', 1, 'Domina los poderes psíquicos con el mazo de Mewtwo V. Esta baraja clásica de la era Espada y Escudo incluye cartas de apoyo esenciales como Investigación de Profesores y Roxy, fundamentales para mantener el flujo de cartas en la mano durante los momentos más críticos del duelo de cartas.', 15.50, 20, '/imagenes/productos/mazo-mewtwo.png'),
-- ACCESORIOS (Marca ID 2)
('400724625123', 'Moneda Coleccionista Lugia Plateada', 2, 'Moneda metálica oficial para lanzar en partidas de TCG. Representa al legendario Lugia con un acabado brillante.', 3.00, 0, '/imagenes/productos/moneda-lugia.png'),
('400724625124', 'Moneda Coleccionista Rayquaza Esmeralda', 2, 'Moneda de alta calidad utilizada para determinar efectos de estado y ataques que requieren lanzamientos de cara o cruz.', 3.00, 0, '/imagenes/productos/moneda-ray.png'),
('400724625125', 'Moneda Coleccionista Pikachu Clásica', 2, 'La moneda más icónica para cualquier jugador de Pokémon. Acabado amarillo vibrante resistente a los arañazos.', 2.50, 0, '/imagenes/productos/moneda-pika.png'),
('843530191234', 'Tapete Neopreno Kanto Starter', 2, 'Tapete de juego profesional fabricado en neopreno de alta densidad. Su superficie ultra suave permite un deslizamiento perfecto de las fundas de las cartas, mientras que la base de goma antideslizante asegura que el tablero permanezca fijo en la mesa incluso en los duelos más intensos de la liga.', 24.90, 0, '/imagenes/productos/tapete-kanto.png'),
('843530191235', 'Tapete Edición Limitada Arceus', 2, 'Tapete de juego con ilustración exclusiva del Pokémon Arceus. Diseñado para ofrecer una experiencia premium, tiene bordes cosidos para evitar el deshilachado con el uso prolongado. Es resistente al agua y fácil de transportar gracias a su material flexible que recupera la forma plana al instante.', 29.90, 10, '/imagenes/productos/tapete-arceus.png'),
-- CARTAS (Marca ID 4 para Oak, ID 1 para el resto)
('0000000000010', 'Carta Profesor Oak (Base Set)', 4, 'La carta de apoyo más mítica de la historia del juego. Permite descartar tu mano y robar 7 cartas nuevas de la baraja.', 85.00, 0, '/imagenes/productos/carta-oak.png'),
('0000000000027', 'Carta Treecko (Rubí y Zafiro)', 1, 'Carta básica de tipo planta de la tercera generación. Esencial para evolucionar a Grovyle y posteriormente al potente Sceptile.', 1.20, 0, '/imagenes/productos/carta-treecko.png'),
('0000000000034', 'Carta Mudkip (Holofoil)', 1, 'Versión brillante de Mudkip. Una de las cartas favoritas de los fans de la región de Hoenn por su diseño tierno y eficaz.', 5.50, 0, '/imagenes/productos/carta-mudkip.png'),
('0000000000041', 'Carta Mewtwo GX', 1, 'Carta de alto valor estratégico con ataques GX que pueden cambiar el rumbo de la partida en un solo turno.', 12.00, 5, NULL),
('0000000000058', 'Carta Charizard VMAX', 1, 'Carta ultra rara de gran tamaño y potencia. Su ataque Gigallamarada es capaz de noquear a casi cualquier Pokémon rival.', 45.00, 0, NULL);

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

-- Insertar usuario administrador inicial
-- Password: Password (BCrypt, cost 12)
INSERT INTO usuarios (username, password, nombre, apellidos, email, activo, fecha_registro)
VALUES ('admin', '$2a$12$Jsh18.x43XF79V2eICH7LO.UsVGT9fKlJJu5ig4Jci00uPgScUQfO', 'Admin', 'Sistema', 'admin@tienda.com', true, CURRENT_TIMESTAMP);