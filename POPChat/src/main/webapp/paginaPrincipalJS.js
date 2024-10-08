function showPopup() {
    document.getElementById('popup').style.display = 'block';
}

function hidePopup() {
    document.getElementById('popup').style.display = 'none';
    const inputPregunta = document.getElementById('inputPregunta');
    
    if (inputPregunta) {
        inputPregunta.disabled = false;  // Habilitar el input para preguntas
    }
}

// Función para obtener la categoría seleccionada desde localStorage
function categoriaSeleccionada() {
    return localStorage.getItem('categoriaSeleccionada');
}

// Función para establecer la categoría seleccionada en localStorage y en el formulario
function setCategoriaSeleccionada(categoria) {
    localStorage.setItem('categoriaSeleccionada', categoria);
    document.getElementById('categoriaSeleccionadaInput').value = categoria;  // Establecer en el campo oculto del formulario
}

window.addEventListener('load', function () {
    // Verificar si ya hay una categoría seleccionada
    const categoria = categoriaSeleccionada();
    if (!categoria) {
        showPopup();  // Si no hay categoría seleccionada, mostrar el popup
    } else {
        // Si ya hay una categoría seleccionada, rellenar el campo oculto del formulario
        document.getElementById('categoriaSeleccionadaInput').value = categoria;
    }

    // Al hacer clic en 'New chat', mostrar el popup y limpiar la categoría seleccionada
    document.getElementById('newChat').addEventListener('click', function () {
        localStorage.removeItem('categoriaSeleccionada');  // Eliminar la categoría
        document.getElementById('categoriaSeleccionadaInput').value = "";  // Limpiar el campo oculto
        showPopup();
    });

    // Manejar la selección de categorías con los botones del popup
    document.querySelectorAll('.pop-categoria button').forEach(button => {
        button.addEventListener('click', function () {
            const categoria = this.value;
            setCategoriaSeleccionada(categoria);  // Guardar categoría en localStorage y campo oculto
            hidePopup();  // Cerrar el popup
            console.log('Categoría seleccionada:', categoria);
        });
    });

    // Cerrar el popup si se hace clic fuera del contenido
    window.addEventListener('click', function (event) {
        const popup = document.getElementById('popup');
        const popupContent = document.querySelector('.popup-contenido');

        if (event.target === popup && !popupContent.contains(event.target)) {
            hidePopup();
        }
    });
});
