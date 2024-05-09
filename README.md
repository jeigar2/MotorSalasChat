# Prueba de Concepto - Motor de Salas de Chat

- Aplicación de Motores de Salas de Chat
  - Habrá entre 2 y 5 motores de Salas de Chat (aleatorio)
  - Cada `motor de Salas de chat` es un hilo para simular concurrencia
    - por cada `motor de salas de chat` se crean aleatoriamente
      - entre 2 y 4 `salas`
      - entre 4 y 10 `usuarios`
  - Cada sala de chat tendrá su fichero de traza
    - guarda
      - cuando se crea la sala de chat
      - los usuarios que
        - entran a la sala de chat
        - sale de la sala de chat
        - Si mandan un mensaje sin destinatario se entera todos los usuarios de la sala de chat
  - Cada usuario tendrá su fichero de traza
    - guarda
      - cuando se crea el usuario
      - si manda un mensaje y a quien
      - si recibe un mensaje y de quien

- Los usuarios cuando entran en una sala dicen su nombre

- Hay un usuario master que aborda al resto de usuarios de una sala de chat
  - el usuario master mantiene un dialogo con cada usuario destinatario de uno en uno.
  - cuando el dialogo con un usuario destinatatio este sale de la sala de chat.

- Esto ocurre simultaneamente en cada motor y sala de chat.

- Para que no haya una secuencialidad exacta
  - al agregar una sala de chat al motor de salas de chat, se espera entre 0,5 y 1,5 segundos
- Cuando se añade un mensaje a la sala se espera entre 0,2 y 0,7 segundos
- Cuando se añade un mensaje a otro usuario se espera entre 0,1 y 0,5 segundos
