<%-- 
    Document   : paginaPrincipalPOPChat
    Created on : 1 ago 2024, 21:32:53
    Author     : Paula Castillo
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Consulta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="paginaPrincipalCSS.css">
  <title>Chat POP</title>
</head>
<body class="light-theme">
  <header class="navbar">
    <div class="logo">
      <img src="Imagenes/Logo_Popchat.jpeg" alt="Logo" class="logo-img">
    </div>
    <div class="nav-controls">
      <button id="loginButton">Iniciar Sesión</button>
      <button id="registerButton">Registrarse</button>
      <label class="theme-switch">
        <input type="checkbox" id="themeToggle">
        <span class="slider"></span>
      </label>
    </div>
  </header>

  <div class="chat-container">
    <div class="chat-area">
      <div class="messages-container" id="messages">
        <%
        // Obtener el historial de la sesión
        List<Consulta> historial = (List<Consulta>) session.getAttribute("historial");
        if (historial != null) {
            for (Consulta consulta : historial) {
                String pregunta = consulta.getPregunta();
                String respuesta = consulta.getRespuesta();
        %>
                <!-- Mostrar la pregunta -->
                <div class="message-container sender-message-container">
                    <div class="message-bubble sender-message-bubble">
                        <p><%= pregunta %></p>
                    </div>
                </div>
                
                <!-- Mostrar la respuesta -->
                <div class="message-container">
                    <div class="message-bubble">
                        <p><%= respuesta %></p>
                    </div>
                </div>
        <%
            }
        }
        %>
    </div>


      <div class="message-input-container">
        <form action="SVPagPrincipal" method="POST" class="formulario">
          <input type="text" id="messageInput" name="pregunta" placeholder="Escribe tu mensaje...">
          <input type="hidden" name="categoria" id="categoriaSeleccionadaInput">
          <button type="submit" id="sendButton">Enviar</button>
        </form>
        <button id="newChatButton" title="Nuevo Chat">💬 Nuevo Chat</button>
      </div>
    </div>
  </div>

  <div id="loginModal" class="modal">
    <div class="modal-content">
      <span class="close-button" id="closeLogin">&times;</span>
      <h2>Iniciar Sesión</h2>
      <form action="SVIniciarSesion" method="POST">
        <input type="email" name="email" placeholder="Correo electrónico" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <button type="submit">Acceder</button>
      </form>
    </div>
  </div>

  <div id="registerModal" class="modal">
    <div class="modal-content">
      <span class="close-button" id="closeRegister">&times;</span>
      <h2>Registrarse</h2>
      <form action="SVRegistro" method="POST">
        <input type="text" name="nombre" placeholder="Nombre" required>
        <input type="email" name="email" placeholder="Correo electrónico" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <button type="submit">Registrar</button>
      </form>
    </div>
  </div>

  <div id="categoryModal" class="modal">
    <div class="modal-content">
      <h2>Seleccione la categoría de sus preguntas</h2>
      <button type="button" id="plumbingButton" onclick="setCategory('plomero')">Plomería</button>
      <button type="button" id="cookingButton" onclick="setCategory('cocinero')">Cocina</button>
      <button type="button" id="electricityButton" onclick="setCategory('electricista')">Electricidad</button>
      <span class="close-button" id="closeCategory">&times;</span>
    </div>
  </div>

  <script src="paginaPrincipalJS.js"></script>
</body>
</html>
