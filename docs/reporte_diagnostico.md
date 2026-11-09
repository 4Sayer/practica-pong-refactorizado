# Reporte de Diagnóstico
**Autor:** Omar Casanova Antúnez

| Problema | Ubicación | Consecuencias | Solución Propuesta e Implementada |
|---|---|---|---|
| **1. Espera Activa (Busy Waiting)** | `BallRunner.java` (`run()`) | El uso de `Thread.yield()` en un ciclo infinito durante la pausa consumía el 100% de un núcleo del CPU. | Se eliminaron los hilos y se reemplazó el control de tiempo por un `javax.swing.Timer` que detiene el procesamiento en pausa. |
| **2. Condición de Carrera (Race Condition)** | `Board.java` y `BallRunner.java` | La pelota era modificada por `BallRunner` al mismo tiempo que `Board` la leía para dibujarla, causando inestabilidad visual. | Uso exclusivo del *Event Dispatch Thread* (EDT) para cálculo y renderizado. |
| **3. Fuga de Procesos (Hilos Zombis)** | `Board.java` (`run()`) | Ciclo `while(true)` sin condición de salida. Al cerrar la ventana, el proceso seguía activo en RAM. | Delegar el ciclo de vida de la ventana y el juego a los mecanismos nativos de Swing (`Timer` y `EXIT_ON_CLOSE`). |
| **4. Violación del Principio SRP** | `Board.java` | Mezclaba renderizado gráfico, control de hilos e inputs de teclado en la misma clase. | Separación en Arquitectura MVC: `GameModel` (datos), `GameLogic` (matemáticas) y `Board` (solo renderizado e inputs). |
| **5. Detección de teclado frágil** | `Board.java` (`KeyListener`) | Si el usuario daba clic fuera de la ventana, el juego dejaba de registrar inputs. | Sustitución de `KeyListener` por `KeyBindings` anclados al contexto de la ventana enfocada. |
