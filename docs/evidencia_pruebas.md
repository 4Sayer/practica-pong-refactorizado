# Evidencias de Pruebas
**Autor:** Omar Casanova Antúnez

## Pruebas Funcionales (Manuales)
*   **CP-01 a CP-04:** El juego arranca, se pausa con `SPACE`, reanuda con `R` sin picos de CPU, y cierra el proceso terminal al dar clic en la X de la ventana.
*   **CP-05 a CP-08:** La pelota rebota fluidamente en límites e invierte la dirección con precisión. Se previene rebote infinito dentro de la raqueta condicionando el rebote a `dy > 0`.
*   **CP-09:** Raqueta confinada correctamente mediante `Math.max` y `Math.min`.
*   **CP-10:** Se implementó estado explícito `GAME_OVER` cuando `y > 480`.

## Pruebas Automatizadas (JUnit)
Ejecución limpia de los 4 tests implementados en `PongTest.java`:
1.  `testWallCollisionLeft()`: Verifica inversión vectorial al cruzar X=0.
2.  `testWallCollisionTop()`: Verifica inversión vectorial al cruzar Y=0.
3.  `testPaddleCollision()`: Asegura rebote en el área del rectángulo.
4.  `testGameOver()`: Transición de estado correcta.
