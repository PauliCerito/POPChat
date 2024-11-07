document.addEventListener('DOMContentLoaded', function() {
    const themeToggle = document.getElementById('themeToggle');
    const body = document.body;
    const messageInput = document.getElementById('messageInput');
    const sendButton = document.getElementById('sendButton');
    const newChatButton = document.getElementById('newChatButton');
    const categoryModal = document.getElementById('categoryModal');
    const closeCategory = document.getElementById('closeCategory');
    const categoriaSeleccionadaInput = document.getElementById('categoriaSeleccionadaInput');

    // Variable para verificar si se ha seleccionado una categoría
    let categoriaSeleccionada = sessionStorage.getItem('categoria') || null;

    // Mostrar el modal solo si no hay una categoría seleccionada
    messageInput.addEventListener('focus', () => {
        if (!categoriaSeleccionada) {
            categoryModal.style.display = 'flex';
        }
    });

    // Acción para el botón "Nuevo Chat"
    newChatButton.onclick = () => {
        categoriaSeleccionada = null; // Resetear categoría seleccionada
        sessionStorage.removeItem('categoria'); // Remover de sessionStorage
        categoriaSeleccionadaInput.value = ""; // Limpiar el campo oculto en el formulario
        categoryModal.style.display = 'flex';
        clearChat(); // Limpia el área de mensajes
    };

    // Cerrar el modal de categoría al hacer clic en la 'X'
    closeCategory.onclick = () => categoryModal.style.display = 'none';

    // Cerrar el modal si se hace clic fuera de él
    window.onclick = function(event) {
        if (event.target === categoryModal) categoryModal.style.display = 'none';
    };

    // Función para seleccionar categoría y guardar en sessionStorage
    function seleccionarCategoria(categoria) {
        categoriaSeleccionada = categoria;
        sessionStorage.setItem('categoria', categoria); // Guardar en sessionStorage
        categoriaSeleccionadaInput.value = categoria; // Enviar la categoría en el formulario
        categoryModal.style.display = 'none'; // Cerrar el modal
        messageInput.focus(); // Enfocar el input
    }

    // Configurar botones de categorías para seleccionar y asignar la categoría
    document.getElementById('plumbingButton').onclick = () => seleccionarCategoria('Plomería');
    document.getElementById('cookingButton').onclick = () => seleccionarCategoria('Cocina');
    document.getElementById('electricityButton').onclick = () => seleccionarCategoria('Electricidad');

    // Función para limpiar el área de chat
    function clearChat() {
        const messagesDiv = document.getElementById('messages');
        messagesDiv.innerHTML = ""; // Limpia el contenido del chat
    }
});
