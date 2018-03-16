# Call-Center

### Consigna

Existe un call center donde hay 3 tipos de empleados: operador,
supervisor y director. El proceso de la atención de una llamada
telefónica en primera instancia debe ser atendida por un operador, si
no hay ninguno libre debe ser atendida por un supervisor, y de no
haber tampoco supervisores libres debe ser atendida por un director.
Requerimientos

Diseñar el modelado de clases y diagramas UML necesarios
para documentar y comunicar el diseño.

Debe existir una clase Dispatcher encargada de manejar las
llamadas, y debe contener el método dispatchCall para que las
asigne a los empleados disponibles.

La clase Dispatcher debe tener la capacidad de poder procesar
10 llamadas al mismo tiempo (de modo concurrente).
Cada llamada puede durar un tiempo aleatorio entre 5 y 10
segundos.

Debe tener un test unitario donde lleguen 10 llamadas.

Extras/Plus
* Dar alguna solución sobre qué pasa con una llamada cuando no hay ningún empleado libre.
* Dar alguna solución sobre qué pasa con una llamada cuando entran más de 10 llamadas concurrentes.
* Agregar los tests unitarios que se crean convenientes.
* Agregar documentación de código.


### Solución

* Diseño del diagramas de clases `src/main/resources/UMLDiagram/Classes Diagram.pdf`.
* Cuando no hay ningún empleado disponible la llamada queda en cola de espera hasta que un empleado se libere y pueda atender. Test: `com.jcalvopina.domain.NotEnoughAvailableEmployeeTest.java`
* La clase `com.jcalvopina.CallCenter.java` inicia la aplicación
* Se puede configurar el numero de OPERADORES, SUPERVISORES, DIRECTORES disponibles, la clase `com.jcalvopina.utils.DummyData.java` los genera.