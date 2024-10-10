// Coordenadas iniciales
var cabaCoords = [-34.6118, -58.4173];

// Inicializar el mapa y establecer la vista en CABA
var map = L.map("map").setView(cabaCoords, 13);

// Añadir una capa de mapa
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    minZoom: 5
}).addTo(map);

// Crear un grupo de capas vacío
var markerLayerGroup = L.layerGroup().addTo(map);

// Validación de los inputs
document.getElementById('confirmar').addEventListener('click', async function () {
    const localidad = document.getElementById('localidad').value;
    const provincia = document.getElementById('provincia').value;
    const mensajeError = document.getElementById('mensaje-error');

    // Mostrar error si faltan campos
    if (localidad === '' || provincia === '') {
        mensajeError.style.display = 'block';
    } else {
        mensajeError.style.display = 'none';

        try {
            // Eliminar los marcadores anteriores
            markerLayerGroup.clearLayers();

            // Obtener los nuevos puntos de donación
            var markers = await fetchDonaciones(provincia, localidad);

            // Añadir nuevos marcadores al mapa
            markers.forEach(function (markerData) {
                var marker = L.marker(markerData.coords).addTo(markerLayerGroup); // Añadir al grupo

                // Plantilla para el popup con nombre y dirección
                const markerTemplate = `
                    <div class="popup-content">
                        <div class="popup-title">${markerData.nombre}</div>
                        <div class="popup-subtitle">${markerData.calle} ${markerData.altura}</div>
                    </div>
                `;

                // Asignar el popup al marcador
                marker.bindPopup(markerTemplate);
            });
        } catch (error) {
            console.error('Error al obtener donaciones:', error);
        }
    }
});
