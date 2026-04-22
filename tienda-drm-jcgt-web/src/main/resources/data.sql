INSERT INTO categorias (nombre, descripcion, imagen)
VALUES ('Sobres', 'Ampliaciones de las últimas ediciones como Escarlata y Púrpura.', 'sobres.jpg');

INSERT INTO categorias (nombre, descripcion, imagen)
VALUES ('Mazos', 'Mazos listos para jugar, ideales para entrenadores principiantes.', NULL);

INSERT INTO categorias (nombre, descripcion, imagen)
VALUES ('Accesorios', 'Protege tus cartas con las mejores fundas y cajas de almacenamiento.', 'accesorios.jpg');

INSERT INTO categorias (nombre, descripcion, imagen)
VALUES ('Cartas', 'Cartas raras, Full Art y Secretas vendidas por separado.', 'cartas.jpg');


-- SOBRES (Con marca e imagen)
INSERT INTO productos (codigo_ean, nombre, marca, descripcion, precio, descuento, imagen)
VALUES ('820650855429', 'Sobre Escarlata y Púrpura-Evoluciones en Paldea', 'Pokémon TCG',
        'Expande tu colección con el sobre de mejora de Evoluciones en Paldea. Esta expansión introduce nuevos Pokémon ex y mecánicas que cambian el juego competitivo actual, permitiendo a los entrenadores descubrir el poder oculto de los iniciales de la región de Paldea en sus formas más poderosas y brillantes.',
        4.50, 0, '/images/productos/sobre-paldea.png'),
       ('082065085055', 'Sobre Espada y Escudo-Origen Perdido', 'Pokémon TCG',
        'Sumérgete en el Abismo con Origen Perdido. Este sobre contiene 10 cartas adicionales diseñadas para potenciar mazos basados en la Zona Perdida. Es una de las colecciones más buscadas por coleccionistas debido a sus cartas con arte alternativo de Giratina V y otras leyendas de la región de Galar.',
        5.25, 10, '/images/productos/sobre-origen.png'),
       ('082065085344', 'Sobre Escarlata y Púrpura-Fuerzas Temporales', 'Pokémon TCG',
        'Las fuerzas del pasado y del futuro chocan en esta increíble expansión. Los Entrenadores pueden encontrar Pokémon Paradoja como Ondulagua y Ferroverdor en sus versiones ex. Cada sobre incluye un código para el juego de cartas coleccionables online, asegurando diversión tanto física como digitalmente.',
        4.99, 0, '/images/productos/sobre-fuerzas.png'),
       ('082065086006', 'Sobre Destinos de Paldea', 'Pokémon TCG',
        'La luz brilla sobre los Pokémon Variocolor en esta edición especial de Destinos de Paldea. Los sobres de esta expansión son conocidos por tener una alta probabilidad de contener cartas Shiny (variocolor), incluyendo al codiciado Charizard ex Shiny, convirtiéndose en el favorito de los abridores de sobres.',
        5.95, 5, '/images/productos/sobre-destinos.png'),
       ('082065085185', 'Sobre Astros Brillantes', 'Pokémon TCG',
        'Descubre el poder de los Pokémon V-ASTRO con Astros Brillantes. Esta colección marcó un antes y un después en el juego competitivo gracias a sus habilidades especiales de un solo uso por partida. Incluye una galería de entrenadores con artes espectaculares que narran historias entre humanos y Pokémon.',
        4.75, 0, '/images/productos/sobre-astros.png');

-- MAZOS YA HECHOS (Con marca, sin imagen)
INSERT INTO productos (codigo_ean, nombre, marca, descripcion, precio, descuento, imagen)
VALUES ('082065085526', 'Mazo de Combate Deluxe Meowscarada ex', 'Pokémon TCG',
        'Mazo listo para jugar que incluye una baraja completa de 60 cartas centrada en Meowscarada ex. Es ideal para jugadores que quieren saltar directamente a la acción competitiva con una estrategia sólida basada en el control del daño en el banco del rival mediante habilidades de tipo planta muy precisas.',
        19.90, 0, NULL),
       ('082065085533', 'Mazo de Combate Deluxe Quaquaval ex', 'Pokémon TCG',
        'Baraja preconstruida de nivel avanzado. Quaquaval ex permite acelerar energías de agua de forma constante, permitiendo ataques potentes turno tras turno. Incluye caja para cartas, contadores de daño y una guía de estrategia para aprender a dominar los combos de energía necesarios para la victoria.',
        19.90, 15, NULL),
       ('082065085465', 'Mazo de Combate Lucario ex', 'Pokémon TCG',
        'Un mazo de inicio perfecto para aprender las mecánicas básicas de los Pokémon ex. Lucario lidera esta baraja de tipo lucha con ataques contundentes que escalan según el daño recibido. Es una opción excelente para niños y nuevos jugadores que buscan una baraja resistente y fácil de manejar en sus primeros torneos.',
        14.95, 0, NULL),
       ('082065085472', 'Mazo de Combate Ampharos ex', 'Pokémon TCG',
        'Mazo temático centrado en el tipo Rayo. Ampharos ex brilla por su capacidad de paralizar al rival mientras inflige grandes cantidades de daño. El kit viene con un tapete de papel para un jugador y monedas exclusivas de la colección, facilitando el montaje de partidas rápidas en cualquier lugar.',
        14.95, 0, NULL),
       ('082065085283', 'Mazo de Inicio V de Mewtwo', 'Pokémon TCG',
        'Domina los poderes psíquicos con el mazo de Mewtwo V. Esta baraja clásica de la era Espada y Escudo incluye cartas de apoyo esenciales como Investigación de Profesores y Roxy, fundamentales para mantener el flujo de cartas en la mano durante los momentos más críticos del duelo de cartas.',
        15.50, 20, NULL);

-- ACCESORIOS (Sin marca, con imagen: Monedas y Tapetes)
INSERT INTO productos (codigo_ean, nombre, marca, descripcion, precio, descuento, imagen)
VALUES ('400724625123', 'Moneda Coleccionista Lugia Plateada', NULL,
        'Moneda metálica oficial para lanzar en partidas de TCG. Representa al legendario Lugia con un acabado brillante.',
        3.00, 0, '/images/productos/moneda-lugia.png'),
       ('400724625124', 'Moneda Coleccionista Rayquaza Esmeralda', NULL,
        'Moneda de alta calidad utilizada para determinar efectos de estado y ataques que requieren lanzamientos de cara o cruz.',
        3.00, 0, '/images/productos/moneda-ray.png'),
       ('400724625125', 'Moneda Coleccionista Pikachu Clásica', NULL,
        'La moneda más icónica para cualquier jugador de Pokémon. Acabado amarillo vibrante resistente a los arañazos.',
        2.50, 0, '/images/productos/moneda-pika.png'),
       ('843530191234', 'Tapete Neopreno Kanto Starter', NULL,
        'Tapete de juego profesional fabricado en neopreno de alta densidad. Su superficie ultra suave permite un deslizamiento perfecto de las fundas de las cartas, mientras que la base de goma antideslizante asegura que el tablero permanezca fijo en la mesa incluso en los duelos más intensos de la liga.',
        24.90, 0, '/images/productos/tapete-kanto.png'),
       ('843530191235', 'Tapete Edición Limitada Arceus', NULL,
        'Tapete de juego con ilustración exclusiva del Pokémon Arceus. Diseñado para ofrecer una experiencia premium, tiene bordes cosidos para evitar el deshilachado con el uso prolongado. Es resistente al agua y fácil de transportar gracias a su material flexible que recupera la forma plana al instante.',
        29.90, 10, '/images/productos/tapete-arceus.png');

-- CARTAS SUELTAS (Sin marca ni imagen)
INSERT INTO productos (codigo_ean, nombre, marca, descripcion, precio, descuento, imagen)
VALUES ('0000000000010', 'Carta Profesor Oak (Base Set)', NULL,
        'La carta de apoyo más mítica de la historia del juego. Permite descartar tu mano y robar 7 cartas nuevas de la baraja.',
        85.00, 0, NULL),
       ('0000000000027', 'Carta Treecko (Rubí y Zafiro)', NULL,
        'Carta básica de tipo planta de la tercera generación. Esencial para evolucionar a Grovyle y posteriormente al potente Sceptile.',
        1.20, 0, NULL),
       ('0000000000034', 'Carta Mudkip (Holofoil)', NULL,
        'Versión brillante de Mudkip. Una de las cartas favoritas de los fans de la región de Hoenn por su diseño tierno y eficaz.',
        5.50, 0, NULL),
       ('0000000000041', 'Carta Mewtwo GX', NULL,
        'Carta de alto valor estratégico con ataques GX que pueden cambiar el rumbo de la partida en un solo turno.',
        12.00, 5, NULL),
       ('0000000000058', 'Carta Charizard VMAX', NULL,
        'Carta ultra rara de gran tamaño y potencia. Su ataque Gigallamarada es capaz de noquear a casi cualquier Pokémon rival.',
        45.00, 0, NULL);