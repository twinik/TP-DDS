var cabaCoords = [-34.6118, -58.4173];

var map = L.map("map").setView(cabaCoords, 13);

L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    minZoom: 5
}).addTo(map);

var marker;
map.on("click", function (e) {
    if (marker) map.removeLayer(marker);
    console.log(e.latlng); // e is an event object (MouseEvent in this case)
    document.getElementById("latitud").value = e.latlng.lat;
    document.getElementById("longitud").value = e.latlng.lng;

    const markerTemplate = `<div class="flex flex-col popup-content">
        <div class="popup-title">Nuevo Punto</div>
        <div class="popup-button" data-lat="${e.latlng.lat}" data-lng="${e.latlng.lng}" onclick="clickOnPopup(this)">
            Seleccionar
        </div>
    </div>`;

    marker = L.marker(e.latlng).addTo(map);
    marker.bindPopup(markerTemplate).openPopup();
});

var markerLayerGroup = L.layerGroup().addTo(map);

document.getElementById('confirmar').addEventListener('click', async function (event) {
    event.preventDefault();

    const latitud = document.getElementById('latitud').value;
    const longitud = document.getElementById('longitud').value;
    const radio = document.getElementById('radio').value;
    const mensajeError = document.getElementById('mensaje-error');

    // Mostrar error si no se seleccionaron todos los campos
    if (latitud === '' || longitud === '' || radio === '') {
        mensajeError.style.display = 'block';

        // Ocultar el mensaje después de 3 segundos (opcional)
        setTimeout(() => {
            mensajeError.style.display = 'none';
        }, 3000);
    } else {
        mensajeError.style.display = 'none';

        try {
            // Eliminar los marcadores anteriores
            markerLayerGroup.clearLayers();

            // Obtener los nuevos puntos de donación
            var markers = await fetchDonacionesCercanas(latitud, longitud, radio);

            // Añadir nuevos marcadores al mapa
            markers.forEach(function (markerData) {
                var marker = L.marker(markerData.coords).addTo(markerLayerGroup);

                const markerTemplate = `<div class="flex flex-col popup-content">
                    <div class="popup-title">${markerData.nombre}</div>
                    <div class="popup-subtitle">${markerData.calle} ${markerData.altura}</div>
                    <div class="popup-button" data-lat="${markerData.latitud}" data-lng="${markerData.longitud}" data-calle="${markerData.calle}" data-altura="${markerData.altura}" onclick="clickOnPopup(this)">
                        Seleccionar
                    </div>
                </div>
            `;
                marker.bindPopup(markerTemplate);

            });
        } catch (error) {
            console.error('Error al obtener los puntos de colocacion:', error);
        }
    }
});

function clickOnPopup(element) {
    const latitudElegida = element.getAttribute('data-lat');
    const longitudElegida = element.getAttribute('data-lng');
    const calleElegida = element.getAttribute('data-calle');
    const alturaElegida = element.getAttribute('data-altura');
    document.getElementById('latitudElegida').value = latitudElegida;
    document.getElementById('longitudElegida').value = longitudElegida;
    document.getElementById('calle').value = calleElegida;
    document.getElementById('numero').value = alturaElegida;
}

document.getElementById('elegir-nuevo-punto').addEventListener('click', function (event) {
    event.preventDefault();
    document.getElementById('latitud').value = '';
    document.getElementById('longitud').value = '';
    document.getElementById('radio').value = '';
    if (marker) map.removeLayer(marker);
    markerLayerGroup.clearLayers();
});
