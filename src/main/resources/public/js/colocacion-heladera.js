async function fetchHeladeras() {
    try {
        const response = await fetch('/heladeras/mapa');
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error('Failed to fetch heladeras:', error);
    }
}

async function fetchHeladerasDonaciones() {
    try {
        const response = await fetch('/heladeras/donar');
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error('Failed to fetch heladeras:', error);
    }
}

async function fetchDonaciones(provincia, localidad) {
    try {
        const response = await fetch(`/heladeras/recibir-puntos?provincia=${provincia}&localidad=${localidad}`);
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        const donaciones = await response.json();
        console.log('Donaciones:', donaciones);
        return donaciones;
    } catch (error) {
        console.error('Failed to fetch donaciones:', error);
    }
}

async function fetchDonacionesCercanas(latitud, longitud, radio) {
    try {
        const response = await fetch(`/colaborar/recibir-puntos-colocacion?latitud=${latitud}&longitud=${longitud}&radio=${radio}`);
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        const puntos_colocacion = await response.json();
        console.log('Puntos Colocacion:', puntos_colocacion);
        return puntos_colocacion;
    } catch (error) {
        console.error('Failed to fetch puntos colocacion:', error);
    }
}

// Map initialization and event handling
var cabaCoords = [-34.6118, -58.4173];
var map = L.map("mapa-container").setView(cabaCoords, 13);
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    minZoom: 5
}).addTo(map);
var marker;
var markerLayerGroup = L.layerGroup().addTo(map);
// Event handler for map clicks
map.on("click", function (e) {
    const isSelectMode = document.getElementById('seleccionar-punto').checked;
    if (isSelectMode) {
        // Mode: "Seleccionar punto específico"
        if (marker) map.removeLayer(marker);
        document.getElementById("latitudElegida").value = e.latlng.lat.toFixed(6);
        document.getElementById("longitudElegida").value = e.latlng.lng.toFixed(6);
        const markerTemplate = `<div class="flex flex-col popup-content">
                <div class="popup-title">Nuevo Punto</div>
                <div class="popup-button" data-lat="${e.latlng.lat}" data-lng="${e.latlng.lng}" onclick="clickOnPopup(this)">
                    Seleccionar
                </div>
            </div>`;
        marker = L.marker(e.latlng).addTo(map);
        marker.bindPopup(markerTemplate).openPopup();
    } else {
        // Mode: "Recomendar ubicación" - Show a temporary marker
        if (marker) map.removeLayer(marker); // Remove previous temporary marker if exists
        document.getElementById("latitud").value = e.latlng.lat.toFixed(6);
        document.getElementById("longitud").value = e.latlng.lng.toFixed(6);
        // Add a temporary marker for visual feedback
        marker = L.marker(e.latlng).addTo(map); // Lower opacity for temporary indication
    }
});
// Event handler for radio button change
document.getElementById('seleccionar-punto').addEventListener('change', function () {
    document.getElementById('seleccionar-punto-form').style.display = 'block';
    document.getElementById('recomendar-punto-form').style.display = 'none';
    markerLayerGroup.clearLayers();
    if (marker) map.removeLayer(marker);
});
document.getElementById('recomendar-punto').addEventListener('change', function () {
    document.getElementById('seleccionar-punto-form').style.display = 'none';
    document.getElementById('recomendar-punto-form').style.display = 'block';
    if (marker) map.removeLayer(marker); // Remove marker if in recommend mode
});
// Event handler for fetching recommendations based on coordinates and radius
document.getElementById('buscar-recomendaciones').addEventListener('click', async function () {
    const latitud = parseFloat(document.getElementById('latitud').value);
    const longitud = parseFloat(document.getElementById('longitud').value);
    const radio = parseFloat(document.getElementById('radio').value);
    if (isNaN(latitud) || isNaN(longitud) || isNaN(radio)) {
        alert('Por favor, complete todos los campos de latitud, longitud y radio.');
        return;
    }
    try {
        markerLayerGroup.clearLayers();
        const markers = await fetchDonacionesCercanas(latitud, longitud, radio);
        markers.forEach(function (markerData) {
            const marker = L.marker([markerData.latitud, markerData.longitud]).addTo(markerLayerGroup);
            const markerTemplate = `<div class="flex flex-col popup-content">
                    <div class="popup-title">${markerData.nombre}</div>
                    <div class="popup-subtitle">${markerData.calle} ${markerData.altura}</div>
                    <div class="popup-button" data-lat="${markerData.latitud}" data-lng="${markerData.longitud}" data-calle="${markerData.calle}" data-altura="${markerData.altura}" onclick="clickOnPopup(this)">
                        Seleccionar
                    </div>
                </div>`;
            marker.bindPopup(markerTemplate);
        });
        map.fitBounds(markerLayerGroup.getBounds());
    } catch (error) {
        console.error('Error al obtener los puntos de colocacion:', error);
    }
});

// Form submission handling
function handleFormSubmit(formId, submitButtonId) {
    document.getElementById(formId).addEventListener('submit', function (event) {
        const submitButton = document.getElementById(submitButtonId);
        submitButton.disabled = true;
        submitButton.innerHTML = 'Enviando...';
    });
}

// Click popup handler to set chosen point data
function clickOnPopup(element) {
    const latitudElegida = element.getAttribute('data-lat');
    const longitudElegida = element.getAttribute('data-lng');
    const calleElegida = element.getAttribute('data-calle');
    const alturaElegida = element.getAttribute('data-altura');
    document.getElementById('latitudElegida').value = latitudElegida;
    document.getElementById('longitudElegida').value = longitudElegida;
    document.getElementById('calle').value = calleElegida || '';
    document.getElementById('numero').value = alturaElegida || '';
}