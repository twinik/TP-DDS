// Coordenadas de CABA, Buenos Aires, Argentina
var cabaCoords = [-34.6118, -58.4173];

// Inicializar el mapa y establecer la vista en CABA
var map = L.map("map").setView(cabaCoords, 13);

// Añadir una capa de mapa (puedes usar otras capas como OpenStreetMap, Mapbox, etc.)
L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
	maxZoom: 19,
	minZoom: 5
}).addTo(map);

// Datos de los marcadores
var markers = [
	{
		coords: [-34.59853190440259, -58.42009848935327],
		title: "Heladera UTN Medrano"
	},
	{
		coords: [-34.6067, -58.4299],
		title: "Heladera Parque Centenario"
	},
	{
		coords: [-34.6037, -58.4105],
		title: "Heladera Abasto"
	},
	{
		coords: [-34.6189, -58.4244],
		title: "Heladera Parque Rivadavia"
	},
	{
		coords: [-34.545278, -58.449722],
		title: "Heladera Mudomental ChangoMas"
	}
];

// Función para crear marcadores
markers.forEach(function (markerData) {
	var marker = L.marker(markerData.coords).addTo(map);

	// Crear el contenido del popup usando el template HTML
	var popupContent = document.getElementById("marker-template").innerHTML;
	var div = document.createElement("div");
	div.innerHTML = popupContent;
	div.querySelector(".popup-title").innerText = markerData.title;

	if (markerData.extraContent) {
		var popupContentDiv = div.querySelector(".popup-content");
		var extraContentDiv = document.createElement("div");
		extraContentDiv.innerHTML = markerData.extraContent;
		popupContentDiv.insertBefore(
			extraContentDiv,
			popupContentDiv.querySelector(".popup-button")
		);
	}

	marker.bindPopup(div.innerHTML);
});
