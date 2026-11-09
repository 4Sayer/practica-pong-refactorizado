# Reporte Técnico de Mejoras y Reflexión
**Autor:** Omar Casanova Antúnez

## Resumen de Mejoras
El proyecto transitó de un modelo desordenado multihilo a una arquitectura síncrona basada en un Game Loop central (MVC). Se erradicaron las esperas activas (`Thread.yield`), se refinó la caja de colisión para evitar que la pelota "atraviese" la raqueta por incrementos rápidos, y se robusteció la captura de teclado mediante `KeyBindings`.

## Preguntas de Reflexión

**1. ¿Qué problemas puede ocasionar modificar el estado de un componente Swing desde un hilo diferente al EDT?**
Genera condiciones de carrera. Swing no es *Thread-Safe*, por lo que modificar un componente gráfico mientras el EDT lo está repintando provoca artefactos visuales, excepciones de concurrencia y cierres inesperados.

**2. ¿Por qué Thread.yield() no constituye una solución adecuada para implementar una pausa?**
`yield()` solo sugiere al planificador de tareas ceder el turno, pero no suspende el hilo. El ciclo sigue iterando millones de veces por segundo preguntando por la bandera `isPaused`, saturando el CPU innecesariamente y elevando el consumo térmico.

**3. ¿Qué ventajas y limitaciones ofrece javax.swing.Timer frente a una implementación basada en Thread?**
*Ventajas:* Ejecuta los eventos de actualización y repintado de forma ordenada y garantizada dentro del EDT, evitando colisiones de hilos. Es fácil de pausar y detener.
*Limitaciones:* No es adecuado para cálculos físicos extremadamente pesados, ya que al ejecutarse en el EDT, podría congelar la interfaz si la iteración tarda más de 16ms (60 FPS).

**4. ¿Cómo influye la magnitud del desplazamiento de la pelota en la precisión de la detección de colisiones?**
Si el incremento de la pelota (`dx`, `dy`) es mayor al grosor de la raqueta, la pelota puede "saltarse" el frame exacto de intersección, atravesando objetos sólidos (efecto *Tunneling*). 

**5. ¿Qué beneficios aporta separar la lógica del juego de su representación gráfica?**
Permite modificar el algoritmo físico sin romper el código gráfico, facilita la portabilidad, previene acoplamiento (Principio de Responsabilidad Única) y hace que la lógica matemática sea 100% testeable sin requerir una pantalla activa.

**6. ¿Cómo ayudan las pruebas automatizadas a reducir el riesgo de introducir defectos durante una refactorización?**
Proveen una red de seguridad. Las pruebas de colisión verifican que tras modificar los vectores, el comportamiento matemático permanece constante. 

**7. ¿Qué aspectos del diseño actual dificultarían agregar un segundo jugador, un marcador o diferentes niveles de dificultad?**
El diseño original tenía propiedades estáticas sin un repositorio de datos (*Model*). Ahora, con `GameModel`, bastaría añadir `scoreP1` o `paddle2` a la clase, y el renderizado y la lógica escalarían sin conflictos.
