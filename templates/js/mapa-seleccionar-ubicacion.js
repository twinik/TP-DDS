// Coordenadas de CABA, Buenos Aires, Argentina
var cabaCoords = [-34.6118, -58.4173];

// Inicializar el mapa y establecer la vista en CABA
var map = L.map("map").setView(cabaCoords, 13);

// Añadir una capa de mapa (puedes usar otras capas como OpenStreetMap, Mapbox, etc.)
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
	marker = L.marker(e.latlng).addTo(map);
});
