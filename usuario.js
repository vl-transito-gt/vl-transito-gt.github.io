
// Configuración: Cambia esto por la clave que desees

display= "none";

    
const CLAVE_CORRECTA = "1106459"




    function verificar() {
        const input = document.getElementById('userInput').value;
        const contenedorResultado = document.getElementById('resultado');
        const imagen = document.getElementById('img-feedback');
        const mensaje = document.getElementById('msg-feedback');
        const inputField = document.getElementById('userInput');



        // Validar si es correcto
        if (input === CLAVE_CORRECTA) {
        

            imagen.src = "https://raw.githubusercontent.com/vl-transito-gt/vl-transito-gt.github.io/main/images/vl.transito.gob.gtConsultasLicenciasCristianGarcia.jpg";// Imagen Verde 
        
            mensaje.innerText = "¡Excelente! Has acertado.";
            mensaje.style.color = "green";
        } else {
            imagen.src = "https://drive.google.com/uc?export=view&id=1W-vy9MMeEPokuzfW6YHUnNOAoVKvkz-H"; // Imagen Roja
            mensaje.innerText = "Error. Esa no es la clave.";
            mensaje.style.color = "red";
        }

        // Mostrar resultado y ocultar input inicial
        contenedorResultado.style.display = "block";
        inputField.style.display = "none";
        document.querySelector('button').style.display = "none";
    }

    function reiniciar() {
        // Limpiar y volver al estado inicial
        document.getElementById('userInput').value = "";
        document.getElementById('userInput').style.display = "inline-block";
        document.querySelector('button').style.display = "inline-block";
        document.getElementById('resultado').style.display = "none";
        document.getElementById('userInput').focus();
    } 