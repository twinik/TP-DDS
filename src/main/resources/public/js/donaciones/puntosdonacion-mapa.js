// Coordenadas iniciales
var cabaCoords = [-34.6118, -58.4173];

// Inicializar el mapa y establecer la vista en CABA
var map = L.map("map").setView(cabaCoords, 13);

// Añadir una capa de mapa
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    minZoom: 5
}).addTo(map);

// Validación de los inputs
document.getElementById('confirmar').addEventListener('click', function() {
    const localidad = document.getElementById('localidad').value;
    const provincia = document.getElementById('provincia').value;
    const mensajeError = document.getElementById('mensaje-error');

    if (localidad === '' || provincia === '') {
        mensajeError.style.display = 'block';
    } else {
        mensajeError.style.display = 'none';
        alert('Campos completados correctamente.');
    }
});
