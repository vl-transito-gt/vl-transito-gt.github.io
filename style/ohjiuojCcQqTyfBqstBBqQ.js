
        const CLAVE_CORRECTA_P = "01106459";
        
        // Configuración: Cambia esto por la clave que desees
        function verificar() {
            const input = document.getElementById('userInput').value;
            const contenedor = document.getElementById('contenedor');
            let clave  = parseInt(input);
            let CLAVE_CORRECTA = parseInt(CLAVE_CORRECTA_P);

            
            if (clave === CLAVE_CORRECTA) {
                // Si es verdadero: muestra el perro
                
                
                
               

                    document.getElementById("contenedor").style.display="none";
                    
                    document.getElementById("imagen-final").style.display="flex"; "justify-content: center"; "align-items: center";



                
            } else if (input === "no") {
                // Si es falso: muestra el gato
                contenedor.innerHTML = `
                    <img src="URL_DEL_GATO" alt="Gato">
                    <p>Es falso. Aquí tienes un gato.</p>
                    <button onclick="location.reload()">Volver</button>
                `;
            } else {

               

                document.getElementById("contenedor").style.display="none";
                document.getElementById("imagen-error").style.display="flex"; "justify-content: center"; "align-items: center";

            }
        }

        function reiniciar() {
            // Limpiar y volver al estado inicial
            document.getElementById('userInput').value = "";
            document.getElementById('userInput').style.display = "inline-block";
            document.querySelector('button').style.display = "inline-block";
            document.getElementById('resultado').style.display = "none";
            document.getElementById('userInput').focus();
        }

