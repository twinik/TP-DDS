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
		id: 1,
		coords: [-34.59853190440259, -58.42009848935327],
		title: "Heladera UTN Medrano"
	},
	{
		id: 2,
		coords: [-34.6067, -58.4299],
		title: "Heladera Parque Centenario"
	},
	{
		id: 3,
		coords: [-34.6037, -58.4105],
		title: "Heladera Abasto"
	},
	{
		id: 4,
		coords: [-34.6189, -58.4244],
		title: "Heladera Parque Rivadavia"
	},
	{
		id: 5,
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
	div.querySelector(".popup-button").id = "selectBtn-" + markerData.id;

	// para la redistribucion de heladeras
	div.querySelector(".popup-destino").id =
		"selectBtnDestino-" + markerData.id;

	marker.bindPopup(div.innerHTML);
});
