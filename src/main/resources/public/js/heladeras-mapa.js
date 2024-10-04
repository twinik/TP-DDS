// Coordenadas de CABA, Buenos Aires, Argentina
var cabaCoords = [-34.6118, -58.4173];

// Inicializar el mapa y establecer la vista en CABA
var map = L.map("map").setView(cabaCoords, 13);

// Añadir una capa de mapa (puedes usar otras capas como OpenStreetMap, Mapbox, etc.)
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    minZoom: 5
}).addTo(map);


// javascript es un asco
(async function main () {
    var markers = await fetchHeladeras()

    // Función para crear marcadores
    markers.forEach(function (markerData) {
        var marker = L.marker(markerData.coords).addTo(map);

        const inhabilitadaContent =
            '<div style="display: flex; flexDirection: row; gap: 5px; alignItems: center"> <svg xmlns = "http://www.w3.org/2000/svg" viewBox = "0 0 16 16" fill = "currentColor" width="16" height="16" > <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14ZM8 4a.75.75 0 0 1 .75.75v3a.75.75 0 0 1-1.5 0v-3A.75.75 0 0 1 8 4Zm0 8a1 1 0 1 0 0-2 1 1 0 0 0 0 2Z" clip-rule="evenodd" /></svg > <span style="color: white;">INHABILITADA</span> </div >';
        const reportarFallaBtn =
            `<a href="/heladeras/${markerData.id}/reportar-falla-tecnica" class="popup-button">Reportar Falla</a>`;

        const markerTemplate= `<div class="popup-content flex flex-col">
                <div class="popup-title">${markerData.title}</div>
                ${markerData.disabled ? inhabilitadaContent : reportarFallaBtn}
                <a href="/heladeras/${markerData.id}/suscribirme" class="popup-button">Suscribirse</a>
            </div>`

        marker.bindPopup(markerTemplate);
    });
})();

