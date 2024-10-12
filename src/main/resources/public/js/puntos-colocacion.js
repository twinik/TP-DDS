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

//cuando le al boton id="elegir-nuevo-punto" quiero que se me limpie el mapa y me deje elegir un nuevo punto, que tambien se borre lo que se escribio en los inputs
document.getElementById('elegir-nuevo-punto').addEventListener('click', function (event) {
    event.preventDefault();
    document.getElementById('latitud').value = '';
    document.getElementById('longitud').value = '';
    document.getElementById('radio').value = '';
    if (marker) map.removeLayer(marker);
});
